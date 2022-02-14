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

    @Override
    public Integer push(Integer object) {
        if(this.size() >= maxSize) {
            throw new ArrayIndexOutOfBoundsException("Stack is full. MaxSize is " + maxSize);
        }
        super.push(object);
        return object;
    }

    public void popAll() {
        super.removeAllElements();
    }

    public Integer top() {
        return super.peek();
    }

    @Override
    public synchronized boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public synchronized int hashCode() {
        return super.hashCode();
    }
}
