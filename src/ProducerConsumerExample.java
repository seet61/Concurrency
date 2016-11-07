/**
 * Инициализация Producer и Consumer.
 * Created by dmitry.arefyev on 07.11.2016.
 */
public class ProducerConsumerExample {
    public static void main(String[] args) {
        Drop drop = new Drop(); // Обменик
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
