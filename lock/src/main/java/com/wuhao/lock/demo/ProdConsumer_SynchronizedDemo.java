package com.wuhao.lock.demo;/**
 *
 */

/**
 *@ClassName ProdConsumer_SynchronizedDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2024/6/17 17:45
 *@Version 1.0
 **/
public class ProdConsumer_SynchronizedDemo {

    public static void main(String[] args) {
        final int BUFFER_SIZE = 10;
        CustomBuffer buffer = new CustomBuffer(BUFFER_SIZE);

        Thread producer = new Thread(new Producer(buffer));
        Thread consumer = new Thread(new Consumer(buffer));

        producer.start();
        consumer.start();
    }

    static class CustomBuffer {
        private int[] buffer;
        private int count = 0;
        private int in = 0;
        private int out = 0;

        public CustomBuffer(int size) {
            buffer = new int[size];
        }

        public synchronized void put(int item) throws InterruptedException {
            while (count == buffer.length) {
                wait(); // 生产者阻塞，等待有空位
            }
            buffer[in] = item;
            in = (in + 1) % buffer.length;
            count++;
            notifyAll(); // 通知等待的线程（可能是消费者）
        }

        public synchronized int get() throws InterruptedException {
            while (count == 0) {
                wait(); // 消费者阻塞，等待有产品
            }
            int item = buffer[out];
            out = (out + 1) % buffer.length;
            count--;
            notifyAll(); // 通知等待的线程（可能是生产者）
            return item;
        }
    }`

    static class Producer implements Runnable {
        private final CustomBuffer buffer;

        public Producer(CustomBuffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 50; i++) {
                try {
                    buffer.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(50); // 模拟生产过程
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Producer interrupted.");
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private final CustomBuffer buffer;

        public Consumer(CustomBuffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int item = buffer.get();
                    System.out.println("Consumed: " + item);
                    Thread.sleep(100); // 模拟消费过程
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Consumer interrupted.");
                }
            }
        }
    }
}
