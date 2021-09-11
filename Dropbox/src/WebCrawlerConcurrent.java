import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//producer - consumer
public class WebCrawlerConcurrent {
    private Queue<String> queue = new LinkedList<>();
    private Set<String> visited = new HashSet<>();
    private static int count;
    private static final int MAX_NUMBER = 10;

    Lock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();

    public void crawl(String startUrl, HtmlParser htmlParser) throws InterruptedException {
        List<String> res = new ArrayList<>();
        while (true) {
            if (count == MAX_NUMBER)
                break;

            lock.lock();
            while (queue.isEmpty())
                notEmpty.await();

            String curUrl = queue.poll();
            lock.unlock();

            String html = "";
            try {
                html = htmlParser.downloadWebPage(curUrl);
            } catch (Exception e) {
                //exponential back off
            }

            if (html.equals(""))
                continue;

            List<String> urls = htmlParser.parse(html);

            lock.lock();
            for (String next : urls) {
                if (isSatisfying(next)) {
                    //do sth
                    //send it to ddb or
                    queue.offer(next);
                    visited.add(next);
                    count++;
                }
            }
            notEmpty.signalAll();
            lock.unlock();
        }
    }

    private boolean isSatisfying(String url) {
        return visited.contains(url);
    }
}
