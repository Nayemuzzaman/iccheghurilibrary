package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.*;
import java.text.NumberFormat;

import static javafx.application.Application.launch;


public class signup
{
    DataAccess da = new DataAccess();

    public static void main(String[] args){
        launch( args );
    }
    //@Override
    public void start(Stage primaryStage)
    {

        StackPane stackpaneOb = new StackPane();
        stackpaneOb.setAlignment(Pos.CENTER_LEFT);

        Scene sceneOb = new Scene(stackpaneOb, 1200, 800);
        sceneOb.getStylesheets().add(Login.class.getResource("/CSS//SignUp.css").toExternalForm());


        GridPane gdtop=new GridPane();
        gdtop.setPadding(new Insets(60,60,60,60));
        gdtop.setVgap(30);
        gdtop.setHgap(40);

        Label title=new Label("SIGN UP");
        title.setFont(Font.font("Arial",FontWeight.BOLD, 40));
        //GridPane.setHalignment(title, HPos.CENTER);
        gdtop.add(title, 1, 0);
        //NAME LABEL
        Label name=new Label("NAME");
        name.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        gdtop.add(name, 0, 1);
        //NAME TEXT BOX
        TextField namet=new TextField();
        namet.setPromptText("Name");
        gdtop.add(namet, 1, 1);

        //EMAIL ADDRESS LABEL
        Label email=new Label("EMAIL");
        email.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        gdtop.add(email, 0, 2);
        //EMAIL ADDRESS TEXT BOX
        TextField emailt=new TextField();
        emailt.setPromptText("Email");
        gdtop.add(emailt, 1, 2);

        //ADDRESS LABEL
        Label cemail=new Label("ADDRESS");
        cemail.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        gdtop.add(cemail, 0, 3);
        //  ADDRESS TEXT BOX
        TextField addresst=new TextField();
        addresst.setPromptText("Address");
        gdtop.add(addresst, 1, 3);

        //MOBILE NUMBER LABEL
        Label mobnol=new Label("MOBILE NUMBER");
        mobnol.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        gdtop.add(mobnol, 0, 4);
        //MOBILE NUMBER TEXT FIELD
        TextField mobnot=new TextField();
        mobnot.setPromptText("Phone/Mobile No");
        gdtop.add(mobnot, 1, 4);


        //PASSWORD LABEL
        Label pwd=new Label("PASSWORD");
        pwd.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        gdtop.add(pwd, 0, 5);
        //PASSWORD TEXTFIELD
        PasswordField pwdt=new PasswordField();
        pwdt.setPromptText("Password");
        gdtop.add(pwdt, 1, 5);


        //CONFIRM PASSWORD LABEL
        Label cpwd=new Label("CONFIRM PASSWORD");
        cpwd.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        gdtop.add(cpwd, 0, 6);
        //CONFIRM PASSWORD TEXTFIELD
        PasswordField pwdc=new PasswordField();
        pwdc.setPromptText("Confirm Password");
        gdtop.add(pwdc, 1, 6);


        //SUBMIT BUTTON HBOX
        HBox subh=new HBox();
        // subh.setPadding(new Insets(50,50,50,50));
        // subh.setSpacing(20);
        subh.setAlignment(Pos.CENTER_RIGHT);

        //SUBMIT BUTTON
        HBox canch=new HBox();

        // canch.setPadding(new Insets(10,10,10,10));
        //canch.setSpacing(20);
        canch.setAlignment(Pos.CENTER_LEFT);
        Button submit=new Button("Submit");
        final Login lgn = new Login();
        submit.setOnAction( new EventHandler <ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (namet.getText().toString()==null||emailt.getText().toString()==null||mobnot.getText().toString()==null||  pwdt.getText().toString()==null || pwdc.getText().toString()==null ){
                    //JOptionPane.showMessageDialog( null,"Please check all Box" );
                    Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
                    alert.setTitle( "Registration Form" );
                    alert.setContentText("Please check all Box");
                }
                if (!new String( pwdt.getText() ).equals( new String( pwdc.getText() ) )){
                    JOptionPane.showMessageDialog( null,"Password doesn't match" );
                }
                else{
                    da.userReg(namet.getText(),emailt.getText(),addresst.getText(),mobnot.getText(),pwdt.getText().toString(), pwdc.getText().toString());
                    //JOptionPane.showMessageDialog( null,"Success" );
                    Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Registration Form Alert");
                    alert.setHeaderText("Thank you for your Registration");
                    alert.setContentText("Have a great day");

                    alert.showAndWait();
                    lgn.start( primaryStage );
                }
            }
        } );
       // submit.setCursor(Cursor.HAND);
        submit.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        //submit.setStyle("-fx-background-color: #3FF4CB;");
        // GridPane.setHalignment(submit, HPos.CENTER);
        subh.getChildren().addAll(submit);
        gdtop.add(subh, 0, 7);

        //CANCEL BUTTON
        Button cancel=new Button("Cancel");
        cancel.setCursor(Cursor.HAND);
        cancel.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        //cancel.setStyle("-fx-background-color: #3FF4CB;");
        canch.getChildren().addAll(cancel);
        gdtop.add(canch,1,7);
        gdtop.setAlignment( Pos.CENTER );
        stackpaneOb.getChildren().add(gdtop);

        //Cancel to main option
        final Login loginOb= new Login();
        cancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // System.out.println("Hello World!");
                loginOb.start( primaryStage );

            }
        });

        primaryStage.setTitle("ACCOUNT SIGN UP");
        primaryStage.setScene(sceneOb);
        primaryStage.show();
    }
}