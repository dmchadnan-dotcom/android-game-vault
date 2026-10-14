# Shadow Circuit: Retro Fighters

A new, original offline 2D fighting-game project inspired by the **genre** of 1990s arcade martial-arts games. Not affiliated with, endorsed by or derived from the Mortal Kombat franchise.

## Planned Android release
- Six original fighters and six arenas
- Arcade ladder, Survival, Versus AI, Local 2P, Training
- Punch, kick, sweep, aerial attacks, guarding, throws, energy special
- Original procedural art and synthetic sound
- API 36, offline WebView, no dangerous or Internet permissions
- Application ID `com.brightforge.shadowcircuit`

## Current GitHub status
This folder currently contains **CI and project documentation**. The playable source files and Android Studio project are available in the ChatGPT-generated downloadable archive `Shadow_Circuit_Fighters_Android_Source.zip`, **but must still be imported** to this repository under `shadow-circuit/android/`. Until that import is complete, the workflow won't produce an APK or AAB.

To import, extract the source archive and copy the contents of `ShadowCircuit_Fighters/android/` into `shadow-circuit/android/`. Keep `app/src/main/assets/index.html` and the original PNG app icon. Do not commit private keystores or signing passwords.

Go to [build workflow](../../actions/workflows/build-shadow-circuit.yml) when import is complete. The workflow yields a **debug APK and unsigned AAB**. Production signing requires your upload key and Play Console configuration.

## Google Play
Google Play does not automatically publish games from GitHub. A Play Console developer must create the app, complete content rating/Data safety/privacy and testing, sign the AAB, upload it, and obtain review approval. The Play Console account and upload key have not been supplied or used for this game.
