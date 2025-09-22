package Practice;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumTask extends RecursiveTask<Long> {
    private final long[] arr;
    private final int start, end;
    private static final int THRESHOLD = 3;

    SumTask(long[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if(end-start<=THRESHOLD) {
            long sum = 0;
            for(int i = start; i<end; i++)
                sum+=arr[i];
            return sum;
        } else {
            int mid = (start+end)/2;
            SumTask left = new SumTask(arr, start, mid);
            SumTask right = new SumTask(arr, mid, end);

            left.fork();
            right.fork();
            return left.join()+right.join();
        }
    }
}
public class ForkJoinRev {
    public static void main(String[] args) {
        long[] arr = createRandomArr(100000000);
        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new SumTask(arr, 0, arr.length));
        System.out.println(result);
    }

    private static long[] createRandomArr(int size) {
        long[] arr = new long[size];
        Random random = new Random();
        for(int i = 0; i<size; i++) {
            arr[i] = random.nextLong(100000000);
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        return arr;
    }
}
