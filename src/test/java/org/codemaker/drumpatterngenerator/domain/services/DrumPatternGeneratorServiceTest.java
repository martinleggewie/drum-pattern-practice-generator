package org.codemaker.drumpatterngenerator.domain.services;

import org.codemaker.drumpatterngenerator.domain.entities.Bar;
import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;
import org.codemaker.drumpatterngenerator.domain.valueobjects.TimeSignature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DrumPatternGeneratorServiceTest {

  @Test
  void createDrumPattern() {
    // Arrange
    DrumPatternGeneratorService cut = new DrumPatternGeneratorService(16);

    // Act
    DrumPattern drumPatternActual = cut.createDrumPattern();

    // Assert
    assertNotNull(drumPatternActual);
    assertEquals(drumPatternActual.getTimeSignature(), new TimeSignature(4, 4));
    assertNotNull(drumPatternActual.getBars());
    int numberOfBars = drumPatternActual.getBars().size();
    assertEquals(16, numberOfBars);
    for (int i = 0; i < numberOfBars; i++) {
      Bar currentBar = drumPatternActual.getBars().get(i);
      assertNotNull(currentBar);
    }
  }
}