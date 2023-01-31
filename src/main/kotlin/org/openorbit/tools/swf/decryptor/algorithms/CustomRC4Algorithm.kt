package org.openorbit.tools.swf.decryptor.algorithms

import org.openorbit.tools.common.utils.byteArrayFromHexStr
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

private const val ALGORITHM_RC4 = "RC4"

class CustomRC4Algorithm(rawKeyStringFromLoadingscreen: String) {

    private val maxLengthOfHashedKey = 16
    private lateinit var hashedKey: ByteArray

    init {
        hashKey(byteArrayFromHexStr(rawKeyStringFromLoadingscreen))
    }

    private fun hashKey(key: ByteArray) {
        val fullHash = MessageDigest.getInstance("SHA-256").digest(key)
        val keyLen = minOf(maxLengthOfHashedKey, fullHash.size)
        hashedKey = ByteArray(keyLen)
        for (idx in 0 until keyLen) {
            hashedKey[idx] = fullHash[idx]
        }
    }

    fun decrypt(data: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(ALGORITHM_RC4)
        val key = SecretKeySpec(hashedKey, ALGORITHM_RC4)
        cipher.init(Cipher.DECRYPT_MODE, key)
        return cipher.doFinal(data)
    }
}