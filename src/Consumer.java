import java.util.Random;

/**
 * Читает сообщения из обменника, пока не получит сообщение DONE.
 * Created by dmitry.arefyev on 07.11.2016.
 */
public class Consumer implements Runnable {
    private Drop drop;//Инициируем обменник

    //Конструктор
    public Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (String message = drop.take();
                ! message.equals("DONE");
                message = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            try {
                int interval = random.nextInt(5000);
                System.out.println(Thread.currentThread().getName() + ":Consumer: interval " + interval);
                Thread.sleep(interval);
            } catch (InterruptedException e) {}
        }
    }
}
