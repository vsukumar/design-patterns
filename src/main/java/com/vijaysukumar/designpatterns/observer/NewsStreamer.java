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
    List<Observer> observers = new ArrayList<>();

    void addObserver(Observer observer){
        observers.add(observer);
    }

    void setData(String data){
        this.data = data;
        notifyAllObservers();
    }

    void notifyAllObservers(){
        for(Observer observer : observers){
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
