# Carrom King 3D — Royal Table

Offline physics-based carrom game with premium shaded board graphics, Rookie / Pro / King AI difficulty, two-player pass-and-play and solo practice.

The playing field uses Canvas 2D rendering with a 3D CSS perspective tilt, shadows and shaded pieces — it is not a WebGL mesh engine.

Features: touch aiming; position and power controls; physics collisions; cushion rebounds; queen covering; fouls; score and locally stored win statistics.

The game deliberately uses a simplified **casual ruleset**, not an official tournament rules simulation. It does not offer online multiplayer, advertisements or in-app purchases.

**Build:** Open android-studio/ in Android Studio with JDK 17, AGP 8.13.2 and SDK 36, or execute `gradle :app:assembleDebug :app:bundleRelease` from the android-studio folder. Android 8.0+ supported. The release AAB is unsigned until you configure signing. No INTERNET permission.

Before Google Play: physical Android QA, intellectual-property/name clearance, a hosted publisher-specific privacy policy, Data Safety declaration, production release signing, content-rating/store listing, and any required closed testing.
