package locks;
class Pen {
    public synchronized void writeWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying to use the paper " + paper);
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
    }
}

class Paper {
    public synchronized void writeWithPenAndPaper(Pen pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper " + this + " and trying to use the pen " + pen);
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using paper " + this);
    }
}

class Task1 implements Runnable{
    private Pen pen;
    private Paper paper;

    public Task1(Pen pen, Paper paper) {
        this.paper = paper;
        this.pen = pen;
    }
    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper);
    }
}

class Task2 implements Runnable{
    private Pen pen;
    private Paper paper;

    public Task2(Pen pen, Paper paper) {
        this.paper = paper;
        this.pen = pen;
    }
    @Override
    public void run() {
        /**
         * This is how we are going to remove the deadlock here. Since
         * this method was trying to acquire the lock of pen, we made
         * access to pen synchronized here.
         */
        /*synchronized (pen) {
            paper.writeWithPenAndPaper(pen);
        }*/
        paper.writeWithPenAndPaper(pen);
    }
}
public class DeadlockExample {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper  paper = new Paper();

        Thread t1 = new Thread(new Task1(pen, paper), "Thread1");
        Thread t2 = new Thread(new Task2(pen, paper), "Thread2");

        t1.start();
        t2.start();


    }
}
