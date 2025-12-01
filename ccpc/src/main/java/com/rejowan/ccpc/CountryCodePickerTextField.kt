package com.rejowan.ccpc

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A composable TextField with integrated country code picker and phone number validation.
 *
 * This component combines [CountryCodePicker] with an [OutlinedTextField] to provide:
 * - Country selection with flag, ISO code, and phone code
 * - Automatic phone number formatting based on selected country
 * - Real-time validation using libphonenumber
 * - Error state display when showError is true
 *
 * Example usage:
 * ```
 * var phoneNumber by remember { mutableStateOf("") }
 * var isValid by remember { mutableStateOf(false) }
 *
 * CountryCodePickerTextField(
 *     number = phoneNumber,
 *     onValueChange = { countryCode, number, valid ->
 *         phoneNumber = number
 *         isValid = valid
 *     }
 * )
 * ```
 *
 * @param modifier Modifier to be applied to the TextField
 * @param number The phone number value (without country code)
 * @param onValueChange Callback with (countryCode: String, phoneNumber: String, isValid: Boolean)
 * @param enabled Whether the TextField is enabled (default: true)
 * @param textStyle Text style for the TextField content
 * @param label Optional label composable for the TextField
 * @param placeholder Optional placeholder composable for the TextField
 * @param trailingIcon Optional trailing icon composable for the TextField
 * @param showError Whether to show error state when number is invalid (default: true)
 * @param keyboardOptions Keyboard configuration options
 * @param keyboardActions Keyboard action handlers
 * @param shape Shape of the TextField (default: RoundedCornerShape(10.dp))
 * @param colors Color configuration for the TextField
 * @param selectedCountry The initially selected country (default: Bangladesh)
 * @param countryList List of countries to display in the picker
 * @param viewCustomization Customization for the country picker view
 * @param pickerCustomization Customization for the picker dialog/sheet
 * @param applyVisualTransformation Whether to apply phone number formatting (default: true)
 * @param backgroundColor Background color of the picker dialog/sheet
 * @param showSheet If true, shows bottom sheet; if false, shows dialog (default: false)
 * @param itemPadding Padding between items in dp (default: 10)
 *
 * @throws IllegalArgumentException if countryList is empty, itemPadding is negative, or selectedCountry not in countryList
 *
 * @see CountryCodePicker
 * @see CCPValidator
 * @see CCPTransformer
 */
@Composable
fun CountryCodePickerTextField(
    modifier: Modifier = Modifier,
    number: String,
    onValueChange: (countryCode: String, value: String, isValid: Boolean) -> Unit,
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    showError: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    selectedCountry: Country = Country.Bangladesh,
    countryList: List<Country> = Country.getAllCountries(),
    viewCustomization: ViewCustomization = ViewCustomization(),
    pickerCustomization: PickerCustomization = PickerCustomization(),
    applyVisualTransformation: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    showSheet: Boolean = false,
    itemPadding: Int = 10,
) {
    // Input validation
    require(countryList.isNotEmpty()) {
        "CountryCodePickerTextField requires at least one country in countryList. " +
        "Use Country.getAllCountries() or provide a custom list."
    }
    require(itemPadding >= 0) {
        "itemPadding must be non-negative, but was $itemPadding. " +
        "Provide a value >= 0 (recommended: 0-20)."
    }
    require(selectedCountry in countryList) {
        "selectedCountry (${selectedCountry.countryName}) must exist in the provided countryList. " +
        "Either add it to countryList or choose a country from the list."
    }

    val context = LocalContext.current

    var country by remember(selectedCountry) {
        mutableStateOf(selectedCountry)
    }


    val validatePhoneNumber = remember(context) {
        CCPValidator(context = context)
    }

    var isNumberValid: Boolean by rememberSaveable(country, number) {
        mutableStateOf(
            validatePhoneNumber(
                number = number, countryCode = country.countryCode
            ),
        )
    }


    OutlinedTextField(
        value = number,
        onValueChange = {
            isNumberValid = validatePhoneNumber(
                number = it, countryCode = country.countryCode
            )
            onValueChange(country.countryCode, it, isNumberValid)
        },
        modifier = modifier,
        textStyle = textStyle,
        singleLine = true,
        shape = shape,
        label = label,
        placeholder = placeholder,
        leadingIcon = {
            CountryCodePicker(
                selectedCountry = country,
                countryList = countryList,
                onCountrySelected = {
                    country = it
                    isNumberValid = validatePhoneNumber(
                        number = number, countryCode = it.countryCode
                    )
                    onValueChange(it.countryCode, number, isNumberValid)
                },
                viewCustomization = viewCustomization,
                pickerCustomization = pickerCustomization,
                backgroundColor = backgroundColor,
                textStyle = textStyle,
                showSheet = showSheet,
                itemPadding = itemPadding
            )

        },
        trailingIcon = trailingIcon,
        isError = !isNumberValid && number.isNotEmpty() && showError,
        visualTransformation = remember(country.countryIso, applyVisualTransformation) {
            if (applyVisualTransformation) {
                CCPTransformer(context, country.countryIso)
            } else {
                VisualTransformation.None
            }
        },
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = colors
    )


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    var value by remember {
        mutableStateOf("")
    }

    CountryCodePickerTextField(onValueChange = { _, number, _ ->
        value = number

    }, number = value)
}