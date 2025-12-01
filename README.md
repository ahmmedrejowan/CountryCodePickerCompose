<p align="center">
<img src="https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/logo.png" width="300px" align="center"/></p>
<h1 align="center">CountryCodePickerCompose</h1>
<h3 align="center">A Modern Country Code Picker for Jetpack Compose<b></b></h3>

<p align="center">
	<a href="https://www.android.com">
		<img src="https://img.shields.io/badge/platform-Android-yellow.svg" alt="platform">
	</a>
	<a href="https://android-arsenal.com/api?level=21">
		<img src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat" alt="API">
	</a>
	<a href="https://jitpack.io/#ahmmedrejowan/CountryCodePickerCompose">
		<img src="https://jitpack.io/v/ahmmedrejowan/CountryCodePickerCompose.svg" alt="JitPack">
	</a>
	<a href="https://github.com/ahmmedrejowan/CountryCodePickerCompose/blob/master/LICENSE">
		<img src="https://img.shields.io/github/license/ahmmedrejowan/CountryCodePickerCompose" alt="GitHub license">
	</a>
</p>

 <p align="center"> <a href="https://github.com/ahmmedrejowan/CountryCodePickerCompose/issues"><img src="https://img.shields.io/github/issues/ahmmedrejowan/CountryCodePickerCompose" alt="GitHub issues"></a> <a href="https://github.com/ahmmedrejowan/CountryCodePickerCompose/fork"><img src="https://img.shields.io/github/forks/ahmmedrejowan/CountryCodePickerCompose" alt="GitHub forks"></a> <a href="https://github.com/ahmmedrejowan/CountryCodePickerCompose/stargazers"><img src="https://img.shields.io/github/stars/ahmmedrejowan/CountryCodePickerCompose" alt="GitHub stars"></a> <a href="https://github.com/ahmmedrejowan/CountryCodePickerCompose/graphs/contributors"> <img src="https://img.shields.io/github/contributors/ahmmedrejowan/CountryCodePickerCompose" alt="GitHub contributors"></a>   </p>


> [!NOTE]
> Full tutorial on this library on YouTube. You can check it
> out [here](https://www.youtube.com/playlist?list=PLmZDG9uYrxfMgCw8LqSuWrz2ormZdPiNz)

## 🎉 What's New in v0.2.0

Version 0.2.0 brings **major UI/UX improvements** along with critical bug fixes and enhanced stability:

### 🎨 **Material 3 Design Overhaul**
- ✅ **Complete Material 3 Compliance**: Proper elevation (6dp dialogs, 1dp bottom sheets), 28dp rounded corners, semantic color tokens
- ✅ **Modern Typography**: Full Material 3 typography scale with proper line heights and letter spacing
- ✅ **Enhanced Visual Feedback**: Selected country highlighting with check icons and background tinting
- ✅ **Improved Search**: Material 3 OutlinedTextField with search result count and better spacing
- ✅ **Bottom Sheet Enhancements**: Drag handles, optimized height, proper keyboard handling
- ✅ **Empty States**: Beautiful empty state UI when no search results found
- ✅ **Better Spacing**: Consistent 8dp grid system, 56dp minimum touch targets, proper padding throughout

### 🐛 **Critical Bug Fixes**
- ✅ **Fixed Critical Bugs**: All GitHub issues (#1, #2, #3) resolved
- ✅ **Keyboard Handling**: Bottom sheet content no longer hidden by keyboard
- ✅ **VisualTransformation Crashes**: Completely rewrote offset mapping logic with bounds checking
- ✅ **API Compatibility**: Works with Compose BOM 2024.12.01+ and Material 3 latest versions

### 🆙 **Updated Dependencies**
- ✅ **Kotlin 2.1.0**: Latest stable Kotlin with improved Compose compiler
- ✅ **Compose BOM 2024.12.01**: Latest Jetpack Compose libraries
- ✅ **Target SDK 35**: Updated for Android 15 support
- ✅ **19 Language Support**: Country names available in 19 languages

### 📱 **Enhanced Sample App**
- ✅ **7 Comprehensive Examples**: From basic usage to advanced validation
- ✅ **Dark/Light Mode Toggle**: Real-time theme switching
- ✅ **15+ Usage Patterns**: Complete showcase of library features
- ✅ **Interactive Demos**: Phone number search, validation, and formatting examples

### 🛡️ **Better Development Experience**
- ✅ **ProGuard Rules**: Automatic R8/ProGuard support for release builds
- ✅ **Comprehensive KDoc**: Complete API documentation with usage examples
- ✅ **Input Validation**: Clear error messages for invalid parameters
- ✅ **38 Unit Tests**: 100% passing test coverage with CI/CD pipeline

**Migration from 0.1.x**: No breaking changes! Just update your dependencies. See the [Migration Guide](#-migration-from-01x-to-020) below.

## Table of Contents

- [Purpose](#purpose)
- [Features](#features)
- [Demo](#demo)
- [Prerequisites](#prerequisites)
- [Dependency](#dependency)
- [Usage](#usage)
  - [CountryCodePicker](#countrycodepicker)
  - [CountryCodePickerTextField](#countrycodepickertextfield)
  - [CountryPickerDialog](#countrypickerdialog)
  - [CountryPickerBottomSheet](#countrypickerbottomsheet)
- [Customization](#customization)
  - [ViewCustomization](#viewcustomization)
  - [PickerCustomization](#pickercustomization)
- [Utility Functions](#utility-functions)
  - [Automatic Country Detection](#automatic-country-detection)
  - [Phone Number Validation](#phone-number-validation)
  - [Visual Transformation](#visual-transformation)
  - [Phone Number Search](#phone-number-search)
- [Migration from 0.1.x to 0.2.0](#-migration-from-01x-to-020)
- [Sample App Examples](#sample-app-examples)
- [Notes](#notes)
- [Contribute](#contribute)
- [License](#license)

## Purpose

In various apps, we need to use a country code picker. There are several libraries available for this purpose. In XML we have a CCP created by hbb20, which is great. In Jetpack Compose there are few libraries but they lack some features and updates. So, I created this library to use in Jetpack Compose.

**CountryCodePickerCompose** is:
- ✨ **Modern**: Built with Jetpack Compose and Material 3 design
- 🎨 **Beautiful**: Follows Material Design 3 guidelines with proper elevation, spacing, and animations
- 🔧 **Customizable**: Highly configurable with multiple usage patterns
- 🌍 **International**: 19 language translations for country names
- 📱 **Flexible**: 4 different usage modes (View, TextField, Dialog, BottomSheet)
- 🛡️ **Reliable**: Comprehensive testing, proper error handling, production-ready

## Features

- **Minimal & Lightweight**: Optimized for performance with efficient code
- **Emoji Flags**: No image assets needed, uses native emoji flags
- **4 Usage Modes**:
  - As a View (Full Screen, Compact, or Attached to TextField)
  - As a TextField (Integrated with OutlinedTextField)
  - As a Dialog (Picker dialog overlay)
  - As a BottomSheet (Material 3 bottom sheet)
- **Automatic Detection**: Detects user's country based on device settings
- **Phone Validation**: Built-in phone number validation using libphonenumber
- **Visual Transformation**: Automatic phone number formatting as you type
- **Advanced Search**: Search by country name, code, or phone number (e.g., "+12125551234")
- **Material 3 Design**: Complete Material You design system compliance
- **19 Languages**: Country names available in multiple languages
- **Dark Mode**: Full dark mode support with proper contrast
- **Highly Customizable**: Extensive customization options for all components

## Demo

| Different Use Cases                                                                                       | Country Picker Dialog                                                                                     | Picker Bottom Sheet                                                                                       |
|-----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| ![Shot1](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot1.jpg) | ![Shot2](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot2.jpg) | ![Shot3](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot3.jpg) |
| ![Shot4](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot4.jpg) | ![Shot5](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot5.jpg) | ![Shot6](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot6.jpg) |

You can download the test apk to try out all features - [Download APK](https://github.com/ahmmedrejowan/CountryCodePickerCompose/raw/master/app/release/app-release.apk)

## Prerequisites

### Kotlin DSL

``` kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
```

## Dependency

Add this to your module's `build.gradle.kts` file (latest version <a href="https://jitpack.io/#ahmmedrejowan/CountryCodePickerCompose"><img src="https://jitpack.io/v/ahmmedrejowan/CountryCodePickerCompose.svg" alt="JitPack"></a>):

### Kotlin DSL
``` kotlin
dependencies {
    implementation("com.github.ahmmedrejowan:CountryCodePickerCompose:0.2.0")
}
```
## Usage

There are 4 different ways to use this library:

1. **CountryCodePicker** - Regular composable (can be used anywhere, attached to TextField)
2. **CountryCodePickerTextField** - All-in-one TextField with integrated picker
3. **CountryPickerDialog** - Standalone picker dialog
4. **CountryPickerBottomSheet** - Material 3 bottom sheet picker

### CountryCodePicker

A flexible composable that can be used in multiple ways - as a standalone picker, attached to a TextField, or in custom layouts.

**Basic Usage:**
``` kotlin
var selectedCountry by remember { mutableStateOf(Country.UnitedStates) }

CountryCodePicker(
    selectedCountry = selectedCountry,
    onCountrySelected = { selectedCountry = it },
    modifier = Modifier.fillMaxWidth()
)
```

**Compact Mode (Flag + Code Only):**
``` kotlin
CountryCodePicker(
    selectedCountry = selectedCountry,
    onCountrySelected = { selectedCountry = it },
    viewCustomization = ViewCustomization(
        showFlag = true,
        showCountryIso = false,
        showCountryName = false,
        showCountryCode = true,
        showArrow = true
    ),
    showSheet = true  // Use bottom sheet (false for dialog)
)
```

**Key Parameters:**
- `selectedCountry: Country` - Currently selected country
- `onCountrySelected: (Country) -> Unit` - Callback when country is selected
- `showSheet: Boolean` - true for bottom sheet, false for dialog (default: false)
- `viewCustomization: ViewCustomization` - Customize the picker button appearance
- `pickerCustomization: PickerCustomization` - Customize the picker dialog/sheet

### CountryCodePickerTextField

An all-in-one solution with integrated TextField, validation, and formatting.

**Basic Usage:**
``` kotlin
var phoneNumber by remember { mutableStateOf("") }
var selectedCountry by remember { mutableStateOf(Country.UnitedStates) }

CountryCodePickerTextField(
    number = phoneNumber,
    onValueChange = { country, number, isValid ->
        selectedCountry = country
        phoneNumber = number
    },
    selectedCountry = selectedCountry,
    modifier = Modifier.fillMaxWidth(),
    label = { Text("Phone Number") },
    showError = true  // Show validation errors
)
```

**With Custom Styling:**
``` kotlin
CountryCodePickerTextField(
    number = phoneNumber,
    onValueChange = { country, number, isValid ->
        selectedCountry = country
        phoneNumber = number
    },
    selectedCountry = selectedCountry,
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    textStyle = MaterialTheme.typography.bodyLarge,
    label = { Text("Enter Phone Number") },
    trailingIcon = {
        if (phoneNumber.isNotEmpty()) {
            IconButton(onClick = { phoneNumber = "" }) {
                Icon(Icons.Default.Clear, "Clear")
            }
        }
    },
    showError = true,
    showSheet = true
)
```

**Key Features:**
- Automatic phone number formatting as you type
- Real-time validation with visual feedback
- Integrated country picker (dialog or bottom sheet)
- Customizable styling and icons

### CountryPickerDialog

A standalone picker dialog for manual integration.

**Usage:**
``` kotlin
var selectedCountry by remember { mutableStateOf(Country.UnitedStates) }
var isDialogOpen by remember { mutableStateOf(false) }

// Your UI element to trigger the dialog
Button(onClick = { isDialogOpen = true }) {
    Text("Select Country")
}

// Dialog
if (isDialogOpen) {
    CountryPickerDialog(
        onDismissRequest = { isDialogOpen = false },
        onItemClicked = { country ->
            selectedCountry = country
            isDialogOpen = false
        },
        selectedCountry = selectedCountry,
        pickerCustomization = PickerCustomization(
            showCountryCode = true,
            showFlag = true
        )
    )
}
```

### CountryPickerBottomSheet

A Material 3 bottom sheet picker with proper drag handle and keyboard support.

**Usage:**
``` kotlin
var selectedCountry by remember { mutableStateOf(Country.UnitedStates) }
var isSheetOpen by remember { mutableStateOf(false) }

// Your UI element to trigger the sheet
Button(onClick = { isSheetOpen = true }) {
    Text("Select Country")
}

// Bottom Sheet
if (isSheetOpen) {
    CountryPickerBottomSheet(
        onDismissRequest = { isSheetOpen = false },
        onItemClicked = { country ->
            selectedCountry = country
            isSheetOpen = false
        },
        selectedCountry = selectedCountry,
        pickerCustomization = PickerCustomization(
            showCountryCode = true,
            showFlag = true
        )
    )
}
```

**Features:**
- Material 3 drag handle
- Proper keyboard handling (content stays visible)
- Smooth animations
- Optimized height (92% of screen)

## Customization

### ViewCustomization

Customize how the picker button/view appears:

``` kotlin
data class ViewCustomization(
    var showFlag: Boolean = true,          // Show country flag emoji
    var showCountryIso: Boolean = false,   // Show country ISO code (e.g., "US")
    var showCountryName: Boolean = false,  // Show country name
    var showCountryCode: Boolean = true,   // Show country code (e.g., "+1")
    var showArrow: Boolean = true,         // Show dropdown arrow
    var clipToFull: Boolean = false        // Clip content to prevent overflow
)
```

**Examples:**
``` kotlin
// Full display: Flag + Name + (ISO) + Code
ViewCustomization(
    showFlag = true,
    showCountryIso = true,
    showCountryName = true,
    showCountryCode = true
)

// Compact: Flag + Code only
ViewCustomization(
    showFlag = true,
    showCountryCode = true,
    showCountryName = false,
    showCountryIso = false
)

// Minimal: Code only
ViewCustomization(
    showFlag = false,
    showCountryCode = true,
    showArrow = true
)
```

### PickerCustomization

Customize the picker dialog/bottom sheet appearance:

``` kotlin
data class PickerCustomization(
    var itemPadding: Int = 10,                           // Padding for list items
    var showSearchClearIcon: Boolean = true,             // Show clear button in search
    var showCountryCode: Boolean = true,                 // Show codes in list
    var showFlag: Boolean = true,                        // Show flags in list
    var showCountryIso: Boolean = false,                 // Show ISO codes in list

    // Custom text strings (can use string resources or direct strings)
    var headerTitle: Int = R.string.select_country,      // Dialog/sheet header
    var headerTitleText: String? = null,                 // Or use direct string
    var searchHint: Int = R.string.search_country,       // Search field hint
    var searchHintText: String? = null                   // Or use direct string
)
```

**Examples:**
``` kotlin
// Custom strings for localization
PickerCustomization(
    headerTitleText = "Sélectionner un pays",  // French
    searchHintText = "Rechercher",
    showCountryCode = true
)

// Minimal picker with codes only
PickerCustomization(
    showFlag = false,
    showCountryCode = true,
    showCountryIso = true
)
```

## Utility Functions

### Automatic Country Detection

Automatically detect the user's country based on their device settings:

``` kotlin
var selectedCountry by remember { mutableStateOf(Country.UnitedStates) }

// Detect country on first composition
LaunchedEffect(Unit) {
    CCPUtils.getCountryAutomatically(context = LocalContext.current)?.let {
        selectedCountry = it
    }
}

// Or use in non-preview mode
if (!LocalInspectionMode.current) {
    CCPUtils.getCountryAutomatically(context = LocalContext.current)?.let {
        selectedCountry = it
    }
}
```

**How it works:**
1. First tries to detect from SIM card (requires READ_PHONE_STATE permission)
2. Falls back to device locale if SIM detection fails
3. Returns null if country cannot be determined

### Phone Number Validation

Validate phone numbers using Google's libphonenumber library:

``` kotlin
val context = LocalContext.current
val validator = remember(context) { CCPValidator(context = context) }

var phoneNumber by remember { mutableStateOf("") }
var isValid by remember { mutableStateOf(false) }

OutlinedTextField(
    value = phoneNumber,
    onValueChange = { newNumber ->
        phoneNumber = newNumber
        isValid = validator(
            number = newNumber,
            countryCode = selectedCountry.countryCode
        )
    },
    isError = phoneNumber.isNotEmpty() && !isValid,
    label = { Text(if (isValid) "Valid ✓" else "Phone Number") }
)
```

**Features:**
- Real-time validation as user types
- Country-specific number format checking
- Returns true for valid numbers, false otherwise

### Visual Transformation

Automatically format phone numbers as the user types:

``` kotlin
OutlinedTextField(
    value = phoneNumber,
    onValueChange = { phoneNumber = it },
    visualTransformation = CCPTransformer(
        context = LocalContext.current,
        countryIso = selectedCountry.countryIso
    ),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
)
```

**What it does:**
- Formats numbers according to country-specific patterns
- Updates automatically when country changes
- Preserves underlying raw value
- Works with paste operations

**Note:** This is already integrated in `CountryCodePickerTextField`.

### Phone Number Search

Find countries by phone number:

``` kotlin
val context = LocalContext.current
var searchQuery by remember { mutableStateOf("") }
var foundCountry by remember { mutableStateOf<Country?>(null) }

OutlinedTextField(
    value = searchQuery,
    onValueChange = { query ->
        searchQuery = query
        // Find country from phone number
        foundCountry = if (query.startsWith("+")) {
            Country.findCountry(query, context)
        } else null
    },
    label = { Text("Enter phone number (e.g., +12125551234)") }
)

// Display result
foundCountry?.let { country ->
    Text("Country: ${country.countryName} (${country.countryCode})")
}
```

**Features:**
- Extracts country from phone number
- Works with full international numbers
- Returns null if country cannot be determined

## 📦 Migration from 0.1.x to 0.2.0

Good news! **Version 0.2.0 has NO breaking changes**. Your existing code will continue to work without modifications.

### What You Need to Update

**1. Update Dependencies:**

```kotlin
// In your build.gradle.kts (Project level)
plugins {
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0" apply false
}

// In your build.gradle.kts (Module level)
plugins {
    id("org.jetbrains.kotlin.plugin.compose")
}

dependencies {
    // Update the library version
    implementation("com.github.ahmmedrejowan:CountryCodePickerCompose:0.2.0")
}
```

**2. Update Kotlin and Compose (Recommended):**

```kotlin
// In your gradle/libs.versions.toml or build.gradle.kts
kotlin = "2.1.0"                      // Updated from 1.9.0
composeBom = "2024.12.01"             // Updated from 2024.05.00
```

**3. Remove Old Compiler Options (if you have them):**

```kotlin
// REMOVE this from your build.gradle.kts:
android {
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.x"  // Not needed with Kotlin 2.0+
    }
}
```

### What Works Automatically

✅ **All your existing code** - No API changes required
✅ **Your custom styles** - ViewCustomization and PickerCustomization remain the same
✅ **Your callbacks** - onCountrySelected, onValueChange work exactly as before
✅ **ProGuard/R8** - Rules are now automatically applied via consumer-rules.pro

### What's New (Optional to Use)

🆕 **Material 3 Improvements** - Your UI automatically looks better with no changes
🆕 **Better keyboard handling** - Bottom sheets work better with keyboard (automatic)
🆕 **19 Languages** - Country names support multiple languages (automatic)
🆕 **Phone number search** - Use `Country.findCountry(phoneNumber)` to find country from phone number
🆕 **Enhanced validation** - Better error handling (automatic)

### Testing Your Migration

1. Update dependencies
2. Clean and rebuild: `./gradlew clean build`
3. Test your app - everything should work as before
4. Optional: Update to use new features if desired

### Need Help?

If you encounter any issues during migration:
1. Check the [Changelog](CHANGELOG.md) for detailed changes
2. See the [Sample App](app/src/main/java/com/rejowan/ccpcsample/MainActivity.kt) for updated examples
3. Open an issue on [GitHub](https://github.com/ahmmedrejowan/CountryCodePickerCompose/issues)

## Sample App Examples

The included sample app demonstrates **7 comprehensive usage patterns** with **15+ examples**:

1. **Basic Picker Variants** - Full display, compact, minimal configurations
2. **Dialog vs Bottom Sheet** - Compare both UI patterns
3. **Integrated TextField** - All-in-one solution with validation
4. **Custom Styled TextField** - Manual integration with custom design
5. **Compact Layouts** - Space-efficient variants
6. **Custom Strings & Localization** - Override default texts
7. **Search & Validation Features** - Advanced phone number search and validation

**Features:**
- 🌓 Dark/Light mode toggle
- 🎨 Clean Material 3 design
- 📱 Real-time validation
- 🔍 Interactive search demos
- 📝 Copy-paste ready code

## Notes

- The library follows **Material Design 3** guidelines for consistent, modern UI
- **19 languages** are supported for country names (automatic based on device locale)
- Emoji flags are used instead of image assets for better performance
- The library uses **libphonenumber** for accurate phone number validation
- **ProGuard/R8 rules** are included automatically - no manual configuration needed
- Keyboard handling in bottom sheet is optimized to prevent content from being hidden
- All components support both **light and dark themes**
- The library is tested with **38 unit tests** (100% passing)

### Known Limitations

- Emoji flags may render differently across devices (depends on OS emoji support)
- Some older Android versions may not support all emoji flags properly
- Phone number validation requires Google Play Services on device

## Inspiration and Credit

- Inspired by [CountryCodePickerProject](https://github.com/hbb20/CountryCodePickerProject) by [hbb20](https://github.com/hbb20/)
- Inspired by [jetpack_compose_country_code_picker](https://github.com/togisoft/jetpack_compose_country_code_picker) by [togisoft](https://github.com/togisoft)
- Inspired by [jetpack_compose_country_code_picker_emoji](https://github.com/jump-sdk/jetpack_compose_country_code_picker_emoji) by [jump-sdk](https://github.com/jump-sdk/)
- Credit to [libphonenumber-android](https://github.com/MichaelRocks/libphonenumber-android) by [MichaelRocks](https://github.com/MichaelRocks/)
- Special thanks to [@Sparks1998](https://github.com/Sparks1998) for the 19-language localization contribution

## Contribute

Please fork this repository and contribute back using [pull requests](https://github.com/ahmmedrejowan/CountryCodePickerCompose/pulls).

**Ways to contribute:**
- 🐛 Report bugs and issues
- 💡 Suggest new features
- 🌍 Add translations for more languages
- 📝 Improve documentation
- ✨ Submit code improvements

**Before contributing:**
1. Check existing [issues](https://github.com/ahmmedrejowan/CountryCodePickerCompose/issues) and [pull requests](https://github.com/ahmmedrejowan/CountryCodePickerCompose/pulls)
2. Run the test suite: `./gradlew test`
3. Follow the existing code style
4. Add tests for new features

If this project helps you, give it a ⭐ Star on GitHub!

## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

```
Copyright 2024 ahmmedrejowan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
