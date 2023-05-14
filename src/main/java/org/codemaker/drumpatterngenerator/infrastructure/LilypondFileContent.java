package org.codemaker.drumpatterngenerator.infrastructure;

import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;

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

    String builder = version() + "\n" + "\n" + paper() + "\n" + layout() + "\n" + maincontent() + "\n";

    return builder;
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

  private String maincontent() {

    String builder =
            "\\book {\n" + "    \\header {\n" + "        title = \"" + title + "\"\n" + "        tagline = \"\"\n" + "    }\n" + "    " +
                    "\\markup \\vspace #2\n" + "    \\score {\n" + "        \\new Staff {\n" + "            \\time 4/4\n" + "            " +
                    "\\new Voice {\n" + "            }\n" + "        }\n\n" + "        \\layout { }\n\n" + "        \\midi { }\n" + "    " +
                    "}\n" + "}\n\n";

    return builder;
  }
}
