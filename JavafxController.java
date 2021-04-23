package com.my.javafx ;

//import com.my.pratice.EventHandler;

import javafx.event.* ;
import javafx.scene.input.* ;

public class JavafxController {
	
	/* for remark EventHanlder Interface 
	package javafx.event; 
	@FunctionalInterface 
	public interface EventHandler<T extends Event> extends EventListener { 
		void handle(T event); // public abstract 
	}
	*/

	public static class ActionListener implements EventHandler<ActionEvent>{
		@Override
	    public void handle(ActionEvent ae) {
			//EventType<ActionEvent> ev = ae.getEventType() ; // ERROR why ?
			EventType ev = ae.getEventType() ;
			if(ev==ActionEvent.ACTION) {System.out.println(ae.getSource());}
			
		}
	}
	
	public static class KeyListener implements EventHandler<KeyEvent>{
		@Override
	    public void handle(KeyEvent ke) {
			EventType<KeyEvent> ev= ke.getEventType() ;
			if(ev==KeyEvent.KEY_RELEASED) {}
			
		}
	}
	public static class MouseListener implements EventHandler<MouseEvent>{
		@Override
	    public void handle(MouseEvent me) {
			//EventType<MouseEvent> ev= me.getEventType() ; // ERROR why ?
			EventType ev= me.getEventType() ;
			if(ev==MouseEvent.MOUSE_CLICKED) {}
			
			
		}
	}
	public static class DragListener implements EventHandler<DragEvent>{
		@Override
	    public void handle(DragEvent de) {
			
		}
	}
	
	
}