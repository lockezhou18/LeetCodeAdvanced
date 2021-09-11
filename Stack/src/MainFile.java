public class MainFile {
    public static void main(String args[]) {
        testCalculator();
    }

    private static void testCalculator() {
        String s = "1 + 1";
        BasicCalculator calculator = new BasicCalculator();
        int res = calculator.calculate(s);
        System.out.println(res);
    }
}
