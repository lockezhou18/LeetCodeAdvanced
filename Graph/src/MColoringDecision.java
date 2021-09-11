
//csp right answer using forward checking
//T.c almost linear time search tree converging to 1 path

import java.util.*;

public class MColoringDecision {
    int V;
    int[][] graph;
    int m;

    public MColoringDecision(int[][] graph, int m) {
        this.graph = graph;
        this.V = graph.length;
        this.m = m;
    }
    public boolean isColorable() { //m color
        Map<Integer, Set<Integer>> domains = constructDomains(graph);
        return isColorable(0, new int[graph.length], graph, domains);
    }

    private Map<Integer, Set<Integer>> constructDomains(int[][] graph) {
        Map<Integer, Set<Integer>> domains = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            Set<Integer> domain = new HashSet<>();
            for (int m = 1; m <= this.m; m++) {
                domain.add(m);
            }
            domains.put(i, domain);
        }
        return domains;
    }

    private boolean isColorable(int v, int[] assignment, int[][] graph, Map<Integer, Set<Integer>> domains) {
        if (v == V) //fully assigment
            return true;

        for (int i = 1; i <= this.m; i++) {
            //if (isSatisfyingConstraint(i, v, assignment)) {
            Set<Integer> domain = domains.get(v);
            Map<Integer, Set<Integer>> removals = suppose(v, graph, domains);
            if (domain.contains(i) && forwardChecking(v, i, domains, graph, assignment)) {
                assignment[v] = i;
                if (isColorable(v + 1, assignment, graph, domains)) {
                    return true;
                }
                restore(domains, removals);
                assignment[v] = 0;
            }

        }

        return false;
    }

    private void restore(Map<Integer, Set<Integer>> domains, Map<Integer, Set<Integer>> removals) {
        for (Integer key : removals.keySet()) {
            domains.put(key, removals.get(key));
        }
    }

    //Using forwarding checking to get the current v domain
    private boolean isColorable(int v, int[] assignment, int[][] graph) {
        if (v == V) //fully assigment
            return true;

        for (int i = 1; i <= this.m; i++) {
            //if (isSatisfyingConstraint(i, v, assignment)) {
            Set<Integer> domain = forwardCheckingForCurDomain(v, graph, assignment);
            if (domain.contains(i)) {
                assignment[v] = i;
                if (isColorable(v + 1, assignment, graph)) {
                    return true;
                }
                assignment[v] = 0;
            }

        }

        return false;
    }

    private Set<Integer> forwardCheckingForCurDomain(int v, int[][] csp, int[] assignment) {
        Set<Integer> candidates = new HashSet<>();
        for (int i = 1; i <= this.m; i++) {
            candidates.add(i);
        }

        int[] nexts = csp[v];
        for (Integer next : nexts) {
            candidates.remove(assignment[next]);
        }
        return candidates;
    }

    private Map<Integer, Set<Integer>> suppose(int v, int[][] graph, Map<Integer, Set<Integer>> domains) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int[] nexts = graph[v];
        for (Integer next : nexts) {
            map.put(next, domains.get(v));
        }
        return map;
    }



    private boolean forwardChecking(int v, int curColor, Map<Integer, Set<Integer>> domains, int[][] graph, int[] assigment) {
        int[] nexts = graph[v];

        for (Integer next : nexts) {
            Set<Integer> domain = domains.get(next);
            if (assigment[next] == 0 && domain.size() > 0) {
                domain.remove(curColor);
                if (domain.size() == 0)
                    return false;
            }
            domains.put(next, domain);
        }
        return true;
    }

    private boolean isSatisfyingConstraint(int curColor, int v, int[] assignment) {
        int[] nexts = graph[v];
        for (Integer next : nexts) {
            if (assignment[next] == curColor)
                return false;
        }

        return true;
    }
}
