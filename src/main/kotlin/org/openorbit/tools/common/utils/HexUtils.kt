package org.openorbit.tools.common.utils

fun byteArrayFromHexStr(hexStr: String): ByteArray {
    var strToParse = hexStr
    if (hexStr.length % 2 == 1)
        strToParse = "0$strToParse"
    val arr = ByteArray(strToParse.length / 2)

    for (i in 0 until strToParse.length step 2) {
        arr[i / 2] = Integer.parseInt(strToParse.substring(i, i + 2), 16).toByte()
    }
    return arr
}