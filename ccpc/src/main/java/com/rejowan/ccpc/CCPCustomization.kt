package com.rejowan.ccpc

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

/**
 * Customization options for the country picker dialog/bottom sheet
 *
 * @param itemPadding Padding between items in dp
 * @param dividerColor Color of the divider between items
 * @param headerTitle String resource ID for the header title. Use [headerTitleText] for direct string
 * @param searchHint String resource ID for the search hint. Use [searchHintText] for direct string
 * @param showSearchClearIcon Whether to show the clear icon in search field
 * @param showCountryCode Whether to show country calling code in the list
 * @param showFlag Whether to show country flag emoji in the list
 * @param showCountryIso Whether to show country ISO code in the list
 * @param headerTitleText Direct string for header title (takes precedence over headerTitle resource)
 * @param searchHintText Direct string for search hint (takes precedence over searchHint resource)
 */
data class PickerCustomization(
    var itemPadding: Int = 10,
    var dividerColor: Color = Color.LightGray,
    @StringRes var headerTitle: Int = R.string.select_country,
    @StringRes var searchHint: Int = R.string.search,
    var showSearchClearIcon: Boolean = true,
    var showCountryCode: Boolean = true,
    var showFlag: Boolean = true,
    var showCountryIso: Boolean = false,

    // Backward compatibility: Allow direct string values
    var headerTitleText: String? = null,
    var searchHintText: String? = null,
)

/**
 * Customization options for the country picker view
 *
 * @param showFlag Whether to show country flag emoji
 * @param showCountryIso Whether to show country ISO code
 * @param showCountryName Whether to show country name
 * @param showCountryCode Whether to show country calling code
 * @param showArrow Whether to show dropdown arrow
 * @param clipToFull Whether the view should fill available width
 */
data class ViewCustomization(
    var showFlag: Boolean = true,
    var showCountryIso: Boolean = false,
    var showCountryName: Boolean = false,
    var showCountryCode: Boolean = true,
    var showArrow: Boolean = true,
    var clipToFull: Boolean = false,
)
