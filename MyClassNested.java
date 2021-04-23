package com.my.pratice ;

/*
. Nested (중첩) 클래스 적용방법은 아래 5가지로 구분된다   
1) 내부 (Inner) Class            : 클래스 내부의 클래스 
2) Static Inner Class             : Static 으로 선언된 Inner class 
3) 지역 (Local) Class            :  메소드 내부의 클래스 
4) 익명 (Anonymous) Class    : 메소드 내부의 클래스 이나 익명을 지닌다   많이 사용된다
5) 4항을 람다식으로 표현    

. 중첩클래스는 아래 목적으로 사용한다
1) Logically grouped 시킨다   : concrete class 에서 implementation 해야 할 interface 가 많은 경우
2) Encapsulation 강화 : security 
3) Code readability (maintainable) 강화 
*/

public class MyClassNested {

    private int abc = 10 ;
    private static int abd = 20 ;
    
   
    public class InnerA {
    	 /* INNER class 
        . 클래스 내부에 클래스를 만들어서 사용하는 경우
        . Inner 클래스 사용법
          -. 먼저 Outer클래스의 객체를 생성하고 이를 통해 Inner클래스 객체를 생성해야 함.
          -. Inner클래스는 Outer class 의 모든 멤버에 접근이 가능 (private 도 가능) 
          -. Outer클래스는 Inner클래스의 멤버에 접근할 수 없다. 

        . 사용 예 : ArrayList class 에서 implement 할 iterator class 를 이너 클래스 형태로 사용한다  

    	*/
        public void method1(){
           abc = 20 ;
           abd = 30 ;  
        }
    } 
    public static class  InnerB {
    	/* Static Inner Class 
        	. Inner클래스가 static으로 정의된 경우로서 Inner class 대비 2가지가 차이 난다 
        	1) Outer클래스의 객체 생성 없이 바로 객체 생성이 가능 
        	2) Inner 클래스에서는 Outer클래스에 정의된 멤버중에 static 멤버만 접근이 가능. 
            Outer클래스의 객체생성 없이 바로 Inner클래스의 객체를 생성하므로 
               Outer클래스의 객체가 생성되지 않았다는 가정하에 static 멤버만 접근이 가능하다
      		. 사용 참고  
         	-. Array.asList : ArrayList 를 private static class 로 지님 
         	-. Builder design pattern 에 사용 – design pattern 자료 참고 
		*/
        public void method1(){ 
            //abc = 20 ; // ERROR as not static 
            abd = 30 ; // access static field 

        }
    } 
    
    
    private int var1 = 10 ;
    public void lcMethod(){
    	/*
        . Method 내부의 지역 Class  
        . 해당 Method 내부의 지역변수 처럼 메소드 내 에서만 사용된다
        . Outer class 의 멤버변수 와 메소드 내부 지역변수 중  final 로 선언된 것은 접근가능
        . 사용은 잘 안된다  
        */
    	
    	int var2 = 20 ;
    	final int var3 = 30 ;

    	class LocalClass{ // Local Class 
    		public void method1() {
              var1 = 20 ;
              //var2 = 30 ; // ERROR 
              //var3 = 40 ; // ERROR 
    		} 
    	}

    	LocalClass local = new LocalClass() ;
    	local.method1() ;
    }

}

