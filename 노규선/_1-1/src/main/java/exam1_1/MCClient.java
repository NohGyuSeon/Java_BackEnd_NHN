package exam1_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class MCClient {

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(
            Option.builder("H").argName("port").hasArg().desc("Sever Active..").build());
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("H")) {
                String portNumber = cmd.getOptionValue("H");
                new Server(portNumber);
            } else {
                new Client(args[0], args[1]);
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
    }
}

class Client {

    private final int portNumber;
    private final String severAddress;

    Client(String severAddress, String portNumber) {
        this.severAddress = severAddress;
        this.portNumber = Integer.parseInt(portNumber);
        sendData();
    }

    public void sendData() {
        try (Socket socket = new Socket(severAddress, portNumber)) {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));

            Thread inputThread = new Thread(() -> {
                try {
                    while (!Thread.interrupted()) {
                        terminal.write(input.readLine());
                        terminal.newLine();
                        terminal.flush();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            inputThread.start();

            while (!Thread.interrupted()) {
                String line = console.readLine();
                output.write(line);
                output.newLine();
                output.flush();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Connection Failed.");
        }
    }
}

class Server {

    private final int portNumber;

    Server(String portNumber) {
        this.portNumber = Integer.parseInt(portNumber);
        receiveData();
    }

    public void receiveData() {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            Socket socket = serverSocket.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));

            while (!Thread.interrupted()) {
                String line = input.readLine();
                log.write(line);
                log.newLine();
                log.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
