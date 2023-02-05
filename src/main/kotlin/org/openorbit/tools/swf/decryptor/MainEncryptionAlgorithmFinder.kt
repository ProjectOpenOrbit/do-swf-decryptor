package org.openorbit.tools.swf.decryptor

import com.jpexs.decompiler.flash.SWF
import java.io.File

/**
 * Utility that can determine the encryption algorithm used to encrypt the main.swf.
 * We decompile the preloader.swf using ffdec and search for the class which is part of the package "algorithms".
 * As the class names do not change, we can do a simple string comparison to determine the algorithm.
 */
object MainEncryptionAlgorithmFinder {

    // obfuscated and de-obfuscated names of the class that contains the custom encryption algorithm
    private val CLASSNAME_ALGO_CUSTOM = arrayOf("§--_--_--§", "class_15")

    // obfuscated and de-obfuscated names of the class that contains the modified ARC4 encryption algorithm
    private val CLASSNAME_ALGO_ARC4 = arrayOf("§-_---_§", "class_16")

    fun determineEncryptionAlgorithm(preloaderFile: File): MainEncryptionAlgorithm {
        val swf = SWF(preloaderFile.inputStream(), true)
        val result = swf.aS3Packs.find { it.classPath.packageStr.last.equals("algorithms") }
        return when (result.toString()) {
            in CLASSNAME_ALGO_ARC4 -> MainEncryptionAlgorithm.RC4
            in CLASSNAME_ALGO_CUSTOM -> MainEncryptionAlgorithm.CUSTOM
            else -> throw RuntimeException("Could not determine encryption algorithm from preloader")
        }
    }

    enum class MainEncryptionAlgorithm {
        RC4, CUSTOM
    }
}