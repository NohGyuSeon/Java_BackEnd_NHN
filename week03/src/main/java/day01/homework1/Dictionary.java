package day01.homework1;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author NohGyuSeon
 * @Date 2023.03.14
 */
public class Dictionary {

    /**
     * @param fileName 파일 경로
     * @return 사전 정보가 담겨있는 HashMap 객체
     */
    public static HashMap<String, String> readDictionary(String fileName) {
        HashMap<String, String> dictionary = new HashMap<>();
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(fileName);
        } catch (Exception e) {
            System.out.println("파일이 존재하지 않습니다.");
            System.exit(-1);
        }

        try(Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine().replaceAll("\\s", "");
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

                dictionary.put(String.valueOf(englishWord), String.valueOf(koreanWord));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dictionary;
    }

    /**
     * 메인 클래스
     */
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
            } else {
                System.out.println("사전에서 단어를 찾을 수 없습니다.");
            }
        }
        System.out.println("검색을 종료하고, 자료 사전 전체를 출력합니다.");
        System.out.println(dictionary);
    }
}
