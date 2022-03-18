package org.example.busdemo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StationHub {
    public Station head;
    public Station tail;
    private int size;

    private final Thread worker;

    public StationHub() {
        this.worker = new Thread(this::work);
    }

    public void add(String stationName, int prevT, int nextT) {
        Station l = tail;
        Station newNode = new Station(stationName, tail, null, prevT, nextT);
        tail = newNode;
        if (head == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    private void work() {
        // 每隔五分钟 生成10名乘客
        while (true) {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                int rS = random.nextInt(size);
                Station s = head;
                for (int j = 0; j < rS; j++) {
                    s = s.next;
                }
                s.incrPassenger(1);
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void start() {
        this.worker.start();
    }

}
