package lk.ijse.multiChatApplication.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author : Pasan Pahasara
 * @since : 0.1.0
 **/
public class UserChatRoomController extends Thread implements Initializable {
    public Pane chat;
    public TextArea messageRoom;
    public TextField msgField;
    public Circle showProPic;
    public Label clientName;
    public JFXButton profileBtn;
    public Pane profile;
    public Label fullName;
    public Label phoneNo;
    public Label gender;
    public ImageView proImage;
    public TextField fileChoosePath;

    Socket socket;
    BufferedReader reader;
    PrintWriter writer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientName.setText(LoginFormController.username);
        connectSocket();
    }

    /**
     * Connect Socket
     */
    private void connectSocket() {
        try {
            socket = new Socket("localhost", 8000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageByKey(KeyEvent keyEvent) {
    }

    public void selectEmoji(MouseEvent mouseEvent) {
    }

    public void selectImage(MouseEvent mouseEvent) {
    }

    public void handleSendEvent(MouseEvent mouseEvent) {
    }

    public void handleProfileBtn(ActionEvent actionEvent) {
    }

    public void saveImage(ActionEvent actionEvent) {
    }

    public void chooseImageButton(ActionEvent actionEvent) {
    }

}
