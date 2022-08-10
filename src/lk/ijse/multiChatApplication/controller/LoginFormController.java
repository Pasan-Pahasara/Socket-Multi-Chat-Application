package lk.ijse.multiChatApplication.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.multiChatApplication.model.User;

import java.util.ArrayList;

/**
 * @author : Pasan Pahasara
 * @since : 0.1.0
 **/
public class LoginFormController {
    public static ArrayList<User> users = new ArrayList<>();
    public Pane pnSignIn;
    public PasswordField txtPassword;
    public TextField txtUsername;
    public Label loginNotifier;
    public JFXButton btnSignUp;
    public Pane pnSignUp;
    public ImageView btnBack;
    public TextField txtRegName;
    public TextField txtRegPassword;
    public TextField txtRegPhoneNo;
    public ToggleGroup Gender;
    public RadioButton male;
    public RadioButton female;
    public Label controlRegLabel;
    public Label success;
    public Label goBack;
    public JFXButton btnLogin;


    /**
     * Navigations To SignUp And Login
     */
    public void handleButtonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(btnSignUp)) {
            new FadeTransition().play();
            pnSignUp.toFront();
        }
        if (actionEvent.getSource().equals(btnLogin)) {
            new FadeTransition().play();
            pnSignIn.toFront();
        }
        loginNotifier.setOpacity(0);
        txtUsername.setText("");
        txtPassword.setText("");
    }

    /**
     * User Registration
     */
    public void registration(ActionEvent actionEvent) {
        if (!txtRegName.getText().equalsIgnoreCase("")
                && !txtRegPassword.getText().equalsIgnoreCase("")
                && !txtRegPhoneNo.getText().equalsIgnoreCase("")
                && (male.isSelected() || female.isSelected())) {
            if (checkUser(txtRegName.getText())) {
                User newUser = new User();
                newUser.name = txtRegName.getText();
                newUser.password = txtRegPassword.getText();
                newUser.phoneNo = txtRegPhoneNo.getText();
                if (male.isSelected()) {
                    newUser.gender = "Male";
                } else {
                    newUser.gender = "Female";
                }
                users.add(newUser);
                goBack.setOpacity(1);
                success.setOpacity(1);
                makeDefault();
            }
        }
    }

    /**
     * Default Details
     */
    private void makeDefault() {
        txtRegName.setText("");
        txtRegPassword.setText("");
        txtRegPhoneNo.setText("");
        male.setSelected(true);
    }

    /**
     * Check User
     */
    private boolean checkUser(String username) {
        for (User user : users) {
            if (user.name.equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }

    public void login(ActionEvent actionEvent) {
    }

    public void backLogin(MouseEvent mouseEvent) {
    }
}
