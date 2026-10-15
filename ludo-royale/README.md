# Ludo Royale — Crown Edition

Fully offline, original Ludo game for 2, 3 or 4 participants, with human and AI seat selection, 3 heuristic AI levels, safe squares, captures, blockades, exact-roll finishing, animated dice, sound, autosave and responsive UI.

Android Java WebView app, Java 17, AGP 8.13.2, Gradle 8.13, compile/target API 36, min 26. No Internet permission, accounts, analytics or advertising SDKs.

**Testing:** 60 end-to-end random simulated AI matches and 10 rules tests passed in Node. Chrome desktop and simulated mobile UI test passed. Real Android device QA remains pending.

**Build:** gradle --no-daemon :app:assembleDebug :app:bundleRelease

Debug APK is for testing. Release AAB is **unsigned**, needs your secure upload key before Google Play distribution. Check title/trademarks, provide a hosted privacy policy, complete Play Console declarations and publisher testing. This is a commercial-capable source project, not a certified or published app.
