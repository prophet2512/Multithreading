package locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;

    /**
     *
     */
    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw "+ amount);
//        if(balance>=amount) {
//            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal.");
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            balance-=amount;
//            System.out.printf("%s completed withdrawal process. Balance left is:%d \n",
//                    Thread.currentThread().getName(), balance);
//        } else {
//            System.out.printf("%s faced insufficient balance", Thread.currentThread().getName());
//        }
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if(balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal.");

                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.printf("%s completed withdrawal process. Balance left is:%d \n",
                                Thread.currentThread().getName(), balance);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.printf("%s faced insufficient balance", Thread.currentThread().getName());
                }
            } else {
                System.out.printf("%s could not acquire the lock, will try later.\n", Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public int getBalance() {
        return balance;
    }
}
