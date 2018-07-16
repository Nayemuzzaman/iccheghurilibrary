package sample;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class BasicCalculator extends Application{

   public static void main(String[] args){
       launch(args);
   }
   public void start(Stage primaryStage) throws Exception{
       StackPane stackpaneOb = new StackPane();
       stackpaneOb.setAlignment( Pos.CENTER_LEFT);

       Scene sceneOb = new Scene(stackpaneOb, 250, 300);
       sceneOb.getStylesheets().add(Login.class.getResource("/CSS//SignUp.css").toExternalForm());

       //User input textfeild
       TextField textField = new TextField(  );
       textField.setMaxSize( 230,36 );
       stackpaneOb.getChildren().add( textField );
       textField.setTranslateX( 10 );
       textField.setTranslateY( -125 );

       //All Button
       Button btn9 = new Button( "9" );
       Button btn8 = new Button( "8" );
       Button btn7 = new Button( "7" );
       Button btnMult = new Button( "X" );

       Button btn6 = new Button( "6" );
       Button btn5 = new Button( "5" );
       Button btn4 = new Button( "4" );
       Button btnSub = new Button( "-" );

       Button btn3 = new Button( "3" );
       Button btn2 = new Button( "2" );
       Button btn1 = new Button( "1" );
       Button btnAdd = new Button( "+" );

       Button btnSharp = new Button( "C" );
       Button btn0 = new Button( "0" );
       Button btnDiv = new Button( "/" );
       Button btnEqual = new Button( "=" );

       //Button First part Size
       btn9.setMaxSize( 30,20 );
       btn8.setMaxSize( 30,20 );
       btn7.setMaxSize( 30,20 );
       btnMult.setMaxSize( 30,20 );

       //Button in first part position
       stackpaneOb.getChildren().addAll( btn9,btn8,btn7,btnMult );
       btn9.setTranslateX( 20 );
       btn9.setTranslateY( -80 );
       btn8.setTranslateX( 70 );
       btn8.setTranslateY( -80 );
       btn7.setTranslateX( 120 );
       btn7.setTranslateY( -80 );
       btnMult.setTranslateX( 170 );
       btnMult.setTranslateY( -80 );

       //Button in Second part Size
       btn6.setMaxSize( 30,20 );
       btn5.setMaxSize( 30,20 );
       btn4.setMaxSize( 30,20 );
       btnSub.setMaxSize( 30,20 );

       //Button in Second part position
       stackpaneOb.getChildren().addAll( btn6,btn5,btn4,btnSub );
       btn6.setTranslateX( 20 );
       btn6.setTranslateY( -40 );
       btn5.setTranslateX( 70 );
       btn5.setTranslateY( -40 );
       btn4.setTranslateX( 120 );
       btn4.setTranslateY( -40 );
       btnSub.setTranslateX( 170 );
       btnSub.setTranslateY( -40 );

       //Button in Third part Size
       btn3.setMaxSize( 30,20 );
       btn2.setMaxSize( 30,20 );
       btn1.setMaxSize( 30,20 );
       btnAdd.setMaxSize( 30,20 );

       //Button in Third part position
       stackpaneOb.getChildren().addAll( btn3,btn2,btn1,btnAdd );
       btn3.setTranslateX( 20 );
       btn3.setTranslateY( -0 );
       btn2.setTranslateX( 70 );
       btn2.setTranslateY( -0 );
       btn1.setTranslateX( 120 );
       btn1.setTranslateY( -0 );
       btnAdd.setTranslateX( 170 );
       btnAdd.setTranslateY( -0 );

       //Button in Fourth part Size
       btnSharp.setMaxSize( 30,20 );
       btn0.setMaxSize( 30,20 );
       btnDiv.setMaxSize( 30,20 );
       btnEqual.setMaxSize( 30,20 );

       //Button in Fourth part position
       stackpaneOb.getChildren().addAll( btnSharp,btn0,btnDiv,btnEqual );
       btnSharp.setTranslateX( 20 );
       btnSharp.setTranslateY( 40 );
       btn0.setTranslateX( 70 );
       btn0.setTranslateY( 40 );
       btnDiv.setTranslateX( 120 );
       btnDiv.setTranslateY( 40 );
       btnEqual.setTranslateX( 170 );
       btnEqual.setTranslateY( 40 );


       primaryStage.setTitle( "Calculator" );
       primaryStage.setScene( sceneOb );
       primaryStage.show();
   }


}