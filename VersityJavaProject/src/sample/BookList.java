package sample;

import com.sun.deploy.util.BufferUtil;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.xml.soap.Text;

public class BookList extends Application {

    private TableView table = new TableView();

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
        labelTitle.setText( " Book List Criteria" );
        labelTitle.setFont( Font.font ("Verdana", 20) );

        stackpaneOb.getChildren().add( labelTitle );

        labelTitle.setTranslateX( -150 );
        labelTitle.setTranslateY( -310 );
        labelTitle.setMaxSize( 280,60 );

        table.setEditable( true );

        TableColumn firstIdCol = new TableColumn( "Book ID" );
        TableColumn secondBookNameCol = new TableColumn( "Book Name" );
        TableColumn thirdBorrowedOnCol = new TableColumn( "Author Names" );
        TableColumn fourthTotalCopyCol = new TableColumn( "Total Copy" );
        TableColumn fifthAvailableCopyCol = new TableColumn( "Available Copy" );
        TableColumn sixthShelfCol = new TableColumn( "Shelf" );


        table.getColumns().addAll( firstIdCol,secondBookNameCol, thirdBorrowedOnCol,fourthTotalCopyCol,fifthAvailableCopyCol,sixthShelfCol);

        final VBox vBox = new VBox(  );
        vBox.setSpacing( 5 );
        vBox.setPadding( new Insets( 150,20,0,260 ) );
        vBox.getChildren().addAll( table );

        stackpaneOb.getChildren().add( vBox );

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
        //btnSearch(button) -> Search(class)
        Search searchOb = new Search();
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
