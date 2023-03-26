package painter;
import file.Acceptor;

public interface Visitor {
    void visit(Acceptor acceptor);
}