package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CombinationSum {

	public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> results = new ArrayList<>();
        if(candidates == null || candidates.length == 0){
            return results;
        }
        // 可以先去重
        Integer[] array = Stream.of(candidates).distinct().toArray(Integer[]::new);
        Arrays.sort(array);
        // Integer[] array = Stream.of(candidates).distinct().sorted().toArray(Integer[]::new);
        recursion(array, 0, target, new ArrayList<Integer>(), results);
        return results;
    }

    private void recursion(Integer[] candidates, 
                            int index,
                            int remainTarget,
                            List<Integer> combination,
                            List<List<Integer>> results){
        // 递归的出口
        if(remainTarget < 0){
            return;
        }
        if(remainTarget == 0){
            results.add(new ArrayList<>(combination));
            return;
        }
        // 递归的拆解
        // 选择一次该处的值，还可以再取
        // 也可使用循环！循环下一个选择的位置！
        combination.add(candidates[index]);
        recursion(candidates, index, remainTarget - candidates[index], combination, results);
        combination.remove(combination.size() - 1);
        // 不选择该处的值
        recursion(candidates, index + 1, remainTarget, combination, results);
    }
}
