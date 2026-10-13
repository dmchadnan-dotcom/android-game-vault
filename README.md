# Android Game Vault

Android source repository for the original ChatGPT game collection: BLOCKWILD, retro arcade games, racing games, drone simulator, Dots & Boxes, and more.

## Current status
The repository is being initialized. The complete Android Studio project and game assets still need to be imported before running a build. Do not upload signing keys or keystore passwords.

## Project archive
The full Android game source archive is available in the ChatGPT conversation as `Android_Games_Play_Store_Source_Pack.zip`. Extract it, then copy the contents of its `Android_Game_Studio_Projects/` folder into the root of this repository, including hidden `.github/` files. The GitHub Actions workflow inside builds installable debug APKs and optionally signed release AABs when signing secrets are configured.

## Android requirements
Android SDK API 36, Java 17, Android Gradle Plugin 8.13.2, Gradle 8.13. The game source is primarily offline HTML/JavaScript wrapped in an Android WebView. Physical phone testing, privacy disclosures, Play Console testing, final package names, and release signing are required before production release.

**This is an original collection of game prototypes, not official Minecraft, Mario, Contra or Caravan SandWitch software.**
