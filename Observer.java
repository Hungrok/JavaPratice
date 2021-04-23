package com.my.pratice ;

import java.util.LinkedList;
import java.util.List;

abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

class Subject {
    List<Observer> observers;
    private String state;
    public Subject(String state) {
        observers = new LinkedList();
        setState(state);
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
        System.out.printf("Current State:%s \n",state);
        notifyObservers();
    }
    public void registerObserver(Observer...observers){
        for(Observer o: observers)
            this.observers.add(o);
    }
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    public void notifyObservers(){
        for(Observer o: observers)
            o.update();
    }
}

class LengthObserver extends Observer {
    public LengthObserver(Subject subject){
        this.subject = subject;
        this.subject.registerObserver(this);
    }
    public void update() {
        System.out.printf("Length:%d \n",subject.getState().length());
    }
}

class CapsObserver extends Observer {

    public CapsObserver(Subject subject){
        this.subject = subject;
        this.subject.registerObserver(this);
    }
    public void update() {
        System.out.printf("Upper Case Chars:%d \n",countUpperCase());
        System.out.printf("Lower Case Chars:%d \n",countLowerCase());
    }
    private int countLowerCase(){
        String str = subject.getState();
        int count = 0;
        for(int i = 0; i < str.length(); i++){
            if(Character.isLowerCase(str.charAt(i)))
                count++;
        }
        return count;

    }
    private int countUpperCase(){
        String str = subject.getState();
        int count = 0;
        for(int i = 0; i < str.length(); i++){
            if(Character.isUpperCase(str.charAt(i)))
                count++;
        }
        return count;
    }
}

class WordsObserver extends Observer {
    public WordsObserver(Subject subject){
        this.subject = subject;
        this.subject.registerObserver(this);
    }
    public void update() {
        System.out.printf("Words:%d \n",countWords());
    }
    private int countWords(){
        String str = subject.getState();
        int count = 1;

        if(str.isEmpty())
            return 0;
        for (int i = 0 ;i <= str.length()-1 ; i++) {
            if (str.charAt(i) == ' ' && str.charAt(i+1) != ' ')
            count++;
        }
        return count;
    }
}
