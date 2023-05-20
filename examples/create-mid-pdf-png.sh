#!/bin/zsh

echo "Creating all MIDI, PDF, and PNG files out of the Lilypond files."

for f in *; do
    filename=$f:t:r;
    fileextension=$f:t:e;

    if [[ $fileextension == "ly" ]];
    then
        echo "Found $f which is a Lilypond file."
        echo "Creating MIDI and PDF ..."
        lilypond -dmidi-extension=mid --format pdf $f
        echo "Creating MIDI and PNG ..."
        lilypond -dmidi-extension=mid -dresolution=500 --format png $f
    fi
done;
