package week02.day04.pratice.prac02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    private final int portNumber;
    private final String severAddress;

    public Client(String severAddress, int portNumber) {
        this.severAddress = severAddress;
        this.portNumber = portNumber;
        sendData();
        System.out.println("send data");
    }

    public void sendData() {
        try (Socket socket = new Socket(severAddress, portNumber)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter sendData = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));

            Thread inputThread = new Thread(() -> {
                try {
                    while (!Thread.interrupted()) {
                        if (input.ready()) {
                            String line = input.readLine();

                            sendData.write(line);
                            sendData.newLine();
                            sendData.flush();
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            inputThread.start();
            inputThread.join();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Connection Failed.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 1234);
    }
}
