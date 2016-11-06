/**
 * Created by dmitry.arefyev on 06.11.2016.
 */
public class SimpleThreads {
    /*
    Печатаем сообщение с указанием потока.
     */
    static void threadMessage (String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    /*
    Чикл который запускается в потоке
    */
    private static class MessageLoop implements Runnable {
        @Override
        public void run() {
            String importantInfo[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };
            try {
                for (int i=0; i<importantInfo.length; i++) {
                    //Поток засыпает на 4 сек.
                    Thread.sleep(4000);
                    threadMessage(importantInfo[i]);
                }
            }
            catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        //Ожидание, после которого убъем поток, по умолчанию 1час
        long patience = 1000 * 60 * 60;

        //Проверяем наличие переданного ожидания из коммандной строки
        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;
            }
            catch (NumberFormatException e) {
                System.err.println("Argument must be an Integer.");
                System.exit(1);
            }
        }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        //Цикл проверки цизни цикла
        while (t.isAlive()) {
            threadMessage("Still Waiting...");
            //Ожидаем одну секунду
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                //Немного подождем его окончания
                t.join();
            }
        }
        threadMessage("Finally!");
    }
}
