package com.rejowan.ccpc

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CountryUI(
    country: Country,
    onCountryClicked: () -> Unit,
    showCountryFlag: Boolean = true,
    showCountryIso: Boolean = false,
    showCountryCode: Boolean = true,
    countryTextStyle: TextStyle,
    itemPadding: Int = 10

) {

    Row(
        Modifier
            .clickable(onClick = { onCountryClicked() })
            .padding(itemPadding.dp, (itemPadding * 1.5).dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically

    ) {

        val countryString = if (showCountryFlag && showCountryIso) {
            (getEmojiFlag(country.countryIso)) + "  " + country.countryName + "  (" + country.countryIso + ")"
        } else if (showCountryFlag) {
            (getEmojiFlag(country.countryIso)) + "  " + country.countryName
        } else if (showCountryIso) {
            country.countryName + "  (" + country.countryIso + ")"
        } else {
            country.countryName
        }

        Text(
            text = countryString,
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp),
            style = countryTextStyle,
            overflow = TextOverflow.Ellipsis
        )

        if (showCountryCode) {
            Text(
                text = country.countryCode, style = countryTextStyle
            )
        }

    }


}


@Preview(showBackground = true)
@Composable
fun PreviewCountryUI() {
    CountryUI(
        country = Country.Bangladesh,
        onCountryClicked = {},
        showCountryFlag = true,
        showCountryIso = true,
        showCountryCode = true,
        countryTextStyle = TextStyle()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCountryUI2() {
    CountryUI(
        country = Country.Bangladesh,
        onCountryClicked = {},
        showCountryFlag = false,
        showCountryIso = false,
        showCountryCode = true,
        countryTextStyle = TextStyle()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCountryUI3() {
    CountryUI(
        country = Country.Bangladesh,
        onCountryClicked = {},
        showCountryFlag = true,
        showCountryIso = true,
        showCountryCode = false,
        countryTextStyle = TextStyle()
    )
}