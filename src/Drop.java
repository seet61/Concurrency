/**
 * Created by dmitry.arefyev on 07.11.2016.
 */
public class Drop {
    /**
     * Отправка сообщений от producer к consumer.
     */
    private String message;
    /**
     * empty флаг.
     * Если true - то consumer ожидает пока producer доставит сообщение.
     * Если false - то producer ожидает пока consumer доставит сообщение.
     */
    private boolean empty = true;

    /**
     * Метод извлечения сообщения из очереди
     * @return
     */
    public synchronized String take() {
        //Ожидаем пока появится сообщение
        while (empty) {
            try {
                wait();
            }
            catch (InterruptedException e) {}
        }

        //Меняем статус
        empty = true;
        //Сообщем producer что статус изменился
        notifyAll();
        return message;
    }

    /**
     * Метод доставки сообщения в очередь
     * @param message
     */
    public synchronized void put(String message) {
        //Ожидаем пока сообщение будет получено
        while (!empty) {
            try {
                wait();
            }
            catch (InterruptedException e) {}
        }
        //Меняем статус
        empty = false;
        //Сохраняем сообщение
        this.message = message;
        //Сообщаем consumer что сообщение в обменнике
        notifyAll();
    }
}
