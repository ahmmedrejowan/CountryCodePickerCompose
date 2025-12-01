package com.rejowan.ccpc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rejowan.ccpc.components.RenderCountryList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPickerBottomSheet(
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
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val scope = rememberCoroutineScope()
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
    
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState,
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),  // Material 3 bottom sheet shape
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp  // Material 3 elevation level 1 for bottom sheets
    ) {
        Surface(
            color = Color.Transparent,
            modifier = modifier
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                
                Spacer(modifier = Modifier.height(itemPadding.dp))
                
                CountryHeaderSheet(title = pickerCustomization.headerTitleText ?: stringResource(pickerCustomization.headerTitle))

                Spacer(modifier = Modifier.height(itemPadding.dp))

                CountrySearch(value = value ,
                    onValueChange = { value = it } ,
                    textStyle = textStyle ,
                    hint = pickerCustomization.searchHintText ?: stringResource(pickerCustomization.searchHint) ,
                    showClearIcon = pickerCustomization.showSearchClearIcon ,
                    requestFocus = false ,
                    onFocusChanged = {
                        if (it.hasFocus) {
                            scope.launch {
                                sheetState.expand()
                            }
                        }

                    })
                
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
fun CountrySheetPreview() {
    CountryPickerBottomSheet(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f) ,
        listOfCountry = Country.getAllCountries() ,
        onDismissRequest = {} ,
        onItemClicked = {} ,
    )
}