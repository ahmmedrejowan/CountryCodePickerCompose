package com.rejowan.ccpc.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.rejowan.ccpc.Country
import com.rejowan.ccpc.CountryUI
import com.rejowan.ccpc.PickerCustomization

@Composable
fun RenderCountryList(
    itemPadding : Int ,
    filteredCountries : List<Country> ,
    pickerCustomization : PickerCustomization ,
    onItemClicked : (Country) -> Unit ,
    textStyle : TextStyle,
    selectedCountry: Country? = null  // New parameter for highlighting selected country
) {
    Spacer(modifier = Modifier.height(itemPadding.dp))

    LazyColumn {
        itemsIndexed(filteredCountries, key = { _, item -> item.countryName }) { index, countryItem ->
            // Material 3: Only show divider between items, not before first item
            if (index > 0) {
                HorizontalDivider(
                    thickness = 1.dp,  // Material 3 standard thickness
                    color = MaterialTheme.colorScheme.outlineVariant  // Material 3 semantic color
                )
            }
            CountryUI(
                country = countryItem ,
                onCountryClicked = { onItemClicked(countryItem) } ,
                countryTextStyle = textStyle ,
                itemPadding = itemPadding ,
                showCountryIso = pickerCustomization.showCountryIso ,
                showCountryCode = pickerCustomization.showCountryCode ,
                showCountryFlag = pickerCustomization.showFlag ,
                isSelected = selectedCountry == countryItem  // Highlight if selected
            )
        }
    }
}