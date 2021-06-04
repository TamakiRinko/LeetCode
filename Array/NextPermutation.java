package Array;

/**
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 */
public class NextPermutation {
    /**
     * 从后往前，找到第一个降序的位置，与后面最后一个比他大的位置调换，对后面进行逆序
     * 126873，找到6，与7调换位置变为127863，并对863逆序，变为127368
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1){
            return;
        }
        int n = nums.length;
        int index = -1;
        for(int i = n - 1; i > 0; --i){
            if(nums[i] > nums[i - 1]){
                index = i - 1;
                break;
            }
        }
        // 不是最大排列，需要调换index和后续最后一个比他大的元素的位置
        if(index != -1){
            int pos = n - 1;
            for(int i = index + 1; i < n; ++i){
                if(nums[i] <= nums[index]){
                    // 最后一个大于index的值，与他调换
                    // 注意<=，重复元素不算不同排序，1510后一个排序为5110而不是1015
                    pos = i - 1;
                    break;
                }
            }
            swap(nums, pos, index);
        }
        // 从index往后逆序
        int begin = index + 1;
        int end = n - 1;
        while(begin < end){
            swap(nums, begin, end);
            begin++;
            end--;
        }
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
