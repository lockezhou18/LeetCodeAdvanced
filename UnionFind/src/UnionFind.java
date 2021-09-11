//standard union find
public class UnionFind {
    class V {
        int val;
        V parent;
        int size;
    }

    private V getRoot(V p) { //path compress and find root of node p
        V cur = p;

        while (cur.parent != cur) {
            cur.parent = cur.parent.parent;
            cur = cur.parent;
        }
        p.parent = cur;
        return cur;
    }

    public boolean find(V p, V q) { //check if p and q has same root in the same set
        return getRoot(p) == getRoot(q);
    }

    public void union(V p, V q) { //smaller tree is connected to the larger tree
        V rootP = getRoot(p);
        V rootQ = getRoot(q);

        if (rootP.size < rootQ.size) {
            rootP.parent = rootQ;
            rootQ.size += rootP.size;
        } else {
            rootQ.parent = rootP;
            rootP.size += rootQ.size;
        }
    }
}
