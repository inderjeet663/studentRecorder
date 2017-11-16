package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.dataModel.DataSource;

public class ResgisterStudentController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField fnameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField mobileField;
    @FXML
    private Label nameMsg;
    @FXML
    private Label fnameMsg;
    @FXML
    private Label ageMsg;
    @FXML
    private Label mobileMsg;
    @FXML
    private Label opMsg;
    @FXML
    private Button saveBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Pane mainPane;
    @FXML
    private Label usernameLabel;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton maleRB;
    @FXML
    private RadioButton femaleRB;
    public void initialize() {
        usernameLabel.setText(LoginStage.User);
    }
    public void onSaveButtonClick() {
        String name=nameField.getText().trim();
        String fname=fnameField.getText().trim();
        int age=0;
        String mobile=mobileField.getText().trim();
        String gender ;

        if (name.equals("")) {
            nameMsg.setText("Please Fill Name!");
            return;
        } else if (name.length()<3) {
            nameMsg.setText("Name too short!");
            return;
        }
        if (fname.equals("")) {
            fnameMsg.setText("Please Fill Father Name!");
            return;
        }else if (fname.length()<3) {
            fnameMsg.setText("Too short name!");
            return;
        }
        if (ageField.getText().trim().equals("")) {
            ageMsg.setText("Please Fill Age!");
            return;
        }else{
            try {
                age = Integer.valueOf(ageField.getText().trim());
                ageMsg.setText("");
            }catch (NumberFormatException e) {
                ageMsg.setText("Invalid age!");
                return;
            }
        } if (age<10 || age>100) {
            ageMsg.setText("Wrong age!");
            return;
        }else {
            ageMsg.setText("");
        }
        if (mobile.equals("")) {
            mobileMsg.setText("Please Fill Mobile!");
            return;
        } else if(mobile.length()!=10) {
            mobileMsg.setText("Enter 10 Digit ");
            return;
        } else if(!mobile.matches("[0-9]+")) {
            mobileMsg.setText("Digits Only!");
            return;
        }
        if(maleRB.isSelected()) {
            gender="Male";
        } else {
            gender="Female";
        }
        boolean flag=DataSource.getInstance().saveStudentData(name,fname,age,mobile,gender);
        if(flag) {
            opMsg.setTextFill(Color.GREEN);
            opMsg.setText("Data Saved!");
            clearFields();
            nameMsg.setText("");
            fnameMsg.setText("");
            ageMsg.setText("");
            mobileMsg.setText("");
            return;
        }
        opMsg.setTextFill(Color.RED);
        opMsg.setText("Some Problem!");
    }
    public  void onResetButtonClick() {
        clearFields();
        opMsg.setText("");
    }
    private void clearFields() {
        nameField.setText("");
        fnameField.setText("");
        ageField.setText("");
        mobileField.setText("");
        nameMsg.setText("");
        fnameMsg.setText("");
        ageMsg.setText("");
        mobileMsg.setText("");

    }
    public void onFieldSelected() {
        if(nameField.isFocused()) {
        nameMsg.setText(""); }
        if(fnameField.isFocused()) {
        fnameMsg.setText(""); }
        if(ageField.isFocused()) {
        ageMsg.setText(""); }
        if(mobileField.isFocused()) {
        mobileMsg.setText(""); }
    }
}
