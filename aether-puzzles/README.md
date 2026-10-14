# Aether Puzzle Worlds
Six offline puzzle modes, 53 levels: Prism Lab (7), Circuit Forge (8), Crate Atlas (8), Chromatic Paths (12), Pixel Oracle (8), Shift Studio (10).
Android project: Java 17 / AGP 8.13.2 / Gradle 8.13 / compile and target API 36 / min API 26.
Build: `cd aether-puzzles && gradle :app:assembleDebug :app:bundleRelease`.
No Internet, storage, contacts or ads permissions. Gameplay is packaged as local HTML + JS via an offline Android WebView with a restricted resource origin. Saves are in local web storage.
Physical Android QA and Play Console publication are not completed. The release AAB from this test workflow is unsigned, NOT ready for Google Play until signed using your upload certificate.
See ChatGPT ZIP for full QA guide, store art drafts and privacy template.
