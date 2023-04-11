package painter;

import java.util.List;

import file.Acceptor;

public class SymbolVisitor implements Visitor{

    String symbols = "[]{}()/\\*";

    @Override
    public void visit(Acceptor acceptor) {
        List<String> contents = acceptor.getContents();
        for (int i = 0; i < contents.size(); i++) {
            String[] chars = splitByOneCharacter(contents.get(i));
            for (int j = 0; j < chars.length; j++) {
                if (isSymbol(chars, j) || (isLineEnd(chars, j) && isSemicolon(chars, j))) {
                    appendRedColor(chars, j);
                }
            }
            setColorizedContent(contents, i, chars);
        }
    }

    private void setColorizedContent(List<String> contents, int i, String[] chars) {
        contents.set(i, String.join("", chars));
    }

    private void appendRedColor(String[] chars, int j) {
        chars[j] = String.format("<span style=\"color:red\">%s</span>", chars[j]);
    }

    private String[] splitByOneCharacter(String line) {
        return line.split("");
    }

    private boolean isSymbol(String[] chars, int j) {
        return symbols.contains(chars[j]);
    }

    private boolean isSemicolon(String[] chars, int j) {
        return chars[j].equals(";");
    }

    private boolean isLineEnd(String[] chars, int j) {
        // words.length - 1은 라인피드 
        return j == chars.length - 2;
    }
}