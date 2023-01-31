import io.ktor.client.*
import io.ktor.client.engine.cio.*
import java.nio.file.Files
import java.nio.file.Paths
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.coroutines.runBlocking
import java.io.File


const val ROOT_PATH = "https://darkorbit-22.bpsecure.com/spacemap"

const val PRELOADER = "preloader.swf"
const val LOADINGSCREEN = "loadingscreen.swf"
const val MAIN = "main.swf"

private suspend fun download(url: String, targetFile: String) {
    val client = HttpClient(CIO)
    val response: HttpResponse = client.get(url)
    println("Download $url: ${response.status}")
    response.bodyAsChannel().copyTo(File(targetFile).outputStream())
    client.close()
}

fun downloadSwfCollection(targetPath: String) {
    Files.createDirectories(Paths.get(targetPath))
    runBlocking {
        for (file in arrayOf(PRELOADER, LOADINGSCREEN, MAIN)) {
            download("$ROOT_PATH/$file", "$targetPath/$file")
        }
    }
}


fun main(args: Array<String>) {
    println(args.asList())
    if (args.size != 1)
        throw IllegalArgumentException("Illegal arguments, expected target save location for SWF files")
    downloadSwfCollection(args[0])

}