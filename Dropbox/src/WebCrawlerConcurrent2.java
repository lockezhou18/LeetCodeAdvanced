import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WebCrawlerConcurrent2 {
    private Queue<String> queue;
    private Set<String> set;
    private static int count;


    //lock
    Lock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();

    public WebCrawlerConcurrent2() {
        queue = new LinkedList<>();
        set = new HashSet<>();

    }

    private String dequeue() throws InterruptedException {
        //dequeue url
        lock.lock();
        try {
            while (queue.isEmpty())
                notEmpty.await();

            String url = queue.poll();
            return url;
        } finally {
            lock.unlock();
        }
    }

    private String download(String url, HtmlParser htmlParser) {
        //download
        String webPage = "";
        try {
            webPage  = htmlParser.downloadWebPage(url);
        } catch (Exception e) { //expoential back-off retry strategy, or put it in dlq
            System.out.println("retry");
        }

        //Todo: webpage validation check
        //TODO: Handle the webpage, write to DDB or Anything else

        return webPage;
    }

    private void enqueue(String webPage, HtmlParser htmlParser) throws InterruptedException {
        lock.lock();
        List<String> urls = htmlParser.parse(webPage);
        count++;
        for (String url : urls) {
            if (!set.contains(url)) {
                queue.offer(url);
                set.add(url);
            }
        }
        notEmpty.signalAll();
        lock.unlock();
    }



    public void crawl(HtmlParser parser) throws InterruptedException {
        while (true) {
            if (queue.isEmpty() && Thread.activeCount() < 1)
                return;

            String url = dequeue();
            String webPage = download(url, parser);
            enqueue(webPage, parser);


        }
    }


}
