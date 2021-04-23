package com.my.pratice ;


// Button UI 객체가 이벤트 핸들링을 하기위하여 지니고 있는 기본적인 모습 입니다
public class Button implements EventHandler  {
    EventListener listener1 ;

    @Override
    public void setOnClickListener(EventListener listener){ // 이벤트 핸들러 등록
        listener1 = listener ;
    }
    public void postEvent(){ // 실제의 이벤트 핸들러 (시스템이 호출한다)
        listener1.onClick() ; // 이벤트 리스너 (최종 이벤트 처리자) 호출
    }

}

interface EventHandler{

    public void setOnClickListener(EventListener listener) ; // abstract

}

interface EventListener{

    public void onClick() ; // abstract
}

class EventListenerClass implements EventListener{
	
	//public EventListenerClass() {}
	
	@Override
	public void onClick() {
		
	}
	
}

