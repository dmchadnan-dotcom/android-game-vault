# Bubble Pop Adventure — Galaxy Gardens

An original offline-first bubble shooter for mobile browsers and Android WebView. This repository contains a complete self-contained HTML5 game, Android Studio project, test scripts, and launch media. Gameplay code and visual effects use native Canvas/CSS/Web Audio with no external JavaScript or asset dependencies.

## Features

- 60 deterministic, replayable levels grouped into six worlds (10 levels each).
- Touch drag/release aim with ricochet guideline; mouse and keyboard inputs also work.
- Hex-grid bubble collision and snapping, match-3+ clearing, gravity/detached islands, combos, increasing difficulty, ice bubbles, advancing danger pressure, lose/win and star ratings.
- Earned in-game coins, Rainbow / Bomb / Swap boosters, score and level records in offline localStorage. No paid currency or real-money checkout.
- Responsive portrait UI; high-contrast outlines, color-assist symbols, sound and reduced-motion settings.
- Original store icon and feature image, realistic device screenshots, privacy notice and release checklist.
- No tracking, remote calls, advertisements, analytics, account registration, or personal information required by the current release.

## Run the HTML game

Open `index.html` in a modern browser such as Chrome. No local server is needed. Offline play works because all source is in this one HTML file. Game saves are device/browser-specific.

## Build Android

Open `android-studio` in Android Studio with Java 17, Android SDK 36, Android Gradle Plugin 8.13.2 / Gradle 8.13. Gradle tasks: `:app:assembleDebug` and `:app:bundleRelease`. The project intentionally does not ship an upload-signing key. The `bundleRelease` AAB is **unsigned and cannot be published to Google Play** until release signing is configured with a securely managed key. Never commit passwords/keystore material into public GitHub.

Android: `com.brightforge.bubblepopadventure`; `minSdk 26`, `targetSdk 36`, `compileSdk 36`. Check package-name ownership/uniqueness and store listing before publishing. Debug APK is for testing only. App requests no permissions and blocks remote WebView network navigation. Offline user progress is stored in origin-scoped WebView localStorage. `allowBackup=false` means it will not transfer on uninstall/reinstall.

## Tests

From a development Python environment with Playwright and Chromium installed:

```sh
python tests/test_web.py
python tests/extra_screens.py
python tests/create_assets.py
```

The regression suite tests touch/mobile layout (390×844 and 320×720), desktop layout, menu and level navigation, aiming/shooting, power-ups, pause, and 3,461 rules/level assertions across all 60 levels. Tests passing in a browser do not guarantee perfect behavior on physical Android devices; complete a real-device test plan before release.

## Release note

The included Google Play documents and graphics are drafts and must be reviewed by the publisher. There is no ads SDK, in-app purchase SDK, or online multiplayer. Optional commercial monetization requires separate, policy-compliant integration and additional privacy/data-safety review. App branding, title, and graphics should be checked for trademark conflicts.
