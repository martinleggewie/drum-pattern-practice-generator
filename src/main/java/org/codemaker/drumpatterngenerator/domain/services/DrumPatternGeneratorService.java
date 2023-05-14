package org.codemaker.drumpatterngenerator.domain.services;

import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;
import org.codemaker.drumpatterngenerator.domain.valueobjects.TimeSignature;

public class DrumPatternGeneratorService {
  private final int numberOfBars;

  public DrumPatternGeneratorService(int numberOfBars) {
    this.numberOfBars = numberOfBars;
  }

  public DrumPattern createDrumPattern() {
    TimeSignature timeSignature = new TimeSignature(4, 4);
    return new DrumPattern(timeSignature);
  }
}
