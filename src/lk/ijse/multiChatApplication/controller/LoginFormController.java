package lk.ijse.multiChatApplication.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * @author : Pasan Pahasara
 * @since : 0.1.0
 **/
public class LoginFormController {
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

    public void registration(ActionEvent actionEvent) {
    }

    public void login(ActionEvent actionEvent) {
    }

    public void backLogin(MouseEvent mouseEvent) {
    }
}
