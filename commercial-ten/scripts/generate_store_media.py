#!/usr/bin/env python3
"""Generate authentic Tenfold browser gameplay screenshots and store PNG assets.

Usage: python commercial-ten/scripts/generate_store_media.py
Screenshots come directly from the committed playable HTML, not composited game mockups.
"""
from pathlib import Path
import shutil
import sys
from PIL import Image
from playwright.sync_api import sync_playwright
import cairosvg

ROOT = Path(__file__).resolve().parents[1]
GAME = ROOT / "app/src/main/assets/index.html"
OUT = ROOT / "store"
SCREENS = OUT / "screenshots"
SCREENS.mkdir(parents=True, exist_ok=True)
html = GAME.read_text(encoding="utf-8")
if "startGame(" not in html or "gameCard" not in html:
    raise RuntimeError("Tenfold game source is missing the game launcher")

cairosvg.svg2png(url=str(OUT / "icon_source.svg"),
                 write_to=str(OUT / "icon_512.png"), output_width=512, output_height=512)
cairosvg.svg2png(url=str(OUT / "feature_graphic_source.svg"),
                 write_to=str(OUT / "feature_graphic_1024x500.png"),
                 output_width=1024, output_height=500)

captures = [
    ("01_arcade_menu.png", None),
    ("02_merge_haven.png", "merge"),
    ("03_color_cascade.png", "sort"),
    ("04_block_bloom.png", "block"),
    ("05_garden_guardians.png", "towers"),
    ("06_neon_circuit.png", "racing"),
    ("07_labyrinth_vault.png", "maze"),
]

with sync_playwright() as pw:
    executable = (
        shutil.which("google-chrome") or
        shutil.which("chromium") or
        shutil.which("chromium-browser")
    )
    kwargs = {"headless": True, "args": ["--no-sandbox", "--disable-dev-shm-usage"]}
    if executable:
        kwargs["executable_path"] = executable
    browser = pw.chromium.launch(**kwargs)
    page = browser.new_page(
        viewport={"width": 540, "height": 960},
        device_scale_factor=2, is_mobile=True, has_touch=True
    )
    errors = []
    page.on("pageerror", lambda e: errors.append(str(e)))
    page.set_content(html, wait_until="domcontentloaded")
    page.wait_for_timeout(500)
    count = page.locator(".gameCard").count()
    if count != 10:
        raise RuntimeError(f"Expected 10 game cards, found {count}")
    for name, game_id in captures:
        if game_id:
            page.evaluate("(id) => startGame(id)", game_id)
            page.wait_for_timeout(500)
            if not page.locator("#gameTitle").inner_text().strip():
                raise RuntimeError(f"No visible game title for {game_id}")
        else:
            page.wait_for_timeout(150)
        path = SCREENS / name
        page.screenshot(path=str(path), animations="disabled")
        with Image.open(path) as im:
            if im.size != (1080, 1920):
                raise RuntimeError(f"Wrong PNG size for {path}: {im.size}")
        print(f"Created {path.relative_to(ROOT)} ({path.stat().st_size} bytes)")
    browser.close()
    if errors:
        raise RuntimeError(f"JavaScript error during screenshot capture: {errors[:3]}")
print("Generated genuine gameplay screenshots and store graphics successfully.")
