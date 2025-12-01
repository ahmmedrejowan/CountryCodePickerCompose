package com.rejowan.ccpc

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests for Country enum and its companion object methods.
 */
class CountryTest {

    @Test
    fun `getAllCountries returns all 241 countries`() {
        val countries = Country.getAllCountries()
        assertEquals("Should have exactly 241 countries", 241, countries.size)
    }

    @Test
    fun `getAllCountries returns sorted list by country name`() {
        val countries = Country.getAllCountries()
        val countryNames = countries.map { it.countryName }
        val sortedNames = countryNames.sorted()
        assertEquals("Countries should be sorted alphabetically", sortedNames, countryNames)
    }

    @Test
    fun `getCountryByIso with valid ISO returns correct country`() {
        val usa = Country.getCountryByIso("US")
        assertNotNull("USA should be found", usa)
        assertEquals("Should return United States", Country.UnitedStates, usa)
    }

    @Test
    fun `getCountryByIso with lowercase ISO returns correct country`() {
        val uk = Country.getCountryByIso("gb")
        assertNotNull("UK should be found with lowercase", uk)
        assertEquals("Should return United Kingdom", Country.UnitedKingdom, uk)
    }

    @Test
    fun `getCountryByIso with invalid ISO returns null`() {
        val invalid = Country.getCountryByIso("XX")
        assertNull("Invalid ISO should return null", invalid)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `getCountryByIso with blank ISO throws exception`() {
        Country.getCountryByIso("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `getCountryByIso with whitespace ISO throws exception`() {
        Country.getCountryByIso("   ")
    }

    @Test
    fun `getAllCountriesExcept filters out specified countries`() {
        val excluding = listOf(Country.UnitedStates, Country.Canada, Country.Mexico)
        val filtered = Country.getAllCountriesExcept(excluding)

        assertEquals("Should have 238 countries (241 - 3)", 238, filtered.size)
        assertFalse("Should not contain US", filtered.contains(Country.UnitedStates))
        assertFalse("Should not contain Canada", filtered.contains(Country.Canada))
        assertFalse("Should not contain Mexico", filtered.contains(Country.Mexico))
    }

    @Test
    fun `getAllCountriesExcept with empty list returns all countries`() {
        val all = Country.getAllCountriesExcept(emptyList())
        assertEquals("Should return all countries", 241, all.size)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `searchCountry with empty list throws exception`() {
        Country.searchCountry("test", null, false, emptyList())
    }

    @Test
    fun `searchCountry with empty query returns full list`() {
        val result = Country.searchCountry("")
        assertEquals("Empty query should return all countries", 241, result.size)
    }

    @Test
    fun `searchCountry with whitespace query returns full list`() {
        val result = Country.searchCountry("   ")
        assertEquals("Whitespace query should return all countries", 241, result.size)
    }

    @Test
    fun `searchCountry by country code finds correct countries`() {
        val result = Country.searchCountry("+1")
        assertTrue("Should find USA", result.contains(Country.UnitedStates))
        assertTrue("Should find Canada", result.contains(Country.Canada))
        assertTrue("Should find multiple +1 countries", result.size > 1)
    }

    @Test
    fun `searchCountry by partial name finds countries`() {
        val result = Country.searchCountry("United")
        assertTrue("Should find United States", result.contains(Country.UnitedStates))
        assertTrue("Should find United Kingdom", result.contains(Country.UnitedKingdom))
        assertTrue("Should find United Arab Emirates", result.contains(Country.UnitedArabEmirates))
    }

    @Test
    fun `searchCountry by ISO code finds country`() {
        val result = Country.searchCountry("US")
        assertTrue("Should find USA by ISO", result.contains(Country.UnitedStates))
    }

    @Test
    fun `searchCountry with phone number finds country`() {
        val result = Country.searchCountry("+12125551234")
        assertTrue("Should find USA from full phone number", result.contains(Country.UnitedStates))
    }

    @Test
    fun `searchCountry is case insensitive`() {
        val lowercase = Country.searchCountry("united states")
        val uppercase = Country.searchCountry("UNITED STATES")
        val mixed = Country.searchCountry("UnItEd StAtEs")

        assertTrue("Lowercase search should find USA", lowercase.contains(Country.UnitedStates))
        assertTrue("Uppercase search should find USA", uppercase.contains(Country.UnitedStates))
        assertTrue("Mixed case search should find USA", mixed.contains(Country.UnitedStates))
    }

    @Test
    fun `findCountry returns first match for query`() {
        val country = Country.findCountry("+1")
        assertNotNull("Should find a country for +1", country)
        assertTrue("Should find a +1 country", country.countryCode == "+1")
    }

    @Test
    fun `findCountry returns first country if no match`() {
        val country = Country.findCountry("nonexistent")
        assertNotNull("Should return first country if no match", country)
    }

    @Test
    fun `country enum has correct data structure`() {
        // Test USA as example
        assertEquals("US", Country.UnitedStates.countryIso)
        assertEquals("United States Of America", Country.UnitedStates.countryName)
        assertEquals("+1", Country.UnitedStates.countryCode)
    }

    @Test
    fun `all countries have valid ISO codes (2 letters)`() {
        val allCountries = Country.getAllCountries()
        allCountries.forEach { country ->
            assertEquals("${country.countryName} should have 2-letter ISO code",
                2, country.countryIso.length)
            assertTrue("${country.countryName} ISO should be uppercase letters",
                country.countryIso.all { it.isLetter() && it.isUpperCase() })
        }
    }

    @Test
    fun `all countries have valid phone codes (starts with +)`() {
        val allCountries = Country.getAllCountries()
        allCountries.forEach { country ->
            assertTrue("${country.countryName} phone code should start with +",
                country.countryCode.startsWith("+"))
            assertTrue("${country.countryName} phone code should contain only digits after +",
                country.countryCode.substring(1).all { it.isDigit() })
        }
    }

    @Test
    fun `all countries have non-empty names`() {
        val allCountries = Country.getAllCountries()
        allCountries.forEach { country ->
            assertTrue("${country.countryIso} should have non-empty name",
                country.countryName.isNotBlank())
        }
    }

    @Test
    fun `no duplicate ISO codes exist`() {
        val allCountries = Country.getAllCountries()
        val isoCodes = allCountries.map { it.countryIso }
        val uniqueIsoCodes = isoCodes.toSet()
        assertEquals("All ISO codes should be unique", isoCodes.size, uniqueIsoCodes.size)
    }

    @Test
    fun `common countries are present`() {
        val commonIsoCodes = listOf("US", "GB", "CN", "IN", "BR", "RU", "DE", "FR", "JP", "AU")
        commonIsoCodes.forEach { iso ->
            val country = Country.getCountryByIso(iso)
            assertNotNull("Common country $iso should be present", country)
        }
    }
}
