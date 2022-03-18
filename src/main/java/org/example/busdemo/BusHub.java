package org.example.busdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusHub {
    private List<Bus> buses = new ArrayList<>();

    public void add(String busName, Station station, int maxPassengers) {
        buses.add(new Bus(busName, station, maxPassengers));
    }

    public void start() {
        for (Bus bus : buses) {
            bus.start();
        }
    }
}
