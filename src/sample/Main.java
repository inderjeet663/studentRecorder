package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.dataModel.DataSource;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //blur effect creating
        ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);
        Parent root = FXMLLoader.load(getClass().getResource("registerStudent.fxml"));
        root.setEffect(adj);

        primaryStage.setTitle("Student Recorder");
        //add icon on corner of window
        primaryStage.getIcons().add(new Image("sample/icons/contact.png"));
        //not allow to resize
        primaryStage.resizableProperty().setValue(false);
        Scene scene = new Scene(root,781,551);
        primaryStage.setScene(scene);
        primaryStage.show();
        new LoginStage(primaryStage);
        if (LoginStage.Exit) {
            Platform.exit();
        }
        root = FXMLLoader.load(getClass().getResource("registerStudent.fxml"));
        scene.setRoot(root);
    }
    @Override
    public void init() throws Exception {
        super.init();
        DataSource.getInstance().openConnection();
        DataSource.getInstance().createTable();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DataSource.getInstance().closeConnection();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
