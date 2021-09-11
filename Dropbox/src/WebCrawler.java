import java.util.*;

public class WebCrawler {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Queue<String> queue = new LinkedList<>();

        queue.offer(startUrl);
        Set<String> visited = new HashSet<>();
        String hostName = startUrl.split("/")[2];
        visited.add(startUrl);

        while (!queue.isEmpty()) {
            String cur = queue.poll();

            List<String> nexts = htmlParser.getUrls(cur);
            for (String next : nexts) {
                if (isSameHost(hostName, next)
                        && !visited.contains(nexts)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }

        List<String> res = new ArrayList<>(visited);
        return res;
    }

    private boolean isSameHost(String hostName, String cur) {
        return hostName.equals(cur.split("/")[2]);
    }
}
