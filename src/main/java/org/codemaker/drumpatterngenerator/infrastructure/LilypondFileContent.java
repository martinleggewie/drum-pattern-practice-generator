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
        StringBuilder builder = new StringBuilder();

        builder.append(version());
        builder.append("\n");
        builder.append("\n");
        builder.append(paper());
        builder.append("\n");

        return builder.toString();
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
}
