package sample;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;



public class Search extends Application {

    DataAccess da = new DataAccess();



    public static void main(String[] args){
        launch( args );
    }

    public TableView<AddBookUser> tableBook = new TableView();
    private List<AddBookUser> tdata;

    Button btnloadData = new Button( "show Book");

    TableColumn firstIdCol,secondBookNameCol,thirdAuthorCol,fourthTotalCopyCol,fifthAvailableCopyCol,sixthShelfCol;

    TextField tfSearch = new TextField(  );
    ObservableList<AddBookUser> bookSearch = FXCollections.observableArrayList();
    //ListView list = new ListView();


    public void start(Stage primaryStage){
        StackPane stackpaneOb = new StackPane();
        stackpaneOb.setAlignment( Pos.CENTER);

        Scene sceneOb = new Scene( stackpaneOb);
        primaryStage.setWidth( 1200 );
        primaryStage.setHeight( 800 );
        sceneOb.getStylesheets().addAll(AddBook.class.getResource("/CSS//AddBook.CSS").toExternalForm());

        //Title
        Label labelTitle = new Label(  );
        labelTitle.setText( " Search Criteria" );
        labelTitle.setFont( Font.font ("Verdana", 20) );

        stackpaneOb.getChildren().add( labelTitle );

        labelTitle.setTranslateX( -150 );
        labelTitle.setTranslateY( -310 );
        labelTitle.setMaxSize( 280,30 );

        firstIdCol = new TableColumn( "Book ID" );
        secondBookNameCol = new TableColumn( "Book Name" );
        thirdAuthorCol = new TableColumn( "Author Name" );
        fourthTotalCopyCol = new TableColumn( "Total Copy" );
        fifthAvailableCopyCol = new TableColumn( "Available Copy" );
        sixthShelfCol = new TableColumn( "Shelf" );


        tableBook.getColumns().addAll( firstIdCol,secondBookNameCol, thirdAuthorCol,fourthTotalCopyCol,fifthAvailableCopyCol,sixthShelfCol);
        tdata =da.getBookInfo();
        tableBook.setItems(FXCollections.observableArrayList(tdata));

        tfSearch.setOnAction(e->initFilter());
        tableBook.setEditable(true);

        final VBox vBox = new VBox(  );
        vBox.setSpacing( 5 );
        VBox.setVgrow(tableBook, Priority.ALWAYS);
        vBox.setPadding( new Insets( 180,250,30,350 ) );
        vBox.getChildren().addAll( tableBook );

        stackpaneOb.getChildren().add( vBox );

        //Search bar
        tfSearch.setPromptText("Search");
        tfSearch.setTranslateX( 50 );
        tfSearch.setTranslateY( -250 );
        tfSearch.setMaxSize( 600,40 );
        stackpaneOb.getChildren().add( tfSearch );



        // Button btnloadData = new Button( "Show Book");
        btnloadData.setTranslateX( 450 );
        btnloadData.setTranslateY( 30 );
        btnloadData.setMaxSize( 105,30 );
        // stackpaneOb.getChildren().add( btnloadData );
        btnloadData.getStyleClass().add("my-special-button");


        stackpaneOb.getChildren().addAll( btnloadData );
        System.out.println("Read");
        System.out.println(this.getClass().getSimpleName()+".initialize");

        btnloadData.setOnAction(e-> showTag());

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

        primaryStage.setResizable( false );
        primaryStage.setScene(sceneOb);
        primaryStage.show();
    }

    //this function use for show table
    private  void showTag(){

        firstIdCol.setCellValueFactory(new PropertyValueFactory<AddBookUser,Integer>("Bookid"));
        secondBookNameCol.setCellValueFactory(new PropertyValueFactory<AddBookUser,String>("BookName"));
        thirdAuthorCol.setCellValueFactory(new PropertyValueFactory<AddBookUser,String>("AuthorName"));
        fourthTotalCopyCol.setCellValueFactory(new PropertyValueFactory<AddBookUser,Integer>("TotalCopy"));
        fifthAvailableCopyCol.setCellValueFactory(new PropertyValueFactory<AddBookUser,Integer>("AvailableCopy"));
        sixthShelfCol.setCellValueFactory(new PropertyValueFactory<AddBookUser,String>("Shelf"));

        tableBook.getItems().setAll( da.getBookInfo() );

    }

    //this use for searching
    private void initFilter() {
        tfSearch.textProperty().addListener(new InvalidationListener() {

            @Override

            public void invalidated(javafx.beans.Observable observable) {

                if(tfSearch.textProperty().get().isEmpty()) {

                    tableBook.setItems((ObservableList<AddBookUser>) tdata);
                    tableBook.getItems().setAll( da.getBookInfo() );
                    return;

                }

                ObservableList<AddBookUser> tableItems = FXCollections.observableArrayList();

                ObservableList<TableColumn<AddBookUser, ?>> cols = tableBook.getColumns();

                for(int i=0; i<tdata.size(); i++) {

                    for(int j=0; j<cols.size(); j++) {

                        TableColumn col = cols.get(j);

                        String cellValue = col.getCellData(tdata.get(i)).toString();

                        cellValue = cellValue.toLowerCase();

                        if(cellValue.contains(tfSearch.textProperty().get().toLowerCase())) {
                            tableItems.add(tdata.get(i));
                            break;
                        }
                    }
                }

                tableBook.setItems(tableItems);

            }

        });

    }

}
