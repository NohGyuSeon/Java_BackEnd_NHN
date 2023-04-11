package file;

import java.util.List;

import painter.Visitor;

public class JavaToHTML extends Acceptor {
    
    public JavaToHTML(List<String> fileContent) {
        super(fileContent);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public List<String> getContents() {
        return fileContent;
    }
}
