package lambda;

public class LambdaExpression {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Hello world");

        Thread t1 = new Thread(runnable);
        t1.start();
    }
}
