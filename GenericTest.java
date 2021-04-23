package com.my.pratice ;


import java.util.ArrayList;
import java.util.List;


import java.util.*;
import java.util.stream.Stream;

/* 제너릭 정의
. 객체화 시 혹은 메소드 사용시 적용되는 data type 을 포괄화
. 선언 (type parameter) --> 확정 (parameterized) - 적용범위 (scope) 표현
. 선언시 scope 는 2가지로 분류
  1) class 혹은 interface 에서 선언 - 구성 멤버에 두루 적용
  2) class 혹은 interface 에서 선언없이 특정 메소드에서 적용 (대부분 static method)
. 선언 (type parameter) 세부 : 일반, bounded
. 확정 (data type 에 대한 확정)
  (1) class 선언 --> class 객체화 시 확정
  (2) interface 선언 --> implements class 에서 확정
. 효과 : Strong type check, 가독성, 재사용성 (다형성)
. 참고 : 적용시 compiler 가 실제 객체화 되는 data type 을 사용한다
*/
public class GenericTest {

    public static void carTest(){ // for parameterized - wildcard bounded
        // 확정
        List<Car> obj1 = new ArrayList<>() ;
        List<Sedan> obj2 = new ArrayList<>() ;
        List<Sonata> obj3 = new ArrayList<>() ;

        // add(List<E>) 인 점을 주지 바람
        obj1.add(new Car()) ; obj1.add(new Sedan()) ; obj1.add(new Sonata()) ;
        obj2.add(new Sedan()) ; obj2.add(new Sonata()) ; // ERROR - obj2.add(new Car())
        obj3.add(new Sonata()) ; // ERROR - obj3.add(new Car()) , obj3.add(new Sedan())

        // parameterized - wildcard bounded (매개변수에 의하여)
        carTest1(obj1) ; carTest1(obj2) ; // ERROR -  carTest1(obj3)
        carTest2(obj2) ; carTest2(obj3) ; // ERROR -  carTest2(obj1)
        carTest3(obj1) ; carTest3(obj2) ; carTest3(obj3) ;

    }
    public static void carTest1(List<? super Sedan> list){ }
    public static void carTest2(List<? extends Sedan> list){ }
    public static void carTest3(List<? extends Car> list){ }

    static void carTest2(){ // Comparator<? super T> 에 대한 이해
        Car2 obj1 = new Car2() ;
        List<Sedan2> obj2 = new ArrayList<>() ;
        List<Sonata2> obj3 = new ArrayList<>() ;
        Collections.sort(obj2,obj1) ; // List<T> 는 obj2, Comparator<? super T> 는 obj1 을 사용
        Collections.sort(obj3,obj1) ; // List<T> 는 obj3, Comparator<? super T> 는 obj1 을 사용

    }

}

class Car {}
class Sedan extends Car{}
class Sonata extends Sedan{}


class GenericDeclare1 <T,E> {
    T obj1 = null ;
    E obj2 = null ;
    public void func1(T t){}

    public <U,R> R func2(T t, U u){R obj1=null ; return obj1 ; } // 메소드 개별적 선언 및 적용
}

class GenericDeclare1b{
    static public <U,R> R func3(U u){R obj1=null ; return obj1 ;}
}


class GenericDeclare2 <T extends List>{
    // bounded type parameter, 명시된 객체 자료형을 상속한 하위객체(Collection-List-ArrayList)
    T obj1 ;
}
/*
class GenericDeclare3 <T super ArrayList> {
    // bounded type parameter, 명시된 객체자료형의 상위 객체
    T obj1 = null ;
} */


interface GenericDeclare4<T>{
    void absMethod(T t) ;
}


class GenericExpression implements GenericDeclare4<Sedan> {

    // implements 에 대한 확정 결과로  추상메소드 매개변수도 확정된다
    @Override
    public void absMethod(Sedan sedan) {

    }

    // Type parameter (선언) 에 대한 확정 (parameterized)
    List<Car> obj1 = new ArrayList<>();
    List<? extends Sedan> test = new ArrayList<>() ;

    // 적용범위를 표현하는 wildcard 는 일반적으로 메쏘드의 매개변수에 사용한다
    // Parameterized 된 객체를 지칭하는데 범위를 추가적으로 명확히 하는 것이다
    void method1(List<?> var) {}
    void method2(List<? super Sedan> var) {}
    void method3(List<? extends Sedan> var) {}

    // 적용범위를 표현하는 wildcard 가 객체화 (instance) 에 적용 되어지는 것은 넌센스 이다
    // parameterized 는 확정을 하고자 하는 의미인데..적용범위가 사용되었다
    List<?> obj2 = new ArrayList<>();
    List<? super Sedan> obj3 = new ArrayList<>();
    List<? extends Sedan> obj4 = new ArrayList<>();

}


class Car2 implements Comparator<Car2> {
    @Override
    public int compare(Car2 o1, Car2 o2) { return 0; }
}
class Sedan2 extends Car2 {}
class Sonata2 extends Sedan2 {}
