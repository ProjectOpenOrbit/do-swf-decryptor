import org.openorbit.tools.swf.decryptor.SwfDecryptionUtility

fun main(args: Array<String>) {
    if(args.isEmpty())
        throw IllegalArgumentException("no root path to swf files given.")
    SwfDecryptionUtility(args[0]).decrypt()
}
