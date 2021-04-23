package com.my.pratice ;

public class MyClass implements MyIf1 {

    @Override
    public void onClick(){
    	System.out.printf("MyClass onClick \n") ;
    }
    public void method1(){ 
    	System.out.printf("MyClass method1 \n") ;
    }
    public final void method2(){ // final 은 override 가 불가하다 
    	System.out.printf("MyClass method2 \n") ;
    }
    
    private void method3(){ // private 은 class 내부에서만 사용된다 
    	System.out.printf("MyClass method3 \n") ;
    }
    

}

interface MyIf1 {

    public void onClick(); // abstract method == abstract void myIf1Method1()

}

// interface MyIf2 extends MyClass{ }  // CAN'T 
	

interface MyIf2 extends MyIf1{  // Interface 는 Interface 를 상속가능 - 확장개념  
	
}

class MyClass3 extends MyClass implements MyIf2{
	
}



final class MyClass2 extends MyClass {

    @Override
    public void onClick(){
        System.out.printf("MyClass2 onClick \n") ;
    }
    @Override
    public void method1(){
    	 System.out.printf("MyClass2 method1 \n") ;
    }
    
    
    /* OVERRIDE 불가능 
    @Override
    public void method2(){
    	System.out.printf("MyClass2 method2 \n") ;
    }
    */
    
    
    public void method3(){ // NOT override as super has private 
    	System.out.printf("MyClass2 method3 \n") ;
    } 
    
    public void method4(){ 
    	method1() ;
    	this.method1();
    	super.method1();
    	method2() ;
    	method3() ;
    	
    } 
    
}

/* Error as MyClass2 is final - 상속불가 
class MyClass3 extends MyClass2 {
	
	
}
*/


abstract class absClass1{
    public void method1(){
        System.out.printf("absClass1 - method1 \n") ;
    }
    public void method2(){
        System.out.printf("absClass1 - method2 \n") ;
    }
}

class MyClass5 extends absClass1{
    @Override
    public void method1(){
        System.out.printf("MyClass5 - method1 \n") ;
    }
    
    public void method3(){
        method1()   ;          // MyClass5 의 mthod1() 이 호출
        this.method1()   ;    // MyClass5 의 mthod1() 이 호출
        super.method1()   ;  // absClass1 의 mthod1() 이 호출
        this.method2() ;     // absClass1 의 method2() 가 호출
        
    }
}
