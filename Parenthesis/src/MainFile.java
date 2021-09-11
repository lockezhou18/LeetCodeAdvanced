public class MainFile {
    public static void main(String args[]) {
        testLC1541();
    }

    private static void testLC1541() {
        LC1541 lc1541 = new LC1541();
        String s = "))())(";

        System.out.println(lc1541.minInsertions(s));
    }
}
