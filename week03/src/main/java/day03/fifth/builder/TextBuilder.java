package day03.fifth.builder;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class TextBuilder implements Builder {
    private String albumTitle;
    private String teamName;

    private StringBuilder buffer = new StringBuilder();

    @Override
    public void albumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
        buffer.append("Title: " + albumTitle + "\n");
    }

    @Override
    public void teamName(String teamName) {
        this.teamName = teamName;
        buffer.append("-----------------------------------\n");
        buffer.append(teamName + "\n");
        buffer.append("-----------------------------------\n");
    }

    @Override
    public void makeItems(ArrayList<String> items) {
        for(int i = 0; i < items.size(); i++) {
            buffer.append(i + ". ");
            buffer.append(items.get(i) + "\n");
        }
    }
    
    @Override
    public void close() {
        buffer.append("-----------------------------------\n");
    }

    @Override
    public void makeFile() {
        try {
            File file = new File(this.albumTitle + " - " + this.teamName + ".txt");
            
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(buffer.toString());
            writer.close();
        }
        catch (IOException e) {}
    }
}
