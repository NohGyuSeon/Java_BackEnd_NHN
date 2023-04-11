import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Connect {
    private final String serverAddress;
    private final int portNumber;
    BufferedReader input;
    BufferedWriter output;
    Socket socket;
    BufferedWriter terminal;
    BufferedReader console;

    public Connect(String serverAddress, int portNumber) throws IOException {
        this.serverAddress = serverAddress;
        this.portNumber = portNumber;

        this.console = new BufferedReader(new InputStreamReader(System.in));
        this.terminal = new BufferedWriter(new OutputStreamWriter(System.out));

        this.socket = new Socket(serverAddress, portNumber);
    }

    public boolean receive() throws Exception {
        if (this.serverAddress.contains("get")) {
            System.out.println(true);
            return true;
        }
        else if (this.serverAddress.contains("post")) {
            System.out.println("post");
            return false;
        } else {
            throw new Exception("잘못된 명령어를 입력하셨습니다.");
        }
    }

    public void get(String command) {
        try (Socket socket = new Socket(serverAddress, portNumber)) {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            output.write(command);
            output.newLine();
            output.flush();

            String httpLine = null;
            int lineCount = 5;

            while (!(httpLine = input.readLine()).isEmpty()) {
                synchronized (httpLine) {
                    terminal.write(httpLine);
                    terminal.newLine();
                    terminal.flush();
                }

                if (httpLine.isEmpty()) {
                    lineCount++;
                }

                if (lineCount > 5) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
