public class LC443 {
    public int compress(char[] chars) {
        if (chars.length == 1)
            return 1;

        int curIdx = 0;
        int res = 0;

        while (curIdx < chars.length) {
            char ch = chars[curIdx];
            int count = 0;

            while (curIdx < chars.length && chars[curIdx] == ch) {
                curIdx++;
                count++;
            }

            chars[res++] = ch;
            if (count > 1) {
                char[] countChars = String.valueOf(count).toCharArray();
                for (char digit : countChars){
                    chars[res++] = digit;
                }
            }
        }

        return res;
    }
}
