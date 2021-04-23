package com.my.pratice ;

public class DesignPattern {

   

    static void observerTest(){
        Subject subject = new Subject("Hello World");
        new CapsObserver(subject);
        new LengthObserver(subject);
        new WordsObserver(subject);
        subject.notifyObservers();
        subject.setState("Testing the Observer Design Pattern");
        subject.setState("");
    }
}
