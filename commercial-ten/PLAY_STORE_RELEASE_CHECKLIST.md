# Tenfold Arcade - Google Play Publishing Checklist

## Technical
- [x] Unique application ID `com.brightforge.tenfold` (confirm you own and want this ID before submitting; cannot be changed for published app).
- [x] compileSdk/targetSdk 36, minSdk 26.
- [x] Offline WebView asset loading only; no Internet permission.
- [x] Debug APK build workflow.
- [x] Release AAB build workflow, **unsigned if signing secrets not configured**.
- [ ] Create upload key and store it privately; configure GitHub Actions encrypted secrets or sign locally.
- [ ] Check 16 KB page compatibility when applicable (for native code—current project uses no native C/C++ libraries).
- [ ] Test web content on Android WebView across real devices, including orientation, back navigation, accessibility, battery use, and save retention.
- [ ] Verify WebView package updates and offline operation on Android 8+.
- [ ] Review icons, color contrast, touch targets, no clipped UI on different aspect ratios.
- [ ] Run release testing and crash/ANR inspections in Play Console.

## Play Console
- [ ] Confirm developer identity and account type; check any production-testing eligibility requirement.
- [ ] Use AAB signed with secure upload certificate, not unsigned AAB or debug APK.
- [ ] Create app listing: descriptions, category Game/appropriate genres, screenshots, 512x512 icon, 1024x500 feature graphic, support email.
- [ ] Publish a real, publicly accessible privacy policy; replace the supplied local DRAFT with an actual URL.
- [ ] Complete Data safety accurately based on final app and any SDKs added later.
- [ ] Complete content rating questionnaire and target audience/Designed for Families declarations as appropriate. No ads at present.
- [ ] Complete government/legal declarations relevant to markets and developer payments/taxes if monetizing.
- [ ] Follow current Play policy for repetitive content, functionality and deceptive behavior; submit ONE differentiated multi-game collection, not ten reskinned copies.
- [ ] Run internal/closed tests and collect meaningful user feedback, including 12 testers for 14 days where account eligibility rules apply.

## Commercial roadmap
1. 50+ user interviews/playtests across Android tiers; instrument privacy-conscious opt-in analytics only after disclosures.
2. Choose top 2–3 games based on tutorial completion, session length, replay, D1/D7 retention and player feedback.
3. Expand each selected title into original levels, art/audio, onboarding, progression, adaptive difficulty, accessibility and localization.
4. Monetization experiments: cosmetic upgrades, optional rewarded ads, or one-time premium purchase after user testing. Do not add SDKs without updating the privacy policy and Data safety declarations.
5. Validate crash-free sessions, performance, game fairness and account/site policy before public launch.