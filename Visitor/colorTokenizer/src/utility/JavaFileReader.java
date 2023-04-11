package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaFileReader {
    
    private JavaFileReader() {
    }

    public static List<String> read(String name) throws IOException {
        if (!name.contains(".java")) throw new IOException("파일 형식을 확인해주세요");
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/" + name)))) {
            while (reader.ready()) {
                lines.add(reader.readLine() + "\n");
            }
        }
        return lines;
    }
}
