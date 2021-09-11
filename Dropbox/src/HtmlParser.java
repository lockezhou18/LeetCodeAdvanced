import java.util.List;

public interface HtmlParser {
    public List<String> getUrls(String url);
    public String downloadWebPage(String url);
    public List<String> parse(String webContent);
}

