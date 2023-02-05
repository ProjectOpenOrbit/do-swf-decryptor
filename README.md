# DarkOrbit SWF Decryption Utility

This is a small suite to decrypt DarkOrbit main.swf and loadingscreen.swf files.
The software is written in Kotlin.

# Purpose

In order to stay up-to-date with the packets of the official gameserver, we need access to the source code of the
gameclient.

This is only possible by decrypting the main.swf file containing that information.

Using that information, we can generate source code which implements the official server protocol.

# Features

1. Downloads swf files `preloader.swf`, `loadingscreen.swf` and `main.swf`
2. Decrypts LoadingScreen and main.swf with little effort.

# Libraries

This tool makes use of the JPEXS ffdec decompiler.
The source code can be found here: https://github.com/jindrapetrik/jpexs-decompiler

I did not find a repository that serves the JAR file, so it is included as a binary in the repo.

# Build

Build the decryptor like this: `gradle build`

The downloader can only be run in an IDE such as IntelliJ.

# Run

The decryptor expects an argument with the path to the folder which contains the swf files.

Run the decryptor like this: `gradle run --args "PATH_TO_FOLDER_WITH_SWF_FILES"`

You cannot create a jar yet. We are working on that.
