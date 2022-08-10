package lk.ijse.multiChatApplication.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * @author : Pasan Pahasara
 * @since : 0.1.0
 **/
public class UserChatRoomController {
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
