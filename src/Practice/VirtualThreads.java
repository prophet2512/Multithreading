package Practice;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreads {
    public static void main(String[] args) throws Exception{
        final int numberOfThreads = 10_0000;
        List<Thread> listThreads = new ArrayList<>();

        Runnable objRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Fetching data from the API!");
                try{
                    Thread.sleep(20000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("Data fetched from the API!");
            }
        };

        for (int i = 0; i<numberOfThreads; i++) {
            Thread thread = Thread.ofVirtual().unstarted(objRunnable);
            //thread.setDaemon(true);
            thread.setName("Thread:"+i);
            thread.start();
            String str = String.format("Thread number %d", i);
            System.out.println(str);
            listThreads.add(thread);
        }

        for(Thread th: listThreads) {
            th.join();
            System.out.println(th.getName()+ " completed execution!");
        }

    }
}
