# Google Play Publication Checklist

## Already prepared

- [x] Separate original game source and responsive mobile HTML.
- [x] Local multiplayer and bots, complete deterministic rules tests, browser integration tests.
- [x] Application ID `com.brightforge.snakeladdersroyale` configured (verify uniqueness before publication).
- [x] Android 8.0+ support with Android 16 API 36 target.
- [x] Self-contained offline WebView; no INTERNET permission.
- [x] 512×512 PNG icon, 1024×500 feature graphic, 1080×1920 game screenshots.
- [x] Listing copy and privacy policy draft.
- [ ] Install and test APK on at least two real devices and varied WebView/Android versions.
- [ ] Fix discovered device-specific/UX issues and obtain accessibility feedback.
- [ ] Confirm the publisher has the rights to all source, assets, brand names, and distribution.
- [ ] Confirm unique app name/package name and handle trademark checks.
- [ ] Host the finalized privacy policy publicly and use a real publisher email.
- [ ] Create a release upload key locally; store securely and back up safely.
- [ ] Build and sign the **release AAB**. GitHub's unsigned test AAB is NOT Play Console ready.
- [ ] Complete Play Console's Data safety questionnaire consistently with the shipped code.
- [ ] Complete content-rating questionnaire, target-audience declaration, ads declaration, and app access form.
- [ ] Upload screenshots, store icon, feature graphic, description, and category.
- [ ] Run internal/closed testing as required for your developer account.
- [ ] Resolve review findings, then submit to Play Console for approval.

## Official Play policy references (checked October 2026)

- API target requirement starting August 31, 2026: Android 16, API 36, for new mobile apps and updates: https://support.google.com/googleplay/android-developer/answer/11926878
- Testing rules for newer personal accounts: 12 opted-in testers for 14 days for qualifying accounts: https://support.google.com/googleplay/android-developer/answer/14151465

**Scope warning:** This is a complete offline playable game, not online multiplayer, real-money competition, or a tournament service. There are no in-app purchases/ads until separately integrated and tested.
