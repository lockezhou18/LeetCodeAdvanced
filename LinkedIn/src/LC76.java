public class LC76 {
    String res = "";

    public String minWindow(String s, String t) {
        if (s.length() < t.length())
            return "";

        if (s.equals(t))
            return s;

        dfs(s, t, 0, 0, new StringBuilder());
        return res;
    }


    private void dfs(String s, String t, int idxS, int idxT, StringBuilder path) {
        if (idxS == s.length()) {
            if (idxT == t.length() && res.length() > path.length())
                res = path.toString();

            return;
        }

        int len = path.length();

        path.append(s.charAt(idxS));

        if (s.charAt(idxS) == t.charAt(idxT)) {
            dfs(s, t, idxS + 1, idxT + 1, path);
            dfs(s, t, idxS + 1, idxT, path);
        } else {
            dfs(s, t, idxS + 1, idxT, path);
        }
        path.setLength(len);
    }
}
