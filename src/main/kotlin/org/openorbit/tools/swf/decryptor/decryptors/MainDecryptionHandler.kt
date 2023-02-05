package org.openorbit.tools.swf.decryptor.decryptors

import org.openorbit.tools.common.utils.Extensions.asInflated
import org.openorbit.tools.common.utils.byteArrayFromHexStr
import org.openorbit.tools.swf.decryptor.MainEncryptionAlgorithmFinder.MainEncryptionAlgorithm
import org.openorbit.tools.swf.decryptor.algorithms.CustomRC4Algorithm
import org.openorbit.tools.swf.decryptor.algorithms.DarkOrbitCustomEncryptionAlgorithm

object MainDecryptionHandler {
    fun decryptMain(algorithm: MainEncryptionAlgorithm, data: ByteArray, key: String): ByteArray {
        var decompressedData = data.asInflated()
        val keyBytes = byteArrayFromHexStr(key)

        decompressedData = when (algorithm) {
            MainEncryptionAlgorithm.RC4 -> CustomRC4Algorithm(keyBytes).decrypt(decompressedData)
            MainEncryptionAlgorithm.CUSTOM -> DarkOrbitCustomEncryptionAlgorithm(keyBytes).decrypt(decompressedData)
        }
        return decompressedData
    }
}