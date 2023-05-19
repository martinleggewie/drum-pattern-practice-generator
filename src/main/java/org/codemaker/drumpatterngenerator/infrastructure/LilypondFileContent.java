package org.codemaker.drumpatterngenerator.infrastructure;

import org.codemaker.drumpatterngenerator.domain.entities.Bar;
import org.codemaker.drumpatterngenerator.domain.entities.Beat;
import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;

import static org.codemaker.drumpatterngenerator.domain.entities.Beat.Type.NOTE;

public class LilypondFileContent {
  private final String title;
  private final DrumPattern drumPattern;

  private final int numberOfBarsPerLine;

  public LilypondFileContent(DrumPattern drumPattern, String title, int numberOfBarsPerLine) {
    this.title = title;
    this.drumPattern = drumPattern;
    this.numberOfBarsPerLine = numberOfBarsPerLine;
  }

  public String getContent() {
    return version() + "\n" + "\n" + paper() + "\n" + layout() + "\n" + book() + "\n";
  }

  private String version() {
    return "\\version \"2.24.1\"";
  }

  private String paper() {
    return "\\paper {\n" + //
            "    indent = 0\n" + //
            "    top-margin = 1\\cm\n" + //
            "    left-margin = 3\\cm\n" + //
            "    right-margin" + " = " + "3\\cm\n" + //
            "    ragged-right = ##f\n" + //
            "}\n";
  }

  private String layout() {
    return "\\layout {\n" + //
            "    \\context {\n" + //
            "        \\Score\n" + //
            "        \\remove \"Bar_number_engraver\"\n" + //
            "    }\n" + //
            "}\n";
  }

  private String book() {
    String timeSignatureString = drumPattern.getTimeSignature().numberOfBeats + "/" + drumPattern.getTimeSignature().barLength;
    StringBuilder builder = new StringBuilder();
    builder.append("\\book {\n");
    builder.append("    \\header {\n");
    builder.append("        title = \"" + title + "\"\n");
    builder.append("        tagline = \"\"\n");
    builder.append("    }\n");
    builder.append("    \\markup \\vspace #2\n");
    builder.append("    \\score {\n");
    builder.append("        \\new Staff {\n");
    builder.append("            \\time " + timeSignatureString + "\n");
    builder.append("            \\new Voice {\n");
    builder.append("                \\relative c'' {\n");

    int barNumber = 0;
    for (Bar currentBar : drumPattern.getBars()) {
      builder.append("                    ");
      for (int i = 0; i < currentBar.getBeats().size(); i++) {
        Beat currentBeat = currentBar.getBeats().get(i);
        builder.append(currentBeat.type == NOTE ? "a" : "r");
        builder.append(currentBeat.durationDenominator);
        if (i < currentBar.getBeats().size() - 1) {
          builder.append(" ");
        }
      }
      builder.append("\n");
      barNumber++;
      if (barNumber == numberOfBarsPerLine) {
        builder.append("                    \\break\n");
        barNumber = 0;
      }
    }

    builder.append("                }\n");
    builder.append("            }\n");
    builder.append("        }\n");
    builder.append("\n");
    builder.append("        \\layout { }\n");
    builder.append("\n");
    builder.append("        \\midi { }\n");
    builder.append("    }\n");
    builder.append("}");

    return builder.toString();
  }
}
