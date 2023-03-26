import java.io.File;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Java -> HTML 테스트용 자바코드
 */
public class TestColorTokenizerFile  {
    private String header;
    private String body;
    public TestColorTokenizerFile() {
        this("", "");
    }

    public TestColorTokenizerFile(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public String getHeader() {
        return header;
    }

    public String getResponseMessage() {
        return getHeader() + getBody();
    }

    private Integer nullTest() {
        return null ;
    }
}
