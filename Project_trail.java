/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_trail;

import com.sun.corba.se.impl.oa.poa.Policies;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author kirtan
 */
public class Project_trail extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      GridPane gridPane = new GridPane();
      gridPane.setMinSize(600,400);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        TextField s = new TextField();
        Label lb1 = new Label("email");
        Label lb2 = new Label("Password");
        PasswordField p = new PasswordField();
        Button btn1 = new Button("Login");
        Label message = new Label();
        p.setAlignment(Pos.TOP_LEFT);
        gridPane.add(lb1, 0, 0);
        gridPane.add(s, 1, 0);
        gridPane.add(lb2, 0, 1);
        gridPane.add(p, 1, 1);
        gridPane.add(message, 2, 1);
        gridPane.add(btn1, 1, 2);
        
       btn1.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              String url="jdbc:mysql://localhost:3306/mini_project";
              String userName="root";
              String password="";
              String UN= s.getText();
              String PN= p.getText();
              try {
                  //              if (!p.getText().equals("kirtan")) {
//                  message.setText("Your password is incorrect");
//                  message.setTextFill(Color.rgb(210, 39, 30));
//              }else{
//                  message.setText("Your password is correct");
//                  message.setTextFill(Color.rgb(21, 117, 84));
//              }
            
                   

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, userName, password);
                    String query="SELECT * FROM logindetails WHERE username=? AND password=? AND isActive=0";
                    PreparedStatement pstm= con.prepareStatement(query);
                    pstm.setString(1, UN);
                    pstm.setString(2, PN);
                     
                    System.out.println(pstm);
                    ResultSet rs = pstm.executeQuery();
                    
                    if (rs.next()) {
                        String userrole = rs.getString("type");

                        if (userrole.equals("admin")) {
                            Dashboard_admin ad=new Dashboard_admin();
                            ad.start(primaryStage);
                        }
                        if (userrole.equals("doctor")) {
                            Doctorpanel dd = new Doctorpanel();
                            dd.start(primaryStage);
                        }
                        
                        if (userrole.equals("nurse")) {
                            Nursepanel np = new Nursepanel();
                            np.start(primaryStage);
                        }
                        
                  }else{
                        JFrame h = new JFrame();
                        JOptionPane.showMessageDialog(h, "Your password or username does not exist"+"\n"+"Contact Admin");
                    }
                    
                    
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(Project_trail.class.getName()).log(Level.SEVERE, null, ex);
              } catch (SQLException ex) {
                  Logger.getLogger(Project_trail.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      });
       
        Scene sc = new Scene(gridPane);
         primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("HMS.png")));
        primaryStage.setTitle("HMS system");
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
