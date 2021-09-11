package Sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC36 {
    public boolean isValidSudoku(char[][] board) {
        Set<Integer>[] rows = new Set[9];
        Set<Integer>[] cols = new Set[9];
        Set<Integer>[] boxs = new Set[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxs[i] = new HashSet<>();
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.')
                    continue;

                int cur = board[i][j] - '0';
                if (rows[i].contains(cur) || cols[j].contains(cur) || boxs[(i / 3) * 3 + j / 3].contains(cur))
                    return false;

                rows[i].add(cur);
                cols[j].add(cur);
                boxs[(i / 3) * 3 + j / 3].add(cur);
            }
        }

        return true;
    }
}
