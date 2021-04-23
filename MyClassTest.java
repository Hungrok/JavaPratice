package com.my.pratice ;

import java.io.PrintStream;

public class MyClassTest {


	public static void classTest() { // 상속 및 확장 기본적 개념
		
		MyClass2 class2 = new MyClass2() ;
		class2.method4();
		
		MyClass5 class5 = new MyClass5() ;
		class5.method3();
		
		/* Interface 나 Abstract class 는 data type 으로 사용가능하지만 객체대상으로는 안된다 
		 * -. 객체모형은 구체적이어야 하지 추상적인 method 를 지니면 안된다
		 * -. data type 으로 사용가능것은 다형성 개념이 적용된 것이다 
		 * -. reference 는 data type 으로 scope 가 한정된다 
		 */
		//MyIf1 myif1 = new MyIf1() ;
		//absClass1 abcls1 = new absClass1() ;
		
	}
	
	public static void nestedClassTest() { // 중첩class 
		
		
		//1. inner class 
		MyClassNested outer = new MyClassNested() ;
		MyClassNested.InnerA ic = outer.new InnerA() ;            
        ic.method1() ;
        
        //2. static inner class 
        MyClassNested.InnerB sic = new MyClassNested.InnerB() ;
        sic.method1() ;
        
        //3. method 내부 class 
        outer.lcMethod() ;
       
        
        //4. 익명 class : new Interface() : compiler 사전약속 --> 익명의 class 를 생성 --> 객체모형화 
        EventListener listener1 = new EventListener() { 
        	@Override
            public void onClick(){
            	
            }
        }; // == new classXXX() ;
        
        //5. 익명 class - 4항에 대한 LAMBDA 식 
        EventListener listener2 = () -> { ; };  
        /*
        . 정의하면 아래와 같다 (람다식을 사용 할 수 있는 조건이 성립된다)  
        -. Interface 가 지니는 추상 메소드에 대한 implement (override) class 를 가져간다 – 하나이상 추상 메쏘드 가능
        -. Interface 이름, method 이름은 익명으로 처리한다 (컴파일러) 
        -. 적용 가능한 Interface 들은 @FunctionalInterface 로 annotation 되어있다  
        */


	}
	
	public static void eventTest() { // Interface 활용 - 익명 class 
		
		 // 상속받거나 Implement 한 interface 도 data type 으로 사용가능 
		 EventHandler btn1 = new Button() ;
		 Button btn2 = new Button() ;
		 
		 
	     EventListener listener = new EventListener() { // 중첩 - 익명 class
	            @Override
	            public void onClick(){
	            	
	            }
	     }; // == new classXXX() ;
	     
	     btn1.setOnClickListener(listener);
	     btn2.setOnClickListener(new EventListenerClass() ) ;


	     StudentHere st1 = new StudentHere() ;
	     test(st1) ; // same as 아래 
	     test(new StudentHere()) ;
		
	}
	
   

    static void test(StudentHere st){

    }

    public static void classTypeCastTest() {
		
		
		
	}


    // 다형성 test 
    static void animalTest(){

        Cat cat = new Cat() ;
        Dog dog = new Dog() ;
        Horse horse = new Horse() ;
        // 다형성-1
        cat.cryingSound();
        dog.cryingSound();
        horse.cryingSound();

        Animal cat1 = new Cat() ; // 자동 casting

        // 다형성-2
        playAnimalCrying(cat) ; // 자동 casting 발생 Cat --> Animal , Animal cat = new Cat() 과 동일
        playAnimalCrying(dog) ; // 자동 casting 발생 Dog --> Animal , Animal dog = new Dog() 과 동일
        playAnimalCrying(horse) ; // 자동 casting 발생 Horse --> Animal , Animal horse = new Horse() 과 동일

        playAnimalCrying3(cat) ;


    }

    static void playAnimalCrying(Animal animal){ // 추상 data type 을 사용하여 다형성을 가져간다
        animal.cryingSound();
    }

    static void playAnimalCrying3(Cat cat){ // Cat 만 처리 가능하다
        cat.cryingSound();
    }



}

class StudentHere{
    String name ;

    /* Constructor 간단설명
      . 객체생성시 객체가 지니는 멤버들에 대한 값을 assignment 
      . Class 이름과 반드시 동일해야 한다 
      . return 이 없다 : 시스템이 객체생성시 호출하기 떄문 
      . new XX() : 객체XX 를 만들고 constructor 호출 argument 를 지정하는 의미이다 
      . constructor 도 overloading 가능하다 
      . Java 에는 deconstructor 가 없다 - JVM 이 자동으로 객체를 소멸시킨다 
    */
    public StudentHere(){ // constructor 를 만들지 않으면 자동으로 이리 만들어 진다  
        
    }
    
    public StudentHere(String name2){ // constructor does not have return 
        this.name = name2 ;
    }
   
}
