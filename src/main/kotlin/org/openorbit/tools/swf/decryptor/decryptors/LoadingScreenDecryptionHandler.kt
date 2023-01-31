package org.openorbit.tools.swf.decryptor.decryptors

import java.util.zip.Inflater
import java.util.zip.InflaterInputStream

object LoadingScreenDecryptionHandler {
    fun decryptLoadingScreen(encryptedData: ByteArray): ByteArray {
        val decompressedData = encryptedData.inflated()

        var xorByte = 57
        decompressedData.forEachIndexed { i, curByte ->
            xorByte = (xorByte + 3) % 256
            decompressedData[i] = curByte.toInt().xor(xorByte).toByte()
        }
        return decompressedData
    }

    /**
     * extension function to easily inflate byte arrays (zlib decompression)
     */
    private fun ByteArray.inflated():ByteArray {
        return InflaterInputStream(this.inputStream(),Inflater()).readBytes()
    }
}