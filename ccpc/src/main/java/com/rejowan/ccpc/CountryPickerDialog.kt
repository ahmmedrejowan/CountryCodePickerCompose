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
import com.rejowan.ccpc.components.RenderCountryList

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
) {
    
    val context = LocalContext.current
    
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
    
    
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            color = backgroundColor , modifier = modifier
        ) {
            
            Column(modifier = Modifier.fillMaxWidth()) {
                
                Spacer(modifier = Modifier.height(itemPadding.dp))
                
                CountryHeaderDialog(
                    title = stringResource(pickerCustomization.headerTitle) ,
                    onDismiss = { onDismissRequest() })
                
                Spacer(modifier = Modifier.height(itemPadding.dp))
                
                CountrySearch(
                    value = value ,
                    onValueChange = { value = it } ,
                    textStyle = textStyle ,
                    hint = stringResource(pickerCustomization.searchHint) ,
                    showClearIcon = pickerCustomization.showSearchClearIcon ,
                )
                
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