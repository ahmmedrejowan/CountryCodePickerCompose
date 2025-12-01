package com.rejowan.ccpc.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
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
    textStyle : TextStyle
) {
    Spacer(modifier = Modifier.height(itemPadding.dp))
    
    LazyColumn {
        items(filteredCountries , key = { it.countryName }) { countryItem ->
            HorizontalDivider(color = pickerCustomization.dividerColor)
            CountryUI(
                country = countryItem ,
                onCountryClicked = { onItemClicked(countryItem) } ,
                countryTextStyle = textStyle ,
                itemPadding = itemPadding ,
                showCountryIso = pickerCustomization.showCountryIso ,
                showCountryCode = pickerCustomization.showCountryCode ,
                showCountryFlag = pickerCustomization.showFlag ,
            )
        }
    }
}