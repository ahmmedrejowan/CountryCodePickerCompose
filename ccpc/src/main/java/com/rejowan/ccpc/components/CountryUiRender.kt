package com.rejowan.ccpc.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
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
    selectedCountry: Country? = null,  // New parameter for highlighting selected country
    searchQuery: String = ""  // New parameter to show empty state only when searching
) {
    Spacer(modifier = Modifier.height(itemPadding.dp))

    // Material 3: Show empty state when search returns no results
    if (filteredCountries.isEmpty() && searchQuery.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.outline
                )
                Text(
                    text = "No countries found",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Try a different search term",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
    } else {
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
}