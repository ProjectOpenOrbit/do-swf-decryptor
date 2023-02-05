package org.openorbit.tools.swf.decryptor.algorithms

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.openorbit.tools.common.utils.byteArrayFromHexStr

class DarkOrbitCustomEncryptionAlgorithmTest {

    val expectedBytes = arrayOf<UByte>(
        99u, 255u, 76u, 234u, 79u, 204u, 156u, 128u, 140u, 153u, 158u, 15u, 109u, 250u, 221u, 179u, 240u, 56u, 20u,
        172u, 52u, 16u, 73u, 180u, 87u, 216u, 212u, 180u, 47u, 160u, 102u, 188u, 193u, 142u, 102u, 16u, 170u, 175u,
        216u, 199u, 85u, 155u, 238u, 207u, 32u, 118u, 197u, 161u, 253u, 203u, 10u, 160u, 145u, 210u, 219u, 202u, 171u,
        228u, 239u, 245u, 192u, 65u, 95u, 89u, 190u, 73u, 201u, 245u, 31u, 47u, 108u, 194u, 140u, 61u, 137u, 191u,
        135u, 92u, 23u, 208u, 184u, 234u, 32u, 119u, 59u, 17u, 107u, 29u, 162u, 49u, 248u, 157u, 219u, 126u, 238u,
        11u, 152u, 174u, 120u, 236u
    )

    val testKey = "17696850441596006280"

    @Test
    fun decrypt() {
        val bytes = ByteArray(100, init = { 0 })
        val algo = DarkOrbitCustomEncryptionAlgorithm(key = byteArrayFromHexStr(testKey))
        val result = algo.decrypt(bytes)
        for (i in result.indices) {
            assertEquals(expectedBytes[i], result[i].toUByte())
        }
    }
}