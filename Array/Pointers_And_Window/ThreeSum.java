package Array.Pointers_And_Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0
 * 且不重复的三元组。 注意：答案中不可以包含重复的三元组。
 */
public class ThreeSum {

	/**
     * 双指针法，先排序（不需要去重），固定a之后，b和c用双指针，从前后缩减
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 3){
            return result;
        }
        int[] array = Arrays.stream(nums).sorted().toArray();
        int n = array.length;

        for(int i = 0; i < n - 2; ++i){
            int begin = i + 1;
            int end = n - 1;
            int a = array[i];
            while(begin < end){
                if(array[begin] + array[end] > -a){
                    end--;
                }else if(array[begin] + array[end] < -a){
                    begin++;
                }else{
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(a);
                    arrayList.add(array[begin]);
                    arrayList.add(array[end]);
                    result.add(arrayList);
                    // 跳过重复的元素
                    while(begin < end && array[begin] == array[begin + 1])  begin++;
                    while(begin < end && array[end] == array[end - 1])  end--;
                    // 依然要继续进行，不能直接break;
                    begin++;
                    end--;
                }
            }
            // 跳过重复的元素
            while(i < n - 2 && array[i] == array[i + 1]) i++;
        }
        return result;
    }
}
