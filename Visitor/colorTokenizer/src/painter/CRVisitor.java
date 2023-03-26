package painter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import file.Acceptor;

public class CRVisitor implements Visitor{
    
    @Override
    public void visit(Acceptor acceptor) {
        List<String> contents = acceptor.getContents();
        convertSpaceToNBSP(contents);
        convertLineFeedToBRTag(contents);
    }

    private void convertSpaceToNBSP(List<String> contents) {
        for (int i = 0; i < contents.size(); i++) {
            StringBuilder sb = new StringBuilder(contents.get(i));
            Deque<Character> chevron = new ArrayDeque<>();
            for (int j = 0; j < sb.length(); j++) {
                switch (sb.charAt(j)) {
                    case '<':
                        chevron.addLast(sb.charAt(j));
                        break;
                    case '>':
                        chevron.pollLast();
                        break;
                    case ' ':
                        if (!isCharIn(chevron)) convertSpaceToNBSP(sb, j);
                        break;
                    default:
                        break;
                }
            }
            setColorizedContent(contents, i, sb);
        }
    }

    private StringBuilder convertSpaceToNBSP(StringBuilder sb, int j) {
        return sb.replace(j, j+1, "&nbsp;");
    }

    private boolean isCharIn(Deque<Character> chevronStack) {
        return !chevronStack.isEmpty();
    }

    private void setColorizedContent(List<String> contents, int i, StringBuilder sb) {
        contents.set(i, sb.toString());
    }

    private void convertLineFeedToBRTag(List<String> contents) {
        contents.replaceAll(string -> string.replaceAll("[\r\n]", "<br>") );
    }
}
