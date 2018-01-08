#!/bin/sh
fluidsynth -F $1.wav /usr/share/sounds/sf2/OPL-3_FM_128M.sf2 $1.mid
sox $1.wav $1-2.wav vol 20 db rate 8k
oggenc -b 32 --downmix --resample 22050 $1-2.wav -o $1.ogg
rm $1.wav $1-2.wav $1.mid
