package VolatileAndAtomic;

class SharedResource {
    private volatile boolean flag = false;

    public void setFlag() {
        System.out.println("Writer thread made the flag true now!");
        flag = true;
    }

    public void printIfFlagIsSet() {
        while(!flag){}
        System.out.println("Flag is true!");

    }


}

public class VolatileExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread writerThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            resource.setFlag();
        });

        Thread readerThread = new Thread(() -> {
            resource.printIfFlagIsSet();
        });

        writerThread.start();
        readerThread.start();
    }
}
