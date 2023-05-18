package org.codemaker.drumpatterngenerator.domain.services;

import org.codemaker.drumpatterngenerator.domain.entities.Bar;
import org.codemaker.drumpatterngenerator.domain.entities.Beat;
import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;
import org.codemaker.drumpatterngenerator.domain.valueobjects.TimeSignature;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DrumPatternGeneratorServiceTest {

  @Test
  void createDrumPattern() {
    // Arrange
    DrumPatternGeneratorService cut = new DrumPatternGeneratorService(1000);

    // Act
    DrumPattern drumPatternActual = cut.createDrumPattern();
    TimeSignature timeSignatureActual = drumPatternActual.getTimeSignature();

    // Assert
    assertNotNull(drumPatternActual);
    assertEquals(timeSignatureActual, new TimeSignature(4, 4));
    assertNotNull(drumPatternActual.getBars());
    int numberOfBars = drumPatternActual.getBars().size();
    assertEquals(1000, numberOfBars);
    for (Bar currentBar : drumPatternActual.getBars()) {
      assertNotNull(currentBar);
      int currentBarDuration = 0;
      for (Beat currentBeat : currentBar.getBeats()) {
        assertNotNull(currentBeat);
        currentBarDuration += 4096 / currentBeat.durationDenominator;
      }
      assertEquals(4096, currentBarDuration);
    }
  }

  @Test
  void randomDurationDenominator() {
    // Arrange
    final int[] durationDenominators = {1, 2, 4, 8, 16, 32, 64};
    DrumPatternGeneratorService cut = new DrumPatternGeneratorService(1);

    // Act and Assert
    int[] currentDurationDenominators = durationDenominators;
    while (currentDurationDenominators.length > 0) {
      for (int i = 0; i < 100000; i++) {
        int durationDenominator = cut.randomDurationDenominator(currentDurationDenominators[0]);
        assertTrue(Arrays.stream(durationDenominators).anyMatch(d -> (d == durationDenominator)));
      }
      currentDurationDenominators = Arrays.copyOfRange(currentDurationDenominators, 1, currentDurationDenominators.length);
    }
  }

  @Test
  void beatDuration_3over4() {
    // Arrange
    DrumPatternGeneratorService cut = new DrumPatternGeneratorService(1);

    // Act and Assert
    TimeSignature timeSignature3over4 = new TimeSignature(3, 4);
    try {
      cut.beatDuration(1, timeSignature3over4);
      fail();
    } catch (RuntimeException e) {
      // left intentionally blank
    }
    assertEquals(2048, cut.beatDuration(2, timeSignature3over4));
    assertEquals(1024, cut.beatDuration(4, timeSignature3over4));
    assertEquals(512, cut.beatDuration(8, timeSignature3over4));
    assertEquals(256, cut.beatDuration(16, timeSignature3over4));
    assertEquals(128, cut.beatDuration(32, timeSignature3over4));
    assertEquals(64, cut.beatDuration(64, timeSignature3over4));
  }

  @Test
  void beatDuration_4over4() {
    // Arrange
    DrumPatternGeneratorService cut = new DrumPatternGeneratorService(1);

    // Act and Assert
    TimeSignature timeSignature4over4 = new TimeSignature(4, 4);
    assertEquals(4096, cut.beatDuration(1, timeSignature4over4));
    assertEquals(2048, cut.beatDuration(2, timeSignature4over4));
    assertEquals(1024, cut.beatDuration(4, timeSignature4over4));
    assertEquals(512, cut.beatDuration(8, timeSignature4over4));
    assertEquals(256, cut.beatDuration(16, timeSignature4over4));
    assertEquals(128, cut.beatDuration(32, timeSignature4over4));
    assertEquals(64, cut.beatDuration(64, timeSignature4over4));
  }

  @Test
  void beatDuration_3over8() {
    // Arrange
    DrumPatternGeneratorService cut = new DrumPatternGeneratorService(1);

    // Act and Assert
    TimeSignature timeSignature3over8 = new TimeSignature(3, 8);
    try {
      cut.beatDuration(1, timeSignature3over8);
      fail();
    } catch (RuntimeException e) {
      // left intentionally blank
    }
    try {
      cut.beatDuration(2, timeSignature3over8);
      fail();
    } catch (RuntimeException e) {
      // left intentionally blank
    }
    assertEquals(1024, cut.beatDuration(4, timeSignature3over8));
    assertEquals(512, cut.beatDuration(8, timeSignature3over8));
    assertEquals(256, cut.beatDuration(16, timeSignature3over8));
    assertEquals(128, cut.beatDuration(32, timeSignature3over8));
    assertEquals(64, cut.beatDuration(64, timeSignature3over8));
  }

  @Test
  void beatDuration_7over8() {
    // Arrange
    DrumPatternGeneratorService cut = new DrumPatternGeneratorService(1);

    // Act and Assert
    TimeSignature timeSignature7over8 = new TimeSignature(7, 8);
    try {
      cut.beatDuration(1, timeSignature7over8);
      fail("RuntimeException should have been thrown.");
    } catch (RuntimeException e) {
      // left intentionally blank
    }
    assertEquals(2048, cut.beatDuration(2, timeSignature7over8));
    assertEquals(1024, cut.beatDuration(4, timeSignature7over8));
    assertEquals(512, cut.beatDuration(8, timeSignature7over8));
    assertEquals(256, cut.beatDuration(16, timeSignature7over8));
    assertEquals(128, cut.beatDuration(32, timeSignature7over8));
    assertEquals(64, cut.beatDuration(64, timeSignature7over8));
  }

  @Test
  void beatDuration_8over8() {
    // Arrange
    DrumPatternGeneratorService cut = new DrumPatternGeneratorService(1);

    // Act and Assert
    TimeSignature timeSignature8over8 = new TimeSignature(8, 8);
    assertEquals(4096, cut.beatDuration(1, timeSignature8over8));
    assertEquals(2048, cut.beatDuration(2, timeSignature8over8));
    assertEquals(1024, cut.beatDuration(4, timeSignature8over8));
    assertEquals(512, cut.beatDuration(8, timeSignature8over8));
    assertEquals(256, cut.beatDuration(16, timeSignature8over8));
    assertEquals(128, cut.beatDuration(32, timeSignature8over8));
    assertEquals(64, cut.beatDuration(64, timeSignature8over8));
  }

  @Test
  void beatDuration_9over8() {
    // Arrange
    DrumPatternGeneratorService cut = new DrumPatternGeneratorService(1);

    // Act and Assert
    TimeSignature timeSignature9over8 = new TimeSignature(9, 8);
    assertEquals(4096, cut.beatDuration(1, timeSignature9over8));
    assertEquals(2048, cut.beatDuration(2, timeSignature9over8));
    assertEquals(1024, cut.beatDuration(4, timeSignature9over8));
    assertEquals(512, cut.beatDuration(8, timeSignature9over8));
    assertEquals(256, cut.beatDuration(16, timeSignature9over8));
    assertEquals(128, cut.beatDuration(32, timeSignature9over8));
    assertEquals(64, cut.beatDuration(64, timeSignature9over8));
  }

  @Test
  void barDuration() {
    // Arrange
    DrumPatternGeneratorService cut = new DrumPatternGeneratorService(1);

    // Act and Assert
    assertEquals(1024, cut.barDuration(new TimeSignature(1, 4)));
    assertEquals(2 * 1024, cut.barDuration(new TimeSignature(2, 4)));
    assertEquals(3 * 1024, cut.barDuration(new TimeSignature(3, 4)));
    assertEquals(4 * 1024, cut.barDuration(new TimeSignature(4, 4)));
    assertEquals(512, cut.barDuration(new TimeSignature(1, 8)));
    assertEquals(2 * 512, cut.barDuration(new TimeSignature(2, 8)));
    assertEquals(3 * 512, cut.barDuration(new TimeSignature(3, 8)));
    assertEquals(4 * 512, cut.barDuration(new TimeSignature(4, 8)));
    assertEquals(5 * 512, cut.barDuration(new TimeSignature(5, 8)));
    assertEquals(6 * 512, cut.barDuration(new TimeSignature(6, 8)));
    assertEquals(7 * 512, cut.barDuration(new TimeSignature(7, 8)));
    assertEquals(8 * 512, cut.barDuration(new TimeSignature(8, 8)));
    assertEquals(9 * 512, cut.barDuration(new TimeSignature(9, 8)));
    assertEquals(10 * 512, cut.barDuration(new TimeSignature(10, 8)));
    assertEquals(11 * 512, cut.barDuration(new TimeSignature(11, 8)));
    assertEquals(12 * 512, cut.barDuration(new TimeSignature(12, 8)));
    assertEquals(13 * 512, cut.barDuration(new TimeSignature(13, 8)));
    assertEquals(14 * 512, cut.barDuration(new TimeSignature(14, 8)));
    assertEquals(15 * 512, cut.barDuration(new TimeSignature(15, 8)));
    assertEquals(16 * 512, cut.barDuration(new TimeSignature(16, 8)));
    assertEquals(17 * 512, cut.barDuration(new TimeSignature(17, 8)));
    assertEquals(18 * 512, cut.barDuration(new TimeSignature(18, 8)));
    assertEquals(19 * 512, cut.barDuration(new TimeSignature(19, 8)));
    assertEquals(20 * 512, cut.barDuration(new TimeSignature(20, 8)));
  }

  @Test
  void minDurationDenominator() {
    // Arrange
    DrumPatternGeneratorService cut = new DrumPatternGeneratorService(1);

    // Act and Assert
    try {
      cut.minDurationDenominator(0);
      fail("RuntimeException should have been thrown.");
    } catch (RuntimeException e) {
      // left intentionally blank
    }
    try {
      cut.minDurationDenominator(63);
      fail("RuntimeException should have been thrown.");
    } catch (RuntimeException e) {
      // left intentionally blank
    }
    assertEquals(64, cut.minDurationDenominator(64));
    assertEquals(64, cut.minDurationDenominator(127));
    assertEquals(32, cut.minDurationDenominator(128));
    assertEquals(32, cut.minDurationDenominator(255));
    assertEquals(16, cut.minDurationDenominator(256));
    assertEquals(16, cut.minDurationDenominator(511));
    assertEquals(8, cut.minDurationDenominator(512));
    assertEquals(8, cut.minDurationDenominator(1023));
    assertEquals(4, cut.minDurationDenominator(1024));
    assertEquals(4, cut.minDurationDenominator(2047));
    assertEquals(2, cut.minDurationDenominator(2048));
    assertEquals(2, cut.minDurationDenominator(4095));
    assertEquals(1, cut.minDurationDenominator(4096));
    assertEquals(1, cut.minDurationDenominator(10000));
    assertEquals(1, cut.minDurationDenominator(100000));
  }
}
