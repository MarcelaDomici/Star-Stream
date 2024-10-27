package Screens;

import java.io.FileNotFoundException;

import Body.Chat;
import Body.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditMessage {
    private Stage stage = new Stage();
    private static int idFriend=-1;
    private Message message;
    private Chat chat;
    private ChatScreen _chatScreen;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnEdit;

    @FXML
    private TextArea textMessage;

        public EditMessage(int b, Chat _chat,Message messageT, ChatScreen _screenChatScreen)throws Exception{
            idFriend=b;
            this.message=messageT;
            this.chat=_chat;
            this._chatScreen=_screenChatScreen;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenEditMessage.fxml"));
            loader.setController(this);
            Pane pane = loader.load();
            this.stage.setScene(new Scene(pane));
            this.stage.setTitle("Editar Mensagem");
            this.stage.setResizable(false);
            this.stage.initStyle(StageStyle.UNDECORATED);

            pane.requestFocus();
            pane.setOnMouseClicked(event ->{
                pane.requestFocus();
            });

            this.textMessage.setText(messageT.getTxtMessage());
        }

        
    public Stage getStage(){return this.stage;}
    
    @FXML
    private void editText(MouseEvent event) throws FileNotFoundException {
        this.chat.editMessage(this.message.getId(), this.textMessage.getText());
        this._chatScreen.genareteViewChat(idFriend);
        _chatScreen.genarateChats();
        this.cancel(null);
    }

    @FXML
    private void cancel(MouseEvent event) {
        _chatScreen.getPane().effectProperty().set(null);
        _chatScreen.getPane().toFront();
        _chatScreen.getPane().setDisable(false);
        this.stage.close();
    }

    
}
