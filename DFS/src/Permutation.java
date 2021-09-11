import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, 0, new HashSet<>());
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path, int[] nums, int position, Set<Integer> visited) {
        if (position == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!path.contains(nums[i])) {
                path.add(nums[i]);
                dfs(res, path, nums, position + 1, visited);
                path.remove(path.size() - 1);
           }
        }
    }

//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> res = new ArrayList<List<Integer>>();
//
//        if(nums == null || nums.length == 0) return res;
//        permutation(nums, 0, res);
//        return res;
//
//    }
//
//    private void permutation(int [] nums, int level, List<List<Integer>> res){
//        if(level == nums.length - 1){
//            List<Integer> tmp = new ArrayList<Integer>();
//            for(int i = 0; i < nums.length; i++){
//                tmp.add(nums[i]);
//            }
//            res.add(tmp);
//            return;
//        }
//
//
//        //Set<Integer> set = new HashSet<Integer>();
//
//        for(int i = level; i < nums.length; i++){
//                swap(nums, i, level);
//                permutation(nums, level + 1, res);
//                swap(nums, i, level);
//        }
//    }
//
//    private void swap(int [] nums, int i, int j){
//        int tmp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = tmp;
//    }
}
