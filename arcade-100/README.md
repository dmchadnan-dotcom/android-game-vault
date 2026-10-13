# Arcade Atlas 100 — Android Studio + browser game source

This is **one Android app** that includes **100 playable offline mini-game variations** based on **10 original gameplay engines**. There are **20 challenges in each of five genres** (3D Action, Retro, Strategy, Board, 3D Puzzle). It is not 100 independent commercial-quality titles or 100 separate Android apps.

## Play on a computer
Open `app/src/main/assets/index.html` in Chrome/Edge/Firefox (or serve its folder from a local web server). Controls: arrow keys, WASD, Space; touch buttons appear under the canvas. Tap squares to make board and strategy moves. Scores are stored locally.

## Build Android
Use Android Studio or on a development computer with the Android SDK (API 36), Java 17 and Gradle 8.13:

```bash
gradle :app:assembleDebug
# For validation only; unsigned until you provide upload key:
gradle :app:bundleRelease
```

APK: `app/build/outputs/apk/debug/app-debug.apk`.
Unsigned AAB: `app/build/outputs/bundle/release/app-release.aab`.

## GitHub workflow
If adding to the existing `android-game-vault` repository, copy project to `arcade-100/` and copy `.github/workflows/build-arcade100.yml` to the repository's root `.github/workflows/` folder. This triggers the **Arcade Atlas 100 Android Builds** workflow.

To reduce GitHub API upload size, a GitHub mirror can contain `assets_payload.b64` (Base64 of zipped HTML/JS/CSS). The workflow decodes it before compiling Android. The downloadable source pack contains the **uncompressed editable source files**.

## Release readiness
Read `PLAY_STORE_CHECKLIST.md`, `PRIVACY_POLICY_DRAFT.md` and `STORE_LISTING_DRAFT.md`. A debug APK is not a Play Store deliverable. A signed release AAB, privacy-policy URL, accurate declarations, device tests and (where applicable) 12 tester closed test are required.

Do not copy copyrighted game assets or brand names. All art is generated procedurally in code.
