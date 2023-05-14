package org.codemaker.drumpatterngenerator.infrastructure;

import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;

public class LilypondFileContent {
    private final String title;
    private final DrumPattern drumPattern;

    public LilypondFileContent(String title, DrumPattern drumPattern) {
        this.title = title;
        this.drumPattern = drumPattern;
    }

    public String getContent() {
        return "";
    }
}
