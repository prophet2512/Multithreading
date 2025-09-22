package Practice;

class ThreadLocalObject implements Runnable{
    ThreadLocal<Long> userIdThreadLocal = new InheritableThreadLocal<>();
    private final Long userId;

    ThreadLocalObject(Long userId) {
        this.userId = userId;
        this.userIdThreadLocal.set(userId);
    }

    @Override
    public void run() {
        System.out.println("Started thread for userId: "+ userId);
        //userIdThreadLocal.set(userId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread execution completed with userId: "+this.userIdThreadLocal.get());
        this.userIdThreadLocal.remove();
        System.out.println("Removed userId! "+userId);
    }
}

public class ThreadLocalLessor {
    public static void main(String[] args) throws InterruptedException {
        //ThreadLocalObject obj = new ThreadLocalObject();
        Long userId1 = 12345L;
        Long userId2 = 54321L;


        Thread thread1 = new Thread(new ThreadLocalObject(userId1));
        Thread thread2 = new Thread(new ThreadLocalObject(userId2));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
