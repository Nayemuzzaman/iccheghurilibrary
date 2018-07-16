package sample;

import com.sun.deploy.util.BufferUtil;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.xml.soap.Text;

public class Search extends Application {

    public static void main(String[] args){
        launch( args );
    }

    public void start (Stage primaryStage) throws Exception{
        StackPane stackpaneOb = new StackPane();
        stackpaneOb.setAlignment( Pos.CENTER);

        Scene sceneOb = new Scene(stackpaneOb, 1200, 800);
        sceneOb.getStylesheets().add(Login.class.getResource("/CSS//login.css").toExternalForm());

        //Title
        Label labelTitle = new Label(  );
        labelTitle.setText( " Search Books Criteria" );

        stackpaneOb.getChildren().add( labelTitle );

        labelTitle.setTranslateX( -110 );
        labelTitle.setTranslateY( -310 );
        labelTitle.setMaxSize( 280,60 );
        labelTitle.setFont( Font.font ("Verdana", 20) );
        //Serach Bar
        TextField textField = new TextField(  );
        stackpaneOb.getChildren().add( textField );

        Button button = new Button( "Search" );
        stackpaneOb.getChildren().add( button );

        button.setTranslateX( 400 );
        button.setTranslateY( -250 );
        button.setMaxSize( 80,40 );

        //search box position
        textField.setTranslateX( 50 );
        textField.setTranslateY( -250 );
        textField.setMaxSize( 600,40 );

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

        stackpaneOb.getChildren().addAll( btnSearch,btnProfile,btnListBook,btnBorrowHistory,btnLogout );


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

        //btnProfile(button) -> profile(class)
        final Profile profileOb = new Profile();
        btnProfile.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    profileOb.start( primaryStage );
                } catch (Exception e) {
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


        //sir
       // Image image = new Image( "searchicon.png" );
       // primaryStage.getIcons().add( image );






        primaryStage.setScene(sceneOb);
        primaryStage.show();
    }
}
