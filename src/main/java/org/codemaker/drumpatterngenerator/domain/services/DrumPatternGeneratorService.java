package org.codemaker.drumpatterngenerator.domain.services;

import org.codemaker.drumpatterngenerator.domain.entities.Bar;
import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;
import org.codemaker.drumpatterngenerator.domain.valueobjects.TimeSignature;

public class DrumPatternGeneratorService {
  private final int numberOfBars;

  public DrumPatternGeneratorService(int numberOfBars) {
    this.numberOfBars = numberOfBars;
  }

  public DrumPattern createDrumPattern() {
    DrumPattern result = new DrumPattern(numberOfBars, new TimeSignature(4, 4));
    for (int i = 0; i < numberOfBars; i++) {
      Bar currentBar = new Bar();
      result.getBars().add(currentBar);
    }
    return result;
  }
}
