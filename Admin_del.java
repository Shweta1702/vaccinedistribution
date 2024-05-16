/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_trail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author kirtan
 */
public class Admin_del extends Application {
    String url="jdbc:mysql://localhost:3306/mini_project";
              String userName="root";
              String password="";
              ComboBox uname_txt = new ComboBox();
              
    @Override
    public void start(Stage primaryStage) {
        
        //setting up grid
        GridPane gridpane = new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setPadding(new Insets(10, 10, 10, 10));
        gridpane.setMinSize(350, 350);
        //Making of objects
        Label del = new Label("Delete user:");
        Label uname= new Label("Email");
        Label pname = new Label("Password");
        Label role = new Label("Role");
        Button delete = new Button("Delete");
        TextField uname2 = new TextField();
        
        PasswordField pname_txt= new PasswordField();
        TextField role_txt= new TextField();
        CheckBox select_role = new CheckBox("See Password");
        Button find = new Button("Find");
        Label title = new Label("Search for user");
        Button dash = new Button("Dashboard");
        
        
       
        //Adding items to gridpane
        gridpane.add(del, 0, 0);
        gridpane.add(uname, 0, 1);
        gridpane.add(uname2, 1, 1);
        gridpane.add(find, 2, 1);
        gridpane.add(uname_txt, 3, 1);
        gridpane.add(pname, 0, 2);
        gridpane.add(pname_txt, 1, 2);
        gridpane.add(role, 0, 3);
        gridpane.add(role_txt, 1, 3);
        gridpane.add(delete, 1, 4);
        gridpane.add(select_role, 2, 2);
        gridpane.add(title, 3, 0);
        gridpane.add(dash, 3, 4);
        // calling method that populates the comboBox
        Item();
        //Find button action
        find.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name =uname_txt.getSelectionModel().getSelectedItem().toString();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, userName, password);
                    String query = "SELECT * FROM `logindetails` WHERE `username`='"+name+"'";
                    PreparedStatement pstm = con.prepareStatement(query);
                    ResultSet rs = pstm.executeQuery();
                    while (rs.next()) {                        
                        uname2.setText(rs.getString("username"));
                        pname_txt.setText(rs.getString("password"));
                        role_txt.setText(rs.getString("type"));
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Admin_del.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_del.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }
        });
        
        //Delete Button Action
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                     Connection con = DriverManager.getConnection(url, userName, password);
                     String query = "UPDATE `logindetails` SET `isActive`=1 WHERE `username`='"+uname2.getText()+"'";
                     PreparedStatement pstm = con.prepareStatement(query);
                     pstm.executeUpdate();
                     // This Restarts the app so updated Data is shown
                     System.out.println( "Restarting app!" );
                         primaryStage.close();
                         Platform.runLater( () -> new Admin_del().start( new Stage() ) );
                     JFrame h = new JFrame("Delete");
                     JOptionPane.showMessageDialog(h, "Deleted user "+uname2.getText());
                             
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Admin_del.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_del.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
            }
        });
        
        dash.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 Dashboard_admin da = new Dashboard_admin();
                 da.start(primaryStage);
            }
        });
         
        
        Scene sc = new Scene(gridpane);
       primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("HMS.png")));
        primaryStage.setTitle("Delete Users");
        primaryStage.setScene(sc);
        primaryStage.show();

        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public void Item(){
    
             try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, userName, password);
                    String query="SELECT * FROM logindetails WHERE isActive=0";
                    PreparedStatement pstm = con.prepareStatement(query);
                    ResultSet rs = pstm.executeQuery();
                    while (rs.next()) {                        
                        uname_txt.getItems().addAll(rs.getString("username"));
                       
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Admin_del.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_del.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void additem(){
        
    
    }
    
}
