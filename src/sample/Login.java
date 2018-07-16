package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.print.Book;

public class Login extends Application {
    public static String name;
    public static String pass;
    public static int admin_id;

    Stage primaryStage;

    public TextField usertestfield = new TextField();
    PasswordField password = new PasswordField();
    DataAccess da = new DataAccess();

    public static void main(String args[]) {
        launch(args);

    }

    public void start(Stage primaryStage){


        //DataAccess da = new DataAccess();
        StackPane stackpaneOb = new StackPane();
        stackpaneOb.setAlignment(Pos.CENTER);

        Scene sceneOb = new Scene(stackpaneOb, 1200, 800);
        sceneOb.getStylesheets().add(Login.class.getResource("/CSS//login.css").toExternalForm());

        Label scenetitle = new Label("Iccheghuri Library");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,50));

        //PasswordField password = new PasswordField();
        Button signUp = new Button("Sign Up");
        Button login = new Button("Login");

        Label userNamelabel = new Label("USER NAME");
        userNamelabel.setTranslateX(-140);
        userNamelabel.setTranslateY(30);
//
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
                sgnp.start( primaryStage );

            }
        });


        final Search searchOb = new Search();
        final AddBook addBookOb = new AddBook();
        login.setOnAction( new EventHandler <ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    if(da.userCheckLogin( getUsertestfield().getText(),new String( password.getText()))==true ) {
                        BookList bklist = new BookList();
                        String emailPass = usertestfield.getText();
                        bklist.setEmail(emailPass);
                        System.out.println("login datbase "+emailPass);
                        searchOb.start( primaryStage );

                    }else if(da.adminCheckLogin( getUsertestfield().getText(),new String( password.getText()))==true){
                        addBookOb.start( primaryStage );
                    }else{
                        Alert alert = new Alert( Alert.AlertType.WARNING);
                        alert.setTitle("Login Information");
                        alert.setHeaderText("Be Relax Man!");
                        alert.setContentText("Please take time! Remember your Email and Password");
                        alert.showAndWait();
                        usertestfield.clear();
                        password.clear();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        } );

        stackpaneOb.getChildren().add(scenetitle);
        stackpaneOb.getChildren().add(getUsertestfield());
        stackpaneOb.getChildren().add(userNamelabel);
        stackpaneOb.getChildren().add(password);
        stackpaneOb.getChildren().add(passwordlabel);
        stackpaneOb.getChildren().add(signUp);//signUp
        stackpaneOb.getChildren().add(login);//login


        signUp.setTranslateX(-30);//signUp
        signUp.setTranslateY(130);//signUp

        login.setTranslateX(60);//login
        login.setTranslateY(130);//login

        getUsertestfield().setTranslateX(10);
        getUsertestfield().setTranslateY(30);

        password.setTranslateX(10);
        password.setTranslateY(75);

        scenetitle.setTranslateX(30);
        scenetitle.setTranslateY(-80);


        primaryStage.setResizable( false );
        primaryStage.setScene(sceneOb);
        primaryStage.show();
    }
//    final public Search searchOb = new Search();
//
//    public void loginVerify(){
//
//                try {
//                    if(da.userCheckLogin( getUsertestfield().getText(),new String( password.getText()))==true ) {
//                        //da.loginData(usertestfield.getText());
//                        String email = getUsertestfield().getText();
//                        searchOb.start( primaryStage );
//
//                    }else{
//                        Alert alert = new Alert( Alert.AlertType.WARNING);
//                        alert.setTitle("Login Information");
//                        alert.setHeaderText("Be Relax Man!");
//                        alert.setContentText("Please take time! Remember your Email and Password");
//                        alert.showAndWait();
//                        usertestfield.clear();
//                        password.clear();
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//    }

    public TextField getUsertestfield() {
        return usertestfield;
    }

//    public void setUsertestfield(TextField usertestfield) {
//        this.usertestfield = usertestfield;
//    }
}
