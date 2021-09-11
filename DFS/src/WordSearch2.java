import org.jetbrains.annotations.NotNull;

import java.util.*;

//wrond answer, maybe using trie
//9.7 - Using Trie

public class WordSearch2 {
    public class TrieNode {
        String word = "#";
        char ch;
        Map<Character, TrieNode> children;

        public TrieNode(char ch) {
            this.ch = ch;
            children = new HashMap<>();
        }
    }

    private TrieNode root = new TrieNode('0');
    private int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public List<String> findWords(char[] @NotNull [] board, String[] @NotNull words) {
        constructTrie(words);
        List<String> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.children.containsKey(board[i][j]))
                    dfs(board, i, j, res, visited, root.children.get(board[i][j]));
            }
        }

        return res;
    }

    private void dfs(char[][] board, int i, int j, List<String> res, Set<Integer> visited, TrieNode cur) {
        //successful
        if (!cur.word.equals("#")) {
            if (cur.word.equals("oath")) {
                System.out.println("debug");
            }
            res.add(cur.word);
            cur.word = "#";
            return;
        }

        visited.add(i * board[0].length + j);
        for (int[] DIRECTION : DIRECTIONS) {
            int ii = i + DIRECTION[0];
            int jj = j + DIRECTION[1];

            if (ii == 1 && jj == 1) {
                System.out.println("debug");
            }
            if (ii >= 0 && ii < board.length && jj >= 0 && jj < board[0].length
                    && !visited.contains(ii * board[0].length + jj)
                    && cur.children.containsKey(board[ii][jj])) {
                TrieNode next = cur.children.get(board[ii][jj]);
                dfs(board, ii, jj, res, visited, next);
            }

            visited.remove(i * board[0].length + j);
        }

    }

    private void constructTrie(String[] words) {
        for (String word : words) {
            TrieNode parent = root;
            for (Character ch : word.toCharArray()) {
                if (parent.children.containsKey(ch)) {
                    parent = parent.children.get(ch);
                    continue;
                }

                TrieNode child = new TrieNode(ch);
                parent.children.put(ch, child);
                parent = child;
            }
            parent.word = word;
        }
    }

}
