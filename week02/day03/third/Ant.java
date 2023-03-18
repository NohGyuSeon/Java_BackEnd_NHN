package week02.day03.third;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Ant {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("D", "use value for given property");
        options.addOption(Option.builder("buildfile")
            .hasArg()
            .argName("file")
            .desc("use given buildfile")
            .build());
        options.addOption(Option.builder("D")
            .hasArg()
            .argName("property")
            .valueSeparator('=')
            .desc("use value for given property")
            .build());
        options.addOption(Option.builder("emacs")
            .desc("produce logging information without adornments")
            .build());
        options.addOption(Option.builder("file")
            .hasArg()
            .argName("file")
            .desc("search for buildfile towards the root of the filesystem and use it")
            .build());
        options.addOption("h", "help", false, "print this message");
        options.addOption(Option.builder("debug")
            .desc("produce debugging information")
            .build());
        options.addOption(Option.builder("listener")
            .hasArg()
            .argName("classname")
            .desc("add an instance of class as a project listener")
            .build());
        options.addOption(Option.builder("V")
            .longOpt("verbose")
            .desc("tell me more")
            .build());
        options.addOption(Option.builder("logger")
            .hasArg()
            .argName("classname")
            .desc("the class which it to perform logging")
            .build());
        options.addOption(Option.builder("projecthelp")
            .desc("print project help information")
            .build());
        options.addOption(Option.builder("quiet")
            .desc("be extra quiet")
            .build());
        options.addOption(Option.builder("Version")
            .desc("print the version information and exit")
            .build());

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            } else {
                for (Option option :
                    cmd.getOptions()) {
                    System.out.println(option.getOpt() + " : " + option.getValue());
                }
            }

        } catch (ParseException e) {
            System.out.println();
        }
    }
}
