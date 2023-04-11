import java.io.IOException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Usage: scurl [options] url
 * Options:
 * -v                 verbose, 요청, 응답 헤더를 출력합니다.
 * -H <line>          임의의 헤더를 서버로 전송합니다.
 * -d <data>          POST, PUT 등에 데이타를 전송합니다.
 * -X <command>       사용할 method 를 지정합니다. 지정되지 않은 경우 기본값은 GET 입니다.
 * -L                 서버의 응딥이 30x 계열이면 다음 응답을 따라 갑니다.
 * -F <name=content>  multipart/form-data 를 구성하여 전송합니다. content 부분에 @filename 을 사용할 수 있습니다.
 */
public class Scurl {

    private final String[] args;
    private final Options options ;
    private String command;

    /**
     * @param args 문자열 인자
     */
    public Scurl(String[] args) {
        this.options = new Options();
        this.args = args;
        Option verbose = Option.builder("v").longOpt("verbose").hasArg(false).build();
        Option header = Option.builder("H").argName("line").longOpt("Header").hasArg(false).build();
        Option data = Option.builder("d").argName("data").longOpt("data").hasArg(false).build();
        Option request = Option.builder("X").argName("command").longOpt("request").hasArg(false)
            .build();
        Option location = Option.builder("L").argName("location").longOpt("location").hasArg(false)
            .build();
        Option filename = Option.builder("F").argName("name=content").longOpt("filename")
            .hasArg(false).build();

        options.addOption(verbose);
        options.addOption(header);
        options.addOption(data);
        options.addOption(request);
        options.addOption(location);
        options.addOption(filename);
        options.addOption("h", "help", false, "도움말");
        command = getRequest(args).toUpperCase() + "  /" + getResourcePath(args[args.length - 1])
            + " HTTP/1.1\nHost: " + getRequestHost(args[args.length - 1])
            + "\nUser-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Whale/3.19.166.16 Safari/537.36\n";
    }

    /**
     * To-do -v, -H<line>, -d<data>, -X<command>,-L,-F <name=content>
     */
    public void setCommand(String request) {
        this.command = this.command.concat(request + "\n");
    }

    /**
     * @return 명령어
     */
    public String getCommand() {
        System.out.println(this.command);
        return this.command;
    }

    /**
     * @param host 요청 받기
     * @return 서버 주소(호스트) 리턴
     */
    public String getRequestHost(String host) {
        String[] requestHost = host.split("/");
        String serverHost = "";
        for (String s : requestHost) {
            if (s.contains(".")) {
                serverHost = s;
                break;
            }
        }
        return serverHost;
    }

    public String findPath(String _host) {
        String[] requestHost = _host.split("/");
        String path = "";
        boolean pastHost = false;

        for (String s : requestHost) {
            if (pastHost) {
                path = path.concat(s).concat("/");
            }
            if (s.contains(".")) {
                pastHost = true;
            }
        }

        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    /**
     * @param host 서버 주소
     * @return 리소스 받아서 경로 탐색
     */
    public String getResourcePath(String host) {
        String request = host;

        if (request.contains("get")) {
            return "get";
        } else if (request.contains("post")) {
            return "post";
        } else {
            return findPath(host);
        }
    }

    /**
     * @param args
     * @return 요청 받기
     */
    public String getRequest(String[] args) {
        for (int i = 0; i <= args.length - 1; i++) {
            if (args[i].equals("GET")) {
                return "get";
            }
            if (args[i].equals("POST")) {
                return "post";
            }
            if (args[i].equals("PUT")) {
                return "put";
            }
            if (args[i].equals("HEAD")) {
                return "head";
            }
        }

        return "get";
    }

    /**
     * @param args 문자열 인자
     * @return 가리키는 인자의 해당 배열 내 데이터 리턴
     */
    public String getHeader(String[] args) {
        int index;
        for (index = 0; index < args.length; index++) {
            if (args[index].equals("-H")) {
                break;
            }
        }

        return args[index + 1];
    }

    /**
     * @param args 문자열 인자
     * @return 해당하는 인자의 데이터 리턴
     */
    public String getData(String[] args) {
        int index;
        for (index = 0; index < args.length; index++) {
            if (args[index].equals("-d")) {
                break;
            }
        }
        return args[index + 1];
    }

    /**
     * 연결 및 시작
     */
    public void start() throws IOException {

        Connect connect = new Connect("httpbin.org", 1234);
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("simple-scurl[options] url", options);
            }

            if (cmd.hasOption("v")) {
                setCommand("Accept: */*");
            }

            if (cmd.hasOption("X")) {
                System.out.println("---X---");
            }

            if (cmd.hasOption("H")) {
                setCommand(getHeader(args));
            } else {
                setCommand("Content-Type: application/x-www-form-urlencoded");
            }

            if (cmd.hasOption("d")) {
                setCommand("Content-Length: " + getData(args).getBytes().length);
                setCommand("\n" + getData(args));
            }

            if (cmd.hasOption("L")) {
                setCommand("Accept-Encoding: gzip, deflate");

            }

            if (cmd.hasOption("F")) {
                setCommand(
                    "Content-Type: multipart/form-data; boundary=------------------------24f971a8a05c2852\n"
                        + "\n" + "--------------------------24f971a8a05c2852\n"
                        + "Content-Disposition: form-data; name=\"test\"; filename=\"test\"\n"
                        + "Content-Type: application/octet-stream\n"
                        + "\n/Users/ganghyun/Documents/nhnachademy/test.txt\n"
                        + "--------------------------24f971a8a05c2852--\n");
            }
            connect.get(getCommand());

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
