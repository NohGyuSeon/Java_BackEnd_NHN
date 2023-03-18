package week02.day03.second;

import org.apache.commons.cli.*;

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

            for (Option
                option : cmd.getOptions()) {

                if (option.getOpt().equals("h")) {
                    HelpFormatter formatter = new HelpFormatter();
                    System.out.println("도움말을 요청합니다");
                    formatter.printHelp("ex1", options);
                } else if (option.getOpt().equals("logfile")) {
                    System.out.println("Log file : " + cmd.getOptionValue("logfile"));
                }
            }
        } catch (ParseException ignore) {
            System.err.println("명령어 인수가 잘못되었습니다");
            System.err.println(ignore.getMessage());
        }
    }
}
