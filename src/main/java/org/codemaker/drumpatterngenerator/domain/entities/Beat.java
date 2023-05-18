package org.codemaker.drumpatterngenerator.domain.entities;

public class Beat {
  public static enum Type {
    NOTE, REST
  }

  public final Type type;
  public final int durationDenominator;

  public Beat(Type type, int durationDenominator) {
    this.type = type;
    this.durationDenominator = durationDenominator;
  }

  @Override
  public String toString() {
    return "\n                Beat{type=" + type + ", durationDenominator=" + durationDenominator + "}";
  }
}
