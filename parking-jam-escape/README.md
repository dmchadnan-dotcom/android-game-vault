# Parking Jam Escape — Neon District

An original offline-first car-escape puzzle game for Android and the browser. **Status: Android testing candidate, not a published or production-signed Google Play release.**

## Gameplay

Tap cars facing north, south, east, or west to drive them off an 8×8 board when every square ahead of the car is clear. A blocked move costs one heart. Clear all cars to win; get coins and up to 3 stars, and unlock new levels. Every shipped level is deterministically generated and solver-validated. Cars vary in length and color, with increased density and deeper dependencies across six districts.

Modes: Journey (120 levels), Daily challenge (seeded from local calendar date), and Free Drive. The game includes hints, undo, restart, sound toggle, light/dark mode, combo scoring, a local progress save, and keyboard accessible cars. No logins, ads, tracking, networking, or external dependencies.

## Files

* `index.html`: truly standalone HTML game. Open in Chrome/Edge/Safari/Android browser; can work without a web server or Wi-Fi.
* `logic.js`: original puzzle engine with graph-based provable solvability.
* `app.js`, `style.css`, `index.template.html`, `assemble.py`: editable browser source and build script. Run `python assemble.py` after edits to rebuild `index.html`; also copy it to `android-studio/app/src/main/assets/index.html`.
* `android-studio/`: import this directory in Android Studio. Java 17, AGP 8.13.2, Gradle 8.13, compile/target SDK 36, min SDK 26.
* `tests/validate_engine.js`: extensive solvability and rules validation. Run `node tests/validate_engine.js`.
* `tests/test_browser.py`, `tests/test_finish.py`: Chromium/Playwright interaction and responsive layout tests.
* `store/`: original app icon, feature graphic, Play Store marketing screenshots captured from actual gameplay.
* `STORE_LISTING.md`, `PRIVACY_POLICY_TEMPLATE.md`, `PLAY_STORE_CHECKLIST.md` and `TEST_REPORT.md`.

## Android build

Open `android-studio/` in Android Studio and install the Android API 36 SDK. Use **Run** for device tests, or Gradle `:app:assembleDebug` to build a debug APK and `:app:bundleRelease` for an **unsigned** release AAB. A release cannot be submitted to Play until it is signed with your secure upload keystore and all review requirements are met. The `.github/workflows/build-parking-jam-escape.yml` in this repository builds the same artifacts on GitHub Actions.

The Android wrapper is an offline-only WebView with blocked network requests, no INTERNET permission, and UI window insets for modern Android phones. Android system Back opens/closes the game menu.

## Testing scope and limitations

The engine's 120 journey levels and further sampled random/daily levels have passed rule-based simulation, and Chromium tests cover click-to-escape, blocked-car hearts, undo, hint, finish/unlock, mode switching, settings, and viewport widths of 320, 390 and 1440 pixels. No physical-device acceptance test has been performed and no Play Console listing has been published. Coin earning and spending are simulated in-game currency; **there is no real-money purchase capability** in this version.

Rules are those of this original path-clearing game, not the exact rules of any third-party app. Do not publish store screenshots, code, trademarks, or branding from competitors.
