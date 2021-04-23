package com.my.javafx ;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.* ;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.* ;
import javafx.stage.Stage;




public class JavafxStudent extends Application {
	
	
	private static BorderPane rootpane = null ;
	private static TextField tfg1,tfg2,tfg3,tfg4,tfg5,tfg6 = null ;
	private static HBox removehbox = null ;
	private static VBox removevbox = null ;
	private static ChoiceBox cbg1,cbg2,cbg3 = null ;
	private static TextArea tag1 = null ;
	
	public static void main(String[] args) {
        Application.launch(args);
    }
    
	 
    @Override
    public void start(Stage stage) { // Main VIEW
 
        // Create MenuBar
        MenuBar menuBar = new MenuBar();
        
        // Create menus
        Menu stdMenu = new Menu("�л�����");
        Menu scMenu = new Menu("��������");
        Menu inqMenu = new Menu("������ȸ");
        
        // Create MenuItems
        MenuItem stdaddItem = new MenuItem("���");
        MenuItem stdmodItem = new MenuItem("����");
        MenuItem stdinqItem = new MenuItem("��ȸ");
       
        MenuItem scaddItem = new MenuItem("�������");
        MenuItem scmodItem = new MenuItem("��������");
        
        MenuItem scinqItem = new MenuItem("��ȸ");
        
        // Add menuItems to the Menus
        stdMenu.getItems().addAll(stdaddItem, stdmodItem, stdinqItem);
        scMenu.getItems().addAll(scaddItem, scmodItem);
        inqMenu.getItems().addAll(scinqItem);
        
        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(stdMenu, scMenu, inqMenu);
        
        BorderPane root = new BorderPane();
        rootpane = root ;
        root.setTop(menuBar);
        Scene scene = new Scene(root, 350, 200);
        
        stage.setTitle("JavaFX Student");
        stage.setScene(scene);
        stage.show();
        
        // Event handler 
        stdaddItem.setOnAction(x -> stdAddView(x)); 
        scaddItem.setOnAction(x -> scoreAddView(x)); 
        scinqItem.setOnAction(x -> scoreInqView(x)); 
      
        
    }
    
    
    public void stdAddView(ActionEvent ev) {
    	
    
    	FlowPane fp1 = new FlowPane() ;
    	Label lb1 = new Label("�ֹι�ȣ��") ;
    	TextField tf1 = new TextField() ;
    	fp1.setAlignment(Pos.CENTER);
    	fp1.getChildren().addAll(lb1,tf1) ;
    	
    	FlowPane fp2 = new FlowPane() ;
    	Label lb2 = new Label("�ֹι�ȣ��") ;
    	TextField tf2 = new TextField() ;
    	fp2.setAlignment(Pos.CENTER);
    	fp2.getChildren().addAll(lb2,tf2) ;
    	
    	FlowPane fp3 = new FlowPane() ;
    	Label lb3 = new Label("�̸�") ;
    	TextField tf3 = new TextField() ;
    	fp3.setAlignment(Pos.CENTER);
    	fp3.getChildren().addAll(lb3,tf3) ;
    	
    	FlowPane fp4 = new FlowPane() ;
    	Label lb4 = new Label("�г�-��") ;
    	TextField tf4 = new TextField() ;
    	fp4.setAlignment(Pos.CENTER);
    	fp4.getChildren().addAll(lb4,tf4) ;
    	
    	FlowPane fp5 = new FlowPane() ;
    	Label lb5 = new Label("Male/Female") ;
    	TextField tf5 = new TextField() ;
    	fp5.setAlignment(Pos.CENTER);
    	fp5.getChildren().addAll(lb5,tf5) ;
    
    	VBox vb = new VBox() ;
    	vb.getChildren().addAll(fp1,fp2,fp3,fp4,fp5) ;
    	//rootpane.getChildren().add(vb) ; // cause duplicate children add 
    	rootpane.setCenter(vb);
    	removevbox = vb ;
   
    	HBox hb = new HBox() ;
    	Button b1 = new Button("Commit") ;
    	Button b2 = new Button("Clear") ;
    	Button b3 = new Button("FileSave") ;
    	Button b4 = new Button("Exit") ;
    	Button b5 = new Button("SampleDB") ;
    	hb.getChildren().addAll(b1,b2,b3,b4,b5) ;
    	rootpane.setBottom(hb);
    	removehbox = hb ;
    	
    	// event handler 
    	{tfg1 = tf1 ; tfg2 = tf2 ; tfg3 = tf3 ; tfg4 = tf4 ; tfg5 = tf5 ;}
    	b1.setOnAction(x -> stdAddCommit(x));;
    	b2.setOnAction(x -> stdAddClear(x));;
    	b3.setOnAction(x -> stdAddFileSave(x));;
    	b4.setOnAction(x -> stdAddExit(x));;
    	b5.setOnAction(x -> stdAddSampleDB(x));;
    }
    
    public void stdAddCommit(ActionEvent ev) {
    	
    	String s1 = tfg1.getText();
    	String s2 = tfg2.getText();
    	String s3 = tfg3.getText();
    	String s4 = tfg4.getText();
    	String s5 = tfg5.getText();
    	
    	// KHR comment, StudentMgrs source file is missed 
    	//StudentMgrs.addStdMember(s1,s2,s3,s4,s5) ;
    	
    }
    public void stdAddClear(ActionEvent ev) {
    	
    	tfg1.clear();
    	tfg2.clear();
    	tfg3.clear();
    	tfg4.clear();
    	tfg5.clear();
    	
    }
    public void stdAddFileSave(ActionEvent ev) {
    	
    	//StudentMgrs.saveStdDatabase() ;
    	
    }
    public void stdAddExit(ActionEvent ev) {
    	
    	rootpane.getChildren().remove(removevbox) ;
    	rootpane.getChildren().remove(removehbox) ;
    }
    public void stdAddSampleDB(ActionEvent ev) {
    	
    	//StudentMgrs.studentFill1() ;
    	//StudentMgrs.studentFill2() ;
    	//StudentMgrs.studentFill3() ;
    	//StudentMgrs.studentFill4() ;
    	//StudentMgrs.saveStdDatabase() ;
    	
    }
    
    public void scoreAddView(ActionEvent ev) {
    	
        
    	FlowPane fp1 = new FlowPane() ;
    	Label lb1 = new Label("�̸�") ;
    	TextField tf1 = new TextField() ;
    	fp1.setAlignment(Pos.CENTER);
    	fp1.getChildren().addAll(lb1,tf1) ;
    	
    	FlowPane fp2 = new FlowPane() ;
    	Label lb2 = new Label("����") ;
    	TextField tf2 = new TextField() ;
    	fp2.setAlignment(Pos.CENTER);
    	fp2.getChildren().addAll(lb2,tf2) ;
    	
    	FlowPane fp3 = new FlowPane() ;
    	Label lb3 = new Label("����") ;
    	TextField tf3 = new TextField() ;
    	fp3.setAlignment(Pos.CENTER);
    	fp3.getChildren().addAll(lb3,tf3) ;
    	
    	FlowPane fp4 = new FlowPane() ;
    	Label lb4 = new Label("����") ;
    	TextField tf4 = new TextField() ;
    	fp4.setAlignment(Pos.CENTER);
    	fp4.getChildren().addAll(lb4,tf4) ;
    	
    	FlowPane fp5 = new FlowPane() ;
    	Label lb5 = new Label("����") ;
    	TextField tf5 = new TextField() ;
    	fp5.setAlignment(Pos.CENTER);
    	fp5.getChildren().addAll(lb5,tf5) ;
    	
    	VBox vb = new VBox() ;
    	vb.getChildren().addAll(fp1,fp2,fp3,fp4,fp5) ;
    	//rootpane.getChildren().add(vb) ; // cause duplicate children add 
    	rootpane.setCenter(vb);
    	removevbox = vb ;
   
    	HBox hb = new HBox() ;
    	Button b1 = new Button("Commit") ;
    	Button b2 = new Button("Clear") ;
    	Button b3 = new Button("FileSave") ;
    	Button b4 = new Button("Exit") ;
    	hb.getChildren().addAll(b1,b2,b3,b4) ;
    	rootpane.setBottom(hb);
    	removehbox = hb ;
    	
    	// event handler 
    	{tfg1 = tf1 ; tfg2 = tf2 ; tfg3 = tf3 ; tfg4 = tf4 ; tfg5 = tf5 ; }
    	b1.setOnAction(x -> scoreAddCommit(x));;
    	b2.setOnAction(x -> scoreAddClear(x));;
    	b3.setOnAction(x -> scoreAddFileSave(x));;
    	b4.setOnAction(x -> scoreAddExit(x));;
    }
    
    public void scoreAddCommit(ActionEvent ev) {
    	
    	String s1 = tfg1.getText();
    	Integer i2=0,i3=0,i4=0,i5 = 0 ;
    	i2 = i2.parseInt(tfg2.getText()) ;
    	i3 = i3.parseInt(tfg3.getText()) ;
    	i4 = i4.parseInt(tfg4.getText()) ;
    	i5 = i5.parseInt(tfg5.getText()) ;
    	
    	
    	//StudentMgrs.addStdScore(s1,2018,12,21,i2,i3,i4,i5) ;
    	
    }
    public void scoreAddClear(ActionEvent ev) {
    	
    	tfg1.clear();
    	tfg2.clear();
    	tfg3.clear();
    	tfg4.clear();
    	tfg5.clear();
    	
    }
    public void scoreAddFileSave(ActionEvent ev) {
    	
    	//StudentMgrs.saveStdDatabase() ;
    	
    }
    public void scoreAddExit(ActionEvent ev) {
    	
    	rootpane.getChildren().remove(removevbox) ;
    	rootpane.getChildren().remove(removehbox) ;
    }
    
    public void scoreInqView(ActionEvent ev) {
    	
        
    	ChoiceBox cb1 = new ChoiceBox() ;
    	cb1.getItems().add("��ü") ;
    	cb1.getItems().add("3�г�") ;
    	cb1.getItems().add("2�г�") ;
    	cb1.getItems().add("1�г�") ;
    	cb1.getItems().add("�ݺ�") ;
    	
    	
    	ChoiceBox cb2 = new ChoiceBox() ;
    	cb2.getItems().add("1-1") ;
    	cb2.getItems().add("1-2") ;
    	cb2.getItems().add("1-3") ;
    	cb2.getItems().add("1-4") ;
    	cb2.getItems().add("2-1") ;
    	cb2.getItems().add("2-2") ;
    	cb2.getItems().add("2-3") ;
    	cb2.getItems().add("2-4") ;
    	cb2.getItems().add("3-1") ;
    	cb2.getItems().add("3-2") ;
    	cb2.getItems().add("3-3") ;
    	cb2.getItems().add("3-4") ;
    	
    	ChoiceBox cb3 = new ChoiceBox() ;
    	cb3.getItems().add("���") ;
    	cb3.getItems().add("����") ;
    	cb3.getItems().add("����") ;
    	cb3.getItems().add("����") ;
    	cb3.getItems().add("����") ;
    	
    	VBox vb = new VBox() ;
    	vb.getChildren().addAll(cb1,cb2,cb3) ;
    	cbg1 = cb1 ;
    	cbg2 = cb2 ;
    	cbg3 = cb3 ;
    	//rootpane.getChildren().add(vb) ; // cause duplicate children add 
    	rootpane.setCenter(vb);
    	removevbox = vb ;
   
    	HBox hb = new HBox() ;
    	Button b1 = new Button("��ȸ") ;
    	Button b2 = new Button("Clear") ;
    	Button b3 = new Button("Exit") ;
    	hb.getChildren().addAll(b1,b2,b3) ;
    	rootpane.setBottom(hb);
    	removehbox = hb ;
    	
    	b1.setOnAction(x -> scoreInqQuery(x));;
    	b2.setOnAction(x -> scoreInqClear(x));;
    	b3.setOnAction(x -> scoreInqExit(x));;
    }
    
    
    public void scoreInqQuery(ActionEvent ev) {
    		
    	String s1 = (String) cbg1.getValue();
    	String s2 = (String) cbg2.getValue();
    	String s3 = (String) cbg3.getValue();
    	//System.out.printf("s1=%s, s2=%s, s3=%s",s1,s2,s3);
    	
    
    	rootpane.getChildren().remove(removevbox) ;
    	//rootpane.getChildren().remove(removehbox) ;
    	
    	TextArea ta = new TextArea() ;
    	rootpane.setCenter(ta);
    	tag1 = ta ;
    	
    
    	//StudentMgrs.setCustomePrtStream(ta) ;
    	//StudentMgrs.inqStudentAll(s1,s2,s3) ;
    	
    }
    
    public void scoreInqClear(ActionEvent ev) {
    	rootpane.getChildren().remove(tag1) ;
    }
    
  
    public void scoreInqExit(ActionEvent ev) {
    	
    	//StudentMgrs.releaseCustomePrtStream() ;
    	
    }
    
}