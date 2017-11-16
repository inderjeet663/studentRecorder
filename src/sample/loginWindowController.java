package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.dataModel.DataSource;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.paint.Color.RED;
import static javafx.stage.StageStyle.TRANSPARENT;

public class loginWindowController {
    @FXML
    private RadioButton userRB;
    @FXML
    private RadioButton adminRB;
    @FXML
    private ToggleGroup radioGroup;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Label usernameMsg;
    @FXML
    private Label passwordMsg;
    @FXML
    private Label opLoginMsg;
    @FXML
    private Pane topPane;
    @FXML
    private Pane leftPane;
    public static String registerWho;

    public void onLoginButtonClick () {
        String who=null;
        String username=usernameField.getText();
        String password=passwordField.getText();
        if(username.equals("")) {
            usernameMsg.setTextFill(RED);
            usernameMsg.setText("Please fill this!");
            return ;
        } else {
            usernameMsg.setText("");
        }
        if(password.equals("")) {
            passwordMsg.setTextFill(RED);
            passwordMsg.setText("Please fill this!");
            return ;
        }else {
            passwordMsg.setText("");
        }
        if(userRB.isSelected()) {
            who="User";
        }
        if(adminRB.isSelected()) {
            who="Admin";
        }
        try {
            if(DataSource.getInstance().login(username,password,who)) {
                LoginStage.User=username;
                Stage stage = (Stage)loginBtn.getScene().getWindow();
                stage.close();
            } else {
                opLoginMsg.setTextFill(RED);
                opLoginMsg.setText("Account not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onExitButtonClick() {
        Stage loginStage =(Stage) loginBtn.getScene().getWindow();
        loginStage.close();
        LoginStage.setExit(true);
    }
    public void onRegisterButtonClick() {
        if(adminRB.isSelected()) {
            String Who="Admin";
            String username=usernameField.getText();
            String password=passwordField.getText();
            if(username.equals("")) {
                usernameMsg.setTextFill(RED);
                usernameMsg.setText("Please fill this!");
                return ;
            } else {
                usernameMsg.setText("");
            }
            if(password.equals("")) {
                passwordMsg.setTextFill(RED);
                passwordMsg.setText("Please fill this!");
                return ;
            }else {
                passwordMsg.setText("");
            }
            try {
                if(DataSource.getInstance().login(username,password,Who)) {
                    this.registerWho="Admin";
                    registerWindowShow();

                } else {
                    opLoginMsg.setTextFill(RED);
                    opLoginMsg.setText("Account not found!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(userRB.isSelected()) {
            this.registerWho="User";
            registerWindowShow();

        }
    }
    public void registerWindowShow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerUser.fxml"));
        Stage stage = new Stage();
        stage.initOwner(registerBtn.getScene().getWindow());
        Parent root3=null;
        try {
            root3= fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root3);
        scene.setFill(null);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(TRANSPARENT);

        stage.showAndWait();
    }

}
