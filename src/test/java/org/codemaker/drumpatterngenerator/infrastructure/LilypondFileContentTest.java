package org.codemaker.drumpatterngenerator.infrastructure;

import org.codemaker.drumpatterngenerator.domain.entities.Bar;
import org.codemaker.drumpatterngenerator.domain.entities.Beat;
import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;
import org.codemaker.drumpatterngenerator.domain.valueobjects.TimeSignature;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.codemaker.drumpatterngenerator.domain.entities.Beat.Type.NOTE;
import static org.codemaker.drumpatterngenerator.domain.entities.Beat.Type.REST;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LilypondFileContentTest {

  @Test
  void getContent() {
    // Arrange
    BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("test-drumpattern.ly"))));
    List<String> expectedLilypondFileContent = bufferedReader.lines().collect(Collectors.toList());
    DrumPattern drumPattern = new DrumPattern(8, new TimeSignature(4, 4));
    Bar bar1 = new Bar();
    bar1.getBeats().add(new Beat(NOTE, 1));
    Bar bar2 = new Bar();
    bar2.getBeats().add(new Beat(NOTE, 2));
    bar2.getBeats().add(new Beat(NOTE, 2));
    Bar bar3 = new Bar();
    bar3.getBeats().add(new Beat(NOTE, 4));
    bar3.getBeats().add(new Beat(NOTE, 4));
    bar3.getBeats().add(new Beat(NOTE, 4));
    bar3.getBeats().add(new Beat(NOTE, 4));
    Bar bar4 = new Bar();
    bar4.getBeats().add(new Beat(NOTE, 8));
    bar4.getBeats().add(new Beat(NOTE, 8));
    bar4.getBeats().add(new Beat(NOTE, 8));
    bar4.getBeats().add(new Beat(NOTE, 8));
    bar4.getBeats().add(new Beat(NOTE, 8));
    bar4.getBeats().add(new Beat(NOTE, 8));
    bar4.getBeats().add(new Beat(NOTE, 8));
    bar4.getBeats().add(new Beat(NOTE, 8));
    Bar bar5 = new Bar();
    bar5.getBeats().add(new Beat(REST, 1));
    Bar bar6 = new Bar();
    bar6.getBeats().add(new Beat(REST, 2));
    bar6.getBeats().add(new Beat(REST, 2));
    Bar bar7 = new Bar();
    bar7.getBeats().add(new Beat(REST, 4));
    bar7.getBeats().add(new Beat(REST, 4));
    bar7.getBeats().add(new Beat(REST, 4));
    bar7.getBeats().add(new Beat(REST, 4));
    Bar bar8 = new Bar();
    bar8.getBeats().add(new Beat(REST, 8));
    bar8.getBeats().add(new Beat(REST, 8));
    bar8.getBeats().add(new Beat(REST, 8));
    bar8.getBeats().add(new Beat(REST, 8));
    bar8.getBeats().add(new Beat(REST, 8));
    bar8.getBeats().add(new Beat(REST, 8));
    bar8.getBeats().add(new Beat(REST, 8));
    bar8.getBeats().add(new Beat(REST, 8));
    drumPattern.getBars().add(bar1);
    drumPattern.getBars().add(bar2);
    drumPattern.getBars().add(bar3);
    drumPattern.getBars().add(bar4);
    drumPattern.getBars().add(bar5);
    drumPattern.getBars().add(bar6);
    drumPattern.getBars().add(bar7);
    drumPattern.getBars().add(bar8);

    // Act
    LilypondFileContent cut = new LilypondFileContent(drumPattern, "My first test drum pattern", 4);
    List<String> resultContent = Arrays.asList(cut.getContent().split("\n"));

    // Assert
    assertEquals(expectedLilypondFileContent.size(), resultContent.size());
    for (int i = 0; i < resultContent.size(); i++) {
      assertEquals(expectedLilypondFileContent.get(i), resultContent.get(i), "Content differs in line " + (i + 1));
    }
  }
}