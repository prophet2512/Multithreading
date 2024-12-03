package states;

public class StateTest2 extends Thread{
    @Override
    public void run() {
        System.out.println("RUNNING");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StateTest2 thread = new StateTest2();
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        thread.sleep(200);
        System.out.println(thread.getState());
        thread.join();
        System.out.println(thread.getState());
    }
}
