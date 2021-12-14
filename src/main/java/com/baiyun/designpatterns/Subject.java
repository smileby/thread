package com.baiyun.designpatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyun
 * @date 2021-12-14-21:06
 */
public class Subject {

    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        if(state == this.state){
            return;
        }
        this.state = state;
        notifyAllObserver();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObserver(){
        observers.stream().forEach(Observer::update);
    }

}
