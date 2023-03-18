package day01.pratice.prac02;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Dictionary {

    public static HashMap<String, String> readDictionary(String fileName) {
        HashMap<String, String> dictioinary = new HashMap<>();
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(fileName);
        } catch (Exception e) {
            System.out.println("파일이 존재하지 않습니다.");
            System.exit(-1);
        }

        try (Scanner sc = new Scanner(inputStream)) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine().replaceAll("\\s", "");
                String[] dictionaryArray = str.split("\\.");
                char[] ch = dictionaryArray[1].toCharArray();

                StringBuilder englishWord = new StringBuilder();
                StringBuilder koreanWord = new StringBuilder();

                IntStream.range(0, ch.length).forEach(i -> {
                    if (String.valueOf(ch[i]).matches("[a-z]")) {
                        englishWord.append(ch[i]);
                    } else {
                        koreanWord.append(ch[i]);
                    }
                });

                dictioinary.put(String.valueOf(englishWord), String.valueOf(koreanWord));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dictioinary;
    }

    public static void main(String[] args) {
        String fileName = "/Users/kusun1020/Downloads/words.txt";
        HashMap<String, String> dictionary = readDictionary(fileName);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("검색할 단어를 입력하세요: ");
            String englishWord = sc.next();
            if (englishWord.equals("exit()")) {
                break;
            }
            if (dictionary.containsKey(englishWord)) {
                String koreanWord = dictionary.get(englishWord);
                System.out.println(englishWord + "는 " + koreanWord + "입니다.");
            }
        }
        System.out.println("검색을 종료하고, 자료 사전 전체를 출력합니다.");
        System.out.println(dictionary);
    }
}
