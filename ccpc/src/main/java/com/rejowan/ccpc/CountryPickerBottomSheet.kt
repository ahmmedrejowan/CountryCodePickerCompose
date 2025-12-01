package com.rejowan.ccpc

import androidx.compose.foundation.layout.*
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
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var value by remember { mutableStateOf("") }
    
    val filteredCountries by remember(context , value) {
        derivedStateOf {
            if (value.isEmpty()) {
                listOfCountry
            } else {
                Country.searchCountry(value , context , list = listOfCountry)
            }
        }
    }
    
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() } ,
        sheetState = sheetState ,
    ) {
        Surface(
            color = backgroundColor , modifier = modifier
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                
                Spacer(modifier = Modifier.height(itemPadding.dp))
                
                CountryHeaderSheet(title = stringResource(pickerCustomization.headerTitle))
                
                Spacer(modifier = Modifier.height(itemPadding.dp))
                
                CountrySearch(value = value ,
                    onValueChange = { value = it } ,
                    textStyle = textStyle ,
                    hint = stringResource(pickerCustomization.searchHint) ,
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
                    textStyle
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