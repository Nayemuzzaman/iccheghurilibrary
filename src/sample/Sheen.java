package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Sheen {
    public void pop(String message){

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/popup.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            popup p = (popup) fxmlLoader.getController();
            Scene scene = new Scene(root,300,150);

            p.setMessage(message);

            scene.getStylesheets().add(getClass().getResource("/css/Todas.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Oops!!!");
            stage.showAndWait();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
