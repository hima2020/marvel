// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.dagger.hilt.android).apply(false)
    alias(libs.plugins.ksp).apply(false)


}
configurations.all {
    resolutionStrategy {
        force("androidx.core:core:1.12.0")
    }
    allprojects {
        repositories {
            google()
            mavenCentral()
        }
    }
}