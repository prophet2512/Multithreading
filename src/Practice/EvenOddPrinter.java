package Practice;

public class EvenOddPrinter {
    private int number = 1;
    private final int MAX = 20;

    public void printOdd() {
        synchronized (this) {
            while(number<=MAX) {
                if(number % 2 == 0){
                    try{
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("Exception occurred!");
                    }
                } else {
                    System.out.println("Odd: "+ number);
                    number++;
                    notifyAll();
                }
            }
        }
    }

    public void printEven() {
        synchronized (this) {
            while(number<=MAX) {
                if(number%2!=0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted exceptions!");
                    }
                } else {
                    System.out.println("Even: "+ number);
                    number++;
                    notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        EvenOddPrinter printer = new EvenOddPrinter();
        Thread oddPrinter = new Thread(printer::printOdd);
        Thread evenPrinter = new Thread(printer::printEven);

        oddPrinter.start();
        evenPrinter.start();
    }
}