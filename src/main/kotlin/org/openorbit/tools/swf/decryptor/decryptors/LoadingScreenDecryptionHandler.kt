package org.openorbit.tools.swf.decryptor.decryptors

import kotlin.experimental.xor

object LoadingScreenDecryptionHandler {
    fun decryptLoadingScreen(encryptedData: ByteArray): ByteArray {
        var xorByte = 57
        val decryptedData = ByteArray(encryptedData.size)
        encryptedData.forEachIndexed { i, curByte ->
            xorByte = (xorByte + 3) and 255
            decryptedData[i] = curByte.xor(xorByte.toByte())
        }
        return decryptedData
    }
}