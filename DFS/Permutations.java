package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null){
            return result;
        }
        if(nums.length == 0){
            result.add(new ArrayList<>());
            return result;
        }
        // 是否访问过
        int[] visited = new int[nums.length];
        Arrays.sort(nums);
        permuteHelper(nums, visited, new ArrayList<>(), result);

        return result;
    }

    private void permuteHelper(int[] nums, int[] visited, List<Integer> list, List<List<Integer>> result){
        if(list.size() == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }

        // 每次从0开始，已经用过的不再使用
        for(int i = 0; i < nums.length; ++i){
            if(visited[i] == 1){
                continue;
            }
            visited[i] = 1;
            list.add(nums[i]);
            permuteHelper(nums, visited, list, result);
            list.remove(list.size() - 1);
            visited[i] = 0;
        }
    }

    /**
     * nums中存在相同元素，取当前元素时，之前相同的元素必须已经取完！
     * @param nums
     * @param visited
     * @param list
     * @param result
     */
    private void permute_ii_Helper(int[] nums, int[] visited, List<Integer> list, List<List<Integer>> result){
        if(list.size() == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }

        // 每次从0开始，已经用过的不再使用
        for(int i = 0; i < nums.length; ++i){
            // 前一个还没取，就想取这个，不可以！
            if((visited[i] == 1) || (i != 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0)){
                continue;
            }
            visited[i] = 1;
            list.add(nums[i]);
            permuteHelper(nums, visited, list, result);
            list.remove(list.size() - 1);
            visited[i] = 0;
        }
    }
}
