public class WildCardMatching {
    public boolean isMatch(String s, String p) {
        return dfs(s, 0, p, 0, new Boolean[s.length() + 1][p.length() + 1]);
    }

   private boolean dfs(String s, int idxS, String p, int idxP, Boolean[][] memo) {
        if (idxP == p.length()) {
            return idxS == s.length();
        }

        if (memo[idxS][idxP] != null)
            return memo[idxS][idxP];

        //1. not star
        if (p.charAt(idxP) != '*') {
           if (!isSame(s, idxS, p, idxP)) {
               memo[idxS][idxP] = false;
               return false;
           }

           memo[idxS][idxP] = dfs(s, idxS + 1, p, idxP + 1, memo);
           return memo[idxS][idxP];
        }

        //2. is star
       for (int k = idxS; k <= s.length(); k++) {
           if (dfs(s, k, p, idxP + 1, memo)) {
               memo[idxS][idxP] = true;
               return memo[idxS][idxP];
           }
       }

       memo[idxS][idxP] = false;
       return memo[idxS][idxP];
   }

    private boolean isSame(String s, int idxS, String p, int idxP) {
        return  (idxS < s.length() && idxP < p.length()
                && (s.charAt(idxS) == p.charAt(idxP) || p.charAt(idxP) == '?'));
    }
}
