package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import DataStructure.TreeNode;

public class MySort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 2, 135, 12, -1, 4, 67, 3, 4, 0, 123, 23};
        // quickSort(arr, 0, arr.length - 1);
        // for(int x: arr){
        //     System.out.println(x);
        // }
        // List<Integer> list = sort(arr);
        // list.forEach(x->{
        //     System.out.println(x);
        // });
        // heapSort(arr);
        arr = mergeSort2(arr, 0, arr.length);
        for(int i = 0; i < arr.length; ++i){
            System.out.println(arr[i]);
        }
     }


    /**
     * 归并排序
     * 先局部，再整体
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr){
        if(arr.length < 2){
            return arr;
        }
        int mid = arr.length / 2;
        // [from, to)
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    // 排序arr的[begin, end)部分，返回[begin, end)部分
    public static int[] mergeSort2(int[] arr, int begin, int end){
        if(end - begin < 2){
            return new int[]{arr[begin]}; 
        }
        int mid = begin + (end - begin) / 2;
        int[] leftArr = mergeSort2(arr, begin, mid);
        int[] rightArr = mergeSort2(arr, mid, end);
        return merge(leftArr, rightArr);
    }

    // 将两个有序数组合并
    public static int[] merge(int[] left, int[] right){
        int leftIndex = 0, rightIndex = 0;
        int[] result = new int[left.length + right.length];
        for(int i = 0; i < result.length; ++i){
            if(leftIndex >= left.length){
                result[i] = right[rightIndex++];
            }else if(rightIndex >= right.length){
                result[i] = left[leftIndex++];
            }else if(left[leftIndex] <= right[rightIndex]){
                result[i] = left[leftIndex++];
            }else{
                result[i] = right[rightIndex++];
            }
        }
        return result;
    }

    /**
     * 快速排序
     * 先整体，再局部
     */
    // 对arr的[left, right]进行排序，原地排序
    public static int[] quickSort(int[] arr, int left, int right){
        if(left < right){
            int partition = partition(arr, left, right);
            quickSort(arr, left, partition - 1);
            quickSort(arr, partition + 1, right);
        }
        return arr;
    }

    public static int partition(int[] arr, int begin, int end){
        // 固定第一个值为最终的pivot
        int pivot = arr[begin];
        // pivotIndex指示的是当前小于等于pivot的最后一个位置的*下一个*位置
        // 若后续出现小于等于pivot的值，则将其交换至这个位置，pivotIndex++
        int pivotIndex = begin + 1;
        for(int i = pivotIndex; i <= end; ++i){
            if(arr[i] <= pivot){
                swap(arr, i, pivotIndex);
                // 比pivot小的元素增加了一个，pivot最终位置后移一位
                pivotIndex++;
            }
        }
        swap(arr, pivotIndex - 1, begin);
        return pivotIndex - 1;
    }

    // 交换arr中i和j位置的值
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 桶排序，不固定桶的个数
     */

    public static List<Integer> sort(int[] sourceArray){
        // 对 arr 进行拷贝，不改变参数内容
        List<Integer> arr = Arrays.stream(sourceArray).boxed().collect(Collectors.toList());

        return bucketSort(arr, 5);
    }
    // 递归定义：给定一个arr，限定每个桶中最多bucketSize个不同元素，返回排序好的arr
    // 不固定桶的个数，bucketSize：每个桶中不同元素的个数，相同元素个数不限
    private static List<Integer> bucketSort(List<Integer> arr, int bucketSize){
        if (arr == null || arr.size() == 0 || bucketSize < 1) {
            return arr;
        }
        // 求得最大，最小值
        int minValue = arr.get(0);
        int maxValue = arr.get(0);
        for (int value : arr) {
            if (value < minValue) {
                minValue = value;
            } else if (value > maxValue) {
                maxValue = value;
            }
        }
        // 得到桶的个数
        int bucketCount = (int) Math.floor((maxValue - minValue) / bucketSize) + 1;
        List<List<Integer>> bucketList = new ArrayList<>();

        for(int i = 0; i < bucketCount; ++i){
            bucketList.add(new ArrayList<>());
        }

        // 利用映射函数将数据分配到各个桶中
        for (int i = 0; i < arr.size(); i++) {
            int index = (int) Math.floor((arr.get(i) - minValue) / bucketSize);
            bucketList.get(index).add(arr.get(i));
        }
        
        List<Integer> result = new ArrayList<>();
        for (List<Integer> bucket : bucketList) {
            if (bucket.size() <= 0) {
                continue;
            }
            // 对每个桶进行排序，使用递归排序
            if(bucketCount == 1){
                // 使用这种桶大小，只会分到一个桶中，变为更小的桶
                bucketSize--;
            }
            List<Integer> temp = bucketSort(bucket, bucketSize);
            for (int value : temp) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * 计数排序
     */

    public static void countingSort(int[] arr){
        int minValue = arr[0];
        int maxValue = arr[0];
        // 找到最大最小值
        for (int value : arr) {
            if (value < minValue) {
                minValue = value;
            } else if (value > maxValue) {
                maxValue = value;
            }
        }
        int size = maxValue - minValue + 1;
        int[] countArray = new int[size];
        // i + minValue为原值！countArray[i]记录了原值出现的次数
        for(int i = 0; i < arr.length; ++i){
            countArray[arr[i] - minValue] += 1;
        }
        int sortedIndex = 0;
        for(int i = 0; i < size; ++i){
            while(countArray[i] > 0){
                arr[sortedIndex++] = i + minValue;
                countArray[i]--;
            }
        }
    }

    /**
     * 堆排序
     *[logn]
     *  ∑    O(h) * n / 2^{h + 1} = O(n)
     * h=0
     */
    
    public static void heapSort(int[] arr){
        // 建堆，对所有非叶子节点，进行下沉操作
        // 这里是最大堆
        for(int i = arr.length / 2 - 1; i >= 0; --i){
            heapify(arr, i, arr.length);
        }

        // 排序，每次将最大的换到底部，对开头进行下沉操作
        int len = arr.length;
        for(int i = len - 1; i > 0; --i){
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
    }
    // 以i为根的下沉操作，将小值沉底
    public static void heapify(int[] arr, int i, int len){
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
    // 树上的的heapify下沉操作
    public static void heapifyTree(TreeNode node){
        // if(node.left == null && node.right == )
        TreeNode largest = node;
        if(node.left != null && node.left.val > largest.val){
            largest = node.left;
        }
        if(node.right != null && node.right.val > largest.val){
            largest = node.right;
        }
        if(largest != node){
            int temp = largest.val;
            largest.val = node.val;
            node.val = temp;
            heapifyTree(largest);
        }
    }

    /**
     * 希尔排序
     * @param arr
     */
    public static void shellSort(int[] arr){
        // gap为几说明分为了几组！
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
           // 从第gap个元素，逐个对其所在组进行直接插入排序操作
           // 不是一个组一个组的排序，而是挨个排序，但是排序的时候只比较自己组的
           for(int i = gap; i < arr.length; i++){
               int j = i;
               while(j - gap >= 0 && arr[j] < arr[j - gap]){
                   //插入排序采用交换法
                   swap(arr, j, j - gap);
                   j -= gap;
               }
           }
       }
    }
}
