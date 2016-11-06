/**
 * Created by dmitry.arefyev on 06.11.2016.
 */

public class Count {
    static class Counter implements Runnable{
        private int c = 0;

        public void increment() {
            c++;
        }

        public void decrement() {
            c--;
        }

        public int value() {
            return c;
        }

        @Override
        public void run() {
            increment();
            System.out.format("%s: %s%n", Thread.currentThread().getName(), value());
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");
        Counter counter = new Counter();
        System.out.format("%s: %s%n", Thread.currentThread().getName(), counter.value());

        /*Thread t1 = new Thread(counter);
        t1.start();

        Thread t2 = new Thread(counter);
        t2.start();*/
        
        for (int i=0; i<51; i++) {
            Thread t = new Thread(counter);
            t.start();
        }

        System.out.println("Finish");
    }

}
