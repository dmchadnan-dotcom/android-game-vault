# Automated Test Report — Snake & Ladders Royale

## Rules tests

- `node tests/validate_rules.js`: **PASS 64,756 assertions**, including 300 independently simulated complete matches.
- Modes: Classic 100 tiles, Sprint 64 tiles, Adventure 12 chapters, Daily date-seeded mode.
- Confirmed fair die bounds, ladders always lead up, snakes always lead down, no duplicated jump origins, 2–4 players, humans and bots, six extra turn, third consecutive six forfeit, exact finish, oversized rolls, won match, save validation, and deterministic board generation.
- All 300 simulated games reached a winning player.

## Browser integration tests

- `python tests/browser_test.py`: **PASS** in Chromium at 320×700, 390×844, and 1440×960.
- Confirmed mode setup, 2/4-player selection, clicking ROLL DICE, Adventure chapter selection, rule dialog, switching to Sprint (64-square board), saving and resuming a game, and no horizontal overflow/uncaught page errors.
- Actual gameplay screenshots are saved in `store/`.

## Not yet independently verified

- Physical Android device gameplay, low-memory devices, accessibility services, and battery/thermal behavior.
- Publisher legal and policy review, production release key, remote Play Console upload, and store approval.

## Design limitations

The board rendering is an original 2D SVG with rich shading and animations, not true 3D. Bots use the same random dice probabilities as humans, not a cheating/difficulty engine. The app deliberately includes no online multiplayer, advertisements, in-app purchases, or accounts.
