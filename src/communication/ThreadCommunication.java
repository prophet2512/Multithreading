package communication;

class SharedResource {
    private int data;
    private boolean hasData;

    public synchronized void produce(int value) {
        while(hasData) {
            try {
                wait(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Produced : "+value);
        notifyAll();
    }

    public synchronized int consume() {
        while(!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        notify();
        System.out.println("Consumed : "+data + " by " + Thread.currentThread().getName());
        return data;
    }
}

class Producer implements Runnable{

    private SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for(int i = 0; i<10; i++) {
            resource.produce(i);
        }
    }
}

class Consumer implements Runnable {
    private SharedResource resource;

    public Consumer(SharedResource sharedResource) {
        this.resource = sharedResource;
    }


    @Override
    public void run() {
        for(int i = 0; i<10; i++) {
            resource.consume();
        }
    }
}
public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread consumerThread = new Thread(new Consumer(resource), "ConsumerThread");
        Thread consumerThread2 = new Thread(new Consumer(resource), "ConsumerThread2");
        Thread producerThread = new Thread(new Producer(resource), "ProducerThread");

        consumerThread.start();
        consumerThread2.start();
        producerThread.start();
    }
}
