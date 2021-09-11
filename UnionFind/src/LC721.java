import java.util.*;

public class LC721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind2 uf = new UnionFind2(accounts.size());
        //1. put all the nodes sharing at least 1 email together in a disjoint set
        Map<String, Integer> e2uMap = new HashMap<>(); //key -> email, val -> node that has this email
        for (int i = 0; i < accounts.size(); i++) { //O(n)
            List<String> account = accounts.get(i);
            int curUser = i;
            uf.initial(curUser);
            for (int j = 1; j < account.size(); j++) { //loop all the emails //O(k)
                String email = account.get(j);
                if (e2uMap.containsKey(email)) {
                    int lastUser = e2uMap.get(email);
                    uf.union(curUser, lastUser); //O(lgn)
                    continue;
                }

                e2uMap.put(email, curUser);
            }
        }
        //2. Collect emails within same disjoint set
        Map<Integer, HashSet<String>> u2eMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            int rootUser = uf.getRoot(i); //判断每一个节点的root，figure out是在哪一个connected components里
            HashSet<String> emails = u2eMap.getOrDefault(rootUser, new HashSet<>());
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                emails.add(email);
            }
            u2eMap.put(rootUser, emails);
        }

        //convert to the res type
        List<List<String>> res = new ArrayList<>();
        for (Integer user : u2eMap.keySet()) {
            List<String> curRes = new ArrayList<>();

            HashSet<String> emails = u2eMap.get(user);
            String userName = accounts.get(user).get(0);

            curRes.add(userName);

            List<String> emailsList = convert(emails);
            curRes.addAll(emailsList);

            res.add(curRes);
        }

        return res;
    }

    private List<String> convert(HashSet<String> emails) {
        List<String> res = new ArrayList<>();
        res.addAll(emails);
        Collections.sort(res);
        return res;
    }
}

class UnionFind2 {
    int[] parents, size;

    public UnionFind2(int size) {
        this.parents = new int[size];
        this.size = new int[size];
    }

    public int getRoot(int p) {
        int cur = p;
        while (parents[cur] != cur) {
            parents[cur] = parents[parents[cur]];
            cur = parents[cur];
        }

        parents[p] = cur;
        return cur;
    }

    public boolean find(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    public void union(int p, int q) {
        int rootP = getRoot(p);
        int rootQ = getRoot(q);

        if (size[rootP] < size[rootQ]) { // p -> q
            parents[rootP] = rootQ;
            size[rootQ] += rootP;
        } else { //q -> p
            parents[rootQ] = rootP;
            size[rootP] += rootQ;
        }
    }

    public void initial(int cur) {
        parents[cur] = cur;
        size[cur] = 1;
    }
}
