package com.rejowan.ccpc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CountryCodePicker(
    selectedCountry: Country = Country.Bangladesh,
    countryList: List<Country> = Country.entries,
    onCountrySelected: (Country) -> Unit,
    showFlag: Boolean = true,
    showCountryIso: Boolean = false,
    showCountryName: Boolean = false,
    showCountryCode: Boolean = true,
    showArrow: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    showSheet: Boolean = false
) {

    var country by remember { mutableStateOf(selectedCountry) }
    var isPickerOpen by remember { mutableStateOf(false) }


    Row(modifier = Modifier
        .clickable {
            isPickerOpen = true
        }
        .background(backgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        CountryView(
            modifier = Modifier.padding(10.dp),
            country = country,
            showFlag = showFlag,
            showCountryIso = showCountryIso,
            showCountryName = showCountryName,
            showCountryCode = showCountryCode,
            showArrow = showArrow
        )

        if (isPickerOpen) {
            if (showSheet) {
                CountryPickerBottomSheet(onDismissRequest = { isPickerOpen = false }, onItemClicked = {
                    onCountrySelected(it)
                    country = it
                    isPickerOpen = false

                }, listOfCountry = countryList)
            } else {
                CountryPickerDialog(onDismissRequest = { isPickerOpen = false }, onItemClicked = {
                    onCountrySelected(it)
                    country = it
                    isPickerOpen = false

                }, listOfCountry = countryList)

            }

        }


    }


}


@Preview(showBackground = true)
@Composable
fun CountryCodePickerPreview() {
    CountryCodePicker(
        onCountrySelected = {},
    )
}