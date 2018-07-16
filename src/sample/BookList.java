package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;

import static jdk.nashorn.internal.objects.NativeDate.setDate;

public class BookList extends Application {
    ListView<AddBookUser> lsv;


    DataAccess da = new DataAccess();
    private TableView table = new TableView();
    Login lg = new Login();

    public static void main(String[] args){
        launch( args );
    }
   public DatePicker datePickerBorrow = new DatePicker();
    public DatePicker datePickerReturn = new DatePicker();

    private String[] data;
    String takeEmail;
    private static String emailPass;

    TableColumn firstIdCol,secondBookNameCol,thirdAuthorCol,fourthTotalCopyCol,fifthAvailableCopyCol,sixthShelfCol;

    //lend test field
    TextField tfBookId = new TextField();
    TextField tfBookName = new TextField();


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
        labelTitle.setText( " Book List Criteria" );
        labelTitle.setFont( Font.font ("Verdana", 20) );

        stackpaneOb.getChildren().add( labelTitle );

        labelTitle.setTranslateX( -150 );
        labelTitle.setTranslateY( -310 );
        labelTitle.setMaxSize( 280,60 );

        //table.setEditable( true );
         firstIdCol = new TableColumn( "Book ID" );
        firstIdCol.setMinWidth(5);
        firstIdCol.setCellValueFactory(
                new PropertyValueFactory<AddBookUser, String>("bookid"));
         secondBookNameCol = new TableColumn( "Book Name" );
         thirdAuthorCol = new TableColumn( "Author Names" );
         fourthTotalCopyCol = new TableColumn( "Total Copy" );
         fifthAvailableCopyCol = new TableColumn( "Available Copy" );
         sixthShelfCol = new TableColumn( "Shelf" );


        table.getColumns().addAll( firstIdCol,secondBookNameCol, thirdAuthorCol,fourthTotalCopyCol,fifthAvailableCopyCol,sixthShelfCol);

//        thirdAuthorCol.setMinWidth(Region.USE_PREF_SIZE);
//        thirdAuthorCol.setMaxWidth(Region.USE_PREF_SIZE);

        //table add in vertical box
        final VBox vBox = new VBox(  );
        vBox.setSpacing( 5 );
        VBox.setVgrow(table, Priority.ALWAYS);
        vBox.setPadding( new Insets( 220,450,30,240 ) );
        vBox.getChildren().addAll( table );
        stackpaneOb.getChildren().add( vBox );

        //label for lend book
        Label lblBookId = new Label("Book Id");
        Label lblBookName = new Label("Book Name");
        Label lblReturnBook = new Label("Borrowed On");
        Label lblExpectReturn = new Label("Expect Return");

        //add book info label position
        lblBookId.setTranslateX( 238 );
        lblBookId.setTranslateY( -150 );
        lblBookName.setTranslateX( 238 );
        lblBookName.setTranslateY( -110 );
        lblReturnBook.setTranslateX( 238 );
        lblReturnBook.setTranslateY( -70 );
        lblExpectReturn.setTranslateX( 238 );
        lblExpectReturn.setTranslateY( -30 );
        stackpaneOb.getChildren().addAll( lblBookId,lblBookName,lblReturnBook,lblExpectReturn);




        datePickerBorrow.setOnAction(e->BorrowDate());
        datePickerReturn.setOnAction(e->ReturnDate());



        //lend testfiel position
        tfBookId.setTranslateX( 410 );
        tfBookId.setTranslateY(-150 );
        tfBookId.setMaxSize(200, 20);
        tfBookId.setPromptText( "Book Id" );
        tfBookName.setTranslateX( 410 );
        tfBookName.setTranslateY(-110 );
        tfBookName.setMaxSize(200, 20);
        tfBookName.setPromptText( "Book Name" );

        //date position
        datePickerBorrow.setTranslateX( 410 );
        datePickerBorrow.setTranslateY(-70 );
        datePickerBorrow.setMaxSize(200, 20);
        datePickerReturn.setTranslateX( 410 );
        datePickerReturn.setTranslateY(-30 );
        datePickerReturn.setMaxSize(200, 20);
        stackpaneOb.getChildren().addAll( tfBookId,tfBookName,datePickerBorrow,datePickerReturn);


        //Beside Bar Option
        Button btnSearch = new Button(  );
        Button btnProfile = new Button(  );
        Button btnListBook = new Button(  );
        Button btnBorrowHistory = new Button(  );
        Button btnLogout = new Button(  );
        Button btnloadMember = new Button( );
        Button btnAddBook =  new Button();

        btnSearch.setText( "Search" );
        btnProfile.setText( "My Profile" );
        btnListBook.setText( "Book List" );
        btnBorrowHistory.setText( "Borrow History" );
        btnLogout.setText( "Logout" );

        btnloadMember.setText("Show Tag");
        btnAddBook.setText("Lend");

        stackpaneOb.getChildren().addAll(btnSearch,btnProfile,btnListBook,btnBorrowHistory,btnLogout,btnloadMember,btnAddBook );


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

        //show tag button position
        btnloadMember.setTranslateX( 300 );
        btnloadMember.setTranslateY( 30 );
        btnloadMember.setMaxSize( 105,30 );
        btnloadMember.getStyleClass().add("my-special-button");

        //lend button position
        btnAddBook.setTranslateX( 460 );
        btnAddBook.setTranslateY( 30 );
        btnAddBook.setMaxSize( 100,30 );
        btnAddBook.getStyleClass().add("my-special-button");

        btnloadMember.setOnAction(e->showTag());
        btnAddBook.setOnAction(e->addbookBorrowHistory());


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
//        table.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if( table.getEditingCell() == null) {
//                    if( event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
//
//                        TablePosition focusedCellPosition = table.getFocusModel().getFocusedCell();
//                        table.edit(focusedCellPosition.getRow(), focusedCellPosition.getTableColumn());
//
//                    }
//                }
//            }
//        });

        //double click after add book list
        table.setRowFactory( tv -> {
            TableRow<AddBookUser> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    AddBookUser clickData = row.getItem();
                    //System.out.println(clickData.getBookName());
                   tfBookId.setText(String.valueOf(clickData.getBookid()));
                   tfBookName.setText(clickData.getBookName());

                   tfBookId.setDisable(true);
                    tfBookId.setStyle("-fx-background-color: WHITE;");
                    tfBookId.setStyle("-fx-font-fill: #d3d3d3;");
                   tfBookName.setDisable(true);
                }
            });
            return row ;
        });
//        lsv.setOnMouseClicked(e->{
//            AddBookUser abu = lsv.getSelectionModel().getSelectedItem();
//
//            if(abu != null){
//                //tfBookId.setText(abu.getBookid());
//                tfBookName.setText(abu.getBookName());
//            }
//        });



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

        table.getItems().setAll( da.getBookInfo() );

    }


    public void BorrowDate(){

        datePickerBorrow.setValue( LocalDate.now());
        datePickerBorrow.setShowWeekNumbers(true);


    }

    public void ReturnDate(){


            if(datePickerBorrow.getConverter().toString()==null){
                datePickerReturn.setDisable(true);
            }else{
                datePickerReturn.setDisable(false);
                datePickerReturn.setValue(datePickerBorrow.getValue().plusDays(7));
                datePickerReturn.setShowWeekNumbers(true);
            }


    }


    public void setEmail(String emailP) {
        this.emailPass = emailP;
        System.out.println("Inside setEmail" + emailP + " " + this.emailPass);
    }

    public String getEmail() {
       // System.out.println(emailPass);
        return this.emailPass;
    }

    public void addbookBorrowHistory(){


        if(tfBookId.getText().equals("")||tfBookName.getText().equals("")||datePickerBorrow.getEditor().getText().equals("")||datePickerReturn.getEditor().getText().equals("")){
            Alert alert = new Alert( Alert.AlertType.ERROR);
            alert.setTitle("Borrow Book");
            alert.setContentText("Please fill up all box.");
            alert.showAndWait();
        }
        else {
            //System.out.println("Insde addbookborrowhistory " + this.emailPass);
            da.borrowhistory(this.emailPass, tfBookId.getText(), tfBookName.getText(), datePickerBorrow.getEditor().getText(), datePickerReturn.getEditor().getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Borrow Book");
            alert.setHeaderText("Now you take this Book");
            alert.setContentText("Happy Reading");
            alert.showAndWait();
            tfBookId.clear();
            tfBookName.clear();
        }
    }
}


