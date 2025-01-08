# Bear Metal Scouting

![Bear Metal Logo](composeApp/src/commonMain/resources/bearmetallogo.jpg)
## Scouting App info

The Bear Metal Scouting App is a multiplatform application for Android. The app changes from year to year to reflect the First Robotics Competition game for each year.


### Multiplatform info

This is a Kotlin project made for Android, but with Multiplatform functionality if needed in the future.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
