package org.codemaker.drumpatterngenerator.domain.services;

import java.util.Arrays;
import java.util.Random;

import org.codemaker.drumpatterngenerator.domain.entities.Bar;
import org.codemaker.drumpatterngenerator.domain.entities.Beat;
import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;
import org.codemaker.drumpatterngenerator.domain.valueobjects.TimeSignature;

public class DrumPatternGeneratorService {

  private final int[] durationDenominators = {1, 2, 4, 8, 16, 32, 64};
  private final int maxDurationDenominator = Arrays.stream(durationDenominators).max().getAsInt();

  private final Random random = new Random();

  private final int numberOfBars;

  public DrumPatternGeneratorService(int numberOfBars) {
    this.numberOfBars = numberOfBars;
  }

  public DrumPattern createDrumPattern() {
    TimeSignature timeSignature = new TimeSignature(4, 4);
    DrumPattern result = new DrumPattern(numberOfBars, timeSignature);
    int barDuration = barDuration(timeSignature);

    for (int currentBarIndex = 0; currentBarIndex < numberOfBars; currentBarIndex++) {
      int currentBarDurationLeft = barDuration;
      Bar currentBar = new Bar();
      while (currentBarDurationLeft > 0) {
        int currentMinDurationDenominator = minDurationDenominator(currentBarDurationLeft);
        Beat randomBeat = randomBeat(currentMinDurationDenominator);
        currentBar.getBeats().add(randomBeat);
        int currentBeatDuration = beatDuration(randomBeat.durationDenominator, timeSignature);
        currentBarDurationLeft -= currentBeatDuration;
      }
      result.getBars().add(currentBar);
    }
    return result;
  }

  int randomDurationDenominator(int minDurationDenominator) {
    int[] availableDurationDenominators = Arrays.stream(durationDenominators).filter(d -> minDurationDenominator <= d).toArray();
    int randomIndex = random.nextInt(availableDurationDenominators.length);
    return availableDurationDenominators[randomIndex];
  }

  Beat randomBeat(int minDurationDenominator) {
    int randomDurationDenominator = randomDurationDenominator(minDurationDenominator);
    Beat.Type randomType = random.nextBoolean() ? Beat.Type.NOTE : Beat.Type.REST;
    return new Beat(randomType, randomDurationDenominator);
  }

  int beatDuration(int durationDenominator, TimeSignature timeSignature) {
    int maxAllowedDuration = maxDurationDenominator * maxDurationDenominator * timeSignature.numberOfBeats / timeSignature.barLength;
    int duration = maxDurationDenominator * maxDurationDenominator / durationDenominator;
    if (duration > maxAllowedDuration) {
      throw new RuntimeException("DurationNominator " + durationDenominator + " not allowed with " + timeSignature);
    }
    return duration;
  }

  int barDuration(TimeSignature timeSignature) {
    return maxDurationDenominator * maxDurationDenominator / timeSignature.barLength * timeSignature.numberOfBeats;
  }

  int minDurationDenominator(int duration) {
    int durationFactor = duration / maxDurationDenominator;
    if (durationFactor < 1) {
      throw new RuntimeException("Duration " + duration + " is too short for a " + maxDurationDenominator + "th  note.");
    }
    float effectiveDurationDenominator = (float) maxDurationDenominator / (float) durationFactor;
    for (int durationDenominator : durationDenominators) {
      if (durationDenominator >= effectiveDurationDenominator) {
        return durationDenominator;
      }
    }

    throw new RuntimeException("Something went terribly wrong.");
  }
}
