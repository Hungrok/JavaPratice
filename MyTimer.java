package com.my.pratice ;


import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    // Soft Timer 이다
    // Timer 객체하나가 thread 를 소유하여 하나 이상의 TimerTask 를 schedule 에 맞추어
    // TimerTask 객체가 지니는 run 을 실행한다

    static void timerTest1(){
        TimerTask timertask1 = new TimerTask(){ // abstract class --> 익명 class 화 (상속까지 되어진다)
            @Override
            public void run(){
                System.out.printf("timertask-1\n") ;

            }
        };
        TimerTask timertask2 = new TimerTask(){ // abstract class --> 익명 class 화 (상속까지 되어진다)
            @Override
            public void run(){
                System.out.printf("timertask-2\n") ;

            }
        };
        Timer timer = new Timer() ;
        timer.schedule(timertask1,0,100) ;
        timer.schedule(timertask2,0,300) ;

    }


}
