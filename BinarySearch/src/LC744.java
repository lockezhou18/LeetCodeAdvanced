public class LC744 {
    //binary search 找大于target的最小值
    //use template 3
    public char nextGreatestLetter(char[] letters, char target) {
        //c.c
        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (letters[mid] == target) { //找 >= target的最小值，所以相等时更新start
                start = mid + 1;
            } else if (letters[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start < letters.length ? letters[start] : letters[0]; //start 出界，target不在范围内，正无穷
    }
}
