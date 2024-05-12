package com.rejowan.ccpc

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rejowan.ccpc.CCPUtils.Companion.getEmojiFlag


@Composable
internal fun CountryHeaderDialog(
    title: String = "Select Country", onDismiss: () -> Unit
) {

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = title,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { onDismiss() }) {
            Icon(Icons.Outlined.Clear, contentDescription = "Close")

        }
    }


}

@Composable
internal fun CountryHeaderSheet(
    title: String = "Select Country"
) {

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        Text(
            text = title,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            textAlign = TextAlign.Center
        )


    }


}


@Composable
internal fun CountrySearch(
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    hint: String = "Search Country",
    showClearIcon: Boolean = true,
    requestFocus: Boolean = true,
    onFocusChanged: (FocusState) -> Unit = {}
) {

    val requester = remember {
        FocusRequester()
    }
    LaunchedEffect(Unit) {
        if (requestFocus) {
            requester.requestFocus()
        } else {
            requester.freeFocus()
        }
    }

    TextField(modifier = Modifier
        .fillMaxWidth()
        .focusRequester(requester)
        .onFocusChanged { onFocusChanged(it) },
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = textStyle,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(text = hint)
        },
        leadingIcon = {
            Icon(Icons.Outlined.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if (showClearIcon && value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(Icons.Outlined.Clear, contentDescription = "Clear")
                }
            }
        }

    )


}


@Composable
internal fun CountryUI(
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

@Composable
internal fun CountryView(
    country: Country,
    textStyle: TextStyle,
    showFlag: Boolean,
    showCountryIso: Boolean,
    showCountryName: Boolean,
    showCountryCode: Boolean,
    showArrow: Boolean,
    itemPadding: Int = 10,
    clipToFull : Boolean = false
) {

    Row(
        modifier = Modifier.padding(itemPadding.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        if (showFlag) {
            Text(
                text = getEmojiFlag(country.countryIso),
                modifier = Modifier.padding(start = 5.dp, end = 10.dp),
                style = textStyle
            )
        }

        if (showCountryName) {
            Text(
                text = country.countryName,
                modifier = Modifier.padding(end = 10.dp),
                style = textStyle
            )
        }

        if (showCountryIso) {
            Text(
                text = "(" + country.countryIso + ")",
                modifier = Modifier.padding(end = 20.dp),
                style = textStyle
            )
        }

        if (clipToFull){
            Spacer(modifier = Modifier.weight(1f))
        }


        if (showCountryCode) {
            Text(
                text = country.countryCode,
                modifier = Modifier.padding(end = 5.dp),
                style = textStyle
            )
        }

        if (showArrow) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
            )
        }


    }


}


/*

@Preview(showBackground = true)
@Composable
private fun CountryHeaderDialogPreview() {
    CountryHeaderDialog(onDismiss = { })
}
*/


/*
@Preview(showBackground = true)
@Composable
private fun CountryHeaderSheetPreview() {
    CountryHeaderSheet()
}
*/


/*
@Preview(showBackground = true)
@Composable
private fun CountrySearchPreview() {
    var value by remember { mutableStateOf("") }
    CountrySearch(value, {
        value = it
    }, showClearIcon = true)
}
*/


/*
@Preview(showBackground = true)
@Composable
private fun PreviewCountryUI() {
    CountryUI(
        country = Country.Bangladesh,
        onCountryClicked = {},
        showCountryFlag = true,
        showCountryIso = true,
        showCountryCode = true,
        countryTextStyle = TextStyle()
    )
}
*/

/*
@Preview(showBackground = true)
@Composable
private fun CountryViewPreview() {
    CountryView(
        country = Country.Bangladesh,
        textStyle = TextStyle(),
        showFlag = true,
        showCountryIso = true,
        showCountryName = true,
        showCountryCode = true,
        showArrow = true
    )
}
*/


