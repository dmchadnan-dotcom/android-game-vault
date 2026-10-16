# Snake & Ladders Royale — Royal Ascent

An original offline mobile-first Snake & Ladders game built with HTML, SVG, CSS, and JavaScript. Includes an Android Studio WebView wrapper for local play on Android 8.0+.

## Delivered game

- **2–4 players:** 1–4 people taking turns on a phone, with computer players filling the remaining slots.
- **Modes:** Royal Classic (100-square fixed board); Lightning Sprint (64 squares); twelve Adventure chapters (distinct deterministic boards with progressively more snakes); Daily Quest (board seeded from the device's local date).
- **Gameplay:** Fair six-sided dice, step-by-step token animations, snake descents, ladder ascents, bonus turn on a six, third consecutive six forfeits the third roll, exact finish to win.
- **Progress:** Offline saved current match, wins/crowns, adventure chapter unlocks, daily quest completion.
- **Presentation:** Self-contained SVG renderer for original themed board, decorative ladders and snakes, 4 token colors, accessible text status, touch buttons and optional synthesized sound.
- **Privacy:** No accounts, web requests, adverts, in-app purchases, analytics SDKs, or personal information collected by the app. Progress is stored only in the app's browser/WebView localStorage.

## How to play

Open `index.html` in a modern browser, including Chrome on Android. This is **one standalone file** and works offline after download. Tap New Match, choose a mode, the total number of players, and how many are human. Tap START MATCH, then ROLL DICE.

Snake & Ladders is a luck-based game. **Bots have no secret difficulty advantage** and roll under the same rules. Adventure maps increase hazard density, but they do not change dice probabilities. Rules are an openly described casual variant, not an exact implementation of every regional ruleset.

## Build Android

1. Open `android-studio/` in Android Studio with JDK 17 and Android SDK 36 installed.
2. Build debug APK using `gradle :app:assembleDebug` or Build > Build APK(s).
3. Build an unsigned release AAB using `gradle :app:bundleRelease`.
4. Generate an upload keystore **securely on your own machine**, sign the release AAB, and follow Play Console's app-signing instructions. Never commit signing credentials to Git.

Android application ID: `com.brightforge.snakeladdersroyale` (verify it is unique and fits your publishing identity before first release). Version 0.9.0, minSdk 26, compileSdk/targetSdk 36. Offline Android WebView; does not declare INTERNET permission. All game resources are bundled within the APK.

`android-studio/app/src/main/assets/index.html` is generated from `src/shell.html`, `src/style.css`, `src/engine.js`, and `src/app.js` using `python build_html.py`.

## Automated tests

```bash
node tests/validate_rules.js
python tests/browser_test.py
```

The rules tests cover 300 whole simulated matches with 2–4 players, 48 distinct mode/stage board configurations, special dice rules, jumps, and saved-state validation. Browser integration tests use Playwright and Chromium at 320 px, 390 px, and 1440 px, testing physical click interaction, local save/restore, 4-player setup, modes, and no horizontal overflow.

## Before commercial launch

This is a playable, visually polished **release candidate**, not an officially published app. Real-phone tests, varied OEM WebView behavior, Android accessibility, app signing, final branding/trademark checks, hosted privacy policy, user feedback, and Play Console review are not yet complete. This edition has no online multiplayer, cloud syncing, ads, purchases, or paid reward mechanics; do not claim otherwise in the listing.
