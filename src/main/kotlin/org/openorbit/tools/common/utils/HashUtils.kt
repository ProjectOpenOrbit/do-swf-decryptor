package org.openorbit.tools.common.utils

import java.security.MessageDigest

fun sha256(bytesToHash: ByteArray): ByteArray {
    val digest = MessageDigest.getInstance("SHA-256")
    digest.update(bytesToHash)
    return digest.digest()
}
