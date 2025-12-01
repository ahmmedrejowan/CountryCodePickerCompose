# CountryCodePickerCompose ProGuard Rules
# These rules are automatically applied when apps use this library

# Keep Country enum - needed for reflection and serialization
-keep enum com.rejowan.ccpc.Country { *; }
-keepclassmembers enum com.rejowan.ccpc.Country {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep all public Composable functions
-keep @androidx.compose.runtime.Composable public class * { *; }
-keep public class com.rejowan.ccpc.*Kt {
    public static ** *(...);
}

# Keep data classes for customization (may be used via reflection)
-keep class com.rejowan.ccpc.ViewCustomization { *; }
-keep class com.rejowan.ccpc.PickerCustomization { *; }
-keepclassmembers class com.rejowan.ccpc.ViewCustomization { *; }
-keepclassmembers class com.rejowan.ccpc.PickerCustomization { *; }

# Keep utility classes and their public methods
-keep class com.rejowan.ccpc.CCPUtils {
    public *;
}
-keep class com.rejowan.ccpc.CCPValidator {
    public *;
}

# Keep libphonenumber classes (required by CCPValidator and CCPTransformer)
-keep class com.google.i18n.phonenumbers.** { *; }
-keep class io.michaelrocks.libphonenumber.android.** { *; }
-dontwarn com.google.i18n.phonenumbers.**
-dontwarn io.michaelrocks.libphonenumber.android.**

# Keep VisualTransformation implementation
-keep class com.rejowan.ccpc.CCPTransformer {
    public *;
}

# Preserve line numbers for debugging stack traces
-keepattributes SourceFile,LineNumberTable

# Keep annotations for Compose
-keepattributes *Annotation*

# Remove logging in release builds (optional - uncomment if needed)
# -assumenosideeffects class android.util.Log {
#     public static *** d(...);
#     public static *** v(...);
#     public static *** i(...);
# }
