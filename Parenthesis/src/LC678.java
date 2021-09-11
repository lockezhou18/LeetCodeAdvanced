public class LC678 {
    //time limit exceed
    public boolean checkValidString(String s) {
        return dfs(s, 0, 0);
    }

    private boolean dfs(String s, int curIdx, int delta) {
        if (curIdx == s.length()) {
            if (delta != 0)
                return false;

            return true;
        }

        if (delta < 0)
            return false;


        char ch = s.charAt(curIdx);

        //left
        if (ch == '(')
            return dfs(s, curIdx + 1, delta + 1);

        //right
        if (ch == ')')
            return dfs(s, curIdx + 1, delta -1);

        //*
        boolean res = dfs(s, curIdx + 1, delta)
                || dfs(s, curIdx + 1, delta + 1)
                || dfs(s, curIdx + 1, delta - 1);

        return res;

    }
}
