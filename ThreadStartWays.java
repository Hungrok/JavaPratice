package com.my.pratice ;


class ThreadStartWays {

    public static void startThread1(){ // 방법1. extends Thread 를 적용한 class
        new Thread1().start() ;
    }
    public static void startThread2(){ // 방법2. implements Runnable 을 적용한 class
        Thread2 thread2 = new Thread2() ; // or Runnable thread2 = new Thread2()
        new Thread(thread2).start() ; // 자동 type casting 발생 (Thread2 --> Runnable)
    }
    public static void startThread3(){
        Runnable thread3 = new Runnable() { // 방법3. 익명 class (메소드 내부의 익명 class)
            @Override
            public void run() {
                while(true) {
                    System.out.printf("Thread-3 run \n");
                    break ;
                }
            }
        };
        new Thread(thread3).start() ;
    }

    public static void startThread4(){
        Runnable thread4 = ()-> { // 방법4. 람다식 익명함수 (메소드 내부의 익명 class --> 익명함수)
            while(true) {
                System.out.printf("Thread-4 run \n");
                break ;
            }
        };
        new Thread(thread4).start() ;
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        while(true){
            System.out.printf("Thread-1 run \n") ;
            break ;
        }
    }

}

class Thread2 extends Object implements Runnable {
    @Override
    public void run() {
        while(true){
            System.out.printf("Thread-2 run \n") ;
            break ;
        }
    }
}

