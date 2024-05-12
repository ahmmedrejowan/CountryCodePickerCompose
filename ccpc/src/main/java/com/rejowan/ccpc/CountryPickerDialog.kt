package com.rejowan.ccpc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CountryPickerDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onItemClicked: (item: Country) -> Unit,
    textStyle: TextStyle = TextStyle(),
    listOfCountry: List<Country>,
    pickerCustomization: PickerCustomization = PickerCustomization(),
    itemPadding: Int = 10,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
) {

    val context = LocalContext.current

    var value by remember { mutableStateOf("") }

    val filteredCountries by remember(context, value) {
        derivedStateOf {
            if (value.isEmpty()) {
                listOfCountry
            } else {
                Country.searchCountry(value, listOfCountry)
            }
        }

    }


    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            color = backgroundColor, modifier = modifier
        ) {

            Column(modifier = Modifier.fillMaxWidth()) {

                Spacer(modifier = Modifier.height(itemPadding.dp))

                CountryHeaderDialog(
                    title = pickerCustomization.headerTitle,
                    onDismiss = { onDismissRequest() })

                Spacer(modifier = Modifier.height(itemPadding.dp))

                CountrySearch(
                    value = value,
                    onValueChange = { value = it },
                    textStyle = textStyle,
                    hint = pickerCustomization.searchHint,
                    showClearIcon = pickerCustomization.showSearchClearIcon,
                )

                Spacer(modifier = Modifier.height(itemPadding.dp))


                LazyColumn {
                    items(filteredCountries, key = { it.countryName }) { countryItem ->
                        HorizontalDivider(color = pickerCustomization.dividerColor)
                        CountryUI(
                            country = countryItem,
                            onCountryClicked = { onItemClicked(countryItem) },
                            countryTextStyle = textStyle,
                            itemPadding = itemPadding,
                            showCountryIso = pickerCustomization.showCountryIso,
                            showCountryCode = pickerCustomization.showCountryCode,
                            showCountryFlag = pickerCustomization.showFlag,
                        )
                    }

                }

            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun CountryDialogPreview() {
    CountryPickerDialog(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(10.dp)),
        listOfCountry = Country.getAllCountries(),
        onDismissRequest = {},
        onItemClicked = {},
    )
}