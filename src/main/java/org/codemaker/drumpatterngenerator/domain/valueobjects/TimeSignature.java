package org.codemaker.drumpatterngenerator.domain.valueobjects;

public class TimeSignature {
  public final int numberOfBeats;
  public final int barLength;

  public TimeSignature(int numberOfBeats, int barLength) {
    this.numberOfBeats = numberOfBeats;
    this.barLength = barLength;
  }
}
