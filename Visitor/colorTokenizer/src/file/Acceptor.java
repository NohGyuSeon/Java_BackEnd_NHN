package file;
import java.util.List;

import painter.Visitor;

public abstract class Acceptor {
    protected List<String> fileContent;
    
    protected Acceptor(List<String> fileContent) {
        this.fileContent = fileContent;
    }

    public List<String> getContents() {
        return this.fileContent;
    }

    public abstract void accept(Visitor visitor);
}
