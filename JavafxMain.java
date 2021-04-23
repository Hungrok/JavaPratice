package com.my.javafx ;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavafxMain extends Application {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args) ;
	}
	
	@Override
	public void start(Stage primaryStage) {
		// 1. Classic 
		JavafxController.ActionListener ah1 = new JavafxController.ActionListener() ;
		// 2. Anonymous class
		EventHandler<ActionEvent> ah2 = new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent ev) {System.out.println(ev.getSource());;}
		};
		// 3. Lambda 
		EventHandler<ActionEvent> ah3 = x -> System.out.println(x.getSource()) ;
				
		try {
			Button btn = new Button() ;
			btn.setText("Test"); 
			btn.setOnAction(ah1); // same with ah2, ah3
			StackPane root = new StackPane() ;
			root.getChildren().add(btn) ;
			
			Scene scene = new Scene(root,300,250) ;
			primaryStage.setTitle("JavaFX App1") ;
			primaryStage.setScene(scene) ;
			primaryStage.show() ;
		}
		catch(Exception e) {
			System.out.println("Exception") ;
		}
	}
	public void start2(Stage primaryStage) { // external class 
		
		try {
			BorderPane root = new BorderPane() ;
			Scene scene = new Scene(root,400,400) ;
			// default css is modena.css 
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()) ;
			primaryStage.setTitle("JavaFX App2") ;
			primaryStage.setScene(scene) ;
			primaryStage.show() ;
		}
		catch(Exception e) {
			System.out.println("Exception") ;
		}
	}
	
	public void start3(Stage primaryStage) { // using XML 
		
		try {
			FXMLLoader loader = new FXMLLoader(
					JavafxMain.class.getResource("/SearchController.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root) ;
			primaryStage.setTitle("JavaFX App3") ;
			primaryStage.setScene(scene) ;
			primaryStage.show() ;
		}
		catch(Exception e) {
			System.out.println("Exception") ;
		}
	}
	
	
	

}
