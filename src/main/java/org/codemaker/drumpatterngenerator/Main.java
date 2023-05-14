package org.codemaker.drumpatterngenerator;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;
import org.codemaker.drumpatterngenerator.domain.services.DrumPatternGeneratorService;
import org.codemaker.drumpatterngenerator.infrastructure.LilypondFileContent;

public class Main {
  public static void main(String[] args) throws IOException {

    // 1. Set up all options
    Option numberOfBarsOption = Option.builder("b").longOpt("numberOfBars").desc("number of bars which should be random-generated")
            .valueSeparator('=').required(true).hasArg(true).build();
    Option numberOfBarsPerLineOption = Option.builder("l").longOpt("numberOfBarsPerLine")
            .desc("number of bars which should appear in one line").valueSeparator('=').required(false).hasArg(true).build();
    Option outputOption = Option.builder("o").longOpt("output")
            .desc("path and name of the output file which contains the generated Lilypond content.").valueSeparator('=').required(true)
            .hasArg(true).build();
    Option helpOption = Option.builder("h").longOpt("help").desc("shows this usage info").required(false).hasArg(false).build();
    Options options = new Options();
    options.addOption(numberOfBarsOption);
    options.addOption(numberOfBarsPerLineOption);
    options.addOption(outputOption);
    options.addOption(helpOption);

    // 2. Parse the arguments and show usage help, if needed.
    CommandLine commandLine = null;
    try {
      commandLine = new DefaultParser().parse(options, args);
    } catch (ParseException e) {
      System.err.println("Error while parsing the arguments. " + e.getMessage() + "\n");
    }

    if (commandLine == null || commandLine.hasOption(helpOption.getOpt()) || commandLine.getOptions().length == 0) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp(100, "drumpatternpracticegenerator",
              "\nCreates a Lilypond file which contains a sequence of random-generated drum patterns.\n\n", options, "\n", true);
      return;
    }

    if (!commandLine.hasOption(numberOfBarsOption.getOpt())) {
      System.err.println(
              "Option " + numberOfBarsOption.getLongOpt() + " is missing. Without this information there is nothing to do here. " + "Call" +
                      " \"drumpatternpracticegenerator --help\" for a usage info.");
    }
    if (!commandLine.hasOption(outputOption.getOpt())) {
      System.err.println(
              "Option " + outputOption.getLongOpt() + " is missing. Without output filename there is nothing to do here. Call " +
                      "\"drumpatternpracticegenerator --help\" for a usage info.");
    }

    // 3. Derive CLI information to be able to start the real work
    String outputFilename = commandLine.getOptionValue(outputOption.getOpt());
    int numberOfBars = Integer.parseInt(commandLine.getOptionValue(numberOfBarsOption.getOpt()));
    int numberOfBarsPerLine = Integer.parseInt(commandLine.getOptionValue(numberOfBarsPerLineOption.getOpt()));
    if (numberOfBarsPerLine == 0) {
      numberOfBarsPerLine = 4;
    }

    // 4. Start the real work
    System.out.println();
    System.out.println("Creating the pattern using the following input information:");
    System.out.println("    number of bars in total: " + numberOfBars);
    System.out.println("    number of bars per line: " + numberOfBarsPerLine);
    System.out.println("    output file:             " + outputFilename);
    System.out.println();

    DrumPatternGeneratorService drumPatternGeneratorService = new DrumPatternGeneratorService(numberOfBars);
    DrumPattern drumPattern = drumPatternGeneratorService.createDrumPattern();
    LilypondFileContent lilypondFileContent = new LilypondFileContent(drumPattern, "A title", numberOfBarsPerLine);

    FileWriter fileWriter = new FileWriter(outputFilename, false);
    fileWriter.write(lilypondFileContent.getContent());
    fileWriter.close();

    System.out.println("Lilypond file created.");
    System.out.println();
    System.out.println("Now use \"lilypond -dresolution=500 --format png " + outputFilename + "\" to create both PNG and MIDI file.");
    System.out.println();
/*

    System.out.println("Writing the diagrams:");
    if (!Files.exists(outputFolderPath)) {
      Files.createDirectory(outputFolderPath);
    }
    TransitionStatePumlDiagramService transitionStatePumlDiagramService = new TransitionStatePumlDiagramService(transitionStateService);
    ScenarioPumlDiagramService scenarioPumlDiagramService = new ScenarioPumlDiagramService(scenarioService);
    BusinessEventPumlDiagramService businessEventPumlDiagramService = new BusinessEventPumlDiagramService(businessEventService);
    ScenarioSequencePumlDiagramService scenarioSequencePumlDiagramService = new ScenarioSequencePumlDiagramService(scenarioSequenceService);
    List<PumlDiagram> pumlDiagrams = new ArrayList<>();
    pumlDiagrams.add(scenarioPumlDiagramService.createDiagram());
    pumlDiagrams.add(transitionStatePumlDiagramService.createDiagram());
    pumlDiagrams.add(businessEventPumlDiagramService.createDiagram());
    pumlDiagrams.addAll(scenarioSequencePumlDiagramService.createDiagrams());
    for (PumlDiagram pumlDiagram : pumlDiagrams) {
      String diagramName = pumlDiagram.getName();
      Path outputFilePath = null;
      String diagramNamePattern = "(\\w+?)_(\\S+)"; // mind the reluctant regex quantifier, that is the "?" in "\w+?".
      if (diagramName.matches(diagramNamePattern)) {
        // If the diagram name contains an underscore, we interprete what is left from that underscore as a folder name.
        Matcher diagramNameMatcher = Pattern.compile(diagramNamePattern).matcher(diagramName);
        if (diagramNameMatcher.find()) {
          String folderName = diagramNameMatcher.group(1);
          Path subFolderPath = Paths.get(outputFolderPath.toAbsolutePath() + "/" + folderName);
          if (!Files.exists(subFolderPath)) {
            Files.createDirectory(subFolderPath);
          }
          outputFilePath = Paths.get(outputFolderPath + "/" + folderName + "/" + diagramName + ".puml");
        }
      } else {
        outputFilePath = Paths.get(outputFolderPath + "/" + diagramName + ".puml");
      }
      System.out.println("    " + outputFilePath);
      FileWriter fileWriter = new FileWriter(outputFilePath.toFile(), false);
      fileWriter.write(pumlDiagram.getContent());
      fileWriter.close();
    }
 */
  }
}
