# MetroBloom City Builder
Original offline 2.5D isometric city building simulation. Build roads, houses, stores, industry, parks, power, water, schools, hospitals and fire stations; develop a working economy, taxes, pollution, happiness and eight city missions. The finite 22x22 map supports drag/pinch zoom on Android and keyboard/mouse controls on desktop.

## Build
Open `metrobloom/` in Android Studio (JDK 17, SDK 36), or run the GitHub Actions workflow. `gradle :app:assembleDebug` builds the test APK. `gradle :app:bundleRelease` builds an unsigned AAB unless a private upload signing key is configured.

No advertising, third-party SDK, account, or Internet permission. Save data remains on-device; players may export/import JSON backups.

This is a playable city-building prototype, not an official SimCity product, and is not yet certified for publication. Before submitting: physical phone QA, unique package ID verification, accessibility, store graphics, a hosted privacy policy, accurate Data safety, content ratings, closed testing where applicable, and a securely signed release AAB.
