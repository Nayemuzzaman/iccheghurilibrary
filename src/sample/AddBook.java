package sample;


import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AddBook extends Application{

    DataAccess da = new DataAccess();



    public static void main(String[] args){
        launch( args );
    }

    public TableView<AddBookUser> tableBook = new TableView();
    private List<AddBookUser> tdata;

    Button btnloadData = new Button( "show Book");
    Button btndelete;

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
        labelTitle.setText( " Add Book Criteria" );
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
        vBox.setPadding( new Insets( 220,450,30,240 ) );
        vBox.getChildren().addAll( tableBook );

        stackpaneOb.getChildren().add( vBox );

        //Search bar
        tfSearch.setPromptText("Search");
        tfSearch.setTranslateX( -220 );
        tfSearch.setTranslateY( -200 );
        tfSearch.setMaxSize( 250 ,30);
        stackpaneOb.getChildren().add( tfSearch );

        //label for add book
        Label lblBookId = new Label("Book Id");
        Label lblBookName = new Label("Book Name");
        Label lblAuthorName = new Label("Author Name");
        Label lblTotalCopy = new Label("Total Copy");
        Label lblAvailableCopy = new Label("Available Copy");
        Label lblShelf = new Label("Shelf");

        //add book info label position
        lblBookId.setTranslateX( 220 );
        lblBookId.setTranslateY( -230 );
        lblBookName.setTranslateX( 230 );
        lblBookName.setTranslateY( -190 );
        lblAuthorName.setTranslateX( 235 );
        lblAuthorName.setTranslateY( -150 );
        lblTotalCopy.setTranslateX( 226 );
        lblTotalCopy.setTranslateY( -110 );
        lblAvailableCopy.setTranslateX( 238 );
        lblAvailableCopy.setTranslateY( -70 );
        lblShelf.setTranslateX( 210 );
        lblShelf.setTranslateY( -30 );
        stackpaneOb.getChildren().addAll( lblBookId,lblBookName,lblAuthorName,lblTotalCopy,lblAvailableCopy,lblShelf);

        //addbook text field
        TextField tfBookId = new TextField(  );
        TextField tfBookName = new TextField();
        TextField tfAuthorName = new TextField();
        TextField tfTotalCopy = new TextField();
        TextField tfAvailableCopy = new TextField();
        TextField tfShelf = new TextField();


        tfBookId.setTranslateX( 410 );
        tfBookId.setTranslateY(-230 );
        tfBookId.setMaxSize(200, 20);
        tfBookId.setPromptText( "Book Id" );
        tfBookName.setTranslateX( 410 );
        tfBookName.setTranslateY(-190 );
        tfBookName.setMaxSize(200, 20);
        tfBookName.setPromptText( "Book Name" );
        tfAuthorName.setTranslateX( 410 );
        tfAuthorName.setTranslateY(-150 );
        tfAuthorName.setMaxSize(200, 20);
        tfAuthorName.setPromptText( "Author Name" );
        tfTotalCopy.setTranslateX( 410 );
        tfTotalCopy.setTranslateY(-110 );
        tfTotalCopy.setMaxSize(200, 20);
        tfTotalCopy.setPromptText( "Total Copy" );
        tfAvailableCopy.setTranslateX( 410 );
        tfAvailableCopy.setTranslateY(-70 );
        tfAvailableCopy.setMaxSize(200, 20);
        tfAvailableCopy.setPromptText( "Available Copy" );
        tfShelf.setTranslateX( 410 );
        tfShelf.setTranslateY(-30 );
        tfShelf.setMaxSize(200, 20);
        tfShelf.setPromptText( "Shelf" );
        stackpaneOb.getChildren().addAll( tfBookId,tfBookName,tfAuthorName,tfTotalCopy,tfAvailableCopy,tfShelf);

        //Add Book Button
        Button btnAddBook = new Button( "Add Book");
        btnAddBook.setTranslateX( 460 );
        btnAddBook.setTranslateY( 30 );
        btnAddBook.setMaxSize( 100,30 );
        //stackpaneOb.getChildren().add( btnAddBook );
        btnAddBook.getStyleClass().add("my-special-button");

       // Button btnloadData = new Button( "Show Book");
        btnloadData.setTranslateX( 300 );
        btnloadData.setTranslateY( 30 );
        btnloadData.setMaxSize( 105,30 );
       // stackpaneOb.getChildren().add( btnloadData );
        btnloadData.getStyleClass().add("my-special-button");

        //button delete
        btndelete = new Button("Delete");
        btndelete.setTranslateX( 300 );
        btndelete.setTranslateY( 90 );
        btndelete.setMaxSize( 105,30 );
        btndelete.getStyleClass().add("my-special-button");

        btndelete.setOnAction(e->showDelete());

        stackpaneOb.getChildren().addAll( btnAddBook,btnloadData,btndelete );
        System.out.println("Read");
        System.out.println(this.getClass().getSimpleName()+".initialize");

        btnloadData.setOnAction(e-> showTag());


        //search filter

//        FilteredList<AddBookUser> filteredData = new FilteredList <>( bookSearch, e-> true );
//        tfSearch.setOnKeyReleased(  e -> {
//
//            tfSearch.textProperty().addListener( (observableValue, oldValue, newValue) -> {
//                filteredData.setPredicate( (Predicate <? super AddBookUser>) user -> {
//                    if (newValue == null || newValue.isEmpty()) {
//                        return true;
//                    }
//                    String lowercaseFilter = newValue.toLowerCase();
//                    if (user.getShelf().contains( newValue )) {
//                        return true;
//                    } else if (user.getBookName().toLowerCase().contains( lowercaseFilter )) {
//                        return true;
//                    } else if (user.getAuthorName().toLowerCase().contains( lowercaseFilter )) {
//                        return true;
//                    }
//                    return false;
//
//                });
//            });
//            SortedList<AddBookUser> sortedData = new SortedList <>( filteredData );
//            sortedData.comparatorProperty().bind( table.comparatorProperty() );
//            table.setItems( sortedData );
//        });


        //add book info in mysql
        btnAddBook.setOnAction( new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (tfBookId.getText().equals("") || tfBookName.getText().equals("")|| tfAuthorName.getText().equals("") || tfTotalCopy.getText().equals("") ||  tfAvailableCopy.getText().equals("") || tfShelf.getText().equals("")  ){
                    Alert alert = new Alert( Alert.AlertType.ERROR);
                    alert.setTitle( "Add Book Info" );
                    alert.setContentText("Why you are not fill up all fields?");
                    alert.showAndWait();
                }
                else{
                    da.addBookTable(tfBookId.getText(),tfBookName.getText(),tfAuthorName.getText(),tfTotalCopy.getText(),tfAvailableCopy.getText(), tfShelf.getText());
                    Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Add Book Info");
                    alert.setHeaderText("You have successfully Add Book");
                    alert.setContentText("Thank you For using library");
                    alert.showAndWait();
                    tfBookId.clear();
                    tfBookName.clear();
                    tfAuthorName.clear();
                    tfTotalCopy.clear();
                    tfAvailableCopy.clear();
                    tfShelf.clear();
                }
            }
        } );



        //Beside Bar Option
        Button btnSearch = new Button(  );
        Button btnProfile = new Button(  );
        Button btnMember = new Button(  );
        Button btnBorrowHistory = new Button(  );
        Button btnLogout = new Button(  );

        btnSearch.setText( "Search" );
        btnProfile.setText( "Add Book");
        btnMember.setText( "Members" );
        btnBorrowHistory.setText( "Borrow History" );
        btnLogout.setText( "Logout" );

        //When  click Members button
        final AddMembers addMembersOb= new AddMembers();
       // final AddBook addBookOb= new AddBook();
        btnMember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // addBookOb.showTag();
                addMembersOb.start(primaryStage);
            }
        });

        final AddSearch addSearchOb= new AddSearch();
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addSearchOb.start(primaryStage);
            }
        });

        //Clcik and go Borrow Class
        final AddBorrow addBorrowOb = new AddBorrow();
        btnBorrowHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addBorrowOb.start(primaryStage);
            }
        });
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

        stackpaneOb.getChildren().addAll( btnSearch,btnProfile,btnMember,btnBorrowHistory ,btnLogout);



        //Profile button position
        btnSearch.setTranslateX(  -480);
        btnSearch.setTranslateY(  -90);
        btnSearch.setMaxSize( 200,50 );

        //Profile button position
        btnProfile.setTranslateX(  -480);
        btnProfile.setTranslateY(  -30);
        btnProfile.setMaxSize( 200,50 );

        //Book button Position
        btnMember.setTranslateX(  -480);
        btnMember.setTranslateY(  30);
        btnMember.setMaxSize( 200,50 );

        //History Button Position
        btnBorrowHistory.setTranslateX(  -480);
        btnBorrowHistory.setTranslateY(  90);
        btnBorrowHistory.setMaxSize( 200,50 );

        //Logout Button Position
        btnLogout.setTranslateX(  -480);
        btnLogout.setTranslateY(  150);
        btnLogout.setMaxSize( 200,50 );

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

    public void showDelete(){
        ObservableList<AddBookUser> memberSelected, allMembers;
        allMembers = tableBook.getItems();
        memberSelected = tableBook.getSelectionModel().getSelectedItems();

        memberSelected.forEach( allMembers::remove );
        da.getBookDelete();
        //tableMembers.getItems().setAll( da.getMemberDelete() );
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
