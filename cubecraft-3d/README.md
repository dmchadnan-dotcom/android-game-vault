# CubeCraft 3D — Original Mobile Cube Studio

A fully offline cube-practice game and 3×3 facelet solver with an original premium UI, animated 3D perspective canvas, touch orbit and turns, 2×2 / 3×3 / 4×4 / 5×5 outer-layer move simulators, wide-layer turns on larger cubes, six pattern presets, a personal speedcubing timer, eight lessons, colour entry with validation, and a 3×3 two-phase solver using the vendored MIT-licensed cube.js library.

## Run the browser game
Open `index.html` inside the full extracted folder. Keep `app.js`, `solver-worker.js`, and `vendor/` together. The app does not fetch scripts from CDNs. The 3×3 solver runs locally in a Web Worker when served from an HTTP(S) origin (including the Android packaged origin) and falls back to the UI thread when opened from `file://`.

## Build Android
Import `android-studio/` into Android Studio with Java 17 and Android SDK 36. Android Gradle Plugin 8.13.2. The WebView intercepts bundled appassets resources and deliberately does not request Internet or other sensitive permissions.

`gradle --no-daemon :app:assembleDebug :app:bundleRelease`

Debug APK is for testing; release bundle must be securely signed and tested before Play submission. Android package `com.brightforge.cubecraft` must be checked for uniqueness before publication.

## Honest feature coverage
- Interactive 2×2, 3×3, 4×4 and 5×5 **outer-layer and wide-layer turns**; this is NOT a complete 4×4/5×5 inner-slice simulator.
- 3×3 arbitrary-valid-state two-phase solver from six painted faces, with physical-state validation; high-strength lookup initialization takes seconds on some phones.
- On 2×2, 4×4 and 5×5, step-by-step solutions reverse the moves recorded since initial solved/scrambled state. These are NOT full general-purpose solvers for external painted cubes. 4×4/5×5 parity handling and inner-slice moves are not implemented.
- The beginner lessons provide written instructions, notation and practice sequences, not a full interactive coached course.
- The timer captures manual solve sessions and saves them on the device; it does not have WCA inspection/timing rules or hardware timer support.
- The source is a WebView native wrapper, not a Unity or native 3D/OpenGL project.

## Commercial release checklist
See `PLAY_STORE_CHECKLIST.md`, `PRIVACY_POLICY_TEMPLATE.md` and `LICENSE-THIRD-PARTY.txt`. Performance and touch interactions require real-device testing before publishing. Original artwork must be kept distinct from any competitor screenshots or logos.
