package com.my.pratice ;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class MyStudent implements Comparable<MyStudent>, Comparator<MyStudent>,
                        Serializable {

    private static final long serialVersionUID = 4420442044204420442L ;

    private String name ;
    private int age ;
    private String ban ;
    public int scoreKor ;  // Comparator 개별객체화시 편의성
    public int scoreMath ;
    public int scoreEng ;
    private int scoreAvg ;

    public MyStudent(String n,int a,String b,int s1, int s2, int s3){
        this.name = n ; this.age = a; this.ban= b ;
        this.scoreKor = s1 ; this.scoreMath = s2 ; this.scoreEng=s3 ;
        this.scoreAvg = (s1+s2+s3/3) ;
    }
    public MyStudent(){
    }
    @Override   //Comparable
    public int compareTo(MyStudent o) { // Natural sorting order , 나를 기준으로 상대 객체와 비교
        System.out.printf("MyStudent-compareTo \n") ;
        int tmp ;
        //tmp = this.scoreAvg - o.scoreAvg ; // 평균점수 기준
        // tmp = this.ban.compareTo(o.ban) ; // 반 기준  - String 객체가 제공하는 compareTo 활용
        tmp = this.name.compareTo(o.name) ; // 이름 기준
        return tmp ;
    }
    @Override   //Comparator
    public int compare(MyStudent o1, MyStudent o2) { // Customized sorting order, 두 객체간 비교기준
        System.out.printf("MyStudent-compare \n") ;
        return o1.scoreAvg - o2.scoreAvg ; // 평균점수 기준
    }

    @Override
    public String toString() {
        String s1 = "Student " ;
        String s2 = "name:"+this.name+"\t" ;
        String s3 = "age:"+this.age+"\t" ;
        String s4 = "ban:"+this.ban+"\t" ;
        String s5 = "avg:"+this.scoreAvg+"\t" ;
        String s6 = "kor:"+this.scoreKor+"\t" ;
        String s7 = "eng:"+this.scoreEng+"\t" ;
        String s8 = "math:"+this.scoreMath+"\t" ;

        return s1+s2+s3+s4+s5+s6+s7+s8 ;
    }
}




class StudentMgr {

    static List<MyStudent> make10StudentList() {
        MyStudent st1 = new MyStudent("Kwon",20,"3-4",65,56,78) ;
        MyStudent st2 = new MyStudent("Kim",21,"3-3",75,66,68) ;
        MyStudent st3 = new MyStudent("Lee",22,"3-2",55,36,98) ;
        MyStudent st4 = new MyStudent("Yang",20,"3-1",62,46,92) ;
        MyStudent st5 = new MyStudent("Park",22,"3-6",69,76,75) ;
        MyStudent st6 = new MyStudent("Choi",21,"3-8",85,56,82) ;
        MyStudent st7 = new MyStudent("Bong",21,"3-5",65,46,85) ;
        MyStudent st8 = new MyStudent("Song",20,"3-7",71,56,74) ;
        MyStudent st9 = new MyStudent("Moon",23,"3-3",77,76,88) ;
        MyStudent st10 = new MyStudent("Jang",22,"3-9",79,66,73) ;

        MyStudent[] star = {st1,st2,st3,st4,st5,st6,st7,st8,st9,st10};
        List<MyStudent> list = Arrays.asList(star) ;

        return list ;
    }

    static List<MyStudent> make10StudentList2() {
        MyStudent st1 = new MyStudent("Kwon2",22,"2-2",68,70,82) ;
        MyStudent st2 = new MyStudent("Kim2",20,"2-3",78,56,88) ;
        MyStudent st3 = new MyStudent("Lee2",21,"2-8",58,56,92) ;
        MyStudent st4 = new MyStudent("Yang2",22,"2-7",68,76,96) ;
        MyStudent st5 = new MyStudent("Park2",23,"2-6",72,66,85) ;
        MyStudent st6 = new MyStudent("Choi2",21,"2-8",80,72,62) ;
        MyStudent st7 = new MyStudent("Bong2",20,"2-5",70,56,85) ;
        MyStudent st8 = new MyStudent("Song2",22,"2-1",69,67,79) ;
        MyStudent st9 = new MyStudent("Moon2",22,"2-4",68,69,89) ;
        MyStudent st10 = new MyStudent("Jang2",23,"2-6",72,72,72) ;

        MyStudent[] star = {st1,st2,st3,st4,st5,st6,st7,st8,st9,st10};
        List<MyStudent> list = Arrays.asList(star) ;

        return list ;
    }

    static void testCollectionsSort(){
        // 참고, Collections sort()
        //public static <T extends Comparable<? super T>> void sort(List<T> list)  // for Natural sorting order
        //public static <T> void sort(List<T> list, Comparator<? super T> c) // for Custom sorting order
        
        List<MyStudent> stds = make10StudentList() ;

        // Comparable 적용
        System.out.printf("Sort by Comparable \n") ;
        Collections.sort(stds); // 결과는 element sorted 되어짐
        for(MyStudent item : stds)
            System.out.printf("%s \n",item.toString()) ;

        // Comparator 적용 - MyStudent 객체 적용
        System.out.printf("Sort by Comparator-1 \n") ;
        Collections.sort(stds,new MyStudent()) ; // 결과는 element sorted 되어짐
        for(MyStudent item : stds)
            System.out.printf("%s \n",item.toString()) ;

        // Comparator 를 개별 객체화 적용 (익명 class)
        System.out.printf("Sort by Comparator-2 \n") ;
        Comparator comp1 = new Comparator<MyStudent>(){ // 인터페이스에 대한 parameterized
            @Override
            public int compare(MyStudent o1, MyStudent o2) {
                return o1.scoreKor - o2.scoreKor ; // 국어점수 기준
            }
        };
        Collections.sort(stds,comp1) ; // 결과는 element sorted 되어짐
        for(MyStudent item : stds)
            System.out.printf("%s \n",item.toString()) ;

        // Comparator 를 개별 객체화 적용 (익명 class)
        System.out.printf("Sort by Comparator-3 \n") ;
        Comparator comp2 = new Comparator<MyStudent> (){ // 인터페이스에 대한 parameterized
            @Override
            public int compare(MyStudent o1, MyStudent o2) {
                return o1.scoreEng - o2.scoreEng ; // 영어점수 기준
            }
        };
        Collections.sort(stds,comp2) ; // 결과는 element sorted 되어짐
        for(MyStudent item : stds)
            System.out.printf("%s \n",item.toString()) ;

        // Comparator 를 개별 객체화 적용 (람다식)
        System.out.printf("Sort by Comparator-4 \n") ;
        Comparator<MyStudent> comp3 = (o1, o2) -> {
                return o1.scoreMath- o2.scoreMath ; // 수학점수 기준
            } ;

        Collections.sort(stds,comp3) ; // 결과는 element sorted 되어짐
        for(MyStudent item : stds)
            System.out.printf("%s \n",item.toString()) ;

    }



}
