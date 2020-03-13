package com.example.soonami;

public class Event {
    private final String title;
    private final long time;
    private int tsunmaiAlert;

    public Event(String title, long time, int tsunmaiAlert) {
        this.title = title;
        this.time = time;
        this.tsunmaiAlert = tsunmaiAlert;
    }

    public String getTitle() {
        return title;
    }

    public long getTime() {
        return time;
    }

    public int getTsunmaiAlert() {
        return tsunmaiAlert;
    }
}
