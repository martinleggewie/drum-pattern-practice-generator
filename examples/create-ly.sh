#!/bin/zsh

echo "Using drumpatterngenerator to create the example Lilypond files."

java -jar ../target/drumpatterngenerator-1.0.0.jar -o drumpattern_default-settings.ly
java -jar ../target/drumpatterngenerator-1.0.0.jar -b 9 -l 3 -d 1,2,4 -t "9 bars, 3 bars per line, whole/half/quarter" -o drumpattern_9-bars_3-bars-per-line_whole-half-quarter.ly
java -jar ../target/drumpatterngenerator-1.0.0.jar -b 40 -l 4 -d 2,4 -t "40 bars, 4 bars per line, half/quarter" -o drumpattern_40-bars_4-bars-per-line_half-quarter.ly
java -jar ../target/drumpatterngenerator-1.0.0.jar -b 40 -l 4 -d 4 -t "40 bars, 4 bars per line, quarter" -o drumpattern_40-bars_4-bars-per-line_quarter.ly
