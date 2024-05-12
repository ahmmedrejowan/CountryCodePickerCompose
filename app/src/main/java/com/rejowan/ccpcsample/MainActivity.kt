package com.rejowan.ccpcsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rejowan.ccpc.CCPTransformer
import com.rejowan.ccpc.CCPUtils
import com.rejowan.ccpc.CCPValidator
import com.rejowan.ccpc.Country
import com.rejowan.ccpc.CountryCodePicker
import com.rejowan.ccpc.CountryCodePickerTextField
import com.rejowan.ccpc.PickerCustomization
import com.rejowan.ccpc.ViewCustomization
import com.rejowan.ccpcsample.ui.theme.CCPCSampleTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CCPCSampleTheme {

                ShowMainScreen()


            }
        }
    }


}

@Composable
fun ShowMainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        ShowCountryCodePicker()

        ShowCCPWithTextField()

        ShowCountryCodePickerTextField()

    }
}

@Composable
fun ShowCountryCodePickerTextField() {

    var text by remember { mutableStateOf("") }

    CountryCodePickerTextField(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        enabled = true,
        textStyle = MaterialTheme.typography.bodyMedium,
        trailingIcon = {
            IconButton(onClick = { text = "" }) {
                Icon(
                    imageVector = Icons.Default.Clear, contentDescription = "Clear"
                )
            }
        },
        label = {
            Text(
                text = "Phone Number", style = MaterialTheme.typography.bodyMedium
            )
        },
        showError = false,
        shape = RoundedCornerShape(10.dp),
        onValueChange = { countryCode, value, isValid ->
            text = value
        },
        number = text,
        showSheet = true,


        )

}

@Composable
fun ShowCCPWithTextField() {

    val context = LocalContext.current

    var text by remember { mutableStateOf("") }

    var country by remember {
        mutableStateOf(Country.Bangladesh)
    }

    val validatePhoneNumber = remember(context) {
        CCPValidator(context = context)
    }

    var isNumberValid: Boolean by rememberSaveable(country, text) {
        mutableStateOf(
            validatePhoneNumber(
                number = text, countryCode = country.countryCode
            ),
        )
    }



    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            isNumberValid = validatePhoneNumber(
                number = it, countryCode = country.countryCode
            )

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 5.dp),
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            Text(
                text = "Enter Your Phone Number", style = MaterialTheme.typography.bodyMedium
            )
        },
        label = {
            Text(
                text = "Phone Number", style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingIcon = {
            CountryCodePicker(
                selectedCountry = country,
                countryList = Country.getAllCountries(),
                onCountrySelected = {
                    country = it
                    isNumberValid = validatePhoneNumber(
                        number = text, countryCode = it.countryCode
                    )
                },

                )

        },
        trailingIcon = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "drop down icon"
                )
            }
        },
        isError = !isNumberValid && text.isNotEmpty(),
        visualTransformation = CCPTransformer(context, country.countryIso),

        )

}


@Composable
fun ShowCountryCodePicker() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = "Country Code Picker",
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 10.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        var country by remember {
            mutableStateOf(Country.Argentina)
        }

        if (!LocalInspectionMode.current) {
            CCPUtils.getCountryAutomatically(context = LocalContext.current).let {
                it?.let {
                    country = it
                }
            }
        }

        CountryCodePicker(
            modifier = Modifier.fillMaxWidth(1f),
            selectedCountry = country,
            onCountrySelected = { country = it },
            viewCustomization = ViewCustomization(
                showFlag = true,
                showCountryIso = true,
                showCountryName = true,
                showCountryCode = true,
                clipToFull = true
            ),
            pickerCustomization = PickerCustomization(
                showFlag = false,
            ),
            showSheet = true,
        )


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CCPCSampleTheme {
        ShowMainScreen()
    }
}