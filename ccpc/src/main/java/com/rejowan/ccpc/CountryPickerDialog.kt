package com.rejowan.ccpc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.rejowan.ccpc.components.RenderCountryList
import kotlinx.coroutines.delay

@Composable
fun CountryPickerDialog(
    modifier : Modifier = Modifier ,
    onDismissRequest : () -> Unit ,
    onItemClicked : (item : Country) -> Unit ,
    textStyle : TextStyle = TextStyle() ,
    listOfCountry : List<Country> ,
    pickerCustomization : PickerCustomization = PickerCustomization() ,
    itemPadding : Int = 10 ,
    backgroundColor : Color = MaterialTheme.colorScheme.surface ,
    selectedCountry: Country? = null  // New parameter for highlighting selected country
) {
    
    val context = LocalContext.current

    var value by remember { mutableStateOf("") }
    var debouncedValue by remember { mutableStateOf("") }

    // Debounce search input (300ms delay)
    LaunchedEffect(value) {
        delay(300)
        debouncedValue = value
    }

    val filteredCountries by remember(context, debouncedValue) {
        derivedStateOf {
            if (debouncedValue.isEmpty()) {
                listOfCountry
            } else {
                Country.searchCountry(debouncedValue, context, list = listOfCountry)
            }
        }

    }
    
    
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false  // Allow custom sizing
        )
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth(0.92f)  // 92% of screen width with margins
                .fillMaxHeight(0.85f)  // Max 85% screen height
                .widthIn(max = 560.dp)  // Material 3 max width for dialogs
                .then(modifier),
            shape = RoundedCornerShape(28.dp),  // Material 3 large container shape
            tonalElevation = 6.dp,              // Material 3 elevation level 3 for dialogs
            shadowElevation = 0.dp              // No drop shadow in Material 3
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                
                Spacer(modifier = Modifier.height(itemPadding.dp))
                
                CountryHeaderDialog(
                    title = pickerCustomization.headerTitleText ?: stringResource(pickerCustomization.headerTitle) ,
                    onDismiss = { onDismissRequest() })

                Spacer(modifier = Modifier.height(itemPadding.dp))

                CountrySearch(
                    value = value ,
                    onValueChange = { value = it } ,
                    textStyle = textStyle ,
                    hint = pickerCustomization.searchHintText ?: stringResource(pickerCustomization.searchHint) ,
                    showClearIcon = pickerCustomization.showSearchClearIcon ,
                )
                
                RenderCountryList(
                    itemPadding ,
                    filteredCountries ,
                    pickerCustomization ,
                    onItemClicked ,
                    textStyle,
                    selectedCountry,  // Pass selected country for highlighting
                    debouncedValue  // Pass search query for empty state
                )
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
            .clip(RoundedCornerShape(10.dp)) ,
        listOfCountry = Country.getAllCountries() ,
        onDismissRequest = {} ,
        onItemClicked = {} ,
    )
}