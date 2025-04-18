package common;

public class EventTimer {
    private final Object lock = new Object();
    private volatile boolean eventOccurred = false;

    public void waitForEventOrTimeout(long timeoutMillis) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + timeoutMillis;

        synchronized (lock) {
            while (!eventOccurred && System.currentTimeMillis() < endTime) {
                try {
                    long remainingTime = endTime - System.currentTimeMillis();
                    lock.wait(remainingTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Восстанавливаем состояние прерывания
                    return; // Завершаем метод, если поток был прерван
                }
            }

            if (eventOccurred) {
                System.out.println("Событие произошло!");
            } else {
                System.out.println("Тайм-аут истек!");
            }
        }
    }

    public void signalEvent() {
        synchronized (lock) {
            eventOccurred = true;
            lock.notifyAll(); // Уведомляем ждущие потоки о произошедшем событии
        }
    }
}
