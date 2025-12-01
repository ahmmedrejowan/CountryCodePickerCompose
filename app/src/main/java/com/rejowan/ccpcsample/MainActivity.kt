package com.rejowan.ccpcsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rejowan.ccpc.*
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        // Header
        Text(
            text = "CountryCodePicker Demo",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        Divider()

        // Example 1: Basic Country Code Picker
        BasicCountryCodePicker()

        Divider()

        // Example 2: With Custom Strings (Backward Compatible)
        CustomStringExample()

        Divider()

        // Example 3: Integrated TextField
        ShowCountryCodePickerTextField()

        Divider()

        // Example 4: Attached to TextField (Manual)
        ShowCCPWithTextField()

        Divider()

        // Example 5: Phone Number Search Demo
        PhoneNumberSearchDemo()

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun BasicCountryCodePicker() {

    Column(Modifier.fillMaxWidth().padding(16.dp)) {

        Text(
            text = "1. Basic Country Code Picker",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Auto-detects device country, shows localized names in 19 languages",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        var country by remember {
            mutableStateOf(Country.Argentina)
        }

        if (!LocalInspectionMode.current) {
            CCPUtils.getCountryAutomatically(context = LocalContext.current)?.let {
                country = it
            }
        }

        // Full Screen Version
        Text(
            text = "Full Display:",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        CountryCodePicker(
            modifier = Modifier.fillMaxWidth(),
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
                showFlag = true,
            ),
            showSheet = true,
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Compact Version
        Text(
            text = "Compact Display:",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        CountryCodePicker(
            modifier = Modifier.align(Alignment.Start),
            selectedCountry = country,
            onCountrySelected = { country = it },
            viewCustomization = ViewCustomization(
                showFlag = true,
                showCountryIso = false,
                showCountryName = false,
                showCountryCode = true,
                clipToFull = false
            ),
            pickerCustomization = PickerCustomization(
                showFlag = true,
            ),
            showSheet = true,
        )
    }
}

@Composable
fun CustomStringExample() {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {

        Text(
            text = "2. Custom Strings (Backward Compatible)",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Pass custom strings directly without string resources",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        var country by remember { mutableStateOf(Country.UnitedStates) }

        CountryCodePicker(
            modifier = Modifier.fillMaxWidth(),
            selectedCountry = country,
            onCountrySelected = { country = it },
            viewCustomization = ViewCustomization(
                showFlag = true,
                showCountryName = true,
                showCountryCode = true,
            ),
            pickerCustomization = PickerCustomization(
                headerTitleText = "Choose Your Country 🌍",  // Direct string!
                searchHintText = "Type to search...",         // Direct string!
                showFlag = true,
            ),
            showSheet = false,  // Use Dialog instead of Sheet
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Selected: ${country.countryName} ${country.countryCode}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ShowCountryCodePickerTextField() {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {

        Text(
            text = "3. Integrated TextField",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Built-in validation and auto-formatting",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        var text by remember { mutableStateOf("") }
        var isValid by remember { mutableStateOf(false) }

        var country by remember {
            mutableStateOf(Country.Bangladesh)
        }

        if (!LocalInspectionMode.current) {
            CCPUtils.getCountryAutomatically(context = LocalContext.current)?.let {
                country = it
            }
        }

        CountryCodePickerTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            textStyle = MaterialTheme.typography.bodyMedium,
            trailingIcon = {
                IconButton(onClick = { text = "" }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear"
                    )
                }
            },
            label = {
                Text(
                    text = "Phone Number",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            showError = true,
            shape = RoundedCornerShape(10.dp),
            onValueChange = { _, value, valid ->
                text = value
                isValid = valid
            },
            number = text,
            showSheet = true,
            selectedCountry = country,
            itemPadding = 0,
            viewCustomization = ViewCustomization(
                showArrow = false,
                clipToFull = false
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (text.isNotEmpty()) {
            Text(
                text = if (isValid) "✓ Valid phone number" else "✗ Invalid phone number",
                style = MaterialTheme.typography.bodySmall,
                color = if (isValid) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun ShowCCPWithTextField() {

    Column(Modifier.fillMaxWidth().padding(16.dp)) {

        Text(
            text = "4. Manual TextField Integration",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Attach country picker to any TextField with manual validation",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )

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
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = "Enter Your Phone Number",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            label = {
                Text(
                    text = "Phone Number",
                    style = MaterialTheme.typography.bodyMedium
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
                IconButton(onClick = { /* Send action */ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send"
                    )
                }
            },
            isError = !isNumberValid && text.isNotEmpty(),
            visualTransformation = CCPTransformer(context, country.countryIso),
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (text.isNotEmpty()) {
            Text(
                text = "Full number: ${country.countryCode}$text",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun PhoneNumberSearchDemo() {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {

        Text(
            text = "5. Phone Number Search (New!)",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Search countries by typing phone numbers",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        val context = LocalContext.current
        var searchQuery by remember { mutableStateOf("") }
        var searchResults by remember { mutableStateOf<List<Country>>(emptyList()) }
        var foundCountry by remember { mutableStateOf<Country?>(null) }

        Column {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { query ->
                    searchQuery = query
                    if (query.isNotEmpty()) {
                        // Search using Context for localized names (optional)
                        searchResults = Country.searchCountry(query, context)
                        // Find single country (for phone numbers)
                        foundCountry = if (query.startsWith("+")) {
                            Country.findCountry(query, context)
                        } else null
                    } else {
                        searchResults = emptyList()
                        foundCountry = null
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Try: +1, +961, +12125551234") },
                placeholder = { Text("Type phone number or country name") },
                singleLine = true,
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (foundCountry != null && searchQuery.startsWith("+")) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(
                            text = "📞 Found from phone number:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${CCPUtils.getEmojiFlag(foundCountry!!.countryIso)} ${foundCountry!!.countryName}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${foundCountry!!.countryCode} • ${foundCountry!!.countryIso}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            } else if (searchResults.isNotEmpty()) {
                Text(
                    text = "Found ${searchResults.size} result(s):",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))

                searchResults.take(5).forEach { country ->
                    Text(
                        text = "${CCPUtils.getEmojiFlag(country.countryIso)} ${country.countryName} (${country.countryCode})",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }

                if (searchResults.size > 5) {
                    Text(
                        text = "... and ${searchResults.size - 5} more",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else if (searchQuery.isNotEmpty()) {
                Text(
                    text = "No results found",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Example queries
            Text(
                text = "Try these examples:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "• +1 (finds US, Canada, etc.)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "• +12125551234 (finds USA from full number)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "• +961 (finds Lebanon)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "• United (searches country names)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CCPCSampleTheme {
        ShowMainScreen()
    }
}
