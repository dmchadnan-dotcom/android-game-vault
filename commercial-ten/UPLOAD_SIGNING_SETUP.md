# Tenfold Arcade — release signing and Google Play setup

> **Status (2026-10-14):** A new 3072-bit RSA **upload key** and a matching **signed release AAB** were generated and verified locally. Neither the secret keystore nor its password are stored in this public GitHub repository. The signed file and secure key backups were delivered separately in the ChatGPT conversation.

## Upload certificate (public, not secret)

- Package/application ID: `com.brightforge.tenfold`
- Key alias: `tenfold-upload`
- Upload certificate SHA-256: `23:00:53:39:16:2E:41:6B:75:09:52:08:41:60:2D:8D:08:A6:A1:8A:9F:06:4B:5C:50:3D:5A:0B:A2:42:C8:45`

**Before uploading:** Compare this SHA-256 fingerprint with the **Upload key certificate** shown for the app in Play Console. If an existing Play Console app expects a different upload certificate, *do not* use this AAB to update it; sign with the correct registered key or request a key reset.

## Signing future builds locally

Download the private `.p12` keystore and its password from the ChatGPT message. Store them in a password manager / encrypted backup, never inside Git or unencrypted cloud drives.

The Android Gradle project at `commercial-ten/` accepts four environment variables:

- `ANDROID_KEYSTORE_PATH` — absolute path to private `.p12`
- `ANDROID_KEYSTORE_PASSWORD` — password
- `ANDROID_KEY_ALIAS` — `tenfold-upload`
- `ANDROID_KEY_PASSWORD` — same password for this PKCS12 keystore

After incrementing `versionCode` in `commercial-ten/app/build.gradle`, build the upload AAB:

```sh
cd commercial-ten
gradle :app:bundleRelease
jarsigner -verify -verbose -certs app/build/outputs/bundle/release/app-release.aab
```

Read the signing secrets from environment / an encrypted manager and *never* echo them in build logs.

## Prepare Google Play listing

The repository includes:
- PNG app icon: `store/icon_512.png`
- PNG feature graphic: `store/feature_graphic_1024x500.png`
- Original browser-rendered gameplay images: `store/screenshots/*.png` (1080 × 1920 pixels)
- `PRIVACY_POLICY_DRAFT.md`, `STORE_LISTING_DRAFT.md`, `PLAY_STORE_RELEASE_CHECKLIST.md`

Review screenshots against the **actual release installed on a phone**, including aspect ratio and UI readability, before final submission.

For Google Play, complete Play App Signing enrollment, app content declarations, Data safety, content rating, published privacy-policy URL, closed testing if required, developer account verification, device QA, and release review.

**A successful build and a cryptographic signature do NOT mean Play Console has accepted or published the app.** No Google Play upload or app signing key enrollment has been performed by ChatGPT.

References:
- https://developer.android.com/studio/publish/app-signing
- https://support.google.com/googleplay/android-developer/answer/9842756
- https://support.google.com/googleplay/android-developer/answer/9866151
