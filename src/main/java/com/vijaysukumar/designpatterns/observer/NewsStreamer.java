package com.vijaysukumar.designpatterns.observer;

import java.util.*;

public class NewsStreamer {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Enter data to be processed. Ctrl-C to stop ");

        for (String string : strings) {
            System.out.println(string);
        }

        News news = new News();
        FeedObserver feedObserver = new FeedObserver(news);
        news.addObserver(feedObserver);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        news.setData(input);

    }
}

class News{
    String data;
    Set<Observer> observers = new HashSet<>();

    //object used for lock
    Object MONITOR = new Object();

    void addObserver(Observer observer){
        synchronized (MONITOR) {
            observers.add(observer);
        }
    }

    void setData(String data){
        this.data = data;
        notifyAllObservers();
    }

    void notifyAllObservers(){

        Set<Observer>  copyObservers = new HashSet<>();
        synchronized (MONITOR) {
            copyObservers = new HashSet<>(observers);
        }
        for (Observer observer : copyObservers) {
            observer.update();
        }
    }
}

abstract class Observer{
    News state;
    abstract void update();
}

class FeedObserver extends Observer{

    FeedObserver(News state) {
        this.state = state;
    }
    void update(){
        System.out.println("processing "  + state.data);
    }
}
