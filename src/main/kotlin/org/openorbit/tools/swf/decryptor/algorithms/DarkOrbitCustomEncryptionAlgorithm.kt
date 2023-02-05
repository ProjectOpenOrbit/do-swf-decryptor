package org.openorbit.tools.swf.decryptor.algorithms

import org.openorbit.tools.common.utils.Extensions.toIntArray
import org.openorbit.tools.common.utils.getIntFromBytesInIntArray
import org.openorbit.tools.common.utils.sha256
import kotlin.experimental.xor

class DarkOrbitCustomEncryptionAlgorithm(key: ByteArray) {

    // Variables for actual decryption. The
    private var currentRandomInt: Int = 0
    private var currentByteShift = 0

    // mutation box that help determine the next PRNG int in the "next" function starts our with
    private val mb = IntArray(10)

    init {
        val initializationVector = IntArray(10, init = { i -> i + 1 })
        // Byte array to Int array
        val hashedKey = sha256(key).toIntArray()

        initializeMutationBox(hashedKey, initializationVector)
    }

    private fun initializeMutationBox(key: IntArray, initVector: IntArray) {

        mb[0] = getIntFromBytesInIntArray(key, 0)
        mb[1] = getIntFromBytesInIntArray(key, 4)
        mb[2] = (key[8] and 0xFF) or ((key[9] shl 8) and 0xFF00)
        mb[3] = getIntFromBytesInIntArray(initVector, 0)
        mb[4] = getIntFromBytesInIntArray(initVector, 4)
        mb[5] = (initVector[8] and 0xFF) or ((initVector[9] shl 8) and 0xFF00)
        mb[6] = 0
        mb[7] = 0
        mb[8] = 0
        mb[9] = 0x7000

        for (i in 0 until 36) {
            var numbers = readNumbers1()
            numbers = readNumbers2(numbers)
            shiftBox(numbers)
        }
    }

    private fun getNextRandomInt(): Int {
        var triple = readNumbers1()
        val nextNumber = triple.getXor()
        triple = readNumbers2(triple)
        shiftBox(triple)
        return nextNumber
    }

    /**
     * Shift the mutation box with the given three values
     */
    private fun shiftBox(
        triple: Triple<Int, Int, Int>
    ) {
        mb[2] = mb[1]
        mb[1] = mb[0]
        mb[0] = triple.third
        mb[5] = mb[4]
        mb[4] = mb[3]
        mb[3] = triple.first
        mb[9] = mb[8]
        mb[8] = mb[7]
        mb[7] = mb[6]
        mb[6] = triple.second
    }

    // ==== START OF CRAZY MUTATION BOX OPERATIONS TO GENERATE RANDOM NUMBERS ====

    /**
     * ONLY USE IF YOU KNOW WHAT YOU ARE DOING
     * Some crazy bit shifting magic. What the fuck were you even thinking, fooskater??
     */
    private fun readNumbers2(triple: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
        val n1 =
            triple.first xor (((mb[2] shl 5) or (mb[1] ushr 27)) and ((mb[2] shl 4) or (mb[1] ushr 28))) xor ((mb[5] shl 18) or (mb[4] ushr 14))
        val n2 =
            triple.second xor (((mb[5] shl 14) or (mb[4] ushr 18)) and ((mb[5] shl 13) or (mb[4] ushr 19))) xor ((mb[8] shl 9) or (mb[7] ushr 23))
        val n3 =
            triple.third xor (((mb[9] shl 19) or (mb[8] ushr 13)) and ((mb[9] shl 18) or (mb[8] ushr 14))) xor ((mb[2] shl 27) or (mb[1] ushr 5))
        return Triple(n1, n2, n3)
    }

    /**
     * ONLY USE IF YOU KNOW WHAT YOU ARE DOING
     * Some crazy bit shifting magic. What the fuck were you even thinking, fooskater??
     */
    private fun readNumbers1(): Triple<Int, Int, Int> {
        val num1 = ((mb[2] shl 30) or (mb[1] ushr 2)) xor ((mb[2] shl 3) or (mb[1] ushr 29))
        val num2 = ((mb[5] shl 27) or (mb[4] ushr 5)) xor ((mb[5] shl 12) or (mb[4] ushr 20))
        val num3 = ((mb[8] shl 30) or (mb[7] ushr 2)) xor ((mb[9] shl 17) or (mb[8] ushr 15))
        return Triple(num1, num2, num3)
    }

    // ==== START OF CRAZY MUTATION BOX OPERATIONS TO GENERATE RANDOM NUMBERS ====

    /**
     * Return next byte from the PRNG stream. The stream returns integers, so we need to extract the bytes from each int
     */
    private fun nextByte(): Byte {
        if (currentByteShift == 0) {
            currentRandomInt = getNextRandomInt()
            currentByteShift = 4
        }

        val ret = (currentRandomInt and 0xFF).toByte()
        currentRandomInt = currentRandomInt shr 8
        --currentByteShift

        return ret
    }

    /**
     * Decrypts the given bytes by xor-ing it with the PRNG byte stream.
     */
    fun decrypt(encryptedData: ByteArray): ByteArray {
        val decryptedData = ByteArray(encryptedData.size)
        for (i in encryptedData.indices) {
            decryptedData[i] = encryptedData[i] xor nextByte()
        }
        return decryptedData
    }

}

/**
 * extension function for Triple class to quickly get the xor of the three values
 */
private fun Triple<Int, Int, Int>.getXor(): Int = first xor second xor third
