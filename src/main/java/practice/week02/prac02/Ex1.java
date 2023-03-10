package practice.week02.prac02;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Ex1 {

    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("h", "help", false, "도움말");
        Option logfileOption = Option.builder("logfile")
            .argName("file")
            .hasArg()
            .valueSeparator('=')
            .desc("use given file for log")
            .build();
        CommandLineParser parser = new DefaultParser();
        options.addOption(logfileOption);

        try {
            CommandLine cmd = parser.parse(options, args);

            for (Option option : cmd.getOptions()) {

                if (option.getOpt().equals("h")) {
                    HelpFormatter formatter = new HelpFormatter();
                    System.out.println("도움을 요청합니다");
                    formatter.printHelp("ex1", options);
                } else if (option.getOpt().equals("logfile")) {
                    System.out.println("Log file : " + cmd.getOptionValue("logfile"));
                }
            }
        } catch (ParseException ignore) {
            System.out.println("명령어 인수가 잘못되었습니다.");
            System.out.println(ignore.getMessage());
        }
    }
}
