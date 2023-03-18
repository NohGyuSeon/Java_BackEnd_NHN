package week02.day04.pratice.prac02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int portNumber;

    public Server(int portNumber) {
        this.portNumber = portNumber;
        receiveData();
    }

    public void receiveData() {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
//            BufferedReader input = new BufferedReader(
//                new InputStreamReader(socket.getInputStream()));
//            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

//                if (input.ready()) {
//                    String line = input.readLine();
//                    output.write(line);
//                    output.newLine();
//                    output.flush();
            Socket socket = serverSocket.accept();

            System.out.println("연결을 기다립니다.");
            new Handler(socket);
            System.out.println("연결 되었습니다.");

//            handler.start();
//            handler.join();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {

        Server server = new Server(1234);
    }
}
