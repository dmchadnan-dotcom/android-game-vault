# Android Game Vault 🎮

Original game prototypes for Android, built as downloadable test APKs and Android Studio projects.

## Projects

| Project | Source | Build |
|---|---|---|
| **Tenfold Arcade** — 10 original casual/strategy/racing/3D puzzle games | [commercial-ten/](commercial-ten/) | [Build APK / unsigned AAB](../../actions/workflows/build-tenfold.yml) |
| **Arcade Atlas 100** — 100 themed mini-game challenges | [arcade-100/](arcade-100/) | [Arcade 100 workflow](../../actions/workflows/build-arcade100.yml) |
| **Indie Game Vault** — original game collection | [app/](app/) | [Main Android workflow](../../actions/workflows/android-build.yml) |

## Tenfold Android Studio setup

Open `commercial-ten/` in Android Studio. Use JDK 17 and Android SDK 36. From `commercial-ten/`:

```bash
gradle :app:assembleDebug
gradle :app:bundleRelease
```

The debug APK is for device testing. The release AAB is **unsigned until you provide your own secure upload signing key**. Never commit keystores or signing credentials.

There are no Internet, contacts, location or advertising permissions in the Tenfold prototype. The game bundles its own browser code inside a WebView. Check each game on a physical Android device before publication.

See [Tenfold publishing checklist](commercial-ten/PLAY_STORE_RELEASE_CHECKLIST.md), [privacy-policy draft](commercial-ten/PRIVACY_POLICY_DRAFT.md), and [QA test report](commercial-ten/QA_TEST_REPORT.md).

## Publication status

The Android test builds are configured. None of these projects should be described as Google Play approved or production-tested. Developer registration, testing requirements, signed release AAB, final store graphics, live privacy-policy page, Data safety declarations, content rating, accessibility and QA remain your responsibility.

All games use original assets and names; references to familiar genres are not affiliations with established game franchises.
