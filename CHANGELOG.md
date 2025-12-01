# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.2.0] - 2024-12-01

### 🎉 Major Updates

This release focuses on fixing critical bugs, improving compatibility with the latest Android ecosystem, and enhancing code quality.

### ✨ Added

- **Comprehensive ProGuard/R8 Rules**: Added consumer-rules.pro to ensure library works correctly in release builds with minification enabled ([#16](https://github.com/ahmmedrejowan/CountryCodePickerCompose/pull/16))
- **Input Validation**: Added validation to all public APIs with clear error messages for invalid parameters
- **KDoc Documentation**: Added comprehensive API documentation with usage examples for all public composables
- **Error Handling**: Improved error handling in CCPUtils and CCPValidator with graceful fallbacks
  - SecurityException handling when READ_PHONE_STATE permission is missing
  - Fallback to device locale when TelephonyManager fails
  - Validation for invalid ISO codes in getEmojiFlag()
- **Backward Compatible String Support**: Restored ability to pass direct strings to PickerCustomization alongside string resources
- **Optional Context Parameter**: Made Context parameter optional in searchCountry() and findCountry() methods
- **Phone Number Search**: Enhanced search functionality to find countries from phone numbers (e.g., "+12125551234" finds USA)
- **19 Language Localization**: Added translations for country names in 19 languages

### 🐛 Fixed

- **Issue #1**: Fixed NoSuchMethodError with rememberModalBottomSheetState in multi-module architectures
- **Issue #2**: Fixed critical VisualTransformation crashes:
  - ArrayIndexOutOfBoundsException when clearing text
  - IllegalStateException with invalid offset mappings
  - Crashes when typing long phone numbers
- **Issue #3**: Fixed BottomSheet API compatibility with Compose BOM 2024.08.00+

### ⬆️ Updated

- **Kotlin**: 1.9.0 → 2.1.0 (major version update)
- **Compose BOM**: 2024.05.00 → 2024.12.01 (latest stable)
- **Core KTX**: 1.13.1 → 1.15.0
- **Lifecycle Runtime**: 2.7.0 → 2.8.7
- **Activity Compose**: 1.9.0 → 1.9.3
- **JUnit**: 1.1.5 → 1.2.1
- **Espresso**: 3.5.1 → 3.6.1
- **Compile SDK**: 34 → 35
- **Target SDK**: 34 → 35

### 🔧 Changed

- **Compose Compiler Plugin**: Migrated from composeOptions.kotlinCompilerExtensionVersion to new Kotlin Compose Compiler plugin (required for Kotlin 2.0+)
- **BottomSheet API**: Updated rememberModalBottomSheetState() usage for compatibility with latest Material 3
- **Offset Mapping**: Completely rewrote VisualTransformation offset mapping logic for better stability

### 🗑️ Removed

- Unused Log import from CCPTransformer
- Redundant getSelectedCountries() function that just returned input
- Deprecated composeOptions block (no longer needed with Kotlin 2.0+)

### 🧹 Code Quality

- Added input validation with meaningful error messages
- Improved null safety throughout the codebase
- Better error handling with specific exception catches
- Cleaned up unused code and imports
- Enhanced documentation with KDoc comments

### 📝 Documentation

- Added comprehensive KDoc to all public APIs
- Created CHANGELOG.md following Keep a Changelog format
- Improved inline code comments
- Added usage examples in documentation

### ⚠️ Breaking Changes

**None** - This release maintains backward compatibility. All API changes are additive or internal improvements.

### 🔄 Migration Guide

If upgrading from 0.1.x:

1. **Update Kotlin version** to 2.0+ in your project
2. **Add Compose Compiler plugin** if not already present:
   ```kotlin
   plugins {
       id("org.jetbrains.kotlin.plugin.compose") version "2.1.0"
   }
   ```
3. **Use Compose BOM 2024.08.00 or later** for best compatibility
4. **No code changes needed** - Your existing implementation will work as-is

### 📦 Minimum Requirements

- **Minimum SDK**: 24 (unchanged)
- **Target SDK**: 35 (updated from 34)
- **Kotlin**: 2.0+ (updated from 1.9.0)
- **Compose BOM**: 2024.08.00+ (updated from 2024.05.00)

### 🙏 Acknowledgments

- Thanks to @Sparks1998 for the comprehensive localization PR
- Thanks to all users who reported issues and provided feedback

---

## [0.1.8] - 2024-03-XX

### Initial Public Release

- Basic country code picker functionality
- Support for 249 countries
- Material 3 Design
- Dialog and Bottom Sheet modes
- Phone number validation
- Visual transformation for phone formatting
- Customizable UI options

---

## How to Update

Update your dependency in `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.ahmmedrejowan:CountryCodePickerCompose:0.2.0")
}
```

[Unreleased]: https://github.com/ahmmedrejowan/CountryCodePickerCompose/compare/v0.2.0...HEAD
[0.2.0]: https://github.com/ahmmedrejowan/CountryCodePickerCompose/compare/v0.1.8...v0.2.0
[0.1.8]: https://github.com/ahmmedrejowan/CountryCodePickerCompose/releases/tag/v0.1.8
