public class LeetCode1812 {
    public boolean squareIsWhite(String coordinates) {
        int col = coordinates.charAt(0) - 'a';
        int row = coordinates.charAt(1) - '1';

        return (col % 2 == 0) ^ (row % 2 == 0); //相同为0，不同为1， 判断1， 0是否不同
    }
}
