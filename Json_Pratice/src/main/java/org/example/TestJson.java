package org.example;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.json.JSONObject;

public class TestJson {

    public static void main(String[] args) throws IOException {
        JSONObject object = new JSONObject();

        object.put("name", "xtra");
        object.put("주소", "IT융합대학 2211");
        object.put("detail", new JSONObject());
        object.put("나이", 20);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./info.json")));
        writer.write(object.toString(4));
        writer.flush();
        fileWriter.write(object.toString(4));
        fileWriter.flush();

        System.out.println(object.toString());
    }
}
