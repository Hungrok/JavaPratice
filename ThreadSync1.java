package com.my.pratice ;


public class ThreadSync1 extends Thread{ // for sync test

    public static Object sync ;
    private int loopcnt=0 , printcnt = 0 ;

    public ThreadSync1(int loopcnt, int printcnt, Object sync) {
        this.loopcnt = loopcnt ;
        this.printcnt = printcnt ;
        this.sync = sync ;
    }

    public void run(){
        System.out.printf("ThreadSync1 now start.. \n") ;
        int count = 0 ;
        int i = 0 ;
        String s1 = "Thread-1 11111111111111111111111111111111111111111111111111111111111111111111111111111" ;
        Object mysync = null ;
        if(sync==null)
            mysync = new Object() ; //async as used only by me
        else
            mysync = sync ; // sync with other thread as used same object

        while(count <loopcnt) {
            synchronized(mysync) {
                try {
                    System.out.printf("Thread1 Tick=%d \n",System.currentTimeMillis()) ;
                    for(i=0 ; i<printcnt ; i++)
                        System.out.printf("cnt=%d.%d %s \n",count,i,s1) ;
                    count++ ;
                    sleep(0) ;
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // synchronized

        }

    }

}


class ThreadSync2 extends Thread{ // for sync test

    public static Object sync=null ;
    private int loopcnt=0 , printcnt = 0 ;

    public ThreadSync2(int loopcnt, int printcnt, Object sync) {
        this.loopcnt = loopcnt ;
        this.printcnt = printcnt ;
        this.sync = sync ;
    }

    public void run(){
        System.out.printf("ThreadSync2 now start.. \n") ;
        int count = 0 ;
        int i = 0 ;
        String s1 = "Thread-2 22222222222222222222222222222222222222222222222222222222222222222222222222222" ;
        Object mysync = null ;
        if(sync==null)
            mysync = new Object() ; //async as used only by me
        else
            mysync = sync ; // sync with other thread as used same object


        while(count <loopcnt) {
            synchronized(mysync) {
                try {
                    System.out.printf("Thread 2 Tick=%d \n",System.currentTimeMillis()) ;
                    for(i=0 ; i<printcnt ; i++)
                        System.out.printf("cnt=%d.%d %s \n",count,i,s1) ;
                    count++ ;
                    sleep(0) ;
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // synchronized

        }

    }

}
