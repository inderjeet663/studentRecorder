package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.stage.StageStyle.TRANSPARENT;

public class LoginStage {
    public static boolean Exit=false;
    public static String User;
    public LoginStage(Stage primaryStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginWindow.fxml"));
        Parent root1 = null;
        {
            try {
                root1 = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = new Stage();
        root1.setStyle("-fx-background-color: rgba(1, 25, 54, 0.8);");
        stage.initOwner(primaryStage.getScene().getWindow());
        Scene scene =new Scene(root1);
        scene.setFill(null);
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(TRANSPARENT);
        stage.showAndWait();
    }
    public static void setExit(boolean value) {
        Exit=value;
    }
}