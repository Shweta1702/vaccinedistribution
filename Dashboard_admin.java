/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_trail;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author shweta
 */
public class Dashboard_admin extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       Button add_user = new Button("Add a new user");
       Button del_user = new Button("Delete a user");
       Button see_del_user = new Button("Activate a Deleted User");
       Button logout = new Button("Logout");
       
        GridPane gridpane = new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setPadding(new Insets(10, 10, 10, 10));
        gridpane.setMinSize(350, 350);
        
        gridpane.add(add_user, 0, 0);
        gridpane.add(del_user, 0, 1);
        gridpane.add(see_del_user, 0, 2);
        gridpane.add(logout, 0, 3);
        add_user.setMaxWidth(200);
        see_del_user.setMaxWidth(200);
        del_user.setMaxWidth(200);
        logout.setMaxWidth(200);
        //Button Actions
        add_user.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              
             Adminpanel ap = new Adminpanel();
             ap.start(primaryStage);
               
           }
       });
        del_user.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              
             Admin_del ap = new Admin_del();
             ap.start(primaryStage);
               
           }
       });
        
        see_del_user.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              
             Admin_del_users ap = new Admin_del_users();
             ap.start(primaryStage);
               
           }
       });
        logout.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              
             Project_trail ap = new Project_trail();
             ap.start(primaryStage);
               
           }
       });
        
        Scene sc = new Scene(gridpane);
        primaryStage.setTitle("Admin dashboard");
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
