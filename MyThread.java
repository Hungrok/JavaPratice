package com.my.pratice ;


public class MyThread {

    

    static void startTest(){ // for thread start 방법 4가지
        ThreadStartWays.startThread1();
        ThreadStartWays.startThread2();
        ThreadStartWays.startThread3();
        ThreadStartWays.startThread4();
    }
    
    static void androidActivity(){ // for MainActivity test
    	
    	// 안드로이드 Main Activity 를 비슷하게 구현하여 
        new MainThread("com.my.pratice.AndroidMainActivity").start() ;
    }

    static void syncTest(){ // Thread sync test
        Object sync = new Object() ;

        //ThreadSync1 th1 = new ThreadSync1(3,20,null) ; // async
        ThreadSync1 th1 = new ThreadSync1(3,20,sync) ; // sync
        th1.start() ;

        //ThreadSync2 th2 = new ThreadSync2(3,20,null) ; // async
        ThreadSync2 th2 = new ThreadSync2(3,20,sync) ; // sync
        th2.start() ;
    }
    
    static void msgqTest() { // Thread Message Queue test

        MsgQueue msgq = new MsgQueue() ;
        new ThreadMsgQ1(msgq).start() ;
        new ThreadMsgQ2(msgq).start() ;


        AndroidIntent intent1 = new AndroidIntent("ACTION_DIAL") ;
        AndroidIntent intent2 = new AndroidIntent("ACTION_SEND") ;
        AndroidIntent intent3 = new AndroidIntent("ACTION_VIEW") ;
        msgq.sendMessage(intent1);
        msgq.sendMessage(intent2);
        msgq.sendMessage(intent3);

        MsgQueue2<AndroidIntent> msgq2 = new MsgQueue2<>() ;


    }
}
