public class LC780 {
    //Reaching points
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        return dfs(sx, sy, tx, ty);
    }

    private boolean dfs(int sx, int sy, int tx, int ty) {
        if (sx > tx || sy > ty)
            return false;

        if (sx == tx || sy == ty)
            return true;

        if (dfs(sx + sy, sy, tx, ty))
            return true;

        return dfs(sx, sy + sx, tx, ty);
    }
}
