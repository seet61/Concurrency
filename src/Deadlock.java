/**
 * Created by dmitry.arefyev on 06.11.2016.
 */
public class Deadlock {
    static class Friend {
        private final String name;

        //Конструктор
        public Friend(String name) {
            this.name = name;
        }

        //Вернуть текущее имя
        public String getName() {
            return this.name;
        }

        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gastone");
        new Thread(new Runnable() {
            @Override
            public void run() {
                alphonse.bow(gaston);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                gaston.bow(alphonse);
            }
        }).start();
    }
}
