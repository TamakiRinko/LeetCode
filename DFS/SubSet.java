package DFS;

import java.util.ArrayList;
import java.util.Arrays;

public class SubSet {
    public ArrayList<ArrayList<Integer>> subsets(int[] nums){
        // nums的所有子集：2^n个
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }

        // Integer[] array = Stream.of(nums).sorted().toArray(Integer[]::new);
        Arrays.sort(nums);
        subsetHelper(nums, 0, result, new ArrayList<>());
        return result;

    }

    private void subsetHelper(int[] nums, int pos, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list){
        // 每个中间过程都是一个结果
        result.add(new ArrayList<>(list));

        for(int i = pos; i < nums.length; ++i){
            list.add(nums[i]);
            subsetHelper(nums, i + 1, result, list);
            // 最后一个add的nums[i]给remove掉
            list.remove(list.size() - 1);
        }
    }

    /**
     * nums中有重复元素时的subsets
     * @param nums  已经排好序的数组
     * @param pos
     * @param result
     * @param list
     */
    private void subset_ii_Helper(int[] nums, int pos, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list){
        // 每个中间过程都是一个结果
        result.add(new ArrayList<>(list));

        for(int i = pos; i < nums.length; ++i){
            if(i != pos && nums[i] == nums[i - 1]){
                // 跳过了nums[i - 1]来取nums[i]，不可以！
                continue;
            }
            list.add(nums[i]);
            subsetHelper(nums, i + 1, result, list);
            // 最后一个add的nums[i]给remove掉
            list.remove(list.size() - 1);
        }
    }


    public ArrayList<ArrayList<Integer>> subsetsNonRecursion(int[] nums){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0; i < (1 << n); ++i){
            // 1, 2 ···, 2^n
            ArrayList<Integer> subsets = new ArrayList<>();
            for(int j = 0; j < n; ++j){
                // 该位为1，则subsets中包含该位
                if((i & (1 << j)) != 0){
                    subsets.add(nums[j]);
                }
            }
            result.add(subsets);
        }
        return result;
    }
}
