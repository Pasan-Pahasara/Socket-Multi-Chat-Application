package lk.ijse.multiChatApplication.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import lk.ijse.multiChatApplication.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.multiChatApplication.controller.LoginFormController.users;

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

    /**
     * Thread Was Constructed Using A Separate Runnable run Object
     */
    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] split = msg.split(" ");
                String terminal = split[0];
                System.out.println(terminal);
                StringBuilder buildFullMsg = new StringBuilder();
                for (int i = 1; i < split.length; i++) {
                    buildFullMsg.append(split[i]);
                }
                System.out.println(buildFullMsg);
                if (terminal.equalsIgnoreCase(LoginFormController.username + ":")) {
                    continue;
                } else if (buildFullMsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                messageRoom.appendText(msg + "\n");
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle Send Event(Print Username)
     */
    public void handleSendEvent(MouseEvent mouseEvent) {
        send();
        for (User user : users) {
            System.out.println(user.name);
        }
    }

    /**
     * Send Message
     */
    public void send() {
        String message = msgField.getText();
        writer.println(LoginFormController.username + ": " + message);
        messageRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        messageRoom.appendText("Me: " + message + "\n");
        msgField.setText("");
        if (message.equalsIgnoreCase("BYE") || (message.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }

    public void sendMessageByKey(KeyEvent keyEvent) {
    }

    public void selectEmoji(MouseEvent mouseEvent) {
    }

    public void selectImage(MouseEvent mouseEvent) {
    }

    public void handleProfileBtn(ActionEvent actionEvent) {
    }

    public void saveImage(ActionEvent actionEvent) {
    }

    public void chooseImageButton(ActionEvent actionEvent) {
    }

}
