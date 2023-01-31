package org.openorbit.tools.common.utils

import java.util.zip.Inflater
import java.util.zip.InflaterInputStream

object Extensions {
    /**
     * extension function to easily inflate byte arrays (zlib decompression)
     */
    fun ByteArray.inflated():ByteArray {
        return InflaterInputStream(this.inputStream(), Inflater()).readBytes()
    }
}