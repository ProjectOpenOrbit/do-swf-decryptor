package org.openorbit.tools.swf.decryptor.decryptors

import org.openorbit.tools.common.utils.Extensions.inflated
import org.openorbit.tools.swf.decryptor.MainEncryptionAlgorithmFinder.MainEncryptionAlgorithm
import org.openorbit.tools.swf.decryptor.algorithms.CustomRC4Algorithm

object MainDecryptionHandler {
    fun decryptMain(algorithm: MainEncryptionAlgorithm, data:ByteArray, key:String):ByteArray{
        var decompressedData = data.inflated()
        decompressedData = when(algorithm){
            MainEncryptionAlgorithm.RC4 -> CustomRC4Algorithm(key).decrypt(decompressedData)
            else -> TODO("Encryption algorithm $algorithm not implemented yet")
        }
        return decompressedData
    }
}