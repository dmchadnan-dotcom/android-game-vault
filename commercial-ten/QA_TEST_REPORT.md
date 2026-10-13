# Tenfold Arcade — Prototype QA Report

Build: v0.1.0, Android API 36, package ID com.brightforge.tenfold.

Automated browser checks: Chromium with Canvas rendering, desktop 1300×1000 and simulated Android phone 412×915.
- Ten launcher cards rendered; all ten games opened and returned to launcher.
- All ten ran without uncaught JavaScript exceptions during smoke tests.
- Gameplay actions tested: merge/spawn, color pour, polyomino placement, car slide, color flood, tower placement and wave start, tactical turn, racing acceleration, drone boost, raycasted maze map toggle.
- End-state tests passed for all ten games with deterministic pre-arranged board positions or near-complete objective states. This verifies the win-path logic, **not** a human playthrough of each game.
- Android debug APK and unsigned AAB compiled successfully in GitHub Actions run 38447091021.
- APK inspection verified an embedded offline 50,499-byte HTML game with all ten original modes, compiled bytecode, manifest and app icon.

NOT VERIFIED:
- Actual installation or use on physical Android phones (including lower-end devices).
- Frame rate, battery use, device heating, landscape behavior, accessibility labels/talkback, gesture navigation.
- Long-term save migration, restore after process death, all randomized level seeds and all loss paths.
- External playtesting, retention metrics, monetization and legal/commercial clearance of third-party assets if any are later added.

Release state: TEST BUILD. Do not treat this package as production-ready without fixing open QA gaps.