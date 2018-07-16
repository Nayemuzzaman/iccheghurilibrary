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





public class AddBorrow extends Application {

    DataAccess da = new DataAccess();



    public static void main(String[] args){
        launch( args );
    }

    public TableView<AddBorrowHistory> tableBorrow = new TableView();
    private List<AddBorrowHistory> tdata;

    Button btnloadData = new Button( "show Book");

    TableColumn firstEmailCol,secondBookidCol,thirdborrowDateCol,fourthExpectDateCol,fifthReturnDateCol,sixthFineCol;

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

        firstEmailCol = new TableColumn( "Email" );
        secondBookidCol = new TableColumn( "Book Id" );
        thirdborrowDateCol = new TableColumn( "Book Name" );
        fourthExpectDateCol = new TableColumn( "BorrowDate On" );
        fifthReturnDateCol = new TableColumn( "ExpectDate On" );
        sixthFineCol = new TableColumn( "Fine" );


        tableBorrow.getColumns().addAll( firstEmailCol,secondBookidCol, thirdborrowDateCol,fourthExpectDateCol,fifthReturnDateCol,sixthFineCol);
        tdata =da.getBorrowHistory();
        tableBorrow.setItems(FXCollections.observableArrayList(tdata));

        tfSearch.setOnAction(e->initFilter());
        tableBorrow.setEditable(true);

        final VBox vBox = new VBox(  );
        vBox.setSpacing( 5 );
        VBox.setVgrow(tableBorrow, Priority.ALWAYS);
        vBox.setPadding( new Insets( 220,450,30,240 ) );
        vBox.getChildren().addAll( tableBorrow );

        stackpaneOb.getChildren().add( vBox );

        //Search bar
        tfSearch.setPromptText("Search");
        tfSearch.setTranslateX( -220 );
        tfSearch.setTranslateY( -200 );
        tfSearch.setMaxSize( 250 ,30);
        stackpaneOb.getChildren().add( tfSearch );



        // Button btnloadData = new Button( "Show Book");
        btnloadData.setTranslateX( 300 );
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
        Button btnAddBook = new Button(  );
        Button btnMember = new Button(  );
        Button btnBorrowHistory = new Button(  );
        Button btnLogout = new Button(  );

        btnSearch.setText( "Search" );
        btnAddBook.setText( "Add Book");
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

        //click and go addbook
        final AddBook addBookOb = new AddBook();
        btnAddBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addBookOb.start(primaryStage);
            }
        });

        //clcik and go search
        final AddSearch addSearchOb= new AddSearch();
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addSearchOb.start(primaryStage);
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

        stackpaneOb.getChildren().addAll( btnSearch,btnAddBook,btnMember,btnBorrowHistory ,btnLogout);



        //Profile button position
        btnSearch.setTranslateX(  -480);
        btnSearch.setTranslateY(  -90);
        btnSearch.setMaxSize( 200,50 );

        //Profile button position
        btnAddBook.setTranslateX(  -480);
        btnAddBook.setTranslateY(  -30);
        btnAddBook.setMaxSize( 200,50 );

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

        //detect double click on table row
        tableBorrow.setRowFactory( tv -> {
            TableRow<AddBorrowHistory> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    AddBorrowHistory clickData = row.getItem();
                    System.out.println(clickData.getBookNameCol());

                }
            });
            return row ;
        });

        primaryStage.setResizable( false );
        primaryStage.setScene(sceneOb);
        primaryStage.show();
    }

    //this function use for show table
    private  void showTag(){
        firstEmailCol.setCellValueFactory(new PropertyValueFactory<AddBorrowHistory,Integer>("Email"));
        secondBookidCol.setCellValueFactory(new PropertyValueFactory<AddBorrowHistory,String>("Book Id"));
        thirdborrowDateCol.setCellValueFactory(new PropertyValueFactory<AddBorrowHistory,String>("Book Name"));
        fourthExpectDateCol.setCellValueFactory(new PropertyValueFactory<AddBorrowHistory,String>("expectdate"));
        fifthReturnDateCol.setCellValueFactory(new PropertyValueFactory<AddBorrowHistory,String>("returndate"));
        sixthFineCol.setCellValueFactory(new PropertyValueFactory<AddBorrowHistory,String>("fine"));

        tableBorrow.getItems().setAll( da.getBorrowHistory() );

    }

    //this use for searching
    private void initFilter() {
        tfSearch.textProperty().addListener(new InvalidationListener() {

            @Override

            public void invalidated(javafx.beans.Observable observable) {

                if(tfSearch.textProperty().get().isEmpty()) {

                    tableBorrow.setItems((ObservableList<AddBorrowHistory>) tdata);
                    tableBorrow.getItems().setAll( da.getBorrowHistory() );
                    return;

                }

                ObservableList<AddBorrowHistory> tableItems = FXCollections.observableArrayList();

                ObservableList<TableColumn<AddBorrowHistory, ?>> cols = tableBorrow.getColumns();

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

                tableBorrow.setItems(tableItems);

            }

        });

    }

}
