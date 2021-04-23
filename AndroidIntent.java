package com.my.pratice ;

//import javafx.util.Pair;

import java.net.URI;
import java.util.*;

public class AndroidIntent { //안드로이드 Intent 이해

    private Activity sourceActivity = null ;
    private String targetActivityClass = null ;
    private String action = null ;
    private URI data = null ;
    private Hashtable<String, Integer> iHash = new Hashtable<>();
    private Hashtable<String, String> sHash = new Hashtable<>() ;

    public AndroidIntent(Activity activity, String target ){ // 명시적 Intent
        this.sourceActivity = activity ;
        this.targetActivityClass = target ;
    }
    public AndroidIntent(String action ){  // 묵시적 Intent
        this.action = action ;
    }
    public AndroidIntent(String action, URI data ){ // 묵시적 Intent
        this.action = action ;
        this.data = data ;
    }

    public void setAction(String action){ this.action = action ; }
    public String getAction(){ return this.action ;}
    public void putExtraString(String name, String value){
        sHash.put(name,value) ;
    }
    public void putExtraInt(String name, int value){
        iHash.put(name,value) ;
    }
    public String getExtraString(String name){
        String extra = "unknown" ;
        Enumeration keys2 = sHash.keys() ;
        Enumeration values2 =  sHash.elements() ;
        while(keys2.hasMoreElements()){ // boolean for search
            String key = (String) keys2.nextElement() ; // To get key
            if(key==name)
                extra = sHash.get(key)  ; // To get value
        }
        return extra ;

    }
    public int getExtraInt(String name){
        int extra = 0 ;
        Enumeration keys2 = iHash.keys() ;
        Enumeration values2 =  iHash.elements() ;
        while(keys2.hasMoreElements()){ // boolean for search
            String key = (String) keys2.nextElement() ; // To get key
            if(key==name)
                extra = iHash.get(key)  ; // To get value
        }
        return extra ;

    }
}

class MsgQueue{
    List<AndroidIntent> intents = new ArrayList<>() ;

    public int size(){
        return intents.size() ;
    }

    public void sendMessage(AndroidIntent intent){
        intents.add(intent) ;
        System.out.printf("sendMessage - Add intent,size=%d \n",intents.size()) ;
    }

    public AndroidIntent recvMessage(){
        AndroidIntent intent = null;
        if (intents.size() >0) {
            intent = intents.get(0);
            intents.remove(0);
            System.out.printf("recvMessage - remove intent,size=%d \n",intents.size()) ;
        }
        return intent ;
    }
}

class MsgQueue2<T> { // 제너릭 적용
    List<T> intents = new ArrayList<>() ;

    public int size(){
        return intents.size() ;
    }

    public void sendMessage(T intent){
        intents.add(intent) ;
        System.out.printf("sendMessage - Add intent,size=%d \n",intents.size()) ;
    }

    public T recvMessage(){
        T intent = null;
        if (intents.size() >0) {
            intent = intents.get(0);
            intents.remove(0);
            System.out.printf("recvMessage - remove intent,size=%d \n",intents.size()) ;
        }
        return intent ;
    }
}


