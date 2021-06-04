package Array.Pointers_And_Window;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 */
public class MergeTwoSortedArrays {
    /**
     * 使用双指针，从后往前填充即可
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums2 == null || nums2.length == 0)  return;
        int index1 = m - 1;
        int index2 = n - 1;
        int index = m + n - 1;
        // 从后往前填充
        while(index >= 0 && index1 >= 0 && index2 >= 0){
            if(nums1[index1] < nums2[index2]){
                nums1[index] = nums2[index2];
                index2--;
            }else{
                nums1[index] = nums1[index1];
                index1--;
            }
            index--;
        }
        // 某个数组有剩余
        while(index1 >= 0){
            nums1[index] = nums1[index1];
            index1--;
            index--;
        }
        while(index2 >= 0){
            nums1[index] = nums2[index2];
            index2--;
            index--;
        }
    }
}
