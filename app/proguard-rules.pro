# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Surati\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-dontpreverify
-ignorewarnings

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-forceprocessing
-verbose    # print error logs

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
#-dontoptimize
#-dontpreverify

# If you want to enable optimization, you should include the
# following:
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*, !code/allocation/variable
-optimizationpasses 5
-allowaccessmodification
#-repackageclasses ''


-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgent
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.support.v4.app.DialogFragment
-keep public class * extends android.app.Fragment
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }


-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

#-keep class com.google.android.gms.common.** { *; }

# exclude retrofit
-keep class com.squareup.** { *; }
-keep interface com.squareup.** { *; }
-dontwarn com.squareup.okhttp.**
-keep class retrofit.** { *; }

-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keep interface retrofit.** { *;}
-keep interface com.squareup.** { *; }
-dontwarn rx.**
-dontwarn retrofit.**

# keep API response modals
-keep class com.krp.android.demoapp.modals.** { *; }
