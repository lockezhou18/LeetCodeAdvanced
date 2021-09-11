public class RegularExpressionMatching {
//    public boolean isMatch(String s, String p) {
//        return dfs(s, 0, p, 0, new Boolean[s.length()][p.length()]);
//    }
//
//    private boolean dfs(String s, int idxS, String p, int idxP, Boolean memo[][]) { //using idxS to represent the goal test of substring [0, idxS]
//        if (idxP == p.length()) {
//            if (idxS == s.length())
//                return true;
//
//            return false;
//        }
//
//        if (memo[idxS][idxP] != null)
//            return memo[idxS][idxP];
//
//        //no need to check cycle, since this is a acycle graph
//
//        //action, transition model
//
//        //no action - no translate expression to words
//        if (idxP + 1 == p.length() || p.charAt(idxP + 1) != '*') {
//            if (isCharSame(s, idxS, p, idxP)) {
//                boolean flag = dfs(s, idxS + 1, p, idxP + 1, memo);
//                memo[idxS][idxP] = flag;
//                return flag;
//            }
//            return false;
//        } else {
//                //match 1+
//                int i = idxS - 1;
//                while (i < s.length() && (i == idxS - 1 || (isCharSame(s, i, p, idxP)))) {
//                    if (dfs(s, i + 1, p, idxP + 2, memo)) {
//                        memo[idxS][idxP] = true;
//                        return true;
//                    }
//                    i++;
//                }
//                memo[idxS][idxP] = false;
//                return false;
//            }
//        }
//
//    private boolean isCharSame(String s, int idxS, String p, int idxP) {
//        return (s.charAt(idxS) == p.charAt(idxP)) || (p.charAt(idxP) == '.');
//    }

    public boolean isMatch(String s, String p) {
        return dfs(s, 0, p, 0, new Boolean[s.length() + 1][p.length() + 1]);
    }

    private boolean dfs(String s, int idxS, String p, int idxP, Boolean[][] memo) { //using idxS to represent the goal test of substring [0, idxS]
        if (idxP == p.length()) {
           return idxS == s.length();
        }

        if (memo[idxS][idxP] != null)
            return memo[idxS][idxP];

        //no need to check cycle, since this is a acycle graph

        //action, transition model

        //no action - no translate expression to words
        if (idxP + 1 == p.length() || p.charAt(idxP + 1) != '*') {
            memo[idxS][idxP] = isCharSame(s, idxS, p, idxP) && dfs(s, idxS + 1, p, idxP + 1, memo);
        } else {
            //match 0
            if (dfs(s, idxS, p, idxP + 2, memo)) {
                memo[idxS][idxP] = true;
            }

            //match 1+
            int i = idxS;
            while (i < s.length() && (isCharSame(s, i, p, idxP))) {
                if (dfs(s, i + 1, p, idxP + 2, memo)) {
                    memo[idxS][idxP] = true;
                }
                i++;
            }
        }
        memo[idxS][idxP] = false;
        return memo[idxS][idxP];
    }

    private boolean isCharSame(String s, int idxS, String p, int idxP) {
        return idxS < s.length() && idxP < p.length() && ((s.charAt(idxS) == p.charAt(idxP)) || (p.charAt(idxP) == '.'));
    }
}
