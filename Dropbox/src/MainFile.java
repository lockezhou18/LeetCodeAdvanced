import java.util.Arrays;

public class MainFile {
    public static void main(String args[]) {
      //  testSegmentTree();
     //   testTokenBucket();
     //  testMyTokenBucket();
        testKVStore();
    }


    private static void testKVStore() {
        KVStore kvStore = new KVStore();

        int t1 = kvStore.begin();
        kvStore.put(t1, 1, 1);
        kvStore.commit(t1);

        int t2 = kvStore.begin();
        int t3 = kvStore.begin();
        int val = kvStore.get(t3, 1);
        kvStore.put(t2, 1, 2);
        System.out.println(kvStore.get(t2, 1));
        kvStore.put(t3, 1, 3);
        kvStore.commit(t2);
        val = kvStore.get(t3, 1);
        System.out.println(val);

        kvStore.commit(t3);

       // System.out.println(kvStore.get(0, 1));
    }

    private static void testMyTokenBucket() {
        MyTokenBucket2 tb = new MyTokenBucket2(100, 10);
        Thread putThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (true){
                        tb.put();
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted.");
                }
            }
        };

        Thread getThread = new Thread("t1") {
            @Override
            public void run() {
                try {
                    while(true) {
                        tb.get(30);
                        //System.out.println(Arrays.toString());
                    }
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread getThread2 = new Thread("t2") {
            @Override
            public void run() {
                try {
                    while(true) {
                        tb.get(11);
                        //System.out.println("t2 " + Arrays.toString());
                    }
                } catch (InterruptedException e) {

                }
            }
        };

        putThread.start();
        getThread.start();
        getThread2.start();
//        try {
//            tb.fill();
//            Thread.sleep(5000);
//            tb.fill();
//        } catch (InterruptedException e) {
//            System.out.println("Interrupted");
//        }


    }

    private static void testTokenBucket() {
        TokenBucket tb = new TokenBucket(100, 10);
        Thread putThread = new Thread() {
            @Override
            public void run() {
               try {
                   while (true){
                       tb.fill();
                       Thread.sleep(5000);
                       tb.fill();
                   }
               } catch (InterruptedException e) {
                   System.out.println("Interrupted.");
               }
            }
        };

        Thread getThread = new Thread() {
            @Override
            public void run() {
                try {
                   while(true) {
                        System.out.println(tb.get(25));
                        Thread.sleep(3000);
                        System.out.println(tb.get(25));
                    }
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
        };

        Thread getThread2 = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        System.out.println(tb.get(25));
                        Thread.sleep(3000);
                        System.out.println(tb.get(25));
                    }
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
        };

        putThread.start();
        getThread.start();
        getThread2.start();
//        try {
//            tb.fill();
//            Thread.sleep(5000);
//            tb.fill();
//        } catch (InterruptedException e) {
//            System.out.println("Interrupted");
//        }


    }

    private static void testSegmentTree() {
        PhoneDirectorySegmentTree segmentTree = new PhoneDirectorySegmentTree(5);
        System.out.println(segmentTree.get());
        System.out.println(segmentTree.get());
        System.out.println(segmentTree.get());
        System.out.println(segmentTree.get());
        System.out.println(segmentTree.get());
        System.out.println(segmentTree.get());
        System.out.println(segmentTree.get());
        System.out.println(segmentTree.check(0));
        System.out.println(segmentTree.get());
    }
}
