package com.rejowan.ccpc

import android.content.Context
import android.telephony.PhoneNumberUtils
import android.text.Selection
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import java.util.Locale

class CCPTransformer(
    context: Context, countryIso: String = Locale.getDefault().country
) : VisualTransformation {

    private val phoneNumberFormatter =
        PhoneNumberUtil.createInstance(context).getAsYouTypeFormatter(countryIso)

    override fun filter(text: AnnotatedString): TransformedText {
        val transformation = reformat(text, Selection.getSelectionEnd(text))

        return TransformedText(
            AnnotatedString(transformation.formatted.orEmpty()),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    // Safely handle offset mapping with bounds checking
                    if (transformation.originalToTransformed.isEmpty()) return 0
                    val safeOffset = offset.coerceIn(0, transformation.originalToTransformed.lastIndex)
                    return transformation.originalToTransformed[safeOffset].coerceAtLeast(0)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    // Safely handle offset mapping with bounds checking
                    if (transformation.transformedToOriginal.isEmpty()) return 0
                    val safeOffset = offset.coerceIn(0, transformation.transformedToOriginal.lastIndex)
                    return transformation.transformedToOriginal[safeOffset].coerceAtLeast(0)
                }
            })
    }

    private fun reformat(s: CharSequence, cursor: Int): Transformation {
        phoneNumberFormatter.clear()

        val curIndex = cursor - 1
        var formatted: String? = null
        var lastNonSeparator = 0.toChar()
        var hasCursor = false

        s.forEachIndexed { index, char ->
            if (PhoneNumberUtils.isNonSeparator(char)) {
                if (lastNonSeparator.code != 0) {
                    formatted = getFormattedNumber(lastNonSeparator, hasCursor)
                    hasCursor = false
                }
                lastNonSeparator = char
            }
            if (index == curIndex) {
                hasCursor = true
            }
        }

        if (lastNonSeparator.code != 0) {
            formatted = getFormattedNumber(lastNonSeparator, hasCursor)
        }

        // Build offset mappings
        val originalToTransformed = mutableListOf<Int>()
        val transformedToOriginal = mutableListOf<Int>()

        // Handle empty or null formatted text
        if (formatted.isNullOrEmpty()) {
            // Return identity mapping for empty text
            val length = s.length
            for (i in 0..length) {
                originalToTransformed.add(i)
                transformedToOriginal.add(i)
            }
            return Transformation(formatted.orEmpty(), originalToTransformed, transformedToOriginal)
        }

        var originalIndex = 0
        // Explicit null check to prevent race conditions
        val formattedText = formatted ?: ""
        formattedText.forEachIndexed { transformedIndex, char ->
            if (PhoneNumberUtils.isNonSeparator(char)) {
                // This is a digit from the original text
                originalToTransformed.add(transformedIndex)
                transformedToOriginal.add(originalIndex)
                originalIndex++
            } else {
                // This is a separator added by formatting (space, dash, parenthesis, etc.)
                // Map it back to the current original position
                transformedToOriginal.add(maxOf(0, originalIndex - 1))
            }
        }

        // Add final position mappings to handle cursor at end
        // For original->transformed: map to end of formatted text
        for (i in originalToTransformed.size..s.length) {
            originalToTransformed.add(formatted?.length ?: 0)
        }

        // For transformed->original: map to end of original text
        transformedToOriginal.add(originalIndex)

        return Transformation(formatted, originalToTransformed, transformedToOriginal)
    }

    private fun getFormattedNumber(lastNonSeparator: Char, hasCursor: Boolean): String? {
        return if (hasCursor) {
            phoneNumberFormatter.inputDigitAndRememberPosition(lastNonSeparator)
        } else {
            phoneNumberFormatter.inputDigit(lastNonSeparator)
        }
    }

    private data class Transformation(
        val formatted: String?,
        val originalToTransformed: List<Int>,
        val transformedToOriginal: List<Int>
    )
}