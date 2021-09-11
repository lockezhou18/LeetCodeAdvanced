//lc 289
public class GameOfLife {
    private static final int[][] DIRECTIONS = {{1,0}, {0,1}, {-1, 0}, {0, -1},
                                                {1,1}, {1, -1}, {-1, 1}, {-1, -1}};
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int liveNum = countLives(i, j, board);
                int curState = board[i][j];
                int nextState = applyRules(curState, liveNum);
                board[i][j] = setState(curState, nextState);
            }
        }

        refreshBoard(board);
    }

    private void refreshBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = getNextState(board[i][j]);
            }
        }
    }

    private int countLives(int i, int j, int[][] board) {
        int res = 0;
        for(int[] direction : DIRECTIONS) {
            int ii = i + direction[0];
            int jj = j + direction[1];
            if (ii >= 0 && ii < board.length && jj >= 0 && jj < board[0].length
                && getCurState(board[ii][jj]) == 1) {
                res++;
            }
        }
        return res;
    }

    private int getCurState(int state) {
        return state & 1; //0 position represent the current state
    }

    private int getNextState(int state) {
        return (state >> 1) & 1;
    }

    private int setState(int curState, int nextState) {
        int mask = 0;
        mask = (mask | nextState) << 1;
        mask |= curState;

        return mask;
    }

    private int applyRules(int curState, int liveNum) {
        if (curState == 0) { //dead cell
            if (liveNum == 3)
                return 1;
            return 0;
        }

        if (liveNum < 2 || liveNum > 3)
            return 0;

        return 1;
    }
}
