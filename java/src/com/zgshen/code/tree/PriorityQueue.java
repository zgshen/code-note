package com.zgshen.code.tree;

import java.util.Arrays;

/**
 * 优先队列
 */
public class PriorityQueue {

    private int[] array;
    private int size;

    public PriorityQueue() {
        array = new int[32];
    }

    //入队
    public void enQueue(int key) {
        if (size > array.length) {
            //扩容
            resize();
        }
        array[size++] = key;
        //上浮操作
        upAdjust();
    }

    //出队
    public int deQueue() throws Exception{
        if (size <= 0) throw new Exception("empty queue...");
        //堆顶元素
        int head = array[0];
        //最后一个元素替代
        array[0] = array[--size];
        //下沉操作
        downAdjust();
        return head;
    }

    /**
     * 上浮
     */
    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = (childIndex - 1) / 2;
        int temp = array[childIndex];
        while (childIndex > 0 && temp > array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉
     */
    private void downAdjust() {
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;
        while (childIndex < size) {
            if (childIndex + 1 < size && array[childIndex+1] > array[childIndex]) {
                childIndex++;
            }
            if (temp > array[childIndex]) break;
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;
        }
        array[parentIndex] = temp;
    }

    private void resize() {
        int newSize = size * 2;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(8);

        System.out.println(Arrays.toString(priorityQueue.array));

        System.out.println("出队" + priorityQueue.deQueue());
        System.out.println("出队" + priorityQueue.deQueue());
        System.out.println(Arrays.toString(priorityQueue.array));

        priorityQueue.enQueue(1);
        priorityQueue.enQueue(12);
        System.out.println(Arrays.toString(priorityQueue.array));
    }
}
