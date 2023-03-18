package day03.fifth.builder;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class HTMLBuilder implements Builder {
    private String albumTitle;
    private String teamName;

    private StringBuilder buffer = new StringBuilder();

    @Override
    public void albumTitle(String albumTitle) {
        buffer.append("<html>\n");
        buffer.append("<body>\n");

        this.albumTitle = albumTitle;
        buffer.append("<h1>" + albumTitle + "</h1>\n");
    }

    @Override
    public void teamName(String teamName) {
        this.teamName = teamName;
        buffer.append("<h2>" + teamName + "</h2>");
    }

    @Override
    public void makeItems(ArrayList<String> items) {
        buffer.append("<ol>\n");
        for (String string : items) {
            buffer.append("<li>" + string + "</li>\n");
        }
        buffer.append("</ol>\n");
    }

    @Override
    public void close() {
        buffer.append("</body>\n");
        buffer.append("</html>\n");
    }

    @Override
    public void makeFile() {
        try {
            File file = new File(this.albumTitle + " - " + this.teamName + ".html");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(buffer.toString());
            writer.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
