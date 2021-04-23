package com.my.pratice ;


import java.lang.reflect.InvocationTargetException;

public class AndroidMainActivity extends Activity{// 안드로이드 Activity 개념이해 목적
    @Override
    void onCreate(){
        System.out.printf("Activity-onCreate \n") ;
    }
    @Override
    void onStart(){
        System.out.printf("Activity-onStart \n") ;
    }
    @Override
    void onResume(){
        System.out.printf("Activity-onResume \n") ;
    }
    @Override
    void onPause(){
        System.out.printf("Activity-onPause \n") ;
    }
    @Override
    void onStop(){
        System.out.printf("Activity-onStop \n") ;
    }
    @Override
    void onRestart(){
        System.out.printf("Activity-onRestart \n") ;
    }
    @Override
    void onDestroy(){
        System.out.printf("Activity-onDestroy \n") ;
    }
}


abstract class Context{
    abstract void onCreate()  ;
    abstract void onStart()  ;
    abstract void onResume() ;
    abstract void onPause() ;
    abstract void onStop() ;
    abstract void onRestart() ;
    abstract void onDestroy() ;

}

abstract class Activity extends Context{
    static final int ACTIVITY_INIT = 0 ;
    static final int ACTIVITY_CREATED = 1;
    static final int ACTIVITY_STARTED = 2 ;
    static final int ACTIVITY_RESUMED = 3 ;
    static final int ACTIVITY_RESTARTED = 4 ;
    static final int ACTIVITY_PAUSED = 5 ;
    static final int ACTIVITY_STOPPED = 6 ;

}


class MainThread extends Thread {

    int activityState = Activity.ACTIVITY_INIT ;
    Context activity = null ;
    String activityClass = null ;

    MainThread(String startActivity){
        activityClass = startActivity ;
    }

    @Override
    public void run() {
        activity = (Activity) createObject(activityClass) ;
        while(true){
            //doStateMachine();
            //kernelWait(2000);
            activity.onCreate();
            activity.onStart();
            activity.onResume();
            break ;
        }
    }
    private Object createObject(String className){
        Object object = null;
        try {
            Class cls = Class.forName(className);
            object = cls.getConstructor().newInstance() ;
        }
        catch(InstantiationException | NoSuchMethodException|
                IllegalAccessException | ClassNotFoundException | InvocationTargetException e )
        { ; }

        return object ;
    }
    private void doStateMachine(){
        switch(activityState){
            case Activity.ACTIVITY_INIT :
                activity.onCreate();
                activityState = Activity.ACTIVITY_CREATED ;
                break ;
            case Activity.ACTIVITY_CREATED :
                activity.onStart();
                activityState = Activity.ACTIVITY_STARTED ;
                break ;
            case Activity.ACTIVITY_STARTED :
                activity.onResume();
                activityState = Activity.ACTIVITY_RESUMED ;
                break ;
            case Activity.ACTIVITY_RESUMED :
                activity.onPause();
                activityState = Activity.ACTIVITY_PAUSED ;
                break ;
            case Activity.ACTIVITY_PAUSED :
                activity.onStop();
                activityState = Activity.ACTIVITY_STOPPED ;
                break ;
            case Activity.ACTIVITY_STOPPED :
                activity.onRestart();
                activityState = Activity.ACTIVITY_RESTARTED ;
                break ;
            case Activity.ACTIVITY_RESTARTED :
                activity.onStart();
                activityState = Activity.ACTIVITY_STARTED ;
                break ;
        }
    }
    private synchronized void kernelWait(int ms){
        try{
            wait(ms) ;
        }
        catch (InterruptedException ev){

        }
    }
}


