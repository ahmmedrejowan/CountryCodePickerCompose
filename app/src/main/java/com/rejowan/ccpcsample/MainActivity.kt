package com.rejowan.ccpcsample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rejowan.ccpc.CountryCodePicker
import com.rejowan.ccpcsample.ui.theme.CCPCSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CCPCSampleTheme {
                Column {
                    CountryCodePicker(onCountrySelected = {
                        Log.e("CountryCodePicker", "Selected Country: ${it.countryName}")
                    }, showSheet = true)


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CCPCSampleTheme {

    }
}