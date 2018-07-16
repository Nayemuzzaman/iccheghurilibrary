package sample;


import com.sun.javafx.scene.control.skin.TextFieldSkin;
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
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AddMembers extends Application{

    DataAccess da = new DataAccess();
    private boolean equals;

    public static void main(String[] args){
        launch( args );
    }


    Button btnloadMember = new Button( "Show Tag");

    private List<AddMembersUser> data;
    public TableView<AddMembersUser> tableMembers = new TableView(  );
    TableColumn nameCol,emailCol,addressCol,mobileNumberCol;

    TextField tfSearch = new TextField(  );
    //TextField tfSearch;
    //ObservableList<AddBookUser> mem = FXCollections.observableArrayList();

    public void start(Stage primaryStage){
        StackPane stackpaneOb = new StackPane();
        stackpaneOb.setAlignment( Pos.CENTER);

        Scene sceneOb = new Scene( stackpaneOb);
        primaryStage.setWidth( 1200 );
        primaryStage.setHeight( 800 );
        sceneOb.getStylesheets().addAll(AddBook.class.getResource("/CSS//AddBook.CSS").toExternalForm());

        //Title
        Label labelTitle = new Label(  );
        labelTitle.setText( " Member List Criteria" );
        labelTitle.setFont( Font.font ("Verdana", 20) );

        stackpaneOb.getChildren().add( labelTitle );

        labelTitle.setTranslateX( -150 );
        labelTitle.setTranslateY( -310 );
        labelTitle.setMaxSize( 280,30 );

        //table position
        final VBox vBox = new VBox( 35 );
        //vBox.setSpacing( 35 );
        vBox.setVgrow(tableMembers, Priority.ALWAYS);
        vBox.setPadding( new Insets( 220,450,30,240 ) );
        vBox.getChildren().addAll( tableMembers );

        stackpaneOb.getChildren().add( vBox );


        //Add Book Button
        Button btndelete = new Button( "Delete");
        btndelete.setTranslateX( 460 );
        btndelete.setTranslateY( 30 );
        btndelete.setMaxSize( 100,30 );
        stackpaneOb.getChildren().add( btndelete );
        btndelete.getStyleClass().add("my-special-button");

        // Button btnloadData = new Button( "Load Book");
        btnloadMember.setTranslateX( 300 );
        btnloadMember.setTranslateY( 30 );
        btnloadMember.setMaxSize( 105,30 );
        stackpaneOb.getChildren().add( btnloadMember );
        btnloadMember.getStyleClass().add("my-special-button");

        System.out.println("Read");
        System.out.println(this.getClass().getSimpleName()+".initialize");


        nameCol = new TableColumn( "Name" );
        emailCol = new TableColumn( "Email" );
        addressCol = new TableColumn( "Address" );
        mobileNumberCol = new TableColumn( "Phone" );

        tableMembers.getColumns().addAll( nameCol,emailCol, addressCol,mobileNumberCol);
        data =da.getMemberInfo();
       tableMembers.setItems(FXCollections.observableArrayList(data));

        btnloadMember.setOnAction(e-> showTag());
//        btnloadMember.setOnAction( new EventHandler <ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                nameCol.setCellValueFactory(new PropertyValueFactory<AddMembersUser,String>("Name"));
//                emailCol.setCellValueFactory(new PropertyValueFactory<AddMembersUser,String>("Email"));
//                addressCol.setCellValueFactory(new PropertyValueFactory<AddMembersUser,String>("Address"));
//                mobileNumberCol.setCellValueFactory(new PropertyValueFactory<AddMembersUser,Integer>("Phone"));
//
//
//              tableMembers.getItems().setAll( da.getMemberInfo() );
//            }
//        } );
        btndelete.setOnAction( e-> deleteButtonClicked() );

        tfSearch.setOnAction( e->initFilter() );


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

        stackpaneOb.getChildren().addAll( btnSearch,btnAddBook,btnMember,btnBorrowHistory ,btnLogout);


        //Profile button position
        btnSearch.setTranslateX(  -480);
        btnSearch.setTranslateY(  -90);
        btnSearch.setMaxSize( 200,50 );

        //Add Book button position
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

        //Search bar
        tfSearch.setPromptText("Search");
        tfSearch.setTranslateX( -220 );
        tfSearch.setTranslateY( -200 );
        tfSearch.setMaxSize( 250 ,30);
        stackpaneOb.getChildren().add( tfSearch );

        final AddBook addBookOb = new AddBook();
        btnAddBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addBookOb.start(primaryStage);
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


        primaryStage.setResizable( false );
        primaryStage.setScene(sceneOb);
        primaryStage.show();
    }

    //this function use for show table
    private  void showTag(){

            nameCol.setCellValueFactory(new PropertyValueFactory<AddMembersUser,String>("Name"));
            emailCol.setCellValueFactory(new PropertyValueFactory<AddMembersUser,String>("Email"));
            addressCol.setCellValueFactory(new PropertyValueFactory<AddMembersUser,String>("Address"));
            mobileNumberCol.setCellValueFactory(new PropertyValueFactory<AddMembersUser,Integer>("Phone"));


            tableMembers.getItems().setAll( da.getMemberInfo() );

    }

    //this use for searching
    private void initFilter() {
        tfSearch.textProperty().addListener(new InvalidationListener() {

            @Override

            public void invalidated(javafx.beans.Observable observable) {

                if(tfSearch.textProperty().get().isEmpty()) {

                    tableMembers.setItems((ObservableList<AddMembersUser>) data);
                    showTag();

                    return;

                }

                ObservableList<AddMembersUser> tableItems = FXCollections.observableArrayList();

                ObservableList<TableColumn<AddMembersUser, ?>> cols = tableMembers.getColumns();

                for(int i=0; i<data.size(); i++) {

                    for(int j=0; j<cols.size(); j++) {

                        TableColumn col = cols.get(j);

                        String cellValue = col.getCellData(data.get(i)).toString();

                        cellValue = cellValue.toLowerCase();

                        if(cellValue.contains(tfSearch.textProperty().get().toLowerCase())) {
                            tableItems.add(data.get(i));
                            break;
                        }
                    }
                }

                tableMembers.setItems(tableItems);

            }

        });

    }



    public void deleteButtonClicked(){
        ObservableList<AddMembersUser> memberSelected, allMembers;
        allMembers = tableMembers.getItems();
        memberSelected = tableMembers.getSelectionModel().getSelectedItems();

        memberSelected.forEach( allMembers::remove );

        da.getMemberDelete();
        //tableMembers.getItems().setAll( da.getMemberDelete() );
    }

}
