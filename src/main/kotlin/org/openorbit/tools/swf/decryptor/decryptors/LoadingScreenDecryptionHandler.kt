package org.openorbit.tools.swf.decryptor.decryptors

import org.openorbit.tools.common.utils.Extensions.inflated

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
}