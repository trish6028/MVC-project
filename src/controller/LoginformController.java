package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginformController  {
    public Label lblerror;
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public AnchorPane LogInFormcontext;
    public JFXButton txtlog;


    public void btnLogin(ActionEvent actionEvent) throws IOException {
        String user = "admin";
        String password = "1234";
        if (this.txtUsername.getText().equals(user) && this.txtPassword.getText().equals(password)) {
            Stage window = (Stage)this.LogInFormcontext.getScene().getWindow();
            window.setScene(new Scene((Parent) FXMLLoader.load(this.getClass().getResource("/view/dashboardForm.fxml"))));
        } else if (this.txtUsername.getText().isEmpty() && this.txtPassword.getText().isEmpty()) {
            this.lblerror.setText("Your User Name Or Password IS Empty...!");
            this.txtUsername.clear();
            this.txtPassword.clear();
        } else if (!this.txtUsername.getText().equals(user)) {
            this.lblerror.setText("Your User Name is incorrect..!");
            this.txtUsername.clear();
            this.txtPassword.clear();
        } else if (!this.txtPassword.getText().equals(password)) {
            this.lblerror.setText("Your Password is incorrect..!");
            this.txtUsername.clear();
            this.txtPassword.clear();
        }

    }

    public void usernameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void pwOnAction(ActionEvent actionEvent) {

    }


}
