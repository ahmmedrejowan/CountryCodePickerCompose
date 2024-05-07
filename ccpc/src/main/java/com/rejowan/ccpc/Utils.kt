package com.rejowan.ccpc

fun getEmojiFlag(isoString: String): String {
    return isoString.uppercase().map { char -> Character.codePointAt("$char", 0) + 0x1F1A5 }
        .joinToString("") {
            String(Character.toChars(it))
        }
}