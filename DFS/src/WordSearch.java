import java.util.HashSet;
import java.util.Set;

//search problem on 2d matrix - template include
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, 0, word, new HashSet<Integer>()))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, int curIdx,
                        String word, Set<Integer> visited) {
        //success case
        if (curIdx == word.length())
            return true;

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
            || word.charAt(curIdx) != board[i][j]
            || visited.contains(i * board[0].length + j))
            return false;

        //mark as visited
        visited.add(i * board[0].length + j);

        boolean res = dfs(board, i - 1, j, curIdx + 1, word, visited)
                    || dfs(board, i, j - 1, curIdx + 1, word, visited)
                    || dfs(board, i + 1, j, curIdx + 1, word, visited)
                    || dfs(board, i, j + 1, curIdx + 1, word, visited);

        visited.remove(i * board[0].length + j);

        return res;

    }
}
