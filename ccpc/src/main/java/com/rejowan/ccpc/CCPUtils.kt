package com.rejowan.ccpc

import android.content.Context
import android.telephony.TelephonyManager

class CCPUtils {

    companion object {


        /**
         * Get emoji flag from country iso code using unicode.
         * Converts ISO 3166-1 alpha-2 country codes to regional indicator symbols.
         * @param isoString country iso code (2 letters, e.g., "US", "GB")
         * @return emoji flag using unicode, or empty string if invalid ISO code
         */
        fun getEmojiFlag(isoString: String): String {
            return try {
                // Validate ISO code format (must be 2 letters)
                if (isoString.length != 2 || !isoString.all { it.isLetter() }) {
                    return ""
                }

                isoString.uppercase().map { char ->
                    Character.codePointAt("$char", 0) + 0x1F1A5
                }.joinToString("") {
                    String(Character.toChars(it))
                }
            } catch (e: Exception) {
                ""
            }
        }


        /**
         * Get country iso code from device automatically.
         * Tries multiple sources in order of preference:
         * 1. Network country ISO (current network)
         * 2. SIM country ISO (SIM card)
         * 3. Device locale country
         *
         * @param context application context
         * @return Country object if detected, null otherwise
         * @see Country
         */
        fun getCountryAutomatically(context: Context): Country? {
            return try {
                // Try to get TelephonyManager
                val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager
                    ?: return getCountryFromLocale(context)

                // Try network/SIM country first
                val iso = telephonyManager.networkCountryIso?.takeIf { it.isNotBlank() }
                    ?: telephonyManager.simCountryIso?.takeIf { it.isNotBlank() }
                    ?: return getCountryFromLocale(context)

                // Find country by ISO
                Country.getCountryByIso(iso) ?: getCountryFromLocale(context)
            } catch (e: SecurityException) {
                // No READ_PHONE_STATE permission - fallback to locale
                getCountryFromLocale(context)
            } catch (e: Exception) {
                // Any other error - fallback to locale
                getCountryFromLocale(context)
            }
        }

        /**
         * Get country from device locale as fallback
         * @param context application context
         * @return Country object from locale, null if not found
         */
        private fun getCountryFromLocale(context: Context): Country? {
            return try {
                val countryCode = context.resources.configuration.locales.get(0).country
                if (countryCode.isNotBlank()) {
                    Country.getCountryByIso(countryCode)
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }


    }

}
