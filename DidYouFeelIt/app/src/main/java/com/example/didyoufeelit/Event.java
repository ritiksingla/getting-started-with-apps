package com.example.didyoufeelit;

public class Event {
    public final String title;
    public final String numOfPeople;
    public final String perceivedStrength;

    public Event(String title, String numOfPeople, String perceivedStrength) {
        this.title = title;
        this.numOfPeople = numOfPeople;
        this.perceivedStrength = perceivedStrength;
    }

    public String getTitle() {
        return title;
    }

    public String getNumOfPeople() {
        return numOfPeople;
    }

    public String getPerceivedStrength() {
        return perceivedStrength;
    }
}
