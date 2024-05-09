package com.rejowan.ccpc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPickerBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onItemClicked: (item: Country) -> Unit,
    textStyle: TextStyle = TextStyle(),
    listOfCountry: List<Country>,
    itemPadding: Int = 10,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    dividerColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    ) {

    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
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

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState,
    ) {
        Surface(
            color = backgroundColor, modifier = modifier
        ) {

            Column(modifier = Modifier.fillMaxWidth()) {

                Spacer(modifier = Modifier.height(10.dp))

                CountryHeaderSheet(title = "Select Country")

                Spacer(modifier = Modifier.height(10.dp))

                CountrySearch(value = value,
                    onValueChange = { value = it },
                    textStyle = textStyle,
                    hint = "Search Country",
                    showClearIcon = true,
                    requestFocus = false,
                    onFocusChanged = {
                        if (it.hasFocus) {
                            scope.launch {
                                sheetState.expand()
                            }
                        }

                    })

                Spacer(modifier = Modifier.height(10.dp))


                LazyColumn {
                    items(filteredCountries, key = { it.countryName }) { countryItem ->
                        HorizontalDivider(color = dividerColor)
                        CountryUI(
                            country = countryItem,
                            onCountryClicked = { onItemClicked(countryItem) },
                            countryTextStyle = textStyle,
                            itemPadding = itemPadding
                        )
                    }

                }

            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun CountrySheetPreview() {
    CountryPickerBottomSheet(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f),
        listOfCountry = Country.getAllCountries(),
        onDismissRequest = {},
        onItemClicked = {},
    )
}