package com.my.pratice ;


import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStream {
    /* Stream 은
       . data 목록 (Collection package - list, set, map) 에 대한 프로세싱 (operation-조작) 이 목적
       . Interface 이다 (추상 메소드 와 실제 구현 메소드로 구성)
       . 추상 메소드는 operator 로 사용되고 매개변수에 람다식 활용
         -. 조작 operator : filter, map, flatMap, limit, skip, sorted, distinct, peek
         -. 종료 operator : collect, count, XMatch, min, max, reduce, forEach, findAny
       . 특징 : source 에 영향을 미치지 않는다, lazy, 자동반복 (compiler 가 적용)

     */
    /* 사용하는 FunctionalInterface
       filter(Predicate)
       map(Function)
       flatMap(Function)
       sorted(Comparator)
       peek(Consumer)
       allMatch(Predicate)
       anyMatch(Predicate)
       noneMatch(Predicate)
       min(Comparator)
       max(Comparator)
       reduce(BinaryOperator)
       forEach(Consumer)
     */
	
	/* FunctionalInterface 분류 
	   매개값   리턴값   해당 Interface  
	     1     0     Consumer : 매개값만 사용한다 (소비자) 
	     0     1	 Supplier    : 매개값 없이 공급자 역할 
         1     1     Predicate  : 매개값 으로 Boolean 도출 
         1     1     Function   : 매개값으로 일반적인 함수역할 
         1     1     Comparator : 매개값으로 비교하여 int return 
	 */

    public static void streamTest1() { // 종료 operations

        System.out.printf("\nstreamTest1\n") ;
        long cnt = Stream.of(100,110,106,108,104,112,115,108)
                .filter(x -> x>104) // Predicate
                .count() ;
        System.out.println(cnt) ; // 6

        boolean b1 = Stream.of(100,110,106,108,104,112,115,108)
                .allMatch(x -> x>=105) ;  // Predicate
        System.out.println(b1) ; // false

        boolean b2 = Stream.of(100,110,106,108,104,112,115,108)
                .anyMatch(x -> x==112) ;  // Predicate
        System.out.println(b2) ; // true

        boolean b3 = Stream.of(100,110,106,108,104,112,115,108)
                .noneMatch(x -> x==103) ;  // Predicate
        System.out.println(b3) ; // true

        List li1 = Stream.of(100,110,106,108,104,112,115,108)
                .filter(x -> x>104)  // Predicate
                .collect(Collectors.toList()) ;
        System.out.println(li1) ; // 110,106,108,112,115,108

        Stream.of(100,110,106,108,104,112,115,108)
                .filter(x -> x>109)  // Predicate
                .forEach(a -> System.out.println(a)) ; // 110,112,115

    }

    public static void streamTest2(){
        /* 참고. filter 일반적인 구현 - Predicate 는 사용
           . 즉, Stream 은 일반적인 람다식과 더불어 Compiler 를 통한 함축을 통하여
             data객체의 오퍼레이션에 대한 사용성의 확대와 이를 간결하게 가져가는 것이다
        */

        System.out.printf("\nstreamTest2 \n") ;

        Integer array1[] = {100,110,106,108,104,112,115,108} ;
        List<Integer> listInt1 =  Arrays.asList(array1) ;
        List<Integer> listInt2 =  new ArrayList<>() ;
        Predicate<Integer> pre = (x) -> x > 109 ;  // LAMBDA 객체
        for (Integer item:listInt1){
            boolean result = pre.test(item) ;
            if(result)
                listInt2.add(item) ;
        }
        System.out.println(listInt2) ; // 110,112, 115

    }

    public static void streamTest3() { // 조작 operations for filter,limit,skip

        System.out.printf("\nstreamTest3 \n") ;
        List li1 = Stream.of(99,100,110,106,108,104,112,115,108,125)
                .filter(x -> x >=100)  // Predicate
                .filter(x -> x <120) // Predicate
                .limit(3)
                .collect(Collectors.toList()) ;
        System.out.println(li1) ; // 100,110,106

        List li2 = Stream.of(99,100,110,106,108,104,112,115,108,125)
                .filter(x -> x >=100) // Predicate
                .filter(x -> x <120) // Predicate
                .skip(3)
                .collect(Collectors.toList()) ;
        System.out.println(li2) ; // 108,104,112,115,108
    }

    public static void streamTest4() { // 종료 operations for max,min,reduce

        System.out.printf("\nstreamTest4 \n") ;
        Comparator<Integer> ca = (x, y) -> x-y;
        BinaryOperator<Integer> ba = (x,y) -> x+y; // 매개변수 가 Comparator
        BinaryOperator<Integer> bb = (x, y) -> x-y;

        Optional<Integer>  o1 = Stream.of(106,100,110,99,125,104,112,115,108,109)
                .max(ca) ; // Comparator
        System.out.println(o1) ; // 125

        Optional<Integer> o2 = Stream.of(106,100,110,99,125,104,112,115,108,109)
                .min((x,y)->x-y) ; // Comparator
        System.out.println(o2) ; // 99

        Optional<Integer>  o3 = Stream.of(106,100,110,99,125,104,112,115,108,109)
                .reduce(ba) ; // BinaryOperator
        System.out.println(o3) ; // 1088
        Integer  o4 = Stream.of(106,100,110,99,125,104,112,115,108,109)
                .reduce(200,bb) ;
        System.out.println(o4) ; // -888

    }


    public static void streamTest5 () { // 조작 operations for sorted

        System.out.printf("\nstreamTest5 \n") ;
        List<MyStudent> la1 = StudentMgr.make10StudentList() ;

        Comparator<MyStudent> ca = (x,y) -> {
            int diff ;
            diff = x.scoreMath-y.scoreMath ;
            return diff ;
        };
        Comparator<MyStudent> cb = (x,y) -> {
            int diff ;
            diff = x.scoreEng-y.scoreEng ;
            return diff ;
        };

        List temp = la1.stream()
                .sorted()      // Natural sorting
                .collect(Collectors.toList()) ;
        System.out.println(temp) ;

        List temp2 = la1.stream()
                .sorted(ca) // Comparator , Custom sorting
                .collect(Collectors.toList()) ;
        System.out.println(temp2) ;

        List temp3 = la1.stream()
                .sorted(cb)  // Comparator , Custom sorting
                .collect(Collectors.toList()) ;
        System.out.println(temp3) ;


    }

    public static void streamTest6 () { // 조작 operations for flatMap

        System.out.printf("\nstreamTest6 \n") ;
        List evens = Arrays.asList(2,4,6) ;
        List odds = Arrays.asList(3,5,7) ;
        List primes = Arrays.asList(2,3,5,7,11) ;

        Object li4 = Stream.of(evens, odds, primes)
                .flatMap(x->x.stream()) // Function
                .collect(Collectors.toList()) ;
        System.out.println(li4) ; // 2,4,6,3,5,7,2,3,5,7,11

        List<MyStudent> la1 = StudentMgr.make10StudentList() ;
        List<MyStudent> la2 = StudentMgr.make10StudentList2() ;
        Function<List,Stream> fb = x -> x.stream() ;

        List<MyStudent> la3 = Stream.of(la1,la2)
                .flatMap(x->x.stream())
                .collect(Collectors.toList()) ;
        System.out.println(la3) ;

    }



    public static void streamTest7() { // 조작 operations for map

        System.out.printf("\nstreamTest7 \n") ;
        Function<String,Integer> fa = x -> Integer.valueOf(x) ;

        List li1 = Stream.of("1","2","3","4")
                .map(Integer::valueOf)
                .collect(Collectors.toList()) ;
        System.out.println(li1) ; // 1,2,3,4

        List li2 = Stream.of("1","2","3","4")
                .map(x -> Integer.valueOf(x))  // Function
                .collect(Collectors.toList()) ;
        System.out.println(li2) ; // 1,2,3,4


        List li3 = Stream.of("1","2","3","4","5","3")
                .map(fa)
                .distinct() // to remove duplicate
                .collect(Collectors.toList()) ;
        System.out.println(li3) ; // 1,2,3,4,5

    }

    public static void streamTest8 () { // 조작 operations for mapToInt

        System.out.printf("\nstreamTest8 \n") ;
        List<MyStudent> la1 = StudentMgr.make10StudentList() ;
        List<MyStudent> la2 = StudentMgr.make10StudentList2() ;
        Function<List,Stream> fb = x -> x.stream() ;
        List<MyStudent> la3 = Stream.of(la1,la2)
                .flatMap(x->x.stream())  // Function
                .collect(Collectors.toList()) ;

        ToIntFunction<MyStudent> ta = x -> x.scoreMath ;
        //Integer a = Stream.of(la1,la2) // Error Why?
        Integer a = la3.stream()
                .mapToInt(ta)
                .sum() ;
        System.out.println(a);  // 1240 for 20 students

        int [] ia = la3.stream()
                .mapToInt(ta)
                .toArray() ;
    }
}
