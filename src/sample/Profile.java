package sample;

import com.sun.deploy.util.BufferUtil;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;



public class Profile extends Application {

    public static void main(String[] args){
        launch( args );
    }
    GridPane gdtop=new GridPane();

    public void start (Stage primaryStage) throws Exception{
        StackPane stackpaneOb = new StackPane();
        stackpaneOb.setAlignment( Pos.CENTER);

        Scene sceneOb = new Scene( stackpaneOb);
        primaryStage.setWidth( 1200 );
        primaryStage.setHeight( 800 );
        sceneOb.getStylesheets().add(Login.class.getResource("/CSS//login.css").toExternalForm());

        //Title
        Label labelTitle = new Label(  );
        labelTitle.setText( " My Profile Criteria" );

        stackpaneOb.getChildren().add( labelTitle );

        labelTitle.setTranslateX( -150 );
        labelTitle.setTranslateY( -310 );
        labelTitle.setMaxSize( 280,60 );
        labelTitle.setFont( Font.font ("Verdana", 20) );

        //Beside Bar Option
        Button btnSearch = new Button(  );
        Button btnProfile = new Button(  );
        Button btnListBook = new Button(  );
        Button btnBorrowHistory = new Button(  );
        Button btnLogout = new Button(  );

        btnSearch.setText( "Search" );
        btnProfile.setText( "My Profile" );
        btnListBook.setText( "Book List" );
        btnBorrowHistory.setText( "Borrow History" );
        btnLogout.setText( "Logout" );

        stackpaneOb.getChildren().addAll( btnSearch,btnProfile,btnListBook,btnBorrowHistory ,btnLogout);


        //Profile button position
        btnSearch.setTranslateX(  -480);
        btnSearch.setTranslateY(  -90);
        btnSearch.setMaxSize( 200,50 );

        //Profile button position
        btnProfile.setTranslateX(  -480);
        btnProfile.setTranslateY(  -30);
        btnProfile.setMaxSize( 200,50 );

        //Book button Position
        btnListBook.setTranslateX(  -480);
        btnListBook.setTranslateY(  30);
        btnListBook.setMaxSize( 200,50 );

        //History Button Position
        btnBorrowHistory.setTranslateX(  -480);
        btnBorrowHistory.setTranslateY(  90);
        btnBorrowHistory.setMaxSize( 200,50 );

        //Logout Button Position
        btnLogout.setTranslateX(  -480);
        btnLogout.setTranslateY(  150);
        btnLogout.setMaxSize( 200,50 );

        //label in front of window
        Label name=new Label("Name");
        name.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        name.setTranslateX(-150);
        name.setTranslateY(-90);

        Label email=new Label("Email");
        email.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        email.setTranslateX(-150);
        email.setTranslateY(-40);

        Label address=new Label("Address");
        address.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        address.setTranslateX(-150);
        address.setTranslateY(10);

        Label mobile=new Label("Phone");
        mobile.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        mobile.setTranslateX(-150);
        mobile.setTranslateY(60);

        Label password=new Label("Password");
        password.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        password.setTranslateX(-160);
        password.setTranslateY(110);

        stackpaneOb.getChildren().addAll(name,email,address,mobile,password);

        //text show
        Text txtName = new Text();
        txtName.setText("This name");
        txtName.setTranslateX(-50);
        txtName.setTranslateY(-90);

        Text txtEmail = new Text();
        txtEmail.setText("This name");
        txtEmail.setTranslateX(-50);
        txtEmail.setTranslateY(-40);

        Text txtAddress = new Text();
        txtAddress.setText("This name");
        txtAddress.setTranslateX(-50);
        txtAddress.setTranslateY(10);

        Text txtPhone = new Text();
        txtPhone.setText("This name");
        txtPhone.setTranslateX(-50);
        txtPhone.setTranslateY(60);

        Text txtPassword = new Text();
        txtPassword.setText("This name");
        txtPassword.setTranslateX(-50);
        txtPassword.setTranslateY(110);

        stackpaneOb.getChildren().addAll(txtName,txtEmail,txtAddress,txtPhone,txtPassword);

        //btnSearch(button) -> Search(class)
        final Search searchOb = new Search();
        btnSearch.setOnAction( new EventHandler <ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    searchOb.start( primaryStage );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } );
        //btnListBook(button) -> BookList(class)
        final BookList bookListOb = new BookList();
        btnListBook.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    bookListOb.start( primaryStage );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );

        //btnBorrowHistory(button) -> BorrowHistory(class)
        final BorrowHistory borrowHistoryOb = new BorrowHistory();
        btnBorrowHistory.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    borrowHistoryOb.start( primaryStage );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );

        //btnLogout(button) -> Login(class)
        Login loginOb = new Login();
        btnLogout.setOnAction( new EventHandler <ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loginOb.start( primaryStage );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } );

        primaryStage.setResizable(false);
        primaryStage.setScene(sceneOb);
        primaryStage.show();
    }
}
