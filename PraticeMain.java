package com.my.pratice;


// Main entry 
public class PraticeMain {

	public static void main(String[] args) {
		
		System.out.printf("Welcome") ;

	}
	
	private static void basicTest() {
	
		MyBasic.testWhat();
		
	}
	
	
	private static void classTest() {
	
		MyClassTest.classTest();
		MyClassTest.nestedClassTest();
		MyClassTest.eventTest();
		MyClassTest.animalTest();
	}
	
	private static void threadTest() {
		MyThread.startTest(); 
		//MyThread.syncTest(); 
		//MyThread.msgqTest(); 
		//MyThread.androidActivity();
	}
	
	private static void stdioTest() {
		
		MyStdIO.printTest1();
		
	}
	
	
	private static void networkTest() {
	
		MyNetwork.networkTestMain(5) ;
	}
	
	private static void fileTest() {
		
		MyFile.fileTestMain(0) ;
	}

	
	private static void timeTimerTest() {
	
		MyTimer.timerTest1() ;
        //MyTime.timeTest1() ;
	}
	
	private static void genericTest() {
		
		GenericTest.carTest();
		MyLambda.LambdaObject();
		MyLambda.forLoopTest();
	}
	
	private static void collectionTest() {
		
		MyCollection.test1();
		//MyCollection.cardShuffle();
		//MyCollection.hashTable();
		
	}
	
	private static void streamTest() {
		
		 MyStream.streamTest1();
		 MyStream.streamTest2();
		 MyStream.streamTest3();
		 MyStream.streamTest4();
		 MyStream.streamTest5();
		 MyStream.streamTest6();
		 MyStream.streamTest7();
		 MyStream.streamTest8();
	    
	}
	
	private static void desginPatternTest() {
		
		DesignPattern.observerTest() ;
		
	}
	
	private static void algorithmTest() {
		
		//Algorithm.howManyDigit(16759);
		//Algorithm.verifyOfDay(987, 3, 8);
		Algorithm.lottoWin(1, 2, 3, 4, 5, 6);
		
	}
	

}
