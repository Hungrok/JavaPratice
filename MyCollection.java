package com.my.pratice ;

import java.lang.reflect.Array;
import java.util.*;

public class MyCollection {

    static void test1(){ // 기본특성
        List<MyStudent> obj1 = new LinkedList<>() ;  // Value
        Set<MyStudent> obj2 = new HashSet<>() ;  // Value
        Map<String, List<MyStudent>> obj3 = new Hashtable<>() ; // Key-Value

        MyStudent st1 = new MyStudent() ;

        obj1.add(st1) ; obj1.add(st1) ; // 중복가능 - 순서있다
        obj2.add(st1) ; obj2.add(st1) ; // 중복불가능 (replace 되어진다) - 순서없다
        obj3.put("3-1", obj1) ; obj3.put("3-1", obj1) ; // 중복불가능 (replace 되어진다) - 순서없다

        System.out.printf("Size=%d, Size=%d, Size=%d \n",obj1.size(), obj2.size(), obj3.size()) ;


    }
    static void cardShuffle(){

        // Create Four Card elements by string
        String[] ranks = {"2","3","4","5","6","7","8","9","10",
                "Jack","Queen","King","Ace"} ;
        String[] suits = {"Clubs","Diamonds","Hearts","Spades" } ;

        List<String> deck = new ArrayList<>() ;
        for (String rank:ranks) {
            for (String suit:suits)
                deck.add(rank + "of" + suit) ;
        }
        System.out.printf("%s \n\n",deck) ;

        Collections.shuffle(deck);
        System.out.printf("%s \n\n",deck) ;
    }

    static void hashTable(){
        Hashtable<String,Integer> ht = new Hashtable <String,Integer>() ;

        ht.put("Kwon",24) ;
        ht.put("Kim",32) ;
        ht.put("Kang",28) ;
        ht.put("Kwon",27) ; // Replaced

        // Key, Values 개별 load
        Set<String> keys1 = ht.keySet() ;
        Collection<Integer> values1 = ht.values();
        System.out.println(keys1) ; // Kwon, Kang, Kim
        System.out.println(values1) ;// 27,28,32

        Enumeration keys2 = ht.keys() ;
        Enumeration values2 =  ht.elements() ;
        while(keys2.hasMoreElements()){ // boolean for search
            String key = (String) keys2.nextElement() ; // To get key
            int value = ht.get(key)  ; // To get value
            System.out.println(key +value) ;
        }

    }

   
}
