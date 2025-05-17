<p align="center">
<img src="https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/logo.png" width="300px" align="center"/></p>
<h1 align="center">CountryCodePickerCompose</h1> 
<h3 align="center">A Country Code Picker Created with Jetpack Compose for Android<b></b></h3>

<p align="center"> 
	<a href="https://www.android.com">
		<img src="https://img.shields.io/badge/platform-Android-yellow.svg" alt="platform">
	</a>
	<a href="https://android-arsenal.com/api?level=21">
		<img src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat" alt="API">
	</a>
	<a href="https://jitpack.io/#Sparks1998/CountryCodePickerCompose">
		<img src="https://jitpack.io/v/Sparks1998/CountryCodePickerCompose.svg" alt="JitPack">
	</a>
	<a href="https://github.com/ahmmedrejowan/CountryCodePickerCompose/blob/master/LICENSE">
		<img src="https://img.shields.io/github/license/ahmmedrejowan/CountryCodePickerCompose" alt="GitHub license">
	</a>
</p>

 <p align="center"> <a href="https://github.com/ahmmedrejowan/CountryCodePickerCompose/issues"><img src="https://img.shields.io/github/issues/ahmmedrejowan/CountryCodePickerCompose" alt="GitHub issues"></a> <a href="https://github.com/ahmmedrejowan/CountryCodePickerCompose/fork"><img src="https://img.shields.io/github/forks/ahmmedrejowan/CountryCodePickerCompose" alt="GitHub forks"></a> <a href="https://github.com/ahmmedrejowan/CountryCodePickerCompose/stargazers"><img src="https://img.shields.io/github/stars/ahmmedrejowan/CountryCodePickerCompose" alt="GitHub stars"></a> <a href="https://github.com/ahmmedrejowan/CountryCodePickerCompose/graphs/contributors"> <img src="https://img.shields.io/github/contributors/ahmmedrejowan/CountryCodePickerCompose" alt="GitHub contributors"></a>   </p>


> [!NOTE]  
> Full tutorial on this library on YouTube. You can check it
> out [here](https://www.youtube.com/playlist?list=PLmZDG9uYrxfMgCw8LqSuWrz2ormZdPiNz)

## Table of Contents

- [Purpose](#purpose)
- [Features](#features)
- [Demo](#demo)
- [Prerequisites](#prerequisites)
- [Dependency](#dependency)
- [Usage](#usage)
- [Customization](#customization)
- [Notes](#notes)
- [Contribute](#contribute)
- [License](#license)

## Purpose

In various apps, we need to use a country code picker. There are several libraries available for this purpose. In XML we
have a CCP created by hbb20, which is great. In Jetpack Compose there are few libraries but they lacks some features and
updates. So, I created this library to use in Jetpack Compose. This library is highly customizable and can be used in
any project. This should cover the lackings of other libraries and provide a better experience to the users.

## Features

- Minimal, lightweight and easy to use.
- Emoji Country flags, no more image assets.
- 4 different ways to use the library.
	- As a View (Full Screen or Small or Attached to TextField)
	- As a TextField (Already intregated with OutlinedTextField)
	- As a Dialog (Just the picker dialog)
	- As a BottomSheet (Just the picker bottom sheet)
- Automatic country detection based on the user's device.
- Phone number validation.
- Visual transformation of the phone number.
- Highly customizable.

## Demo

| Different Use Cases                                                                                       | Country Picker Dialog                                                                                     | Picker Bottom Sheet                                                                                       |
|-----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| ![Shot1](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot1.png) | ![Shot2](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot2.png) | ![Shot3](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot3.png) |

| Phone Number Validation                                                                                   | Visual Transformation                                                                                     | Picker Search Functions                                                                                   |
|-----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| ![Shot4](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot4.png) | ![Shot5](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot5.png) | ![Shot6](https://raw.githubusercontent.com/ahmmedrejowan/CountryCodePickerCompose/master/files/shot6.png) |

You can download the test apk to try out the features of this
library - [Download](https://github.com/ahmmedrejowan/CountryCodePickerCompose/raw/master/app/release/app-release.apk)

## Prerequisites

### Kotlin DSL

``` Kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{
            url = uri("https://jitpack.io")
        }
    }
}
```

``` groovy
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

## Dependency

Add this to your module's `build.gradle.kts` file (latest version 
<a href="https://jitpack.io/#Sparks1998/CountryCodePickerCompose">
	<img src="https://jitpack.io/v/Sparks1998/CountryCodePickerCompose.svg" alt="JitPack">
</a>):

``` kotlin
dependencies {
    ...
    implementation("com.github.Sparks1998:CountryCodePickerCompose:0.1.7")
}
```

``` groovy
dependencies {
    ...
    implementation 'com.github.Sparks1998:CountryCodePickerCompose:0.1.7'
}
```

## Usage ([See Wiki](https://github.com/ahmmedrejowan/CountryCodePickerCompose/wiki))

There are 4 different usages of this library.

1. As a regular Composable using `CountryCodePicker`
2. As a OutlinedTextField Composable using `CountryCodePickerTextField`
3. As a Picker Dialog using `CountryPickerDialog`
4. As a Picker BottomSheet using `CountryPickerBottomSheet`

### CountryCodePicker

This is the regular composable, it can be used in any places, can be also attached to a TextField. Here is a simple
example-

``` Kotlin
      var country by remember {
            mutableStateOf(Country.Argentina)
        }

        CountryCodePicker(
            modifier = Modifier.align(Alignment.CenterHorizontally),
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
                showFlag = false,
            ),
            showSheet = true,
        )
```

### CountryCodePickerTextField

This is the OutlinedTextField Composable, it can be used as a TextField. Here is a simple example-

``` Kotlin

  var country by remember {
        mutableStateOf(Country.Bangladesh)
    }

    if (!LocalInspectionMode.current) {
        CCPUtils.getCountryAutomatically(context = LocalContext.current).let {
            it?.let {
                country = it
            }
        }
    }


    CountryCodePickerTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
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
        showError = true,
        shape = RoundedCornerShape(10.dp),
        onValueChange = { _, value, _ ->
            text = value
        },
        number = text,
        showSheet = true,
        selectedCountry = country


    )

```

### CountryPickerDialog

This is Dialog Composable. It will return the selected country when an item is clicked. Here is a simple example-

``` kotlin
        var country by remember { mutableStateOf(selectedCountry) }
        var isPickerOpen by remember { mutableStateOf(false) }

        CountryPickerDialog(
            modifier = Modifier.clip(shape = RoundedCornerShape(10.dp)),
            onDismissRequest = { isPickerOpen = false },
            onItemClicked = {
                country = it
                isPickerOpen = false

            },
            textStyle = textStyle,
            listOfCountry = countryList,
            pickerCustomization = pickerCustomization,
            itemPadding = itemPadding,
            backgroundColor = backgroundColor
        )

```

### CountryPickerBottomSheet

This is Dialog Composable. It will return the selected country when an item is clicked. Here is a simple example-

``` kotlin
        var country by remember { mutableStateOf(selectedCountry) }
        var isPickerOpen by remember { mutableStateOf(false) }

        CountryPickerBottomSheet(
            modifier = Modifier.clip(shape = RoundedCornerShape(10.dp)),
            onDismissRequest = { isPickerOpen = false },
            onItemClicked = {
                country = it
                isPickerOpen = false
            },
            textStyle = textStyle,
            listOfCountry = countryList,
            pickerCustomization = pickerCustomization,
            itemPadding = itemPadding,
            backgroundColor = backgroundColor
        )

```

## Customization ([See Wiki](https://github.com/ahmmedrejowan/CountryCodePickerCompose/wiki))

All of the Composables have customization options. The full customization is available in the
Wiki - [See Wiki](https://github.com/ahmmedrejowan/CountryCodePickerCompose/wiki)

There are several utils to make things easier. They are-

### Automatic Country Detection

This will detect the country based on the user's device.

``` kotlin
    var country by remember {
        mutableStateOf(Country.Bangladesh)
    }

    if (!LocalInspectionMode.current) {
        CCPUtils.getCountryAutomatically(context = LocalContext.current).let {
            it?.let {
                country = it
            }
        }
    }
```

### Phone Number Validation

This will validate the phone number based on the selected country.

``` kotlin
    
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
            isNumberValid = validatePhoneNumber(
                number = it, countryCode = country.countryCode
            )

        },

        // Other properties

     )


```

### Visual Transformation

This will transform the phone number based on the selected country. Attach this to text field to see the transformation.
It's already integrated with `CountryCodePickerTextField`.

``` kotlin

    OutlinedTextField(
        // Other properties
        visualTransformation = CCPTransformer(context, country.countryIso)
        // Other properties
    )

```

### View Customization

``` kotlin
data class ViewCustomization(
    var showFlag: Boolean = true,
    var showCountryIso: Boolean = false,
    var showCountryName: Boolean = false,
    var showCountryCode: Boolean = true,
    var showArrow: Boolean = true,
    var clipToFull: Boolean = false,
)
```

### PickerCustomization

``` kotlin
data class PickerCustomization(
    var itemPadding: Int = 10,
    var dividerColor: Color = Color.LightGray,
    var headerTitle: String = "Select Country",
    var searchHint: String = "Search Country",
    var showSearchClearIcon: Boolean = true,
    var showCountryCode: Boolean = true,
    var showFlag: Boolean = true,
    var showCountryIso: Boolean = false,
    )
```

## Notes

- The library is in its early stages, so there may be some bugs.
- If you find any bugs, please report them in the `Issues` tab.
- Sample app is available in the [app](https://github.com/ahmmedrejowan/CountryCodePickerCompose/tree/master/app)
  directory.
- Right now, it doesn't support translations. But it will be added in the future.

## Inspiration and Credit

- Inspired by [CountryCodePickerProject](https://github.com/hbb20/CountryCodePickerProject)
  by  [hbb20](https://github.com/hbb20/)
- Inspired by [jetpack_compose_country_code_picker](https://github.com/togisoft/jetpack_compose_country_code_picker)
  by  [togisoft](https://github.com/togisoft)
- Inspired
  by [jetpack_compose_country_code_picker](https://github.com/jump-sdk/jetpack_compose_country_code_picker_emoji)
  by  [jump-sdk](https://github.com/jump-sdk/)
- Creadit to [libphonenumber-android](https://github.com/MichaelRocks/libphonenumber-android)
  by  [MichaelRocks](https://github.com/MichaelRocks/)

## Contribute

Please fork this repository and contribute back
using [pull requests](https://github.com/ahmmedrejowan/CountryCodePickerCompose/pulls).

Any contributions, large or small, major features, bug fixes, are welcomed and appreciated.

Let me know which features you want in the future in `Request Feature` tab.

If this project helps you a little bit, then give a to Star ⭐ the Repo.

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
