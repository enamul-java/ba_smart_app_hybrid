# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class eraapps.bankasia.bdinternetbanking.apps.alert.** { *; }

#-keep class com.shockwave.**(Add Raihan For Pdf View)

#Begin RxJava
-dontwarn org.reactivestreams.FlowAdapters
-dontwarn org.reactivestreams.**
-dontwarn java.util.concurrent.Flow.**
-dontwarn java.util.concurrent.**
#End RxJava
-dontwarn kotlin.**

# Adapt Kotlin metadata.
#-adaptkotlinmetadata

# Preserve Kotlin Metadata but allow obfuscation.
-keep class kotlin.Metadata { *; }

# Temporarily disable optimization on Kotlin classes.
#noinspection ShrinkerUnresolvedReference
-keep,includecode,allowobfuscation,allowshrinking @kotlin.Metadata class ** { *; }

# Adapt Kotlin metadata.
#-adaptkotlinmetadata

# Adapt information about Kotlin file facades.
-adaptresourcefilecontents **.kotlin_module

# Preserve Kotlin metadata.
-keep class kotlin.Metadata { *; }

# Temporarily disable optimization on Kotlin classes.
#noinspection ShrinkerUnresolvedReference
-keep,includecode,allowobfuscation,allowshrinking @kotlin.Metadata class ** { *; }

# Retrofit
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keep class sun.misc.Unsafe { *; }
#your package path where your gson models are stored
-keep class eraapps.bankasia.bdinternetbanking.apps.data.** { *; }
-keep class eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.** { *; }
-keep class eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.** { *; }
-keep class eraapps.bankasia.bdinternetbanking.apps.util.permission.** { *; }
#Card Statement Info not working
-keep class eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.card.** { *; }
-keep class eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar.** { *; }

-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class retrofit.** { *; }

#blur effect
-keep class android.support.v8.renderscript.** { *; }
-keep class androidx.renderscript.** { *; }


#retrofit 11 may 2023
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.


# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>