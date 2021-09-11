public class MainFile {
    public static void main(String args[]) {
        testLC305();
    }

    private static void testLC305() {
        LC305 lc305 = new LC305();
        int[][] positions = {{0,0},{0,1},{1,2},{2,1}};
        int m = 3;
        int n = 3;

        System.out.println(lc305.numIslands2(m, n, positions));
    }
}
