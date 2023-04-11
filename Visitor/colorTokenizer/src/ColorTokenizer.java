import java.io.IOException;
import java.util.List;

import file.JavaToHTML;
import painter.CRVisitor;
import painter.KeywordVisitor;
import painter.SymbolVisitor;
import utility.HTMLFileWriter;
import utility.JavaFileReader;

public class ColorTokenizer {
    public static void main(String[] args) throws IOException {
        List<String> fileContent = JavaFileReader.read(args[0]);
        
        JavaToHTML javaToHTML = new JavaToHTML(fileContent);

        CRVisitor crVisitor = new CRVisitor();
        SymbolVisitor symbolVisitor = new SymbolVisitor();
        KeywordVisitor keywordVisitor = new KeywordVisitor();

        javaToHTML.accept(symbolVisitor);
        javaToHTML.accept(keywordVisitor);
        javaToHTML.accept(crVisitor);
        
        HTMLFileWriter.write(javaToHTML.getContents(), args[0]);
    }

}