# do-swf-decryptor
Suite to decrypt DarkOrbit main.swf and loadingscreen.swf files.
Written in Kotlin.

# Purpose

In order to stay up-to-date with the packets of the official gameserver, we need access to the source code of the
gameclient.

This is only possible by decrypting the main.swf file containing that information.

Using that information, we can generate source code which implements the official server protocol.

# Libraries

We need ffdec-lib for decompiling SWF files.
Before you can compile the tool, you will have to download the library from
here: https://github.com/jindrapetrik/jpexs-decompiler.
Paste it as "ffdec_lib.jar" in the lib directory and gradle should run fine.