package org.example.busdemo;

/**
 * 站点
 */
public class Station {
    private final String name;

    public Station prev;
    public int prevTime;
    public Station next;
    public int nextTime;

    // 乘客 默认0
    private int passenger = 0;


    public Station(String name, Station prev, Station next, int prevTime, int nextTime) {
        this.name = name;
        this.prev = prev;
        this.next = next;
        this.prevTime = prevTime;
        this.nextTime = nextTime;
    }

    public String getName() {
        return name;
    }

    public int getPassenger() {
        return passenger;
    }

    public void incrPassenger(int num) {
        synchronized (this) {
            this.passenger = this.passenger + num;
        }
    }

    public int decrPassenger(int maxNum) {
        synchronized (this) {
            int factNum = 0;
            if (maxNum > this.passenger) {
                factNum =  this.passenger;
            } else {
                factNum =  maxNum;
            }
            this.passenger = this.passenger - factNum;
            return factNum;
        }
    }

}
