    import java.util.*;

public class MainFile {
    public static void main(String args[]) {
        //testGenerateParenthesis();
        //testWordSearch();
        testWordSearch2();
        //testCombinationSum();
        //testRestoreIP();
        //testWordBreak();
        //testExpression();
        //testRemoveParentheses();
        //testAbbbrev();

        //System.out.println(-12 % 5);
        //testZumaGame();
        //Set<Integer> set = new HashSet<>();
        //int[] nums = new int[set.size()];

        //testSubSet();
        //testCombination();
        //testPermutation();
        //testWordBreak2();
        //testRegularExpressionMatching();
        //testFlipGame();
        //testWildCardMatching();
        //testCanIWin();
        //testFrogJump();
        //testMaxAreaOfIsland();
        //testPathWithMinimumEffort();
        //testEditDistance();
        //testBurstBallons();
        //testMinimumCosttoMergeStones();
        //testPathWithMinimumEffort();
        //testPalidromeMinCut();
        //testInterleavingString();
        //testLC472();
       // testLC1780();
        //testLC473();
    }


    private static void testLC473() {
        LC473 lc473 = new LC473();
        int[] matchsticks = {1,1,2,2,2};
        lc473.makesquare(matchsticks);
    }

    private static void testLC780() {
        LC780 lc780 = new LC780();

        int sx = 1;
        int sy = 1;
    }

    private static void testLC1780() {
        LC1780 lc1780 = new LC1780();
        lc1780.checkPowersOfThree(100000);
        System.out.println(lc1780.count);

    }

    private static void test428() {
        LC428 lc428 = new LC428();

    }

    private static void testLC472() {
        LC472 lc472 = new LC472();
        int[] testData = {83,22,11,15,50,78,16,38,23,77,81,16,3,72,94,53,91,73,7,74,86,12,36,51,5,80,47,68,29,71,5,16,26,4,16,26,6,8,48,93,27,10,93,61,34,50,50,82,59,10,7,94,18,5,5,97,21,9,71,72,29,87,83,31,71,61,79,49,27,18,72,55,75,1,67,54,90,87,93,49,66,8,11,85,74,50,45,33,33,85,35,86,57,26,29,64,75,73,5,71};
        boolean res = lc472.canPartition(testData);

        System.out.println(lc472.count);
    }

    private static void testInterleavingString() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbaccc";

        System.out.println(new InterleavingString().isInterleave(s1, s2, s3));
    }

    private static void testPalidromeMinCut() {
        String s = "ab";
        PalidromePartition partition = new PalidromePartition();
        //System.out.println(partition.minCut(s));
        //System.out.println(partition.isPalidrome(s));
        System.out.println(partition.getPalidromeMatrix(s));
    }

    private static void testMinimumCosttoMergeStones() {
        MinimumCosttoMergeStones minimumCosttoMergeStones = new MinimumCosttoMergeStones();
        int[] nums = {3,2,4,1};
        System.out.println(minimumCosttoMergeStones.mergeStones(nums, 2));
    }

    private static void testBurstBallons() {
        BurstBallons burstBallons = new BurstBallons();
        int[] nums = {1,5};
        System.out.println(burstBallons.maxCoinsDP(nums));
    }

    private static void testEditDistance() {
        EditDistance editDistance = new EditDistance();
//        String word1 = "horse";
//        String word2 = "ros";
        String word1 = "";
        String word2 = "abc";

        System.out.println(editDistance.minDistance(word1, word2));
    }

    private static void testPathWithMinimumEffort() {
        //int[][] heights = {{1,2,2}, {3,8,2}, {5,3,5}};
        //int[][] heights = {{1,2,3},{3,8,4},{5,3,5}};
        int[][] heights = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}};
        //int[][] heights = {{4,3,4,10,5,5,9,2},{10,8,2,10,9,7,5,6},{5,8,10,10,10,7,4,2},{5,1,3,1,1,3,1,9},{6,4,10,6,10,9,4,6}};
        PathWithMinimumEffort pathWithMinimumEffort = new PathWithMinimumEffort();
        System.out.println(pathWithMinimumEffort.minimumEffortPath(heights));
    }

    private static void testMaxAreaOfIsland() {
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        int grid[][] = {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }


    private static void testFrogJump() {
        int[] stones = {0,1,2,5};
        FrogJump frogJump = new FrogJump();
        System.out.println(frogJump.canCross(stones));
    }

    private static void testCanIWin() {
        int maxChoosableInteger = 10;
        int desiredTotal = 11;
        CanIWin canIWin = new CanIWin();
        System.out.println(canIWin.canIWin(maxChoosableInteger, desiredTotal));
    }

    private static void testWildCardMatching() {
        WildCardMatching wildCardMatching = new WildCardMatching();
        String s = "ab"; String p = "?*";
        System.out.println(wildCardMatching.isMatch(s, p));
    }

    private static void testFlipGame() {
        FlippingGame flippingGame = new FlippingGame();
        String initialState = "++++";
        System.out.println(flippingGame.canWin(initialState));
    }

    private static void testRegularExpressionMatching() {
        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();
        String s = "a"; String p = ".*..a*";
        System.out.println(regularExpressionMatching.isMatch(s, p));
    }

    private static void testWordBreak2() {
        WordBreak2 wordBreak2 = new WordBreak2();
        String s = "catsanddog";
        String[] wordDict = new String[]{"cat","cats","and","sand","dog"};
        System.out.println(wordBreak2.wordBreak(s, Arrays.asList(wordDict)));
    }

    private static void testPermutation() {
        Permutation permutation = new Permutation();
        ArrayList<Integer> res = new ArrayList<Integer>();
        System.out.println(permutation.permute(new int[]{1,2,3}));
    }

    private static void testPermutation2() {
        Permutation2 permutation = new Permutation2();
        System.out.println(permutation.permute(new int[]{1,1}));
    }
    private static void testCombination() {
        Combinations combinations = new Combinations();
        System.out.println(combinations.combine(1, 1));
    }
    private static void testSubSet() {
        SubSet subSet = new SubSet();

        System.out.println(subSet.subsets(new int[]{1,2,3}));
    }

    private static void testZumaGame() {
//        String board = "WRRBBW";
//        String hand = "RB";
        String board = "G"; String hand = "GGGGG";

        System.out.println(new ZumaGame().findMinStep(board, hand));
    }

    private static void testAbbbrev () {
        GeneralizedAbbreviation ga = new GeneralizedAbbreviation();
        String word = "word";

        List<String> res = ga.generateAbbreviations(word);
        Collections.reverse(res);
        System.out.println(res.toString());


    }
    private static void testRemoveParentheses() {
        String s  = "()())()";
        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();
        int[] minNum = removeInvalidParentheses.findMinRemoveNum(s);
        System.out.println("rml:" + minNum[0] + "  rmr:" + minNum[1]);
        System.out.println(removeInvalidParentheses.removeInvalidParentheses(s).toString());
    }


    private static void testExpression() {
        String num = "00";
        int target = 0;

        ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();
        List<String> res = expressionAddOperators.addOperators(num, target);
        System.out.println(res.toString());
    }

    private static void testWordBreak() {
//        String s = "leetcode";
//        String[] wordDict = new String[]{"leet","code"};

        String s = "applepenapple";
        String[] wordDict = new String[]{"apple","pen"};

//        String s = "bb";
//        String[] wordDict = new String[]{"a","b","bbb","bbbb"};

        WordBreak wordBreak = new WordBreak();
        System.out.println(wordBreak.wordBreak(s, Arrays.asList(wordDict)));
    }
    private static void testRestoreIP() {
        String s = "25525511135";
        RestoreIP restoreIP = new RestoreIP();
        List<String> res = restoreIP.restoreIpAddresses(s);
        System.out.println(res.toString());
    }

    private static void testCombinationSum() {
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int target = 8;
        CombinationSum1and2 combinationSum1and2 = new CombinationSum1and2();
        List<List<Integer>> res = combinationSum1and2.combinationSum(candidates, target);

        System.out.println(res.toString());
    }
    private static void testWordSearch2() {
        char[][] board = new char[][] {{'o','a','b','n'},{'o','t','a','e'},{'a','h','k','r'},{'a','f','l','v'}};
        String [] words = new String[]{"oath","eat"};
//        char[][] board = new char[][] {{'a','b'},{'c','d'}};
//        String[] words = new String[]{"abcb"};

        WordSearch2 wordSearch2 = new WordSearch2();
        List<String> res = wordSearch2.findWords(board, words);

        System.out.println(res.toString());
    }

    private static void testGenerateParenthesis() {
        int n = 1;
        GenerateParenthesis gp = new GenerateParenthesis();
        List<String> res = gp.generateParenthesis(n);

        System.out.println(res.toString());
    }

    private static void testWordSearch() {
        char[][] board = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCB";

        WordSearch wordSearch = new WordSearch();

        System.out.println(wordSearch.exist(board, word));
    }
}
