package Screens;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Body.Chat;
import Body.Message;
import Body.Post;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EncaminharScreen {
    private static int id = 0;
    private Stage stage = new Stage();
    private Pane pane;
    private Pane otherPane;
    private User user;
    private Post post;

    @FXML
    private ImageView ExitEncaminharScreen;

    @FXML
    private ScrollPane ScrollEncaminhar;

    @FXML
    private VBox VboxEncaminhar;

    public EncaminharScreen(int newId, Pane newPane, Post newPost) throws Exception {
        id = newId;
        otherPane = newPane;
        user = List_User.getPoint(2).user[id];
        post = newPost;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenEncaminhar.fxml"));
        loader.setController(this);
        pane = loader.load();
        this.stage.setScene(new Scene(pane));
        this.stage.setTitle("Depoimento");
        Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
            stage.getIcons().add(image);
        this.stage.setResizable(false);
        this.stage.initStyle(StageStyle.UNDECORATED);

        pane.requestFocus();
        pane.setOnMouseClicked(event -> {
            pane.requestFocus();
        });
    }

    public Stage getStage() {
        return this.stage;
    }

    public Pane getPane() {
        return this.pane;
    }

    @FXML
    private void initialize() throws FileNotFoundException {

        ExitEncaminharScreen.setCursor(Cursor.HAND);
        listFriends();
    }

    private void listFriends() throws FileNotFoundException {

        if (!user.getFriends().isEmpty()) {

            for (int i = 0; i <= user.getFriends().size() - 1; i++) {

                int index = user.getFriends().get(i);

                ImageView imgIConFriend = (List_User.getPoint(2).user[index].getPhotoProfile() == null)
                        ? new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                        : new ImageView(
                                new Image(new FileInputStream(List_User.getPoint(2).user[index].getPhotoProfile())));
                imgIConFriend.setFitHeight(48);
                imgIConFriend.setFitWidth(48);

                Circle circle = new Circle(24, 24, 24);
                imgIConFriend.setClip(circle);

                // nome do amigo
                Label userName = new Label(List_User.getPoint(2).user[index].getName());
                userName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                userName.setPadding(new Insets(15, 0, 0, 10));

                HBox hBoxLeft = new HBox(10);
                hBoxLeft.getChildren().addAll(imgIConFriend, userName);
                hBoxLeft.setPadding(new Insets(5, 10, 5, 10));
                hBoxLeft.setCursor(Cursor.HAND);

                hBoxLeft.setOnMouseClicked(event->{

                    sendPostChat(index, post);

                     Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Aviso!");
                    alert.setContentText("Post enviado com sucesso para: \n\t"+List_User.getPoint(2).user[index].getName());
                    alert.showAndWait();

                    ExitEncaminharScren(event);
                });

                this.VboxEncaminhar.getChildren().addAll(new Separator(), hBoxLeft, new Separator());
            }
        }

    }

    private void sendPostChat(int idFriend, Post postC){

        User user = List_User.getPoint(idFriend).user[id];
        User userFriend = List_User.getPoint(idFriend).user[idFriend];
        Chat chat;    
            if(!user.getChats().containsKey(idFriend)&&!userFriend.getChats().containsKey(id)){
                chat = new Chat();
                user.getChats().put(idFriend, chat);
                user.getDequeChat().add(idFriend);
                userFriend.getChats().put(id, chat);
                userFriend.getDequeChat().add(id);
            }
        chat = user.getChats().get(idFriend);
        Message message = new Message();
        message.setSender((short)id);
        message.setReceptor((short)idFriend);
        message.setPost(postC);
        chat.add(message);

        user.getDequeChat().remove((Integer)idFriend);
        user.getDequeChat().addFirst(idFriend);
        userFriend.getDequeChat().remove((Integer)id);
        userFriend.getDequeChat().addFirst((Integer)id);

    }

    @FXML
    private void ExitEncaminharScren(MouseEvent event) {
        this.otherPane.effectProperty().set(null);
        otherPane.setDisable(false);
        this.stage.close();
    }

}
