import org.openorbit.tools.swf.decryptor.SwfDecryptionUtility
import java.nio.file.Files.isDirectory
import java.nio.file.Files.notExists
import java.nio.file.Paths
import kotlin.system.exitProcess

const val VERSION: String = "1.0"
var downloadFiles: Boolean = false
var pathToFiles: String = ""

fun parseArgs(args: Array<String>) {
    val it = args.iterator()
    while (it.hasNext()) {
        when (val token = it.next()) {
            in "-d", "--download" -> downloadFiles = true
            in "-p", "--path" -> pathToFiles =
                if (it.hasNext()) it.next() else error("No path specified. Example: -p /Path/To/Files")

            in "-h", "--help" -> printHelpTextAndExit()
            else -> error("Invalid argument '$token'")
        }
    }
    if (pathToFiles.isEmpty()) error("Error: No Path specified! Example: -p /Path/To/Files")

}

/**
 * Make sure that the target path
 * 1) exists and
 * 2) is a directory
 */
fun checkPathValid() {
    val path = Paths.get(pathToFiles)
    if (notExists(path)) error("The given target path does not exist: $pathToFiles")

    if (!isDirectory(path)) error("The given target path is not a directory: $pathToFiles")
}

fun printHelpTextAndExit() {
    println(
        """==================={ DarkOrbit SWF Decryption Suite v$VERSION by tram98 }===================
        |
        |===={ Introduction }====
        |
        |This tool is a suite to decrypt the SWF files "loadingscreen.swf" and "main.swf" of the browser game DarkOrbit.
        |It extracts the decryption key from the loadingscreen.swf file and determines the decryption algorithm
        |from the preloader.swf file.
        |
        |
        |===={ Usage }====
        | 
        |Example: ./do-swf-decryptor [ -d / --download ] -p / --path <path to target directory>
        |
        |-d | --download : Download the latest swf files from the official servers directly. (optional)
        |
        |-p | --path     : Specify the target directory where the files to decrypt are stored. This is also the 
        |                  directory where the files are downloaded to, if enabled. (mandatory)
        |                   
    """.trimMargin()
    )

    exitProcess(0)
}


fun main(args: Array<String>) {

    if (args.isEmpty()) printHelpTextAndExit()

    println("DarkOrbit SWF Decryption Suite v$VERSION by tram98\n")
    parseArgs(args)

    checkPathValid()

    if (downloadFiles) {
        downloadSwfCollection(pathToFiles)
    }

    SwfDecryptionUtility(workingDirectory = pathToFiles).decrypt()
}
