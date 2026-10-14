#!/usr/bin/env python3
"""Royal Chess Studio: generate authentic game captures and Play Store graphics.

Screenshots are captured from the playable committed HTML using Chromium. No
screenshots are hand-composited or imported from competing games.
"""
from pathlib import Path
import shutil
from PIL import Image, ImageDraw, ImageFont
from playwright.sync_api import sync_playwright
ROOT=Path(__file__).resolve().parents[1]
OUT=ROOT/'store'
OUT.mkdir(parents=True,exist_ok=True)
html=(ROOT/'app/src/main/assets/index.html').read_text(encoding='utf-8')
if 'ChessCore' not in html or 'Start playing' not in html:
    raise RuntimeError("Incomplete chess game HTML")
bold='/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf'
serif='/usr/share/fonts/truetype/dejavu/DejaVuSerif.ttf'
chessfont='/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf'
def face(path,size):
    return ImageFont.truetype(path,size)
logo=Image.new('RGB',(512,512))
px=logo.load()
for y in range(512):
    for x in range(512):
        px[x,y]=(9+y//85,24+y//65,38+x//55)
d=ImageDraw.Draw(logo)
d.rounded_rectangle((40,40,472,472),radius=83,fill=(19,40,55),outline=(197,166,111),width=7)
for r in range(4):
    for c in range(4):
        x=109+c*74;y=101+r*74
        d.rectangle((x,y,x+74,y+74),fill=(50,76,75) if (r+c)%2 else (188,178,154))
d.ellipse((113,99,403,399),fill=(13,34,45),outline=(219,185,119),width=9)
d.text((251,242),'♞',font=face(chessfont,240),anchor='mm',fill=(245,227,181),stroke_fill=(11,21,33),stroke_width=3)
logo.save(OUT/'icon_512.png')
w,h=1024,500;banner=Image.new('RGB',(w,h));p=banner.load()
for y in range(h):
    for x in range(w):p[x,y]=(8+y//90,22+x//105,38+y//50)
d=ImageDraw.Draw(banner)
for r in range(5):
    for c in range(6):
        xx=624+c*72;yy=71+r*72
        d.rectangle((xx,yy,xx+72,yy+72),fill=(43,73,74) if (r+c)%2 else (182,174,145))
for xy,symbol in [((690,136),'♜'),((853,260),'♞'),((925,136),'♛')]:
    d.text(xy,symbol,font=face(chessfont,105),anchor='mm',fill=(13,29,41),stroke_width=1,stroke_fill=(219,196,145))
d.text((64,170),'ROYAL',font=face(serif,84),fill=(242,229,194))
d.text((64,254),'CHESS',font=face(serif,84),fill=(242,229,194))
d.text((68,325),'STUDIO  ·  PLAY ANYWHERE',font=face(bold,21),fill=(153,216,208))
banner.save(OUT/'feature_1024x500.png')

chromium=shutil.which('google-chrome') or shutil.which('chromium') or shutil.which('chromium-browser')
with sync_playwright() as pw:
    kw={'headless':True,'args':['--no-sandbox','--disable-dev-shm-usage']}
    if chromium:kw['executable_path']=chromium
    browser=pw.chromium.launch(**kw)
    page=browser.new_page(viewport={'width':540,'height':960},device_scale_factor=2,is_mobile=True,has_touch=True)
    errors=[]
    page.on('pageerror',lambda e:errors.append(str(e)))
    page.set_content(html,wait_until='load')
    if page.locator('.square').count()!=64: raise RuntimeError('Chessboard not rendered')
    def snap(n):
        path=OUT/f'play_screenshot_{n}.png'
        page.wait_for_timeout(250)
        page.screenshot(path=str(path),full_page=False,animations='disabled')
        with Image.open(path) as im:
            if im.size!=(1080,1920): raise RuntimeError('Unexpected screenshot dimensions')
        print(path.name,path.stat().st_size,'bytes')
    snap(1)
    page.locator('#startBtn').click()
    snap(2)
    page.locator('#newBtn').click()
    page.locator('[data-mode=local]').click()
    page.locator('#startBtn').click()
    for a,b in [(52,36),(12,28),(62,45),(1,18)]:
        page.locator(f'[data-square="{a}"]').click()
        page.locator(f'[data-square="{b}"]').click()
    snap(3)
    page.locator('#themeSelect').select_option('walnut')
    snap(4)
    page.locator('#themeSelect').select_option('ocean')
    snap(5)
    browser.close()
    if errors: raise RuntimeError('Browser JavaScript errors: '+str(errors[:3]))
print('Royal Chess Store PNG files generated and checked.')
