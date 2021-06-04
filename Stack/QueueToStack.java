package Stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用两个队列实现栈
 * 插入O(1)，top和pop都是O(n)
 * 不好！使用下面的方式二，插入次数远少于top和pop!
 */
public class QueueToStack {
    Queue<Integer> A = new LinkedList<>();
    Queue<Integer> B = new LinkedList<>();
    /** Initialize your data structure here. */
    public QueueToStack() {

    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        if(!B.isEmpty()){
            B.add(x);
        }else{
            A.add(x);
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        Queue<Integer> from;
        Queue<Integer> to;
        if(!B.isEmpty()){
            from = B;
            to = A;
        }else{
            from = A;
            to = B;
        }
        while(from.size() > 1){
            to.add(from.remove());
        }
        return from.remove();
    }
    
    /** Get the top element. */
    public int top() {
        Queue<Integer> from;
        Queue<Integer> to;
        if(!B.isEmpty()){
            from = B;
            to = A;
        }else{
            from = A;
            to = B;
        }
        while(from.size() > 1){
            to.add(from.remove());
        }
        int result = from.peek();
        to.add(from.remove());
        return result;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return A.isEmpty() && B.isEmpty();
    }
}


/**
 * 使用一个队列，或者两个队列同样方式，插入O(n)，top和pop都是O(1)
 */
class MyStack {
    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        int n = queue.size();
        queue.offer(x);
        // 翻转！
        for (int i = 0; i < n; i++) {
            queue.offer(queue.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
