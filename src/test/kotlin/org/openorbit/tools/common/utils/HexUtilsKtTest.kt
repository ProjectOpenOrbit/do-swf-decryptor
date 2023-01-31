package org.openorbit.tools.common.utils

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class HexUtilsKtTest {

    @Test
    fun byteArrayFromHexStr() {
        val expectedArray = ByteArray(4)
        expectedArray[0] = 0xAB.toByte()
        expectedArray[1] = 0xCD.toByte()
        expectedArray[2] = 0xEF.toByte()
        expectedArray[3] = 0x01.toByte()
        val testStr = "ABCDEF01"
        val arr = byteArrayFromHexStr(testStr)
        assertArrayEquals(expectedArray,arr)
    }
}