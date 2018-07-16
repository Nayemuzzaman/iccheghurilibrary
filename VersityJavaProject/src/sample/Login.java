package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;

public class Login extends Application {
    public static String name;
    public static String pass;
    public static int admin_id;

    Stage primaryStage;

    //Sheen sheenOb = new Sheen();
    public static void main(String args[]) {
        launch(args);
    }


    public void start(Stage primaryStage){

        DataAccess da = new DataAccess();
        StackPane stackpaneOb = new StackPane();
        stackpaneOb.setAlignment(Pos.CENTER);

        Scene sceneOb = new Scene(stackpaneOb, 1200, 800);
        sceneOb.getStylesheets().add(Login.class.getResource("/CSS//login.css").toExternalForm());

        Label scenetitle = new Label("Iccheghuri Library");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,50));

        PasswordField password = new PasswordField();
        TextField usertestfield = new TextField();
        Button signUp = new Button("Sign Up");
        Button login = new Button("Login");

        Label userNamelabel = new Label("USER NAME");
        userNamelabel.setTranslateX(-140);
        userNamelabel.setTranslateY(30);
        usertestfield.setMaxSize(200, 30);
        usertestfield.setPromptText("Your Email");


        Label passwordlabel = new Label("YOUR PASSWORD");
        passwordlabel.setTranslateX(-150);
        passwordlabel.setTranslateY(75);

        password.setMaxSize(200, 30);
        password.setPromptText("Enter password");

        //signUp(button) -> signup(class)
        final signup sgnp= new signup();
        signUp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               // System.out.println("Hello World!");
                sgnp.start( primaryStage );

            }
        });

        //btnSearch(button) -> Search(class)
        //Database connection
        final Search searchOb = new Search();
        login.setOnAction( new EventHandler <ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(da.userCheckLogin( usertestfield.getText(),new String( password.getText()))==true ) {
                        searchOb.start( primaryStage );
                    }else{
                       //sheenOb.pop("wrong password ");
                        Alert alert = new Alert( Alert.AlertType.WARNING);
                        alert.setTitle("Login Information");
                        alert.setHeaderText("Be Relax Man!");
                        alert.setContentText("Please take time! Remember your Email and Password");
                        alert.showAndWait();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        } );

        stackpaneOb.getChildren().add(scenetitle);
        stackpaneOb.getChildren().add(usertestfield);
        stackpaneOb.getChildren().add(userNamelabel);
        stackpaneOb.getChildren().add(password);
        stackpaneOb.getChildren().add(passwordlabel);
        stackpaneOb.getChildren().add(signUp);//signUp
        stackpaneOb.getChildren().add(login);//login


        signUp.setTranslateX(-30);//signUp
        signUp.setTranslateY(130);//signUp

        login.setTranslateX(60);//login
        login.setTranslateY(130);//login

        usertestfield.setTranslateX(10);
        usertestfield.setTranslateY(30);

        password.setTranslateX(10);
        password.setTranslateY(75);

        scenetitle.setTranslateX(30);
        scenetitle.setTranslateY(-80);


        primaryStage.setResizable( false );
        primaryStage.setScene(sceneOb);
        primaryStage.show();
    }
}
