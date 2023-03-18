package week02.day03.first;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Exam1 {

    public static void main(String[] args) {
        try (Socket socket = new Socket("192.168.64.2", 1234)) {

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));

            BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            Thread inputThread = new Thread(() -> {
                try {
                    while ((!Thread.interrupted())) {
                        terminal.write(input.readLine());
                        terminal.newLine();
                        terminal.flush();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            inputThread.start();

            while(!Thread.interrupted()) {
                String line = console.readLine();
                output.write(line);
                output.newLine();   // readLine()과 연관, 버퍼에 가지고 있음
                output.flush();     // newLine()까지 포함하여 버퍼를 비우고 뿌림
            }

            String message = "0123";
            output.write(message);
            output.newLine();
            output.flush();
            String value = input.readLine();
            System.out.println("Received: " + value);
        } catch (IOException ignore) {
            System.err.println(ignore);
            System.err.println("연결을 실패하였습니다.");
        }
    }
}
