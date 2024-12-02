package Volatile;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private final Queue<Integer> q;

    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        q = new LinkedList<>();
    }

    public boolean add(int item) {
        synchronized (q) {
            while(q.size()==capacity) {
                //do something
                try {
                    q.wait(); //adder1, adder2 (in case there are 2 threads which are in wait state)

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            q.add(item);
            q.notifyAll();
            return true;
        }
    }

    public int remove() {
        synchronized (q) {
            while(q.isEmpty()){
                //do something
                try {
                    q.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int element = q.poll();
            q.notifyAll();
            return element;
        }
    }
}
