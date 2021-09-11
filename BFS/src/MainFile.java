import java.util.*;

public class MainFile {

    class TreeNode {
        int val;
    }

    public static void main(String args[]) {
        //wordLadder2Test();
        //wordLadder3Test();
        //leetCode752Test();
        //System.out.println(1 + '1');
        //leetCodeConvertTest();
        testLeetCode310();
    }


    private static void testLeetCode310() {
        LeetCode310 leetCode310 = new LeetCode310();
        int n = 4;
        int edges[][] = {{1,0},{1,2},{1,3}};
        List<Integer> res = leetCode310.findMinHeightTrees(n, edges);

        System.out.println(res);
    }

    private static void leetCodeConvertTest() {
//        String[] deadends = new String[]{"0201","0101","0102","1212","2002"};
//        String target = "0202";

        String[] deadends = new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";

        LeetCode752 leetCode752 = new LeetCode752();
        List<String> res = leetCode752.convert("0000");

        for (String str : res) {
            System.out.println(str);
        }
    }

    private static void testCollectionsMax() {
        List<TreeNode> queue = new ArrayList<>();

        Collections.max(queue, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                return o1.val - o2.val;
            }
        });
    }

    private static void leetCode752Test() {
        String[] deadends = new String[]{"0201","0101","0102","1212","2002"};
        String target = "0202";

        LeetCode752 leetCode752 = new LeetCode752();
        int res = leetCode752.openLock(deadends, target);

        System.out.println(res);
    }

    private static void wordLadder3Test() {
        String beginWord = "hit";
        String endWord = "adklfjal";
        String words [] = new String[] {"hot","dot","dog","lot","log", "adklfjal"};

        WordLadder3 wordLadder3 = new WordLadder3();
        List<String> res = wordLadder3.findLadders(beginWord, endWord, new HashSet<>(Arrays.asList(words)));

        System.out.println(res.toString());
    }

    private static void wordLadder2Test() {
        WordLadder2 wordLadder2 = new WordLadder2();

//        String beginWord = "leet";
//        String endWord = "code";
//        String words [] = new String[]{"lest","leet","lose","code","lode","robe","lost"};

//        String beginWord = "a";
//        String endWord = "c";
//        String words [] = new String[]{"a","b","c"};


        String beginWord = "hit";
        String endWord = "cog";
        String words [] = new String[] {"hot","dot","dog","lot","log","cog"};

        List<List<String>> res = wordLadder2.findLadders(beginWord, endWord, Arrays.asList(words));

        for (List<String> path : res) {
            System.out.println(path.toString());
        }
    }
}
