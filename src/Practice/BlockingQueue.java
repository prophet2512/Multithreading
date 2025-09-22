package Practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

class BlockingBuffer {
    private int capacity = Integer.MAX_VALUE;
    private final Queue<Integer> queue = new LinkedList<>();

    public BlockingBuffer(){}

    public BlockingBuffer(int size) {
        this.capacity = size;
    }

    public void produce(int item) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == capacity)
                wait();
            queue.add(item);
            //System.out.println("Produced: "+item+ " and size = "+queue.size());
            notify();
        }
    }

    public int consume() throws InterruptedException {
        synchronized (queue) {
                while (queue.isEmpty()) {
                    wait();
                }

            int item = queue.poll();
            System.out.println("Consumed: " + item + " and size = " + queue.size());
            notify();
            return item;
        }
    }
}

class ProducerBlockingQueue implements Runnable {
    private java.util.concurrent.BlockingQueue<Integer> queue;

    public ProducerBlockingQueue(java.util.concurrent.BlockingQueue queue){ this.queue = queue; }

    @Override
    public void run() {
        try {
            for(int i = 0; i<100; i++) {
                Thread.sleep(1000);
                queue.put(i);
                System.out.println("Produced: "+i);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class ConsumerBlockingQueue implements Runnable{
    private java.util.concurrent.BlockingQueue<Integer> queue;

    public ConsumerBlockingQueue(java.util.concurrent.BlockingQueue<Integer> queue) { this.queue = queue; }

    @Override
    public void run() {
        try {
            while(true) {
                Integer item = queue.take();
                System.out.println("Consumed Item: "+ item);
                Thread.sleep(2000);
                //return item;
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class BlockingQueue {
    public static void main(String[] args) throws InterruptedException {
//        BlockingBuffer blockingBuffer = new BlockingBuffer();
//        Thread producer = new Thread(() -> {
//            int value = 0;
//            try {
//                while(value<40) {
//                    blockingBuffer.produce(value++);
//                    Thread.sleep(500);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread consumer = new Thread(() -> {
//            try {
//                while(true){
//                    blockingBuffer.consume();
//                    Thread.sleep(800);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();;
//            }
//        });
//
//        producer.start();
//        consumer.start();
//
//        producer.join();
//        consumer.join();
//
//
        java.util.concurrent.BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(new ProducerBlockingQueue(queue));
        Thread consumer = new Thread(new ConsumerBlockingQueue(queue));

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

    }
}
