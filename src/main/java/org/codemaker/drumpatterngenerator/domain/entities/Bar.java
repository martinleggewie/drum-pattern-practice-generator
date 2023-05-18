package org.codemaker.drumpatterngenerator.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Bar {
  private final List<Beat> beats = new ArrayList<>();

  public List<Beat> getBeats() {
    return beats;
  }

  @Override
  public String toString() {
    return "        Bar{\n" + "            beats=" + beats + "        }\n";
  }
}
