package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DataStreamMedian {

    PriorityQueue<Integer> maxHeap, minHeap;
    int numOfElements = 0;

    public int[] medianII(int[] nums){
        int length = nums.length;
        minHeap = new PriorityQueue<>(length);
        maxHeap = new PriorityQueue<>(length, (x, y)->{return y - x;});
        // maxHeap = new PriorityQueue<>(length, Comparator.comparing(Integer::intValue).reversed());
    
        int[] ans = new int[length];
        for(int i = 0; i < length; ++i){
            addNumber(nums[i]);
            ans[i] = getMedian();
        }
        return ans;
    }

    public void addNumber(int num){
        maxHeap.add(num);
        if(numOfElements % 2 == 0){
            if(minHeap.isEmpty()){
            }else if(maxHeap.peek() > minHeap.peek()){
                int max = maxHeap.poll();
                int min = minHeap.poll();
                maxHeap.add(min);
                minHeap.add(max);
            }
        }else{
            minHeap.add(maxHeap.poll());
        }
        numOfElements++;
        return;
    }

    public int getMedian(){
        return maxHeap.peek();
    }
}
