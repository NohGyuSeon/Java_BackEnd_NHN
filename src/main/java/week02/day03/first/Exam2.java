package week02.day03.first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam2 {

    // 핸들러 생성 시, 소켓을 생성하여 연결
    // 소켓을 통해 데이터 통신
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

                // 쓰레드 동작 중이라면
                while(!Thread.interrupted()) {
                    // 소켓 인풋 스트림에서 읽어오기, 리더
                    String line = input.readLine();
                    if(line.equals("끝")) {
                        break;
                    }

                    // 소켓 아웃 스트림에 쓰기
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
            // 10001 포트 서버 열고(서버 소켓), 연결 대기
            System.out.println("연결을 기다립니다.");
            // 핸들러 열고, 연결
            Handler handler = new Handler(serverSocket.accept());
            System.out.println("연결 되었습니다.");
            handler.start();
            handler.join();
            // 핸들러 쓰레드 종료 시
            System.out.println("연결이 끊겼습니다.");

        } catch (IOException ignore) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
