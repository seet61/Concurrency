import java.util.Random;

/**
 * Используется для отправки сообщений.
 * Сообщение типа "DONE" означает что все сообщения отправлены.
 * Created by dmitry.arefyev on 07.11.2016.
 */
public class Producer implements Runnable{
    private Drop drop; //инициируем наш обменник

    //Конструктор класса
    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        //Буфер сообщений
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
 
        Random random = new Random();

        for (int i=0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);
            try {
                int interval = random.nextInt(5000);
                System.out.println(Thread.currentThread().getName() + ":Producer: interval " + interval);
                Thread.sleep(interval);
            } catch (InterruptedException e) {}
        }
        drop.put("DONE");
    }
}
