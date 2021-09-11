public class MainFile {
    public static void main(String args[]) {
    //   testThread();
      //  testMask();
        String s1 = new String("aa");
        String s2 = new String("aa");


    }

    private static void testMask() {
        int mask = 0;

        int a = mask | 1;

        System.out.println(mask);
    }

    private static void testThread() {
        int i = 0;
        while (i < 100){
            final int cur = i;
            Thread t = new Thread(i + "") {
                @Override
                public void run() {
                    System.out.println(cur);
                }
            };
            t.start();
            i++;
        }
    }
}
