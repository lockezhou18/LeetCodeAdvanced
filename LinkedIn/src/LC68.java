import java.util.ArrayList;
import java.util.List;

public class LC68 {
    private int maxWidth;
    private String[] words;
    private int[] prefixSum;
    private static final String SPACE = " ";
    private static final String EMPTY = "";
    public LC68() {

    }

    public LC68(String[] words, int maxWidth) {
        this.words = words;
        this.maxWidth = maxWidth;
        this.prefixSum = getPrefix(words);
    }


    public List<String> fullJustify(String[] words, int maxWidth) {
        this.words = words;
        this.maxWidth = maxWidth;
        this.prefixSum = getPrefix(words);

        int left = 0;
        List<String> res = new ArrayList<>();
        while (left < words.length) {
            int right = getRight(left);
            boolean islastLine = right == words.length - 1;
            if (!islastLine) {
                res.add(fullyJustify(left, right));
            } else {
                res.add(leftJustify(left, right));
            }

            left = right + 1;
        }

        return res;
    }

    public String leftJustify(int left, int right) {
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(words[i]);
            if (sb.length() == maxWidth)
                break;
            sb.append(constructSpaces(1));
        }
        //String res = sb.toString().trim();
        sb.append(constructSpaces(maxWidth - sb.length()));

        return sb.toString();
    }

    public int getRight(int cur) {
        int minSpacesNeeded = 0;
        int i = cur;

        for (i = cur; i < words.length; i++) {
            int wordLength = prefixSum[i] - (cur - 1 < 0 ? 0 : prefixSum[cur - 1]);
            boolean condition = minSpacesNeeded++ + wordLength <= maxWidth;
            if (!condition)
                break;
        }
        return Math.max(i - 1, 0); //get valid index Math.max(i - 1, 0)
    }

    public int[] getPrefix(String[] words) {
        int[] prefix = new int[words.length];
        int curSum = 0;

        for (int i = 0; i < words.length; i++) {
            curSum += words[i].length();
            prefix[i] = curSum;
        }
        return prefix;
    }

    public String fullyJustify(int left, int right) {
        //求prefix sum 【i， j]范围的长度, 闭区间
        //prefixSum[j] - prefixSum[i - 1]
        //可能会越界，用三目运算符判断是否会越界，越界时候取0
        int wordLength = prefixSum[right] - ((left - 1 < 0) ? 0 : prefixSum[left - 1]);
        int total = maxWidth - wordLength;
        int slot = Math.max(1, right - left);
        List<String> spacesList = getSpaces(total, slot);

        return concat(left, right, spacesList);
    }

    public String concat(int left, int right,
                         List<String> spacesList) {

        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(words[i]);

            if (i - left < spacesList.size())
                sb.append(spacesList.get(i - left));
        }

        return sb.toString();
    }

    //spaces needed for each slot between words
    //output : idx i -> slot position
    public List<String> getSpaces(int total, int slot) {
        List<String> res = new ArrayList<>();

        if (slot == 0)
            return res;

        int reminder = total % slot;
        final int count = total / slot;

        while (slot-- > 0) { //前prefix k个 + 1
            if (reminder-- > 0) {
                res.add(constructSpaces(count + 1));
            } else {
                res.add(constructSpaces(count));
            }
        }

        return res;
    }

    private String constructSpaces(int k) {
        StringBuilder sb = new StringBuilder();
        while (k-- > 0) {
            sb.append(SPACE);
        }
        return sb.toString();
    }
}

