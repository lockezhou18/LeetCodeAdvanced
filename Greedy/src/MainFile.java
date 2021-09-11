public class MainFile {
    public static void main(String args[]) {
//        NonDivisableKnapsPackProblem nonDivisableKnapsPackProblem = new NonDivisableKnapsPackProblem();
//        Item[] items = new Item[5];
//        Item item1 = new Item(1, 1);
//        Item item2 = new Item(8, 3);
//        Item item3 = new Item(18, 5);
//        Item item4 = new Item(22, 6);
//        Item item5 = new Item(28, 7);
//
//        items[0] = item1;
//        items[1] = item2;
//        items[2] = item3;
//        items[3] = item4;
//        items[4] = item5;
//
//        System.out.println(nonDivisableKnapsPackProblem.getMaxValue(items, 11));

        testLC322();
    }

    private static void testLC322() {
        LC322 lc322 = new LC322();

        int[] coins = {1, 2, 5};
        System.out.println(lc322.coinChange(coins, 11));
    }
}
