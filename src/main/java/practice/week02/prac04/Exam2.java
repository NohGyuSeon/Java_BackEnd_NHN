package practice.week02.prac04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam2 {

    public static class Handler extends Thread {
        Socket socket;

        public Handler(Socket socket) {
            super("Handler");
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));

                while(!Thread.interrupted()) {
                    String line = input.readLine();
                    if(line.equals('끝')) {
                        break;
                    }

                    output.write(line);
                    output.newLine();
                    output.flush();

                    log.write(line);
                    log.newLine();
                    log.flush();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(10001)) {
            System.out.println("연결을 기다립니다.");
            Handler handler = new Handler(serverSocket.accept());
            System.out.println("연결 되었습니다.");
            handler.start();
            handler.join();
            System.out.println("연결이 끊겼습니다.");
        } catch (IOException ignore) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
