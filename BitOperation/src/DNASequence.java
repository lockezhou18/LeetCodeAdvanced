import java.util.*;

public class DNASequence {
    public List<String> findRepeatedDnaSequences(String s) {
        return this.findRepeatedDnaSequencesSlidingWindow(s);
    }

    //Using 20 bits to represent current state (
    private List<String> findRepeatedDnaSequencesBit(String s) {
        Map<Integer, Boolean> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        int key = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            key = key << 2 & 0xfffff | convert(ch); //左移两位，去掉滑出去的头，加上新进来的，用 | 16进制中，1个f是（1111）， 5个f是20个1， 之前都是0
            if (i < 9)
                continue;

            Boolean val = map.get(key);
            if (val == null) {
                map.put(key, false);
            } else if (val == false) {
                String cur = s.substring(i - 9, i + 1); //[i - 9, i]
                res.add(cur);
                map.put(key, true);
            }
        }
        return res;
    }

    private int convert(char ch) {
        switch (ch) {
            case 'A' : {
                return 0;
            }

            case 'C' : {
                return 1;
            }

            case 'G' : {
                return 2;
            }

            case 'T' : {
                return 3;
            }
        }
        return -1;
    }


    //brute force sliding window
    private List<String> findRepeatedDnaSequencesSlidingWindow(String s) {
        //c.c
        Map<String, Boolean> map = new HashMap<>(); //val represents if it is in res or not
        List<String> res = new ArrayList<>();
        int start = 0; //10 chars sliding window
        for (int i = 0; i < s.length(); i++) {
            if (i < 9) {
                continue;
            }

            String cur = s.substring(start, i + 1);
            Boolean val = map.get(cur);
            if (val == null) { //没出现过
                map.put(cur, false);
            } else if (val == false) {//出现过一次
                res.add(cur);
                map.put(cur, true);
            }
            start++;
        }

        return res;
    }
}
