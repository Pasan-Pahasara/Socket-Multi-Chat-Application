package lk.ijse.multiChatApplication.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.multiChatApplication.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author : Pasan Pahasara
 * @since : 0.1.0
 **/
public class LoginFormController {
    public static ArrayList<User> users = new ArrayList<>();
    public static String username, password, gender;
    public static ArrayList<User> loggedInUser = new ArrayList<>();
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

    /**
     * Login User Account
     */
    public void login(ActionEvent actionEvent) {
        username = txtUsername.getText();
        password = txtPassword.getText();
        boolean login = false;
        for (User x : users) {
            if (x.name.equalsIgnoreCase(username) && x.password.equalsIgnoreCase(password)) {
                login = true;
                loggedInUser.add(x);
                System.out.println(x.name);
                gender = x.gender;
                break;
            }
        }
        if (login) {
            changeWindow();
        } else {
            loginNotifier.setOpacity(1);
        }
    }

    /**
     * Navigations To Chat Room
     */
    private void changeWindow() {
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        URL resource = this.getClass().getResource("/lk/ijse/multiChatApplication/view/UserChatRoom.fxml");
        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.show();
        stage.setTitle(username + "");
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Navigations To LogIn
     */
    public void backLogin(MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(btnBack)) {
            pnSignIn.toFront();
        }
    }
}
