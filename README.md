# Raster
This game is born as an experiment.
Raster is a puzzle game based on a white grid, you can play with your friend, or alone with the CPU.
Min 2 player, Max 4( ATM ), every player it has its own color.

#Hacking
All made using Processing 3.0

https://processing.org/download/

#Precompiled Processing libraries
Unfortunately Processing provides precompiled library required for this project:
* `processing-core.jar` − [processing/wiki/Build-Instructions](https://github.com/processing/processing/wiki/Build-Instructions)
* `apwidgets.jar` − [I dunno how to compile it actually](https://code.google.com/archive/p/apwidgets/source/default/source)

...and unfortunately it seems that Processing requires Oracle's JRE instead of the OpenJDK to compile the `processing-core.jar`. I'm following this bug report:

https://github.com/processing/processing/issues/4415
