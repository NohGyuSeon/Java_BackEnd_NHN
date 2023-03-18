package week02.day04.pratice.prac02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * new Handler(serverSocket.accept()).start() -> 손님을 받으면서 소켓 생성
 * 브로드캐스트 메서드 -> 다른 핸들러(매니저)에게 전달 => 핸들러 리스트에서 해당 소켓에 대해서 write, newLine, flush)
 * 이때, 동기화 필요
 * 다이렉트 메시지 메서드 ->
 */
public class Handler extends Thread {
    private Thread thread;
    private String id;
    private BufferedReader receiveData;
    private BufferedWriter sendData;
    private Socket socket;
    private  static List<Handler> handlers = new LinkedList<>();

    public Handler(Socket socket) throws IOException {
        this.socket = socket;

        thread = new Thread(this);

        receiveData = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        handlers.add(this);

        sendData = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        System.out.println("socket make");

    }

    public void directMessage(String message) throws IOException {
        BufferedWriter getData = new BufferedWriter(new OutputStreamWriter(System.out));

        getData.write(message);
        getData.newLine();
        getData.flush();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                if(receiveData.ready()) {
                    String message = receiveData.readLine();
                    if (message.equals("끝")) {
                        break;
                    } else {
                        broadcast(message);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void broadcast(String message) throws IOException {

        for(Handler handler : handlers) {
            handler.directMessage(message);
        }
    }
}
