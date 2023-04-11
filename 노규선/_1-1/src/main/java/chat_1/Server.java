package chat_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server를 먼저 열고, Client에서 접속하여 데이터 전송 시
 * Server 측에서 데이터 수신 가능.
 */
public class Server {
    private final int portNumber;

    public Server(int portNumber) {
        this.portNumber = portNumber;
        receiveData();
    }

    public void receiveData() {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            Socket socket = serverSocket.accept();
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

            while (!Thread.interrupted()) {
                if (input.ready()) {
                    String line = input.readLine();
                    output.write(line);
                    output.newLine();
                    output.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(1234);
    }
}
