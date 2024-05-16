/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_trail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ChoiceBox;
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
public class Adminpanel extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // VAR for databse
        String url="jdbc:mysql://localhost:3306/mini_project";
              String userName="root";
              String password="";
              
        // Declaring Items to add in the GridPane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        Label add = new Label("Add users for the system");
        Label uname= new Label("email");
        Label pname= new Label("Password");
        Label role=new Label("Role");
        ChoiceBox select_role= new ChoiceBox();
        select_role.getItems().addAll("nurse","doctor");
        TextField uname_txt = new TextField();
        PasswordField pname_txt = new PasswordField();
        Button signup = new Button("Sign Up");
        CheckBox see_pass = new CheckBox("See password");
        Button logout = new Button("Log out");
        Button dash = new Button("Dashboard");
        
       
        // Adding Items in the Gridpane
        gridPane.add(add, 0, 0);
        gridPane.add(uname, 0, 1);
        gridPane.add(uname_txt, 1, 1);
        gridPane.add(pname, 0, 2);
        gridPane.add(pname_txt, 1, 2);
        gridPane.add(role, 0, 3);
        gridPane.add(select_role, 1, 3);
        gridPane.add(signup, 1, 4);
        gridPane.add(see_pass, 2, 2);
        gridPane.add(logout, 0, 4);
        gridPane.add(dash, 2, 4);
        
       //CSS code
        add.setStyle("-fx-text-alignment: center;");
        
        
        //Logout Action
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             Project_trail login= new Project_trail();
             login.start(primaryStage);
            }
        });
        //Signup Action
        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String see=select_role.getValue().toString();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, userName, password);
                    String query="INSERT INTO `logindetails`( `username`, `password`, `type`,`isActive`) VALUES ('"+uname_txt.getText()+"','"+pname_txt.getText()+"','"+see+"','"+0+"')";
                    Statement st = con.createStatement();
                    st.executeUpdate(query);
                    JFrame h = new JFrame();
                    //Restarting GUI
                     System.out.println( "Restarting app!" );
                         primaryStage.close();
                         Platform.runLater( () -> new Adminpanel().start( new Stage() ) );
                    JOptionPane.showMessageDialog(h, "Added user");
                    uname_txt.setText("");
                    pname_txt.setText("");
                   // select_role.setValue(0);
                } catch (ClassNotFoundException ex) {
                  
                    JFrame h = new JFrame();
                    JOptionPane.showMessageDialog(h,ex);
                } catch (SQLException ex) {
                    JFrame h = new JFrame();
                    JOptionPane.showMessageDialog(h,ex);
                }
                    
            }
        });
        
        //Delete user Action
        dash.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 Dashboard_admin da = new Dashboard_admin();
                 da.start(primaryStage);
            }
        });
        
        //creating Scene
        Scene sc = new Scene(gridPane);
        // Adding Logo to the project where it can be seen in the task bar below
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("HMS.png")));
        primaryStage.setTitle("Add users");
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
