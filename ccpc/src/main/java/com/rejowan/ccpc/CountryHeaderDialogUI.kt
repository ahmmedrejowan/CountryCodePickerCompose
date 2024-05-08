package com.rejowan.ccpc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CountryHeaderDialog(
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
fun CountryHeaderSheet(
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


@Preview(showBackground = true)
@Composable
fun CountryHeaderDialogPreview() {
    CountryHeaderDialog(onDismiss = { })
}

@Preview(showBackground = true)
@Composable
fun CountryHeaderSheetPreview() {
    CountryHeaderSheet()
}