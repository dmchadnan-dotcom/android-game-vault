# Commercial Play Store release checklist

## Finished as of 16 October 2026

- [x] Original functioning HTML/JavaScript game, no external dependencies.
- [x] 120 journey levels, daily challenge, Free Drive, hints, undo, level unlocking, progress save, settings.
- [x] Deterministically checked car positions and graph-based solvability.
- [x] Responsive web interface tested at 320, 390 and 1440 px widths.
- [x] Android Studio project set to minSdk 26, targetSdk 36, compileSdk 36.
- [x] Offline WebView and blocked external requests; no INTERNET permission.
- [x] Original launcher icon, 1024×500 feature graphic, 1080×1920 gameplay screenshots, store copy, policy draft.

## Must complete before commercial release

- [ ] Install and manually QA a complete game on multiple physical Android devices, especially Android 15/16 edge-to-edge, gesture navigation, screen rotation, sound, accessibility, touch and suspend/resume.
- [ ] Accessibility checks (screen reader, large text, motion-reduction, color contrast), plus performance/battery tests.
- [ ] Review and enhance long-term level difficulty balancing and subjective player experience; consider tester feedback.
- [ ] Verify app name, logo and applicationId availability and avoid third-party trademark conflicts.
- [ ] Fill the publisher's legal name and support email in the privacy policy; host policy at a working public URL.
- [ ] Produce a secure **signed** Play App Bundle via a protected keystore / Google Play App Signing; safeguard signing/upload credentials. The automated test AAB is unsigned.
- [ ] Run Play Console internal testing and any required closed-testing period for your developer account; address pre-launch reports.
- [ ] Finalize store listing, screenshots, app category, age target, content rating, Data safety answers and app access declarations.
- [ ] Verify current target API, policy, testing and required identity verification in Google Play Console at submission time.
- [ ] Audit all app dependencies and update privacy/Data safety declarations if you add ad networks, analytics, purchases, push notifications or cloud saves.
- [ ] Verify real-user analytics/monetization goals independently rather than assuming revenue.

This checklist does not certify commercial readiness or approval by Google. Do not publish until outstanding items pass.
