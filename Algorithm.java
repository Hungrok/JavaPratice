package com.my.pratice ;


public class Algorithm {
  

    // 몇자리 숫자인지 찾아낸다 
    static void howManyDigit(int number){
        /*
        int temp = (int)Math.log10(number) ;
        System.out.printf("Digit=%d \n",temp+1) ;
        */
        for (int pow=1 ;pow<=20; pow++){
            int temp = (int) (Math.pow(10,pow)) ;
            if(temp > number){
                System.out.printf("Digit=%d \n",pow) ;
                break ;
            }
        }
    }

    // mod operator (%) 를 구현한다 
    public static int modOperator(int boonja, int boonmo) {
        int remainder=0 ;

        remainder = (boonja) - ((boonja/boonmo) * boonmo) ;

        System.out.printf("boonja mod boonmo = %d \n",remainder) ;
        return remainder ;
    }

    public static void verifyStringHashCode(){
        char alpha[] = new char[3] ;
        int r1=0, r2=0, r3 = 0 ;
        alpha[0] = 'a';
        alpha[1] = 'a';
        alpha[2] = 'a';
        String s1 = null ;
        int hc[] = new int[17576] ;
        int count=0 ;
        int hashcode = 0 ;

        for (r1=0 ; r1 <26 ; r1++) {
            for (r2=0 ; r2 <26 ; r2++) {
                for (r3=0 ; r3 <26 ; r3++) {
                    s1 = s1.valueOf(alpha) ;
                    hc[count] = s1.hashCode() ;
                    //System.out.printf("count=%d, s1=%s \n",count,s1) ;
                    alpha[2]++ ;
                    count++ ;
                    if (alpha[2]==('z'+1))
                        alpha[2] = 'a' ;

                }
                alpha[1]++ ;
                if (alpha[1]==('z'+1))
                    alpha[1] = 'a' ;

            }
            alpha[0]++ ;
            if (alpha[0]==('z'+1))
                alpha[0] = 'a' ;

        }

        System.out.printf("count=%d, s1=%s \n",count,s1.valueOf(alpha)) ;
        for (r1 = 0 ; r1 < 17576 ; r1++) {
            hashcode = hc[r1] ;
            count = 0 ;
            for (r2 = 0 ; r2 < 17576 ; r2++) {
                if(hashcode == hc[r2])
                    count++ ;
            }
            if (count > 1) {
                System.out.printf("Break as same hashcode \n",count) ;
                break ;
            }
            //System.out.printf("same hashcode count=%d \n",count) ;
        }
    }

    // 년월일을 입력받아 요일을 알아낸다 
    public static void verifyOfDay(int year, int month, int day) {
        // remark 01.01.01 is Monday and we don't have year 0000


        int days = 0 ;
        int yoonyears = 0 ;
        // Previous total days
        days = (year-1)*365 ;

        yoonyears = (year-1)/4 ;
        yoonyears -= (year-1)/100 ;
        yoonyears += (year-1)/400 ;
        days += yoonyears ;
        System.out.printf("Previous year, days=%d, yoonyears=        if (year%4==0) thisyearleaf = true ;        boolean thisyearleaf=false ;\n%d \n",days,yoonyears) ;

        // this year
        boolean thisyearleaf = false;

        if (year%100==0) thisyearleaf = false ;
        if (year%400==0) thisyearleaf = true ;

        int monthdays1[] = {31,28,31,30,31,30,31,31,30,31,30,31} ;
        int monthdays2[] = {31,29,31,30,31,30,31,31,30,31,30,31} ;
        for (int i=1 ; i <month ; i++) {
            if(!thisyearleaf)
                days += monthdays1[i-1] ;
            else
                days += monthdays2[i-1] ;
        }
        days += day ;

        System.out.printf("This year, days=%d, thisyearleaf=%b \n",days,thisyearleaf) ;



        String daytext1[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"} ; // 01.01.01 is monday
        System.out.printf("Verify day is %s \n",daytext1[days%7]) ;

    }

    public static int[] makeLottoNumber() {
        int lotto[] = new int[6] ;
        int same = 0 ;

        for (int i=0 ; i <6 ; i++) {
            lotto[i] = MyUtility.randomInt(45,1) ;


            while(true) { // 중복 check
                same = 0 ;
                for (int k=0 ; k <i ; k++ ) {
                    if(lotto[i] == lotto[k]) {
                        same++ ;
                        //System.out.printf("Same number= %d \n",i) ;
                        lotto[i] = MyUtility.randomInt(45,1) % 45;

                    }
                }
                if(same==0) break ;
            }


        }
        //System.out.printf("Lotto Number=%d-%d-%d-%d-%d-%d \n",lotto[0],lotto[1],lotto[2],lotto[3],lotto[4],lotto[5]) ;
        return lotto ;
    }

    // 임의의 로또번호 6개를 받아 6개가 맞을 확률을 알아낸다 
    public static void lottoWin(int l1,int l2, int l3, int l4, int l5, int l6) {

        int lotto[]  = null;

        int matchcnt = 0 ;
        int count = 1 ;

        while(true) {
            matchcnt = 0 ;
            lotto = makeLottoNumber() ;

            for(int a:lotto)
                if(a==l1) matchcnt++ ;
            for(int a:lotto)
                if(a==l2) matchcnt++ ;
            for(int a:lotto)
                if(a==l3) matchcnt++ ;
            for(int a:lotto)
                if(a==l4) matchcnt++ ;
            for(int a:lotto)
                if(a==l5) matchcnt++ ;
            for(int a:lotto)
                if(a==l6) matchcnt++ ;

            if(matchcnt==6)
                break ;
            count++ ;
            if (count > 100000000)
                break ;
        }

        System.out.printf("\nLotto win-one, matchcnt=%d,count=%d \n",matchcnt,count) ;
    }




}
