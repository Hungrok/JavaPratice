package com.my.pratice ;


import java.time.LocalDateTime;

public class MyTime {

    static void timeTest1(){

        LocalDateTime tnow = LocalDateTime.now();
        LocalDateTime tof = LocalDateTime.of(1,1,1,0,0,0); // 0000 년 1월1일
        LocalDateTime tplus1 = tof.plusYears(1969) ; // UTC based
        LocalDateTime tplus2 = tof.plusYears(1979) ; // GPS based

        LocalDateTime[] objs = {tnow,tof,tplus1,tplus2} ;

        for (LocalDateTime ld : objs ) {
            System.out.printf("%d : %s : %d : %s \n "
                    ,ld.getYear()
                    ,ld.getMonth()
                    ,ld.getDayOfMonth()
                    ,ld.getDayOfWeek());
        }

    }

}
