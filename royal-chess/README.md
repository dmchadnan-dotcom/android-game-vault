# Royal Chess Studio
Premium offline mobile chess with **six relative AI difficulty tiers** and full legal move validation, including check/checkmate, castling, en passant, promotions, stalemate, threefold repetition, 50-move rule and insufficient material. Includes local two-player mode, free study board, clocks, takeback, hints, themes, PGN/FEN, and offline autosave.

Open `royal-chess/` in Android Studio, using JDK 17, Gradle 8.13, Android SDK 36. Run `gradle :app:assembleDebug :app:bundleRelease` inside the folder.

All game files are embedded in a hardened WebView at `appassets.androidplatform.net`, with no Internet permission. The release AAB from CI is **unsigned**; secure signing, physical-device tests, Google Play app declarations and a public privacy-policy URL are required before public release.

The 6 AI labels are progressive difficulty settings rather than independently tested Elo ratings.
