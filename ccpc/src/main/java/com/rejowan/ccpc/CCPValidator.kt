package com.rejowan.ccpc

import android.content.Context
import io.michaelrocks.libphonenumber.android.NumberParseException
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil

/**
 * Validator for phone numbers using libphonenumber.
 * @param context Android context for initializing PhoneNumberUtil
 */
class CCPValidator(private val context: Context) {

    private val phoneUtil: PhoneNumberUtil by lazy { PhoneNumberUtil.createInstance(context) }

    /**
     * Validates a phone number with the given country code.
     * @param number The phone number to validate (without country code)
     * @param countryCode The country code including '+' (e.g., "+1")
     * @return true if the number is valid for the given country code, false otherwise
     */
    operator fun invoke(number: String, countryCode: String): Boolean {
        // Validate inputs
        if (number.isBlank() || countryCode.isBlank()) {
            return false
        }

        return try {
            val fullNumber = "$countryCode$number"
            val phoneNumber = phoneUtil.parse(fullNumber, null)
            phoneUtil.isValidNumber(phoneNumber)
        } catch (e: NumberParseException) {
            // Invalid number format - expected exception
            false
        } catch (e: Exception) {
            // Unexpected error - log and return false
            false
        }
    }


}


