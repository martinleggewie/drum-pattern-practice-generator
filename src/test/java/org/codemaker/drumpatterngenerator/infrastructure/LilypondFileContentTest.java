package org.codemaker.drumpatterngenerator.infrastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;
import org.codemaker.drumpatterngenerator.domain.valueobjects.TimeSignature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LilypondFileContentTest {

  @Test
  void getContent() {
    // Arrange
    BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("test-drumpattern.ly"))));
    List<String> expectedLilypondFileContent = bufferedReader.lines().collect(Collectors.toList());
    DrumPattern drumPattern = new DrumPattern(16, new TimeSignature(4, 4));

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