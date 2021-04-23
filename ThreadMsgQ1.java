package com.my.pratice ;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadMsgQ1 extends Thread{ // for message test - sender
   MsgQueue msgq = null ;

    public ThreadMsgQ1(MsgQueue msgq) {
        this.msgq = msgq ;
    }

    public void run() {
        while(true) {
            synchronized(msgq) {
                try {

                    while (msgq.size()>=20)
                        msgq.wait();  // Queue 가 full 이라면 무한 wait

                    System.out.printf("MSGQ send,size=%d \n",msgq.size()) ;
                    msgq.notifyAll() ; // msgq 와 sync 된 다른 thread wake-up
                    msgq.wait(5000) ; // 5초를 기다리거나 누군가 notify 에 의하여 깨어남
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}

class ThreadMsgQ2 extends Thread{ // for message test - receiver

    MsgQueue msgq = null ;

    public ThreadMsgQ2(MsgQueue msgq) {
        this.msgq = msgq ;
    }

    public void run() {
        AndroidIntent intent ;
        while(true) {
            synchronized(msgq) {
                try {
                    while (msgq.size()==0)
                        msgq.wait(); // 무한 wait --> 누군가 notify 에 의하여 깨어남 --> while

                    System.out.printf("MSGQ received,size=%d \n",msgq.size()) ;
                    intent = msgq.recvMessage() ;
                    msgq.notifyAll() ; // msgq 와 sync 된 다른 thread wake-up
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
