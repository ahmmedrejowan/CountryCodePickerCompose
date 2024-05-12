package com.rejowan.ccpc

import android.content.Context
import android.telephony.TelephonyManager

class CCPUtils {

    companion object {


        /**
         * Get emoji flag from country iso code using unicode
         * @param isoString country iso code
         * @return emoji flag using unicode
         */
        fun getEmojiFlag(isoString: String): String {
            return isoString.uppercase().map { char -> Character.codePointAt("$char", 0) + 0x1F1A5 }
                .joinToString("") {
                    String(Character.toChars(it))
                }
        }


        /**
         * Get country iso code from device
         * @param context application context
         * @return Country object
         * @see Country
         */

        fun getCountryAutomatically(context: Context): Country? {
            val telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            val iso = telephonyManager.networkCountryIso ?: telephonyManager.simCountryIso
            ?: context.resources.configuration.locales.get(0).country

            iso?.let {
                Country.getCountryByIso(it)?.let { isoCountry ->
                    return isoCountry
                }
            }
            return null
        }


    }

}
