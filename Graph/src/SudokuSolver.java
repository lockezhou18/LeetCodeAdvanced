import java.util.*;

//http://www.norvig.com/sudoku.html
public class SudokuSolver {
    public void solveSudoku(char[][] board) {

    }

    private Map<Integer, Set<Character>> constructDomains(char[][] board) {
        return new HashMap<>();
    }

    private void solveSudoku(char[][] assignment, int i, int j,
                             Map<Integer, Set<Character>> domains, Map<Integer, List<Integer>> csp) {
        int row = assignment.length;
        int col = assignment[0].length;
        int curIdx = i * col + j;
        if (i == row && j == col)
            return;

        for (char c = '1'; c <= '9'; c++) {
            Set<Character> domain = domains.get(curIdx);
            Map<Integer, Set<Character>> removals = suppose(csp, domains, i, j);
            if (domain.contains(c) && forwardChecking(assignment, c, csp, i, j, domains)) {
                assignment[i][j] = c;
                if (j <= col)
                    solveSudoku(assignment, i, j + 1, domains, csp);
                else
                    solveSudoku(assignment, i + 1, j, domains, csp);
                assignment[i][j] = '\u0000';
            }
            restore(domains, removals);
        }
    }

    private void restore(Map<Integer, Set<Character>> domains, Map<Integer, Set<Character>> removals) {
        for (Integer key : removals.keySet()) {
            domains.put(key, removals.get(key));
        }
    }

    private Map<Integer, Set<Character>> suppose(Map<Integer, List<Integer>> csp,
                                                 Map<Integer, Set<Character>> domains,
                                                 int i, int j) {
        Map<Integer, Set<Character>> removals = new HashMap<>();

        List<Integer> nexts = csp.get(i * 9 + j);
        for (Integer next : nexts) {
            removals.put(i * 9 + j, domains.get(i * 9 + j));
        }
        return removals;
    }

    private boolean forwardChecking(char[][] assignment, char curVal,
                                    Map<Integer, List<Integer>> csp,
                                    int i, int j, Map<Integer, Set<Character>> domains) {
        List<Integer> nexts = csp.get(i * 9 + j);

        for (Integer next : nexts) {
            Set<Character> domain = domains.remove(next);
            int ii = next / 9;
            int jj = next % 9;

            for (char c = '1'; c <= '9'; c++) {
                if (assignment[ii][jj] == '\u0000' && domain.size() > 0) {
                    domain.remove(curVal);
                    if (domain.size() == 0)
                        return false;
                }
            }
            domains.put(next, domain);
        }
        return true;
    }

    private Map<Integer, List<Integer>> constructCSP() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {

        }
        return map;
    }
}
