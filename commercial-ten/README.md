# TENFOLD Arcade — Ten Commercial-Track Mobile Game Prototypes

## What this is
A single, offline Android game-collection prototype with ten *different* playable rule systems, built for validating commercial potential. **Not** ten production-ready, stand-alone games. These are original, ad-free concepts with locally saved scores and a consistent interface.

1. Merge Haven — merge 2 / cozy progression
2. Color Cascade — color sorting logic
3. Block Bloom — line-clearing block puzzle
4. Traffic Tangle — sliding vehicle logic puzzle
5. Hex Conquest — territory flood-control strategy
6. Garden Guardians — wave-based tower defense
7. Pocket Tactics — turn-based squad tactics
8. Neon Circuit — arcade 2.5D street racing
9. Sky Ring Pilot — 2.5D drone racing
10. Labyrinth Vault — raycasted first-person 3D maze

## Running
- **HTML:** open `Tenfold_Arcade_10_Games_Offline.html` in a modern browser; offline, no external dependencies.
- **Android:** open this project in Android Studio, use JDK 17 and Gradle 8.13, target/compile API 36, run `gradle :app:assembleDebug`. The debug APK appears at `app/build/outputs/apk/debug/app-debug.apk`.
- **Github Actions:** The provided workflow builds the APK and an unsigned release AAB; the AAB must be securely signed before release. Never commit your keystore or passwords.
- **Controls:** Tap targets directly for puzzles. For action games use direction pad and two action buttons, or WASD/arrow keys, Space and Shift/E. Pause and restart on the toolbar.

## Legal and publishing
- Google Play requires AAB for new listings, correct target API, app content declarations, privacy policy (hosted publicly), Data safety answers, content rating, screenshots and testing requirements for the specific developer account.
- No INTERNET permission, analytics, ad SDK, account, push notifications or in-app purchases in this version. `localStorage` retains high scores on the device.
- Current Java Android Activity wraps offline game assets using an allowlisted appassets HTTPS origin, with file/content/mixed access disabled.
- Brand, icon, screenshots and feature graphic are drafts pending Play Console review.
- No marketing claims of 100 levels or live multiplayer, or real-world earnings. Production release requires code review, external QA, load/performance testing, accessibility/localization, multiple fully fleshed levels, game balance and store listing verification.

## Market research
- Sensor Tower, State of Gaming 2026: https://sensortower.com/press/press-release-sensor-tower-state-of-gaming-gaming-drove-52-billion-downloads-82b-iap-revenue-on-mobile-and-12b-premium-revenue-on-steam
- AppMagic (2026): https://appmagic.rocks/files/view/upload/Reports/EN_MobileMarkeLandscape2026.pdf
- PocketGamer H1 2026 genre analysis: https://www.pocketgamer.biz/h1-2026-genre-analysis-strategy-stumbles-rpgs-fall-and-puzzle-revenue-ramps-up/
- Sensor Tower India: https://sensortower.com/blog/india-mobile-game-insights-2025