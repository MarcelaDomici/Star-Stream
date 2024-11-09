package Screens;

import java.io.FileNotFoundException;

import Body.Chat;
import Body.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditMessage {
    private Stage stage = new Stage();
    private static int idFriend = -1;
    private Message message;
    private Chat chat;
    private ChatScreen _chatScreen = null;
    private ChatCommunity _chatCommunity = null;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnEdit;

    @FXML
    private TextArea textMessage;

    public EditMessage(int b, Chat _chat, Message messageT, ChatScreen _newChatScreen,
            ChatCommunity _newChatCommunity) throws Exception {
        idFriend = b;
        this.message = messageT;
        this.chat = _chat;
        _chatScreen = _newChatScreen;
        _chatCommunity = _newChatCommunity;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenEditMessage.fxml"));
        loader.setController(this);
        Pane pane = loader.load();
        this.stage.setScene(new Scene(pane));
        this.stage.setTitle("Editar mensagem");
        Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
        stage.getIcons().add(image);
        this.stage.setResizable(false);
        this.stage.initStyle(StageStyle.UNDECORATED);

        pane.requestFocus();
        pane.setOnMouseClicked(event -> {
            pane.requestFocus();
        });

        this.textMessage.setText(messageT.getTxtMessage());
    }

    public Stage getStage() {
        return this.stage;
    }

    @FXML
    private void editText(MouseEvent event) throws FileNotFoundException {
        this.chat.editMessage(this.message.getId(), this.textMessage.getText());

        cancel(null);
    }

    @FXML
    private void cancel(MouseEvent event) {

        if (_chatScreen == null) {

            _chatCommunity.getPane().effectProperty().set(null);
            _chatCommunity.getPane().toFront();
            _chatCommunity.getPane().setDisable(false);
            _chatCommunity.initialize();
            stage.close();

        } else {

            _chatScreen.getPane().effectProperty().set(null);
            _chatScreen.getPane().toFront();
            _chatScreen.getPane().setDisable(false);
            _chatScreen.genareteViewChat(idFriend);
            this.stage.close();
        }

    }

}
