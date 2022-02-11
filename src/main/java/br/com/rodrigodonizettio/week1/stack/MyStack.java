package br.com.rodrigodonizettio.week1.stack;

import java.util.Stack;

public class MyStack extends Stack<Integer> {
    private int maxSize;

    public MyStack(Integer maxSize) {
        if(maxSize <= 0) {
            throw new IllegalArgumentException("MaxSize cannot be <= 0");
        }
        this.maxSize = maxSize;
    }


    public synchronized boolean isEmpty() {
        return super.isEmpty();
    }

    public synchronized int size() {
        return super.size();
    }

    public Integer push(Integer object) {
        if(this.size() >= maxSize) {
            throw new ArrayIndexOutOfBoundsException("Stack is full. MaxSize is " + maxSize);
        }
        super.push(object);
        return object;
    }

    public synchronized Integer pop() {
        return super.pop();
    }

    public void popAll() {
        super.removeAllElements();
    }

    public Integer top() {
        return super.peek();
    }
}
