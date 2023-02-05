package org.openorbit.tools.common.utils

/**
 * Returns an integer from bytes in an integer array {arr} starting at the position {i}
 * Example: starting index "i" is 0, "arr" contains [0x000000AB 0x000000CD 0x000000EF 0x00000042] stored as Integers
 * The function must return 0xABCDEF42 as a result of bit shifting and combination     *
 */
fun getIntFromBytesInIntArray(arr: IntArray, i: Int): Int {
    return (arr[i + 3] shl 24) or ((arr[i + 2] and 0xFF) shl 16) or ((arr[i + 1] and 0xFF) shl 8) or (arr[i] and 0xFF)
}