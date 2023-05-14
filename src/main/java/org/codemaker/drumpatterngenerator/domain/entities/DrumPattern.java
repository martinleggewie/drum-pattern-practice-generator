package org.codemaker.drumpatterngenerator.domain.entities;

import java.util.ArrayList;
import java.util.List;

import org.codemaker.drumpatterngenerator.domain.valueobjects.TimeSignature;

public class DrumPattern {
    private final List<Bar> bars = new ArrayList<>();

    private final TimeSignature timeSignature;

    public DrumPattern(TimeSignature timeSignature) {
        this.timeSignature = timeSignature;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public TimeSignature getTimeSignature() {
        return timeSignature;
    }
}
