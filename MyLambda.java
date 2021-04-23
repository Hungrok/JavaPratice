package com.my.pratice ;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* LAMBDA expression 
   . JAVA SE 8 (1.8.0) 에서 도입 
   . 표현식을 축약화 하여 
     -. 객체를 만드는 것이다 
     -. functional oriented 프로그래밍, DRY (do not repeat yourself) 기법 
     -. code 가독성은 난이하여 진다 
   . 표현식을 compiler 가 해독하여 정규식 으로 전환 
   . 표현식 
     Interface refName1 = () -> { ; };  
     Interface refName2 = (a,b) -> { ; }; 
     Interface refName3 = (a,b) -> ; 
     Interface refName4 = a ->  ; 
     () : 추상메소드가 지니는 arguments , argument 가 1개 일때는 (a) or a 
     -> : 람다식 임을 구체적으로 compiler 에거 알려주는 표현
     {} : 추상메소드가 지니는 구현부, single statement 이면 {} 생략해도 무방
   . 아래 3곳에서 주로 사용 
     1) 익명 class : EventListener-onClick(), Runnable-run(), Comparator-compare() 
     2) forEach : for loop 
     3) collection : Consumer, Producer, Predicate, Function 
     
 */

public class MyLambda {

    
    // 객체생성 함축화 - 익명 class 
    static void LambdaObject(){ 
    	
    	EventListener listener1 = () -> { 
    		 System.out.printf("I are onClick() method \n");
    	};  
    	
    	
    	Runnable thread4 = ()-> { 
            while(true) {
                System.out.printf("I are run() method \n");
                break ;
            }
        };
        new Thread(thread4).start() ;
        
        
        Comparator<MyStudent> ca = (x,y) -> { // x,y 는 MyStudent 객체이다 
            int diff ;
            diff = x.scoreMath-y.scoreMath ;
            System.out.printf("I are comapre() method \n");
            return diff ;
        };
    	
    	
    }
    
    static void forLoopTest(){

        String c1 = "kim" ;
        String c2 = "hong" ;
        String c3 = "kwon" ;
        List<String> customer = new LinkedList<String>() ;
        customer.add(c1);
        customer.add(c2);
        customer.add(c3);

        System.out.printf("for loop test \n") ;

        // 방법1. 표준적인 for loop 사용
        for(int i=0; i<customer.size() ; i++) { 
            System.out.printf("%s ",customer.get(i)) ;
        }
        System.out.printf("\n") ;
        
        // 방법2. Iterator for loop - by compiler 
        for (String a: customer) { 
            System.out.printf("%s ",a) ;
        }
        System.out.printf("\n") ;
        
        // 방법3. 명시적 Iterator 객체사용 
        ListIterator<String> la = customer.listIterator() ;
        while(la.hasNext()) { 
            System.out.printf("%s ",la.next()) ;
        }
        System.out.printf("\n") ;

        // 방법4. forEach 람다식 (Iterable Interface) 
        customer.forEach(a -> System.out.printf("%s ",a)) ;
        System.out.printf("\n") ;

    }
    

}

