package org.example.busdemo;

public class Application {
    public static void main(String[] args) throws InterruptedException {

        StationHub stationHub = new StationHub();
        stationHub.add("01", 0, 5);
        stationHub.add("02", 4, 6);
        stationHub.add("03", 7, 7);
        stationHub.add("04", 5, 8);
        stationHub.add("05", 6, 4);
        stationHub.add("06", 3, 3);
        stationHub.add("07", 4, 6);
        stationHub.add("08", 5, 5);
        stationHub.add("09", 3, 6);
        stationHub.add("10", 7, 7);
        stationHub.add("11", 4, 4);
        stationHub.add("12", 5, 3);
        stationHub.add("13", 4, 6);
        stationHub.add("14", 5, 3);
        stationHub.add("15", 4, 0);
        stationHub.start();

        BusHub busHub = new BusHub();

        busHub.add("b01", stationHub.head, 29);
        busHub.add("b02", stationHub.head, 29);
        busHub.add("b03", stationHub.head, 29);
        busHub.add("b04", stationHub.head, 29);
        busHub.add("b05", stationHub.head, 29);

        busHub.add("b06", stationHub.tail, 29);
        busHub.add("b07", stationHub.tail, 29);
        busHub.add("b08", stationHub.tail, 29);
        busHub.add("b09", stationHub.tail, 29);
        busHub.add("b10", stationHub.tail, 29);

        busHub.start();

        Thread.sleep(1000000000L);
    }
}
