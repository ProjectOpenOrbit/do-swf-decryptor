# do-swf-decryptor
Suite to decrypt DarkOrbit main.swf and loadingscreen.swf files.
Written in Kotlin.

# Purpose

In order to stay up-to-date with the packets of the official gameserver, we need access to the source code of the
gameclient.

This is only possible by decrypting the main.swf file containing that information.

Using that information, we can generate source code which implements the official server protocol.

# Features
1. Download swf files `preloader.swf`, `loadingscreen.swf` and `main.swf`
2. Decrypts loadingscreen and main.swf with little effort.
   
(!) There are two algorithms for the main.swf. This tool only supports RC4. The other algorithm is in the works.
   
# Libraries

We need ffdec-lib for decompiling SWF files.

Before you can compile the tool, you will have to download the library from here: https://github.com/jindrapetrik/jpexs-decompiler.

Paste it as "ffdec_lib.jar" in the lib directory and gradle should run fine.


# Build
Build the decryptor like this: `gradle build`

The downloader can only be run in an IDE such as IntelliJ.

# Run
The decryptor expects an argument with the path to the folder which contains the swf files.

Run the decryptor like this: `gradle run --args "PATH_TO_FOLDER_WITH_SWF_FILES"`

You cannot create a jar yet. We are working on that.
