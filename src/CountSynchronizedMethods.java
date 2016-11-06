/**
 * Created by dmitry.arefyev on 06.11.2016.
 */
public class CountSynchronizedMethods {
    static class CounterSync implements Runnable {
        private int c = 0;

        public synchronized void increment() {
            c++;
        }

        public synchronized void decrement() {
            c--;
        }

        public CounterSync() {

        }

        public synchronized int value() {
            return c;
        }

        @Override
        public void run() {
            increment();
            System.out.format("%s: value %s%n", Thread.currentThread().getName(), value());
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");
        CounterSync counter = new CounterSync();
        System.out.format("%s: value %s%n", Thread.currentThread().getName(), counter.value());

        for (int i=0; i<50; i++) {
            Thread t = new Thread(counter);
            t.start();
        }

        System.out.println("Finish");
    }

}
