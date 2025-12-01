package com.rejowan.ccpcsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.ui.unit.dp
import com.rejowan.ccpc.*
import com.rejowan.ccpcsample.ui.theme.CCPCSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            CCPCSampleTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        isDarkTheme = isDarkTheme,
                        onThemeToggle = { isDarkTheme = !isDarkTheme }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Country Code Picker",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    // Theme Toggle
                    Row(
                        modifier = Modifier.padding(end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = if (isDarkTheme) "🌙" else "☀️",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = { onThemeToggle() }
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Welcome Section
            WelcomeSection()

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // Example 1: Basic Picker Variants
            ExampleSection(
                title = "1. Basic Picker Variants",
                description = "Different display configurations"
            ) {
                BasicPickerVariants()
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // Example 2: Dialog vs Bottom Sheet
            ExampleSection(
                title = "2. Dialog vs Bottom Sheet",
                description = "Choose your preferred UI pattern"
            ) {
                DialogVsBottomSheet()
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // Example 3: Integrated TextField
            ExampleSection(
                title = "3. Integrated TextField",
                description = "All-in-one solution with validation"
            ) {
                IntegratedTextFieldExample()
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // Example 4: Custom Styled Picker
            ExampleSection(
                title = "4. Custom Styled TextField",
                description = "Manual integration with your design"
            ) {
                CustomStyledPicker()
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // Example 5: Compact Pickers
            ExampleSection(
                title = "5. Compact Layouts",
                description = "Space-efficient variants"
            ) {
                CompactLayouts()
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // Example 6: Custom Strings
            ExampleSection(
                title = "6. Custom Strings & Localization",
                description = "Override default texts"
            ) {
                CustomStringsExample()
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // Example 7: Search & Validation
            ExampleSection(
                title = "7. Search & Validation Features",
                description = "Advanced phone number handling"
            ) {
                SearchValidationExample()
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun WelcomeSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🌍 CountryCodePicker Demo",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Material 3 Design • 19 Languages • Phone Validation",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ExampleSection(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Section Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            TextButton(onClick = { expanded = !expanded }) {
                Text(if (expanded) "Hide" else "Show")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Expandable Content
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            content()
        }
    }
}

@Composable
fun BasicPickerVariants() {
    val context = LocalContext.current
    val isInspectionMode = LocalInspectionMode.current

    var selectedCountry by remember(context, isInspectionMode) {
        mutableStateOf(
            if (!isInspectionMode) {
                CCPUtils.getCountryAutomatically(context) ?: Country.UnitedStates
            } else {
                Country.UnitedStates
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Full Display
        ExampleCard("Full Display - All Information") {
            CountryCodePicker(
                modifier = Modifier.fillMaxWidth(),
                selectedCountry = selectedCountry,
                onCountrySelected = { selectedCountry = it },
                viewCustomization = ViewCustomization(
                    showFlag = true,
                    showCountryIso = true,
                    showCountryName = true,
                    showCountryCode = true,
                    showArrow = true,
                    clipToFull = true
                ),
                pickerCustomization = PickerCustomization(
                    showFlag = true,
                ),
                showSheet = true
            )
        }

        // Flag + Code Only
        ExampleCard("Flag + Country Code") {
            CountryCodePicker(
                modifier = Modifier.wrapContentWidth(),
                selectedCountry = selectedCountry,
                onCountrySelected = { selectedCountry = it },
                viewCustomization = ViewCustomization(
                    showFlag = true,
                    showCountryIso = false,
                    showCountryName = false,
                    showCountryCode = true,
                    showArrow = true,
                    clipToFull = false
                ),
                showSheet = true
            )
        }

        // Code Only
        ExampleCard("Code Only (Minimal)") {
            CountryCodePicker(
                modifier = Modifier.wrapContentWidth(),
                selectedCountry = selectedCountry,
                onCountrySelected = { selectedCountry = it },
                viewCustomization = ViewCustomization(
                    showFlag = false,
                    showCountryIso = false,
                    showCountryName = false,
                    showCountryCode = true,
                    showArrow = true,
                    clipToFull = false
                ),
                showSheet = true
            )
        }

        ResultDisplay(selectedCountry)
    }
}

@Composable
fun DialogVsBottomSheet() {
    var dialogCountry by remember { mutableStateOf(Country.UnitedKingdom) }
    var sheetCountry by remember { mutableStateOf(Country.Canada) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Dialog Version
        ExampleCard("Dialog Picker") {
            CountryCodePicker(
                modifier = Modifier.fillMaxWidth(),
                selectedCountry = dialogCountry,
                onCountrySelected = { dialogCountry = it },
                viewCustomization = ViewCustomization(
                    showFlag = true,
                    showCountryName = true,
                    showCountryCode = true,
                ),
                showSheet = false  // Dialog mode
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Selected: ${dialogCountry.countryName}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

        // Bottom Sheet Version
        ExampleCard("Bottom Sheet Picker") {
            CountryCodePicker(
                modifier = Modifier.fillMaxWidth(),
                selectedCountry = sheetCountry,
                onCountrySelected = { sheetCountry = it },
                viewCustomization = ViewCustomization(
                    showFlag = true,
                    showCountryName = true,
                    showCountryCode = true,
                ),
                showSheet = true  // Bottom sheet mode
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Selected: ${sheetCountry.countryName}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun IntegratedTextFieldExample() {
    val context = LocalContext.current
    val isInspectionMode = LocalInspectionMode.current

    var phoneNumber by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(false) }
    var selectedCountry by remember(context, isInspectionMode) {
        mutableStateOf(
            if (!isInspectionMode) {
                CCPUtils.getCountryAutomatically(context) ?: Country.UnitedStates
            } else {
                Country.UnitedStates
            }
        )
    }

    ExampleCard("Built-in Validation & Formatting") {
        CountryCodePickerTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            textStyle = MaterialTheme.typography.bodyLarge,
            trailingIcon = {
                if (phoneNumber.isNotEmpty()) {
                    IconButton(onClick = { phoneNumber = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            },
            label = {
                Text(
                    text = "Phone Number",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            showError = true,
            shape = RoundedCornerShape(12.dp),
            onValueChange = { countryCode, value, valid ->
                phoneNumber = value
                isValid = valid
            },
            number = phoneNumber,
            showSheet = true,
            selectedCountry = selectedCountry,
            viewCustomization = ViewCustomization(
                showArrow = false,
                clipToFull = false
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (phoneNumber.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isValid)
                        MaterialTheme.colorScheme.secondaryContainer
                    else
                        MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isValid) "✓ Valid Phone Number" else "✗ Invalid Phone Number",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isValid)
                                MaterialTheme.colorScheme.onSecondaryContainer
                            else
                                MaterialTheme.colorScheme.onErrorContainer
                        )

                        Text(
                            text = "Full: ${selectedCountry.countryCode}$phoneNumber",
                            style = MaterialTheme.typography.bodySmall,
                            color = if (isValid)
                                MaterialTheme.colorScheme.onSecondaryContainer
                            else
                                MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomStyledPicker() {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf(Country.Australia) }

    val validator = remember(context) { CCPValidator(context) }
    var isValid by rememberSaveable(selectedCountry, phoneNumber) {
        mutableStateOf(
            validator(
                number = phoneNumber,
                countryCode = selectedCountry.countryCode
            )
        )
    }

    ExampleCard("Custom OutlinedTextField Integration") {
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
                isValid = validator(
                    number = it,
                    countryCode = selectedCountry.countryCode
                )
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            label = {
                Text("Mobile Number")
            },
            placeholder = {
                Text("Enter phone number")
            },
            leadingIcon = {
                CountryCodePicker(
                    selectedCountry = selectedCountry,
                    onCountrySelected = {
                        selectedCountry = it
                        isValid = validator(
                            number = phoneNumber,
                            countryCode = it.countryCode
                        )
                    },
                    viewCustomization = ViewCustomization(
                        showFlag = true,
                        showCountryCode = true,
                        showCountryName = false,
                        showCountryIso = false,
                        showArrow = true,
                        clipToFull = false
                    ),
                    showSheet = false  // Use dialog
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { /* Send action */ },
                    enabled = isValid && phoneNumber.isNotEmpty()
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send",
                        tint = if (isValid && phoneNumber.isNotEmpty())
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    )
                }
            },
            isError = !isValid && phoneNumber.isNotEmpty(),
            supportingText = {
                if (phoneNumber.isNotEmpty()) {
                    Text(
                        text = if (isValid) "Valid number" else "Invalid phone number for ${selectedCountry.countryName}",
                        color = if (isValid)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.error
                    )
                }
            },
            visualTransformation = CCPTransformer(context, selectedCountry.countryIso),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            )
        )
    }
}

@Composable
fun CompactLayouts() {
    var country1 by remember { mutableStateOf(Country.Japan) }
    var country2 by remember { mutableStateOf(Country.Germany) }
    var country3 by remember { mutableStateOf(Country.Brazil) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExampleCard("Flag + Code (Compact)") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CountryCodePicker(
                    selectedCountry = country1,
                    onCountrySelected = { country1 = it },
                    viewCustomization = ViewCustomization(
                        showFlag = true,
                        showCountryCode = true,
                        showCountryName = false,
                        showCountryIso = false,
                        showArrow = true,
                        clipToFull = false
                    ),
                    showSheet = true
                )

                Text(
                    text = country1.countryName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        ExampleCard("Code Only (Ultra Compact)") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CountryCodePicker(
                    selectedCountry = country2,
                    onCountrySelected = { country2 = it },
                    viewCustomization = ViewCustomization(
                        showFlag = false,
                        showCountryCode = true,
                        showCountryName = false,
                        showCountryIso = false,
                        showArrow = true,
                        clipToFull = false
                    ),
                    showSheet = true
                )

                Text(
                    text = "${CCPUtils.getEmojiFlag(country2.countryIso)} ${country2.countryName}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        ExampleCard("Flag Only") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CountryCodePicker(
                    selectedCountry = country3,
                    onCountrySelected = { country3 = it },
                    viewCustomization = ViewCustomization(
                        showFlag = true,
                        showCountryCode = false,
                        showCountryName = false,
                        showCountryIso = false,
                        showArrow = true,
                        clipToFull = false
                    ),
                    showSheet = false
                )

                Text(
                    text = "${country3.countryName} (${country3.countryCode})",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun CustomStringsExample() {
    var selectedCountry by remember { mutableStateOf(Country.France) }

    ExampleCard("Custom Header & Search Text") {
        CountryCodePicker(
            modifier = Modifier.fillMaxWidth(),
            selectedCountry = selectedCountry,
            onCountrySelected = { selectedCountry = it },
            viewCustomization = ViewCustomization(
                showFlag = true,
                showCountryName = true,
                showCountryCode = true,
            ),
            pickerCustomization = PickerCustomization(
                headerTitleText = "🌍 Choose Your Country",
                searchHintText = "Type to search countries...",
                showFlag = true,
                showSearchClearIcon = true
            ),
            showSheet = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        ResultDisplay(selectedCountry)
    }
}

@Composable
fun SearchValidationExample() {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<Country>>(emptyList()) }
    var foundCountry by remember { mutableStateOf<Country?>(null) }

    ExampleCard("Phone Number Search") {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                if (query.isNotEmpty()) {
                    searchResults = Country.searchCountry(query, context)
                    foundCountry = if (query.startsWith("+")) {
                        Country.findCountry(query, context)
                    } else null
                } else {
                    searchResults = emptyList()
                    foundCountry = null
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search Countries") },
            placeholder = { Text("Try: +1, +44, +91, or 'United'") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = {
                        searchQuery = ""
                        searchResults = emptyList()
                        foundCountry = null
                    }) {
                        Icon(Icons.Default.Clear, "Clear")
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Results Display
        if (foundCountry != null && searchQuery.startsWith("+")) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = "📞 Found from phone number:",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${CCPUtils.getEmojiFlag(foundCountry!!.countryIso)} ${foundCountry!!.countryName}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )

                    Text(
                        text = "${foundCountry!!.countryCode} • ${foundCountry!!.countryIso}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
        } else if (searchResults.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = "Found ${searchResults.size} result${if (searchResults.size != 1) "s" else ""}:",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    searchResults.take(5).forEach { country ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${CCPUtils.getEmojiFlag(country.countryIso)} ${country.countryName}",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )

                            Text(
                                text = country.countryCode,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    if (searchResults.size > 5) {
                        Text(
                            text = "... and ${searchResults.size - 5} more",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        } else if (searchQuery.isNotEmpty()) {
            Text(
                text = "No results found",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (searchQuery.isEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "💡 Try these examples:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold
            )

            Column(modifier = Modifier.padding(start = 8.dp, top = 4.dp)) {
                listOf(
                    "+1 → US, Canada",
                    "+44 → United Kingdom",
                    "+91 → India",
                    "United → Search by name"
                ).forEach { example ->
                    Text(
                        text = "• $example",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ExampleCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            content()
        }
    }
}

@Composable
fun ResultDisplay(country: Country) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Selected:",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "${CCPUtils.getEmojiFlag(country.countryIso)} ${country.countryName} (${country.countryCode})",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}
