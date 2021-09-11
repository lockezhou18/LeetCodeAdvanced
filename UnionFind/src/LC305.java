import java.util.ArrayList;
import java.util.List;

class UF{
    int count;
    int m;
    int n;
    int[] parents;
    int[] size;

    public UF(int m, int n) {
        this.m = m;
        this.n = n;
        parents = new int[m * n];
        size = new int[m * n];
        initialParents();
    }

    private void initialParents() { //0 -> valid node. -1 -> not island.
        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1;
        }
    }
    private int getRoot(int i, int j) {
        int cur = i * n + j;
        while (parents[cur] != cur) {
            parents[cur] = parents[parents[cur]];
            cur = parents[cur];
        }

        parents[i * n + j] = cur;
        return cur;
    }

    public boolean find(int i, int j, int ii, int jj) {
        return getRoot(i, j) == getRoot(ii, jj);
    }

    public void union(int i, int j, int ii, int jj) {
        int root1 = getRoot(i, j);
        int root2 = getRoot(ii, jj);

        if (size[root1] < size[root2]) {
            parents[root1] = root2;
            size[root2] += size[root1];
        } else {
            parents[root2] = root1;
            size[root1] += size[root2];
        }
        count--;
    }

    public void addIsland(int i, int j) {
        parents[i * n + j] = i * n + j;
        size[i * n + j] = 1;
        count++;
    }

    public int getIslands() {
        return count;
    }
}
public class LC305 {
    int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        UF uf = new UF(m, n);

        for (int[] position : positions) {
            int i = position[0];
            int j = position[1];

            //set current node as island
            uf.addIsland(i, j);

            for (int[] direction : DIRECTIONS) {
                int ii = i + direction[0];
                int jj = j + direction[1];

                if (ii < 0 || ii >= m || jj < 0 || jj >= n || uf.parents[ii * n + jj] == -1)
                    continue;

                if (!uf.find(i, j, ii, jj))
                    uf.union(i, j, ii, jj);
            }

            res.add(uf.getIslands());
        }

        return res;
    }
}
