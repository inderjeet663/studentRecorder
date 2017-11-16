package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.dataModel.DataSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;

import static javafx.scene.paint.Color.RED;

public class RegisterUserController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField mobileField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField repasswordField;
    @FXML
    private DatePicker dobField;
    @FXML
    private Button saveBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private ImageView imageBox;
    @FXML
    private Label nameErr;
    @FXML
    private Label emailErr;
    @FXML
    private Label mobileErr;
    @FXML
    private Label dobErr;
    @FXML
    private Label passwordErr;
    @FXML
    private Label repasswordErr;
    @FXML
    private Label photoErr;
    @FXML
    private Label opregisterMsg;
    @FXML
    private Button photoUploadBtn;
    private File file=null;
    private FileInputStream fin;
    private Image defaultImage = new Image("sample/images/defaultImage.png");

    public void initialize () {
        imageBox.setImage(defaultImage);
        dobField.setEditable(false);
    }
  public void onPhotoUploadBtnClick() {
      FileChooser fileChooser = new FileChooser();
      FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
              "JPG File(*.jpg)","*.JPG");
      FileChooser.ExtensionFilter extensionFilter1 = new FileChooser.ExtensionFilter(
              "PNG File(*.png)","*.PNG");
      fileChooser.getExtensionFilters().addAll(extensionFilter,extensionFilter1);
      file = fileChooser.showOpenDialog(null);
      if(file!=null) {
      try {
          BufferedImage imageBf = ImageIO.read(file);
          Image image = SwingFXUtils.toFXImage(imageBf,null);
          imageBox.setImage(image);
          fin = new FileInputStream(file);
          int i = (int)file.length();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
  }
    public void onSaveButtonClick () throws ParseException {
        String name = nameField.getText();
        String email= emailField.getText();
        String mobile= mobileField.getText();
        String password= passwordField.getText();
        String repassword= repasswordField.getText();
        String dob;
            if (name.equals("")) {
                nameErr.setText("Fill name field!");
                return;
            }else {
                nameErr.setText("");
            }
            if (email.equals("")) {
                emailErr.setText("Fill email field!");
                return;
            } else {
                emailErr.setText("");
            }
            if (mobile.equals("")) {
                mobileErr.setText("Fill mobile field!");
                return;
            }else {
                mobileErr.setText("");
            }
        if (dobField.getValue() == null) {
            dobErr.setText("Select DOB");
            return;
        } else {
            dob = dobField.getValue().toString();
            dobErr.setText("");
        }
            if (password.equals("")) {
                passwordErr.setText("Fill password field!");
                return;
            } else {
                passwordErr.setText("");
            }
            if (repassword.equals("")) {
                repasswordErr.setText("Fill Confirm pass field!");
                return;
            }else {
                repasswordErr.setText("");
            }
            if(!password.equals(repassword)) {
                repasswordErr.setText("Password Not Match!");
                return;
            }
            if (file == null) {
                photoErr.setText("Upload Photo!");
                return;
            }else {
                photoErr.setText("");
            }
                String registerWho =loginWindowController.registerWho;
        if(DataSource.getInstance().saveNewUser(name,email,mobile,dob,password,registerWho,fin,(int)file.length())){
            opregisterMsg.setText(registerWho+" Data Saved!");
        }else {
            opregisterMsg.setTextFill(RED);
            opregisterMsg.setText("Some problem!");
        }
    }
    public void onExitButtonClick() {
        Stage stage = (Stage)exitBtn.getScene().getWindow();
        stage.close();
    }
    public void onResetButtonClick() {
      nameField.setText("");
        emailField.setText("");
        mobileField.setText("");
        dobField.setValue(null);
        passwordField.setText("");
        repasswordField.setText("");
        imageBox.setImage(defaultImage);
        nameErr.setText("");
        emailErr.setText("");
        mobileErr.setText("");
        dobErr.setText("");
        passwordErr.setText("");
        repasswordErr.setText("");
        photoErr.setText("");
    }

}
