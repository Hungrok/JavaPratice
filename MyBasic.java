package com.my.pratice ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyBasic {

    static final int manAdultStdAge = 30 ; // 정적상수
    static final int womanAdultStdAge = 40 ; // 정적상수
    static int sample = 10 ; // 정적변수
    int instance1 = 10 ; // instance 변수도 초기화 가능하다
    List<Integer> instance2 = new ArrayList<Integer>() ; // 참조형 instance 변수도 초기화 가능하다

    public static void testWhat() {


        System.out.printf("HelloWorld\n") ;
        //primitiveDataType();
        //stringOperatorTest1() ;
        //displayMulNumber (200,8) ;
        //checkYoung(true,12) ;
        System.out.printf("CheckYoung = %s \n",checkYoung(true,12) ) ;
        checkYoung(false,80) ;


    }

    static void displayMulNumber (int range, int base){ // quiz 2
        // range 는 1부터 시작 합시다
        int count =0 ;
        for (int curNumber =1 ; curNumber <=range ; curNumber++){
            int temp1 = curNumber % base ;
            if(temp1==0) {
                count++ ;
                System.out.printf("%d 의 %d번째 배수는 %d 입니다 \n"
                        , base,count,curNumber);
            }
        }
    }

    static String checkYoung (boolean man, int age){ // quiz 3
        String str1 = "He is Young" ;
        String str2 = "He is Old" ;
        String str3 = "She is Young" ;
        String str4 = "She is Old" ;
        String str5 = null ;

        /*
        if(man){
            if (age>=30)
                str5 = str2;
            else
                str5 = str1 ;
        }
        else{
            if (age>=40)
                str5 = str4;
            else
                str5 = str3 ;

        System.out.printf("CheckYiung = %s \n",str5) ;
        return str5 ;
        */

        //(expr) ? true statement : false statement // 3항 연산식 expression 입니다
        return (man==true) ? ((age>=manAdultStdAge) ? str2 : str1 ) : ((age>=womanAdultStdAge) ? str4 : str3 ) ;

    }

    static void primitiveDataType() { // 원시형 data type 8가지
        /*  특징
            -. unsigned (음수) data type 은 별도로 없으며 '-' 로 시작한다
            -. 이진수, 팔진수, 십진수, 십육진수 로 표현이 가능하다
            -. L(long),F(float),D(double) 써픽스는 data type 에 대한 확실함을 가져간다
            -. 실수는 지수표현이 가능하다
        */

        char alpha1 = 's' ; // ASCII code (1 byte)
        char alpha2 = '김' ; // char 형은 2 byte 를 지닌다 -  for Unicode
        byte bit8 = 127 ;
        short bit16 = 32767 ;
        int bit32b  = 0b00000001000000010000000100000001 ; // 2진수 - 0b 로 시작
        int	bit32d = 80000 ; // 10진수
        int bit32o = 07777777777 ;  // 8진수 - 0 으로 시작
        int bit32h = 0xffffffff ; // 16진수 - 0x 로 시작,안드로이드 color 값 표현시 16진수 사용 (RGB)
        int bit32u = -20000000 ; // 음수
        long bit64 = 100000000L ;

        float float32 = -3.22F ; // 실수 32 bit
        double double1 = 0.0000012D; // 실수 64 bit
        double double2 = 0.12E-5 ; // 0.12*10 -5
        if(double1 == double2)
            System.out.printf("double is same \n") ;

        boolean bool = true ;
    }

    static void typeCastingTest1() { // auto casting
        char c1 = 'a' ;
        byte b1 = 10 ;
        short s1 = 10 ;
        int   i1 = 10 ;
        long l1 = 10L;
        float f1 = 10.1F ;
        double d1 = 10.1D ;

        //c1 = b1 ; // byte --> char (X)
        //s1 = c1 ; // char --> short (X)
        i1 = c1 ; // char --> int
        f1 = i1 ; // int --> float
        d1 = i1 ; // int --> double
        f1 = l1 ; // long --> float
        d1 = l1 ; // long --> double
        d1 = f1 ; // float --> double


    }

    static void typeCastingTest2() { // explicit casting
        char c1 = 'a' ;
        byte b1 = 10 ;
        short s1 = 10 ;
        int   i1 = 10 ;
        long l1 = 10L;
        float f1 = 10.1F ;
        double d1 = 10.1D ;
        String str1 = "abcde" ;

        d1 = (double) i1 ;
        b1 = (byte) i1 ;
        i1 = (int) d1 ;
        //c1 = (char) str1 ; // Can't convert between char <--> string

        //byte result1 = b1+5 ; // Can't as using +-*% then result be must be int
        //short result2 = s1+5 ; // Can't
        int result3 = i1+5 ;
        float result4 = f1+5 ;
        double result5 = d1+5 ;


    }
    static void operatorTest1() {

        int result6 = 534 / 30 ;
        int result7 = 534 % 30 ;
        int result8 = 356 % 100 ;
        System.out.printf("result6=%d, result7=%d, result8=%d \n",result6, result7, result8) ;

    }
    static void operatorTest2() {

        String str1 = "abcde" ;
        String str2 = "fghij"  ;
        String str3 = str2 ;
        if(str1!=str2)
            System.out.printf("not same str1 with str2 \n") ;
        if(str2==str3)
            System.out.printf("same str2 with str3 \n") ;



        boolean ba = true ;
        boolean bb = !ba ;
        System.out.printf("boolean bb=%s \n",bb) ;
        if(ba && bb )
            System.out.printf("both are true \n") ;
        if(ba || bb )
            System.out.printf("either one be true \n") ;

        int bitshifta = 0x10000000 ;
        int bitshiftb = (bitshifta >> 1) ; // shift to right 1 step
        int bitshiftc = (~bitshifta) ; // 1's complement
        int bitshiftd = (bitshifta ^ 0xefffffff) ; // exclusive OR

        System.out.printf("bitshiftb=0x%8h , bitshiftc=0x%8h, bitshiftd=0x%8h \n"
                ,bitshiftb,bitshiftc,bitshiftd) ;

        int age = 10 ;
        String ageResult1 = (age >30) ? "He is old" : "He is Young" ;
        System.out.printf("ageResult=%s \n",ageResult1) ;
        String ageResult2 = (age <30) ? "He is Young" : "He is Old" ;
        System.out.printf("ageResult=%s \n",ageResult2) ;

    }

    static void controlStatementTest1() {

        double d1 ;
        //switch(d1) { } // ERROR as double

        for (int i=0 ; i<5 ; i++) {
            for (int k=0 ; k<=i ; k++)
                System.out.printf("*");
            System.out.printf("\n");
        }
    }

    static void referenceDataTest1() {

        int[] intar = new int[3] ;
        float[] floatar = new float[3] ;
        boolean[] boolar = new boolean[3] ;
        String[] strar = new String[3] ;

        System.out.printf("intar[0]=%d, floatar[0]=%f, boolar[0]=%b, strar[0]=%s \n"
                ,intar[0], floatar[0], boolar[0], strar[0]) ;

        int[][] int2ar = {{95,86},{83,92,96},{78,83,93,87,88}} ;
        System.out.printf("length=%d,1st length=%d, 2nd length=%d, 3rd length=%d\n"
                ,int2ar.length, int2ar[0].length, int2ar[1].length, int2ar[2].length) ;

    }
    static void stringOperatorTest1(){
        String s1 = "abcde" ;
        String s2 = "12345" ;
        String s3 = s1+s2 ;  // + 연산자 를 이용하여 string 결합 가능
        //String s3a = s1-s2 ;  // - 연산자 불가능
        String s4 = s3 + "kkk" ;
        System.out.printf("s4=%s \n",s4) ;
        String s5 = s4 + 12 ;  // Number 를 컴파일러가 String 으로 변환 한다
        System.out.printf("s5=%s \n",s5) ;

        String s6 = String.valueOf(12) ; // String 클래스가 제공하는 메소드 이용 (정수 --> String)
        String s7 = s4+s6 ;
        System.out.printf("s7=%s \n",s7) ;

        int convert = Integer.parseInt("900904") ; // 참고 (String --> int)
    }

    static void arrayTest1(){



        int ar1[] = {7,8,10,2,4,17} ;        // 리터럴 방법으로 생성, 요소 (element) size 는 6
        int ar2[] = new int[] {0,1,2,3} ;  // new 방법으로 생성, 요소 (element) size 는 4
        int ar3[] = new int[5] ;           // 요소 (element) size 는 5, 초기화 안됨

        ar1[3] = 30 ;
        System.out.printf(" %d %d %d %d %d %d \n"
                ,ar1[0],ar1[1],ar1[2],ar1[3],ar1[4],ar1[5]);


        String sar1[] = {"korea","usa","england"} ;
        String sar2[] = new String[] {""} ;
        String sar3[] = new String[5] ;

        // 2차원 array
        int[][] int2ar = {{95,86},{83,92,96},{78,83,93,87,88}} ;
        System.out.printf("length=%d,1st length=%d, 2nd length=%d, 3rd length=%d\n"
                ,int2ar.length, int2ar[0].length, int2ar[1].length, int2ar[2].length) ;

    }

    static void arrayLengthTest(){
        int[][] array = {
                {95,86},
                {83,92,96},
                {78,83,93,87,88}
        };

        int arsize = array.length ;
        int ar1size = array[0].length ;
        int ar2size = array[1].length ;
        int ar3size = array[2].length ;

        System.out.printf("arsize=%d, ar1=%d,ar2=%d,ar3=%d \n"
                ,arsize,ar1size,ar2size,ar3size) ;

    }

    static void forTest(){
        int[] array = {2,7,9,15,88} ;
        for (int item=0 ; item < array.length ; item++ )
            System.out.printf("value=%d \n", array[item]) ;

        for (int item:array){ // 콤파일러가 번역하여 위에 문장으로 만든다
            System.out.printf("value=%d \n", item) ;
        }

        StudentHere st1 = new StudentHere("Kwon") ;
        StudentHere st2 = new StudentHere("Kim") ;
        StudentHere st3 = new StudentHere("Kang") ;
        List<StudentHere> stlist = new LinkedList<StudentHere>() ;

        stlist.add(st1) ;
        stlist.add(st2) ;
        stlist.add(st3) ;

        for (StudentHere item:stlist){
            System.out.printf("name=%s \n", item.name) ;
        }

    }

}

