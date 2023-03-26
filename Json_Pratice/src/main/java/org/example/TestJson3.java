package org.example;

import java.lang.ProcessHandle.Info;
import org.json.JSONObject;

public class TestJson3 {
    public static class Info {
        private String address;
        private int age;
        private boolean male;

        public Info() {
            address = "IT 2211";
            age = 20;
        }

        public String getAddress() {
            return address;
        }

        public int getAge() {
            return age;
        }

        public boolean isMale() {
            return male;
        }
    }

    public static void main(String[] args) {
        Info info = new Info();
        JSONObject jsonObject = new JSONObject(info);

        System.out.println(jsonObject.toString(4));

    }
}
