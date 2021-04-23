package com.my.pratice ;

import java.io.PrintStream;

public abstract class Animal {



    public static void staticMethod(){
        System.out.printf("This is common method \n") ;
    }
    public abstract void cryingSound() ;


}

class Cat extends Animal{

    @Override
    public  void cryingSound() {

        System.out.printf("야옹야옹 \n") ;
    }

}

class Dog extends Animal{
    @Override
    public void cryingSound() {
        System.out.printf("멍멍멍멍 \n") ;
    }
}

class Horse extends Animal{
    @Override
    public void cryingSound() {
        System.out.printf("히이잉잉 \n") ;
    }
}

class Tiger {

    private Tiger(){

    }
    public void cryingSound() {
        System.out.printf("으허엉엉 \n") ;
    }
}
