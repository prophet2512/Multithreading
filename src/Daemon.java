public class Daemon implements Runnable{
    @Override
    public void run() {
        while(true) {
            System.out.println("Hello world!");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Daemon(), "t1");
        t1.setDaemon(true);
        t1.start();
    }
}

/**
 * DAEMON Threads are the threads which run in the background
 * Only the user threads will run as long as they are running even if the main thread stops its execution
 */
