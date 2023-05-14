package org.codemaker.drumpatterngenerator;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

  public static void main(String[] args) throws IOException {

    // 1. Set up all options
    Option numberOfBarsOption = Option.builder("b").longOpt("numberOfBars")
            .desc("number of bars which should be random-generated").valueSeparator('=')
            .required(true).hasArg(true).build();
    Option outputOption = Option.builder("o").longOpt("output")
            .desc("path and name of the output file which contains the generated Lilypond content.")
            .valueSeparator('=').required(true).hasArg(true).build();
    Option helpOption = Option.builder("h").longOpt("help").desc("shows this usage info").required(false).hasArg(false).build();
    Options options = new Options();
    options.addOption(numberOfBarsOption);
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
              "\nCreates a Lilypond file which contains a sequence of random-generated drum patterns.\n\n",
              options, "\n", true);
      return;
    }

    if (!commandLine.hasOption(outputOption.getOpt())) {
      System.err.println(
              "Option " + outputOption.getLongOpt() + " is missing. Without output filename there is nothing to do here. Call " +
                      "\"drumpatternpracticegenerator --help\" for a usage info.");
    }

    // 3. Derive input and output file information to be able to start the real work
/*

    String inputFilename = commandLine.getOptionValue(inputOption.getOpt());
    Path inputFilePath = Paths.get(inputFilename);

    String outputFoldername = commandLine.getOptionValue(outputOption.getOpt());
    Path outputFolderPath;
    if (outputFoldername == null) {
      outputFolderPath = inputFilePath.toAbsolutePath().getParent();
    } else {
      outputFolderPath = Paths.get(outputFoldername);
    }
*/
    // 4. Start the real work
/*
    System.out.println();
    System.out.println("Preparing:");
    System.out.println("    input file:    " + inputFilePath.toAbsolutePath());
    System.out.println("    output folder: " + outputFolderPath.toAbsolutePath());

    System.out.println();
    System.out.println("Reading the Excel file:");
    XSSFWorkbook workbook = new XSSFWorkbook(Files.newInputStream(inputFilePath.toFile().toPath()));
    DescriptorRepository descriptorRepository = new XSSFWorkbookDescriptionRepository(workbook);
    TransitionStateService transitionStateService = new TransitionStateService(descriptorRepository);
    ScenarioService scenarioService = new ScenarioService(descriptorRepository);
    BusinessEventService businessEventService = new BusinessEventService(descriptorRepository, scenarioService);
    ScenarioSequenceService scenarioSequenceService = new ScenarioSequenceService(businessEventService, scenarioService,
            transitionStateService, descriptorRepository);
    System.out.println();
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
