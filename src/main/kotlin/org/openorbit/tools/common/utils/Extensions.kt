package org.openorbit.tools.common.utils

import java.util.zip.Inflater
import java.util.zip.InflaterInputStream

object Extensions {
    /**
     * extension function to easily inflate byte arrays (zlib decompression)
     */
    fun ByteArray.asInflated(): ByteArray {
        return InflaterInputStream(this.inputStream(), Inflater()).readBytes()
    }

    fun ByteArray.toIntArray(): IntArray {
        val ret = IntArray(size)
        for (i in indices) {
            ret[i] = this[i].toInt()
        }
        return ret
    }
}