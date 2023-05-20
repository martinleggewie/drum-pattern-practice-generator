package org.codemaker.drumpatterngenerator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.codemaker.drumpatterngenerator.domain.entities.DrumPattern;
import org.codemaker.drumpatterngenerator.domain.services.DrumPatternGeneratorService;
import org.codemaker.drumpatterngenerator.infrastructure.LilypondFileContent;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {

  public static final int[] DEFAULT_DURATION_DENOMINATORS = {1, 2, 4, 8, 16, 32, 64};

  public static void main(String[] args) throws IOException {

    // 1. Set up all options
    Option numberOfBarsOption = Option.builder("b").longOpt("numberOfBars").desc("number of bars which should be random-generated")
            .valueSeparator('=').required(false).hasArg(true).build();
    Option numberOfBarsPerLineOption = Option.builder("l").longOpt("numberOfBarsPerLine")
            .desc("number of bars which should appear in one line").valueSeparator('=').required(false).hasArg(true).build();
    Option durationDenominatorsOption = Option.builder("d").longOpt("durationDenominators")
            .desc("comma-separated list of all duration denominators which should be used to generate the pattern").valueSeparator('=')
            .required(false).hasArg(true).build();
    Option titleOption = Option.builder("t").longOpt("title").desc("title to appear at the top of the generated score").valueSeparator('=')
            .required(false).hasArg(true).build();
    Option outputOption = Option.builder("o").longOpt("output")
            .desc("path and name of the output file which contains the generated Lilypond content.").valueSeparator('=').required(true)
            .hasArg(true).build();
    Option helpOption = Option.builder("h").longOpt("help").desc("shows this usage info").required(false).hasArg(false).build();
    Options options = new Options();
    options.addOption(numberOfBarsOption);
    options.addOption(numberOfBarsPerLineOption);
    options.addOption(durationDenominatorsOption);
    options.addOption(titleOption);
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

    // 3. If there is no CLI parameter provided at all, then show the help message.
    if (!commandLine.hasOption(outputOption.getOpt())) {
      System.err.println(
              "Option " + outputOption.getLongOpt() + " is missing. Without output filename there is nothing to do here. Call " +
                      "\"drumpatternpracticegenerator --help\" for a usage info.");
    }

    // 4. Derive CLI information to be able to start the real work
    String outputFilename = commandLine.getOptionValue(outputOption.getOpt());
    int numberOfBars = Integer.parseInt(commandLine.getOptionValue(numberOfBarsOption.getOpt(), "16"));
    int numberOfBarsPerLine = Integer.parseInt(commandLine.getOptionValue(numberOfBarsPerLineOption.getOpt(), "4"));
    String durationDenominatorsString = commandLine.getOptionValue(durationDenominatorsOption.getOpt());
    int[] durationDenominators;
    if (durationDenominatorsString == null || durationDenominatorsString.trim().length() == 0) {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < DEFAULT_DURATION_DENOMINATORS.length; i++) {
        builder.append(DEFAULT_DURATION_DENOMINATORS[i]);
        if (i < DEFAULT_DURATION_DENOMINATORS.length - 1) {
          builder.append(",");
        }
      }
      durationDenominatorsString = builder.toString();
      durationDenominators = DEFAULT_DURATION_DENOMINATORS;
    } else {
      durationDenominators = Arrays.stream(durationDenominatorsString.split(",")).mapToInt(Integer::parseInt).toArray();
    }
    String title = commandLine.getOptionValue(titleOption.getOpt());
    if (title == null || title.trim().length() == 0) {
      DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      title = formatter.format(new Date());
    }

    // 5. Start the real work
    System.out.println();
    System.out.println("Creating the pattern using the following input information:");
    System.out.println("    number of bars in total: " + numberOfBars);
    System.out.println("    number of bars per line: " + numberOfBarsPerLine);
    System.out.println("    duration denominators:   " + durationDenominatorsString);
    System.out.println("    title:                   " + title);
    System.out.println("    output file:             " + outputFilename);
    System.out.println();

    DrumPatternGeneratorService drumPatternGeneratorService = new DrumPatternGeneratorService();
    DrumPattern drumPattern = drumPatternGeneratorService.createDrumPattern(numberOfBars, durationDenominators);
    LilypondFileContent lilypondFileContent = new LilypondFileContent(drumPattern, title, numberOfBarsPerLine);

    FileWriter fileWriter = new FileWriter(outputFilename, false);
    fileWriter.write(lilypondFileContent.getContent());
    fileWriter.close();

    System.out.println("Lilypond file created.");
    System.out.println();
    System.out.println(
            "Now use \"lilypond -dresolution=500 -dmidi-extension=mid --format png " + outputFilename + "\" to create both PNG and MIDI " +
                    "file.");
    System.out.println();
  }
}
