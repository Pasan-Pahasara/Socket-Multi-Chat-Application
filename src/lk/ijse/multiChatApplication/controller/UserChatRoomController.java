package lk.ijse.multiChatApplication.controller;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.multiChatApplication.model.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
    public boolean toggleChat = false, toggleProfile = false;
    public boolean saveControl = false;
    public VBox vBox;
    public ImageView emojiBtn;

    Socket socket;
    BufferedReader reader;
    PrintWriter writer;

    private FileChooser fileChooser;
    private File filePath;


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

                String[] massageAr = msg.split(" ");
                String string = "";
                for (int i = 0; i < massageAr.length - 1; i++) {
                    string += massageAr[i + 1] + " ";
                }

                Text text = new Text(string);
                String fChar = "";

                if (string.length() > 3) {
                    fChar = string.substring(0, 3);
                }

                if (fChar.equalsIgnoreCase("img")) {
                    string = string.substring(3, string.length() - 1);

                    File file = new File(string);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitWidth(100);
                    imageView.setFitHeight(70);

                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);

                    if (!terminal.equalsIgnoreCase(clientName.getText())) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);

                        Text text1 = new Text("  " + terminal + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);
                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        hBox.getChildren().add(text1);
                    }

                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));

                } else {
                    TextFlow tempTextFlow = new TextFlow();

                    if (!terminal.equalsIgnoreCase(clientName.getText() + ":")) {
                        Text name = new Text(terminal + " ");
                        name.getStyleClass().add("name");
                        tempTextFlow.getChildren().add(name);
                    }

                    tempTextFlow.getChildren().add(text);
                    tempTextFlow.setMaxWidth(120);

                    TextFlow textFlow = new TextFlow(tempTextFlow);
                    textFlow.setStyle("-fx-background-color:#FD85FF;"+"-fx-background-radius: 20px");
                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    HBox hBox = new HBox(10);
                    hBox.setPadding(new Insets(5));

                    if (!terminal.equalsIgnoreCase(clientName.getText() + ":")) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(textFlow);
                    } else {
                        Text text1 = new Text(buildFullMsg + ": Me");
                        TextFlow textFlow1 = new TextFlow(text1);
                        textFlow1.setStyle("-fx-background-color:#6EDFC5;"+"-fx-background-radius: 20px");
                        textFlow1.setPadding(new Insets(5, 10, 5, 10));
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(textFlow1);
                    }
                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
                }

                System.out.println(buildFullMsg);
                if (terminal.equalsIgnoreCase(LoginFormController.username + ":")) {
                    continue;
                } else if (buildFullMsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
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
        msgField.setText("");
        if (message.equalsIgnoreCase("BYE") || (message.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }

    /**
     * Send Message By Key
     */
    public void sendMessageByKey(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            send();
        }
    }

    /**
     * Navigations To View Profile
     */
    public void handleProfileBtn(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(profileBtn) && !toggleProfile) {
            new FadeIn(profile).play();
            profile.toFront();
            chat.toBack();
            toggleProfile = true;
            toggleChat = false;
            setProfile();
        } else if (actionEvent.getSource().equals(profileBtn) && toggleProfile) {
            new FadeIn(chat).play();
            chat.toFront();
            toggleProfile = false;
            toggleChat = false;
        }
    }

    /**
     * Set Profile
     */
    private void setProfile() {
        for (User user : users) {
            if (LoginFormController.username.equalsIgnoreCase(user.name)) {
                fullName.setText(user.name);
                fullName.setOpacity(1);
                phoneNo.setText(user.phoneNo);
                gender.setText(user.gender);
            }
        }
    }

    /**
     * Choose Profile Image
     */
    public void chooseImageButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        try {
            this.filePath = fileChooser.showOpenDialog(stage);
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "Empty Select");
        }
        fileChoosePath.setText(filePath.getPath());
        saveControl = true;
    }

    /**
     * Save Image
     */
    public void saveImage(ActionEvent actionEvent) {
        if (saveControl) {
            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                proImage.setImage(image);
                showProPic.setFill(new ImagePattern(image));
                saveControl = false;
                fileChoosePath.setText("");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Choose Sending Images
     */
    public void selectImage(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image");
            this.filePath = fileChooser.showOpenDialog(stage);
            writer.println(clientName.getText() + " " + "img" + filePath.getPath());
            writer.flush();
        }catch (NullPointerException e){
            System.out.println("Image is not Selected!");
        }
    }

    public void selectEmoji(MouseEvent mouseEvent) {
    }

}
