package painter;

import java.util.List;
import file.Acceptor;

/**
 * KeywordVisitor
 */
public class KeywordVisitor implements Visitor {

    String keywordList = "void, boolean, byte, char, float, double, int, short, String" +
        "transient, volatile, if, else, switch, case, for, while, do, break, continue, return, " +
        "class, interface, abstract, final, extends, implements, this, super, new, instanceof, static" +
        "public, protected, private " +
        "try, catch, finally, throw, throws" +
        "import, package" +
        "null, true, false" +
        "native, synchronized";

    @Override
    public void visit(Acceptor acceptor) {
        List<String> contents = acceptor.getContents();
        for (int i = 0; i < contents.size(); i++) {
            String[] words = splitBySpace(contents.get(i));
            for (int j = 0; j < words.length; j++) {
                if (isKeyword(words[j])) {
                    appendBlueColor(words, j);
                }
            }
            setColorizedContent(contents, i, words);
        }
    }

    private void setColorizedContent(List<String> contents, int i, String[] words) {
        contents.set(i, String.join(" ", words));
    }

    private boolean isKeyword(String word) {
        return keywordList.contains(word);
    }

    private String[] splitBySpace(String line) {
        return line.split(" ");
    }

    private void appendBlueColor(String[] words, int j) {
        words[j] = String.format("<span style=\"color:blue\">%s</span>", words[j]);
    }
}