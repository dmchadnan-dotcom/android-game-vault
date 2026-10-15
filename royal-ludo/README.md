# Royal Ludo Studio
Original offline 2–4 player Ludo for Android and web.
- **Modes:** local pass-and-play, computer opponents Easy/Medium/Hard.
- **Rules:** six-to-enter, capture bonus turns, start and star safety, non-safe blockades, three sixes forfeits, exact finishing rolls, first to finish four tokens wins.
- **Offline:** no account, external API, INTERNET permission, advertisements or tracking SDK.
- Web game: `app/src/main/assets/index.html`.
- Android: open this root in Android Studio or build with Gradle 8.13 and Java 17 (`gradle :app:assembleDebug :app:bundleRelease`).
- Debug APK is for Android device testing. The release AAB built by CI is **unsigned and not ready for publishing**.
- Google Play requires independently verified account, package name, store materials, privacy policy and upload key.
- This uses a lightweight heuristic AI rather than trained neural AI; network multiplayer not included.
- Original SVG/CSS board and sound. No borrowed image or audio assets.
