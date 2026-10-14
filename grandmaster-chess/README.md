# Grandmaster Arena Chess — Android test build

Offline chess with AI levels Beginner/Casual/Intermediate/Advanced/Expert/Master and local two-player mode. Contains an original lightweight rules/search engine; relative levels are NOT certified Elo ratings and not Stockfish strength.

Java 17, Android Gradle Plugin 8.13.2, Gradle 8.13, target API 36, min API 26. All game assets bundled offline; no Internet or phone permissions.

To build: `cd grandmaster-chess && gradle :app:assembleDebug :app:bundleRelease`. Debug APK is suitable for phone testing. AAB is unsigned and not ready for Google Play until signed with a securely stored upload key.

Known prototype gaps: chess clock, threefold repetition, 50-move automatic claim, selectable promotion, PGN/FEN, chess960, accessibility testing and high-strength native chess engine. Real Android phone testing and Play Console requirements remain to be completed.
