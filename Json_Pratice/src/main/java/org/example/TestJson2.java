package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class TestJson2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream("./info.json")));
        String line;
        StringBuilder info = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            info.append(line);
        }
        System.out.println(info.toString());

        JSONObject jsonObject = new JSONObject(info.toString());
        System.out.println(jsonObject.toString(4));

        Object field = jsonObject.get("주소");
        System.out.println(field.toString());
        System.out.println(field.getClass().getName());

        Object field2 = jsonObject.get("나이");
        System.out.println(field2.toString());
        System.out.println(field2.getClass().getName());

        
    }
}
