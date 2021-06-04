package Heap;

import java.util.PriorityQueue;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */
public class FindKthLargest {
    /**
     * 使用PriorityQueue，注意默认为最小堆
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->{return b - a;});
        for(int i = 0; i < n; ++i){
            pq.add(nums[i]);
        }
        for(int i = 0; i < k - 1; ++i){
            pq.poll();
        }
        return pq.poll();
    }

    /**
     * 手动建堆！大顶堆，下沉操作为将小的沉底
     */
    public int findKthLargest2(int[] nums, int k){
        // 建堆，对所有非叶子节点，进行下沉操作
        // 这里是最大堆
        int n = nums.length;
        for(int i = n / 2 - 1; i >= 0; --i){
            heapify(nums, i, n);
        }
        // k-1次将最大值放到末尾，对顶部进行下沉操作
        for(int i = 0; i < k - 1; ++i){
            swap(nums, 0, n - 1);
            n--;
            heapify(nums, 0, n);
        }
        return nums[0];
    }
    // 以i为根的下沉操作，将小值沉底
    public void heapify(int[] arr, int i, int len){
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        if(left < len && arr[left] > arr[largest]){
            largest = left;
        }
        if(right < len && arr[right] > arr[largest]){
            largest = right;
        }
        if(largest != i){
            // 小于左右孩子，下沉并递归判断
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

    // 交换arr中i和j位置的值
    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
