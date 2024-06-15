buildscript {

    dependencies {
        classpath(libs.google.services)
        classpath(libs.hilt.android.gradle.plugin)
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
        classpath("com.google.devtools.ksp:symbol-processing-gradle-plugin:1.9.0-1.0.13")

    }

}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
   // id("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false
}