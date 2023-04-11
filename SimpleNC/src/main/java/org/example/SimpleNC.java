package org.example;

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

/**
 * SNC 클래스
 */
public class SimpleNC {

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(
            Option.builder("l").argName("port").hasArg().desc("Sever Active..").build());
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("l")) {
                String portNumber = cmd.getOptionValue("l");
                new Server(portNumber);
            } else {
                new Client(args[0], args[1]);
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
    }
}

/**
 * 클라이언트 클래스
 */
class Client {

    private final int portNumber;
    private final String severAddress;

    /**
     * @param severAddress 호스트 네임
     * @param portNumber 포트 주소
     */
    Client(String severAddress, String portNumber) {
        this.severAddress = severAddress;
        this.portNumber = Integer.parseInt(portNumber);
        sendData();
    }

    /**
     * 데이터 전송
     */
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

/**
 * 서버 클래스
 */
class Server {

    private final int portNumber;

    /**
     * @param portNumber 포트 번호
     */
    Server(String portNumber) {
        this.portNumber = Integer.parseInt(portNumber);
        receiveData();
    }

    /**
     * 데이터 수신
     */
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
