package org.codemaker.drumpatterngenerator.domain.entities;

import java.util.ArrayList;
import java.util.List;

import org.codemaker.drumpatterngenerator.domain.valueobjects.TimeSignature;

public class DrumPattern {
  private final List<Bar> bars = new ArrayList<>();

  private final int numberOfBars;

  private final TimeSignature timeSignature;

  public DrumPattern(int numberOfBars, TimeSignature timeSignature) {
    this.numberOfBars = numberOfBars;
    this.timeSignature = timeSignature;
  }

  public List<Bar> getBars() {
    return bars;
  }

  public TimeSignature getTimeSignature() {
    return timeSignature;
  }

  @Override
  public String toString() {
    return "DrumPattern{\n" +
            "    bars=\n" + bars + "," +
            "\n    numberOfBars=" + numberOfBars + "," +
            "\n    timeSignature=" + timeSignature +
            '}';
  }
}
