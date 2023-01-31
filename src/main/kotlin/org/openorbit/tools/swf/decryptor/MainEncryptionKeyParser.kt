package org.openorbit.tools.swf.decryptor

import com.jpexs.decompiler.flash.SWF
import java.io.File

private const val NAME_OF_CLASS_WITH_KEY = "ViewPrepCommand"

object MainEncryptionKeyParser {
    fun parseEncryptionKey(decryptedLoadingScreen: File): String {
        val loadingScreen = SWF(decryptedLoadingScreen.inputStream(), true)
        println(loadingScreen.aS3Packs.size)
        val script = getContainingClassCode(loadingScreen)
        return parseKeyFromScript(script)
    }

    private fun parseKeyFromScript(script: String): String {

        val res = script.split("\n").first {
            it.contains("return") && it.contains("\"")
        }.split("\"")[1]
        return res
    }

    private fun getContainingClassCode(loadingScreen: SWF): String {
        val find = loadingScreen.aS3Packs.find {
            it.classPath.className.apply{println(it)}.equals(NAME_OF_CLASS_WITH_KEY)
        }
        return SWF.getCached(find).text
    }
}