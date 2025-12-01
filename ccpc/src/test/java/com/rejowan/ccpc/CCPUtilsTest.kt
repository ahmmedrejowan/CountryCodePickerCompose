package com.rejowan.ccpc

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests for CCPUtils utility class.
 */
class CCPUtilsTest {

    @Test
    fun `getEmojiFlag with valid 2-letter ISO returns emoji`() {
        val usFlag = CCPUtils.getEmojiFlag("US")
        assertNotNull("US flag should not be null", usFlag)
        assertTrue("US flag should not be empty", usFlag.isNotEmpty())
        // US flag emoji should be 🇺🇸
        assertEquals("Should return US flag emoji", "🇺🇸", usFlag)
    }

    @Test
    fun `getEmojiFlag with lowercase ISO returns emoji`() {
        val ukFlag = CCPUtils.getEmojiFlag("gb")
        assertEquals("Should return UK flag emoji", "🇬🇧", ukFlag)
    }

    @Test
    fun `getEmojiFlag with invalid ISO length returns empty string`() {
        val single = CCPUtils.getEmojiFlag("U")
        val triple = CCPUtils.getEmojiFlag("USA")

        assertEquals("Single letter should return empty", "", single)
        assertEquals("Triple letters should return empty", "", triple)
    }

    @Test
    fun `getEmojiFlag with numbers returns empty string`() {
        val withNumbers = CCPUtils.getEmojiFlag("U1")
        assertEquals("ISO with numbers should return empty", "", withNumbers)
    }

    @Test
    fun `getEmojiFlag with special characters returns empty string`() {
        val special = CCPUtils.getEmojiFlag("U!")
        assertEquals("ISO with special chars should return empty", "", special)
    }

    @Test
    fun `getEmojiFlag with empty string returns empty string`() {
        val empty = CCPUtils.getEmojiFlag("")
        assertEquals("Empty ISO should return empty", "", empty)
    }

    @Test
    fun `getEmojiFlag is case insensitive`() {
        val upper = CCPUtils.getEmojiFlag("US")
        val lower = CCPUtils.getEmojiFlag("us")
        val mixed = CCPUtils.getEmojiFlag("Us")

        assertEquals("All cases should return same emoji", upper, lower)
        assertEquals("All cases should return same emoji", upper, mixed)
    }

    @Test
    fun `getEmojiFlag returns different emojis for different countries`() {
        val us = CCPUtils.getEmojiFlag("US")
        val uk = CCPUtils.getEmojiFlag("GB")
        val cn = CCPUtils.getEmojiFlag("CN")

        assertNotEquals("US and UK flags should be different", us, uk)
        assertNotEquals("US and CN flags should be different", us, cn)
        assertNotEquals("UK and CN flags should be different", uk, cn)
    }

    @Test
    fun `getEmojiFlag produces valid regional indicator symbols`() {
        // Regional indicator symbols range from U+1F1E6 (A) to U+1F1FF (Z)
        val flag = CCPUtils.getEmojiFlag("US")

        // Each flag is composed of 2 regional indicator symbols (8 bytes in UTF-8)
        assertEquals("Flag should have 8 bytes (2 symbols)", 8, flag.toByteArray(Charsets.UTF_8).size)
    }

    @Test
    fun `getEmojiFlag handles all valid letter combinations`() {
        // Test a few valid ISO codes
        val validIsoCodes = listOf("US", "GB", "CN", "IN", "BR", "RU", "DE", "FR", "JP", "AU")

        validIsoCodes.forEach { iso ->
            val flag = CCPUtils.getEmojiFlag(iso)
            assertTrue("Flag for $iso should not be empty", flag.isNotEmpty())
            assertNotEquals("Flag for $iso should not be the ISO code", iso, flag)
        }
    }

    @Test
    fun `getEmojiFlag returns consistent results for same input`() {
        val first = CCPUtils.getEmojiFlag("US")
        val second = CCPUtils.getEmojiFlag("US")
        val third = CCPUtils.getEmojiFlag("US")

        assertEquals("Multiple calls should return same result", first, second)
        assertEquals("Multiple calls should return same result", first, third)
    }

    @Test
    fun `getEmojiFlag handles whitespace by returning empty`() {
        val spaces = CCPUtils.getEmojiFlag("  ")
        assertEquals("Whitespace should return empty", "", spaces)
    }

    // Note: getCountryAutomatically requires Android Context and cannot be easily unit tested
    // without mocking framework. It would be better tested as an instrumentation test.
}
