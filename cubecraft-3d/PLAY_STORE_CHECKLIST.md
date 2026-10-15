# CubeCraft 3D: Google Play release checklist

1. Verify the distinctive application ID `com.brightforge.cubecraft` is yours and is unique; confirm branding trademark availability.
2. Test devices and Android 8.0–16 navigation, multitouch/gestures, Web Worker initialization and solver performance, timers, battery use, portrait layout, device rotation and accessibility.
3. Run independent chess-like *cube state* regression and user acceptance testing: 3×3 scanned inputs, invalid states, each permutation and draw of rendering, 2×2–5×5 move reversibility.
4. If shipping the MIT cube.js solver, include `LICENSE-THIRD-PARTY.txt` with the package and make notices accessible to users.
5. Replace privacy-policy template contact placeholders, obtain legal review and host it at a public URL. Complete Data Safety and content rating forms truthfully.
6. Generate a release/upload keystore in your secure environment; configure Play App Signing. DO NOT upload signing keys or passwords to GitHub.
7. Produce release signed AAB. Debug APK and unsigned AAB must never be published.
8. Prepare 512x512 icon, 1024x500 feature graphic and actual gameplay screenshots. Check Google Play screenshot specifications and listing rules.
9. Ensure new app targetSdk 36 or above under Aug 31, 2026 policy, and meet any newer policies before submission.
10. Complete Play Console testing tracks and any applicable closed test requirements. Resolve pre-launch report crashes / ANRs.
11. Avoid copying competing app art, title, screenshots or store descriptions; only original code UI/art assets and MIT library properly attributed.
12. Decide monetization separately, without making inaccurate claims about purchases/ads or collecting personal data.