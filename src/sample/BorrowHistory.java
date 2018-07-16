package sample;

import com.sun.deploy.util.BufferUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.util.List;

public class BorrowHistory extends Application {

    DataAccess da = new DataAccess();
    private TableView<AddBorrowHistory> tableborrow = new TableView();
    private List<AddBorrowHistory> tdata;

    public static void main(String[] args){
        launch( args );
    }
   private TableColumn firstIdCol,secondBookNameCol,thirdBorrowedOnCol,fourthExpectedReturnCol,fifthReturnedCol,sixthFineCol;
    Button loadButton,btnReturn;

//
//    private ObservableList<AddBorrowHistory> dataList =
//            FXCollections.observableArrayList(
//                    new AddBorrowHistory(100,"Januar","12/5/3" ,"19/5/3"),
//                    new AddBorrowHistory(102,"Janua","12/5/3" ,"19/5/3"),
//                    new AddBorrowHistory(101,"Janu","12/5/3" ,"19/5/3"));

    public void start (Stage primaryStage) throws Exception{
        StackPane stackpaneOb = new StackPane();
        stackpaneOb.setAlignment( Pos.CENTER);

        Scene sceneOb = new Scene( stackpaneOb);
        primaryStage.setWidth( 1200 );
        primaryStage.setHeight( 800 );

        String cssFile1 = this.getClass().getResource("/CSS//login.css").toExternalForm();
        String cssFile2 = this.getClass().getResource("/CSS//AddBook.CSS").toExternalForm();
        sceneOb.getStylesheets().addAll(cssFile1,cssFile2);

        //Title
        Label labelTitle = new Label(  );
        labelTitle.setText( " Borrow History Criteria" );
        labelTitle.setFont( Font.font ("Verdana", 20) );

        stackpaneOb.getChildren().add( labelTitle );

        labelTitle.setTranslateX( -150 );
        labelTitle.setTranslateY( -310 );
        labelTitle.setMaxSize( 280,160 );

        //table.setEditable( false );

        firstIdCol = new TableColumn( "Book ID" );
        secondBookNameCol = new TableColumn( "Book Name" );
        thirdBorrowedOnCol = new TableColumn( "Borrowed On" );
        fourthExpectedReturnCol = new TableColumn( "Expected Return" );
        fifthReturnedCol = new TableColumn( "Returned On" );
        sixthFineCol = new TableColumn( "Fine" );

        tableborrow.getColumns().addAll( firstIdCol,secondBookNameCol, thirdBorrowedOnCol,fourthExpectedReturnCol,fifthReturnedCol,sixthFineCol);

        final VBox vBox = new VBox(  );
        vBox.setSpacing( 5 );
        vBox.setPadding( new Insets( 150,20,0,260 ) );
        vBox.getChildren().add( tableborrow );

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

        //Button for borrow list
        loadButton = new Button("Borrow List");
        loadButton.setTranslateX(-40);
        loadButton.setTranslateY(200);
        loadButton.setMaxSize( 130,20 );
        loadButton.setOnAction(e->showTag());

        //button for return book
        btnReturn = new Button("Return Book");
        btnReturn.setTranslateX(150);
        btnReturn.setTranslateY(200);
        btnReturn.setMaxSize( 140,20 );
        btnReturn.setOnAction(e->showTag());

        stackpaneOb.getChildren().addAll(loadButton,btnReturn);



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

        //btnListBook(button) -> BookList(class)
        final Search searchOb = new Search();
        btnSearch.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    searchOb.start( primaryStage );
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



        tableborrow.setRowFactory( tv -> {
            TableRow<AddBorrowHistory> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    AddBorrowHistory clickData = row.getItem();
                    System.out.println(clickData.getBookNameCol());

                }
            });
            return row ;
        });

        primaryStage.setResizable(false);
        primaryStage.setScene(sceneOb);
        primaryStage.show();
    }
    private  void showTag(){


        firstIdCol.setCellValueFactory(new PropertyValueFactory<AddBorrowHistory, Integer>("Book ID"));
        secondBookNameCol.setCellValueFactory(new PropertyValueFactory<AddBorrowHistory, String>("Book Name"));
        thirdBorrowedOnCol.setCellValueFactory(new PropertyValueFactory<AddBorrowHistory, String>("Borrowed On"));
        fourthExpectedReturnCol.setCellValueFactory(new PropertyValueFactory<AddBorrowHistory, String>("Expected Return"));

//        firstIdCol.getCellValueFactory(new PropertyValueFactory<AddBorrowHistory, Integer>("Bookid"));
//        secondBookNameCol.setCellFactory(new PropertyValueFactory<AddBorrowHistory, String>("Book Name"));
//        thirdBorrowedOnCol.setCellFactory(new PropertyValueFactory<AddBorrowHistory, String>("Borrowed On"));
//        fourthExpectedReturnCol.setCellFactory(new PropertyValueFactory<AddBorrowHistory, String>("Expected Return"));

        //tableborrow.setItems(dataList);
        //tableborrow.getColumns().addAll(firstIdCol, secondBookNameCol,thirdBorrowedOnCol,fourthExpectedReturnCol);

       tableborrow.getItems().setAll( da.getBorrowHistory() );

    }
}
