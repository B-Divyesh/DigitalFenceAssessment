// Top-level build file where you can add configuration options common to all modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // declare only, don't apply at root
    alias(libs.plugins.ksp) apply false
}
