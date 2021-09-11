package Sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC37 {
    char[][] res;
    public void solveSudoku(char[][] board) {
        csp(board, 0);
        board = res;
    }

    private char[][] deepCopy(char[][] board) {
        char[][] res = new char[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                res[i][j] = board[i][j];
            }
        }

        return res;
    }

    private void csp(char[][] board, int curIdx) {
        if (curIdx == 80) {
            res = deepCopy(board);
            return;
        }

        int i = curIdx / 9;
        int j = curIdx % 9;

        if (board[i][j] != '.')
            return;

        for (char ch = '1'; ch <= '9'; ch++) {
            board[i][j] = ch;
            if (isValidSudoku(board)) {
                csp(board, curIdx + 1);
            }
            board[i][j] = '.';
        }
    }

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
