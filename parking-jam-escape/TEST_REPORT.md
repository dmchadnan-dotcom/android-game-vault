# Parking Jam Escape — automated test report

Automated rules and browser interaction tests were executed 16 October 2026 in the local project environment.

## Rules engine

**22,866 successful assertions across 460 generated levels**: 120 production journey levels, 250 additional generated levels, 90 daily seeds. Checked deterministic generation, non-overlapping car cells, acyclic dependency graphs, legal escape orders, blocked-car detection, and completing every car-removal sequence. All passed. 6,662 simulated legal vehicle exits completed. Levels ranged up to 18 vehicles with dependency depth up to 10.

## Browser UI

Desktop (1440×900), regular phone (390×844) and small phone (320×710) tests: page starts with menu; all cars render within board; zero JS page exceptions; no horizontal overflow; clicking actual free car drives out; undo restores state; hint token decrements; controls work.

Complete level test on phone viewport: tap actual blocked car (heart decrement), then safely exit every car, see success dialog and star reward, unlock level 2, switch to daily challenge, switch to Free Drive, toggle sound. Passed with zero JS page errors.

## Not tested yet

Physical Android devices, all Android API versions, store sandbox, payment flows (none implemented), long-term retention / progression telemetry (no analytics), performance across low-end devices, hardware back gestures. Store publication and production signing are NOT completed by these tests.
