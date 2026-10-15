# Google Play Publication Checklist — Bubble Pop Adventure

## Build / technical

- [x] Original HTML5 game and mobile UI bundled offline
- [x] Project configured for Android 16 (target/compile API 36), minimum API 26
- [x] App does not request Android permissions; network access blocked in WebView
- [x] Android Studio source prepared with Java 17 / AGP 8.13.2
- [x] Headless browser automated tests passed (60 levels / 3,461 assertions; mobile 320 / 390, desktop 1440 px; shooting, UI, pause, boosters)
- [ ] Verify full Android build in Android Studio or CI and retain debug APK
- [ ] Test on physical Android devices (screen density, memory, audio, back navigation, touch latency, install/uninstall, airplane mode)
- [ ] Complete gameplay QA on all 60 levels, especially level balance, ice bubbles, bomb/rainbow booster edge cases, high level difficulty
- [ ] Perform accessibility / screen reader and colorblind QA
- [ ] Release sign AAB with upload key kept in private keystore (NEVER commit keystore)
- [ ] Set unique permanent Play package/application ID and verify it is available
- [ ] Run Android lint, test startup/crash-free sessions and battery behavior

## Play Console

- [x] 512×512 store icon, 1024×500 feature graphic, 1080×1920 screenshots included
- [x] Draft title, short/long descriptions and privacy policy provided
- [ ] Replace privacy publisher/contact placeholders and publish final policy at public HTTPS URL
- [ ] Create Play Console listing, set countries, app category, support email
- [ ] Complete Data safety form to reflect the exact shipped app and any future SDKs
- [ ] Complete content rating questionnaire and target audience declaration
- [ ] Complete Ads declaration (currently no ads) and payment/IAP declarations (currently none)
- [ ] Confirm app content meets Families policy if marketed to children
- [ ] Check the final icon/name for trademark conflicts; ensure store screenshots accurately depict released game
- [ ] Upload signed release AAB and complete device catalog / pre-launch report
- [ ] For applicable new personal accounts: complete Google Play closed testing (12 testers / 14 continuous days) and request production access
- [ ] Perform final policy and publication checks, then submit for review

Google Play's API 36 target requirement took effect August 31, 2026 for new mobile apps and updates. Always verify the current official requirements before uploading.
