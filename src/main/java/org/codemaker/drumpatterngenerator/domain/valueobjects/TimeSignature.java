package org.codemaker.drumpatterngenerator.domain.valueobjects;

import java.util.Objects;

public class TimeSignature {
  public final int numberOfBeats;
  public final int barLength;

  public TimeSignature(int numberOfBeats, int barLength) {
    this.numberOfBeats = numberOfBeats;
    this.barLength = barLength;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    TimeSignature that = (TimeSignature) o;
    return numberOfBeats == that.numberOfBeats && barLength == that.barLength;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numberOfBeats, barLength);
  }
}
