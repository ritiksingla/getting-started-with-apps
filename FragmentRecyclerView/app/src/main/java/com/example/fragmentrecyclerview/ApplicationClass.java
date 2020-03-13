package com.example.fragmentrecyclerview;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {
    public static ArrayList<Person>people;
    @Override
    public void onCreate() {
        super.onCreate();
        people=new ArrayList<Person>();
        people.add(new Person("Ritik Singla","8448408104"));
        people.add(new Person("Raman Singla","9811485910"));
        people.add(new Person("Seema Singla","9953072774"));
    }
}
