package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HTMLFileWriter {

    private HTMLFileWriter() {
    }

    public static void write(List<String> contents, String filname) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filname + ".html"))) {
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html lang=\"en\">\n");
            writer.write(String.format("<head>%n<title>%s</title>%n</head>%n", filname));
            contents.stream().forEach(content -> {
                try {
                    writer.write(content + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.write("\n</html>");
            writer.flush();
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }
}