package org.example.busdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bus {
    private final String name;
    private Station currStation;
    private boolean isToNext; // 方向
    private int passengers;
    // 最多承载乘客数量
    private final int maxPassengers;

    private final List<OperationRecord> recordList = new ArrayList<>();
    private final Thread worker;

    public Bus(String name, Station station, int maxPassengers) {
        this.name = name;
        this.currStation = station;
        this.maxPassengers = maxPassengers;
        this.isToNext = station.next != null;
        this.worker = new Thread(this::work);
    }

    public void start() {
        this.worker.start();
    }

    private void work() {
        while (true) {
            int downNum = 0;
            if (isFinalDestination()) {
                downNum = this.passengers;
                this.passengers = 0; // 全部下车
            }
            // 上客
            int currPassenger = currStation.getPassenger();
            int fact = currStation.decrPassenger(availablePassengers());
            passengers = passengers + fact;
            OperationRecord record = new OperationRecord(currStation, currPassenger, fact, downNum);
            recordList.add(record);
            System.out.println(record);
            // 前往下一站
            Station nextStation = nextStation();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currStation = nextStation;
        }
    }

    // 是否是终点站
    private boolean isFinalDestination() {
        return currStation.next == null || currStation.prev == null;
    }

    private Station nextStation() {
        if (isFinalDestination()) { // 是否是终点站
            isToNext = currStation.next != null;
        }
        if (isToNext) { // 下行
            return currStation.next;
        } else { // 上行
            return currStation.prev;
        }
    }

    private int availablePassengers() {
        return maxPassengers - passengers;
    }

    class OperationRecord {
        Station station;
        int stationPassenger; // 站点人数
        int upNum; // 上车乘客
        int downNum; // 下车乘客

        public OperationRecord(Station station, int stationPassenger, int upNum, int downNum) {
            this.station = station;
            this.stationPassenger = stationPassenger;
            this.upNum = upNum;
            this.downNum = downNum;
        }

        @Override
        public String toString() {
            return String.format("Bus %s, 当前站点%s人数%d, 上客 %d， 下客 %d 总乘客 %d", name, station.getName(), stationPassenger, upNum, downNum, passengers);
        }
    }

}
