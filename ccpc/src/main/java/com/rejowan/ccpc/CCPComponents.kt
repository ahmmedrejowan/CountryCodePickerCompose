package com.rejowan.ccpc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rejowan.ccpc.CCPUtils.Companion.getEmojiFlag


@Composable
internal fun CountryHeaderDialog(
    title: String = "Select Country", onDismiss: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),  // Material 3 spacing
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,  // Material 3 typography scale
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSurface
        )

        IconButton(onClick = { onDismiss() }) {
            Icon(
                Icons.Outlined.Clear,
                contentDescription = "Close dialog",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }


}

@Composable
internal fun CountryHeaderSheet(
    title: String = "Select Country"
) {

    // Material 3: Bottom sheet headers should be simple with proper padding
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,  // Material 3 typography scale
        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp),
        color = MaterialTheme.colorScheme.onSurface
    )


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

    // Material 3 search field with proper padding and styling
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)  // Material 3 content margins
            .focusRequester(requester)
            .onFocusChanged { onFocusChanged(it) },
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge,  // Material 3 typography
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        leadingIcon = {
            Icon(
                Icons.Outlined.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingIcon = {
            // Material 3: Animated clear button
            AnimatedVisibility(
                visible = showClearIcon && value.isNotEmpty(),
                enter = fadeIn(animationSpec = tween(200)) + scaleIn(animationSpec = tween(200)),
                exit = fadeOut(animationSpec = tween(200)) + scaleOut(animationSpec = tween(200))
            ) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        Icons.Outlined.Clear,
                        contentDescription = "Clear search",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        shape = RoundedCornerShape(28.dp),  // Material 3 search bar shape
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            capitalization = KeyboardCapitalization.Words
        )
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
    itemPadding: Int = 10,
    isSelected: Boolean = false  // New parameter for selection state

) {

    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)  // Material 3 list item minimum height
            .background(
                if (isSelected) MaterialTheme.colorScheme.secondaryContainer
                else Color.Transparent
            )
            .clickable(
                onClickLabel = "Select ${country.countryName}",
                role = Role.Button,
                onClick = { onCountryClicked() }
            )
            .padding(horizontal = 24.dp, vertical = 12.dp)  // Material 3 spacing
            .semantics {
                contentDescription = "${country.countryName}, country code ${country.countryCode}${if (isSelected) ", selected" else ""}"
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically

    ) {

        val countryString = if (showCountryFlag && showCountryIso) {
            (getEmojiFlag(country.countryIso)) + "  " + country.getLocalisedName() + "  (" + country.countryIso + ")"
        } else if (showCountryFlag) {
            (getEmojiFlag(country.countryIso)) + "  " + country.getLocalisedName()
        } else if (showCountryIso) {
            country.getLocalisedName() + "  (" + country.countryIso + ")"
        } else {
            country.getLocalisedName()
        }

        Text(
            text = countryString,
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp),
            style = countryTextStyle,
            color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis
        )

        if (showCountryCode) {
            Text(
                text = country.countryCode,
                style = countryTextStyle,
                color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (isSelected) {
            Spacer(Modifier.width(12.dp))
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(24.dp)
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
    clipToFull: Boolean = false
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
                text = country.getLocalisedName(),
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

        if (clipToFull) {
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


