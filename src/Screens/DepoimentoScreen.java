package Screens;

import Body.Depoimento;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DepoimentoScreen {
    private Stage stage = new Stage();
    private static int id = 0;
    private static int idAmg = 0;
    private static FriendProfile _FriendProfile;
    private User user;
    private Pane pane;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnPublic;

    @FXML
    private TextArea textMessage;

    public DepoimentoScreen(int newId, int newIdAmg, FriendProfile newFriendProfile)throws Exception{

        id = newId;
        idAmg = newIdAmg;
        _FriendProfile = newFriendProfile;
        user = List_User.getPoint(idAmg).user[idAmg];

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenDepoimento.fxml"));
            loader.setController(this);
            pane = loader.load();
            this.stage.setScene(new Scene(pane));
            this.stage.setTitle("Depoimento");
            Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
            stage.getIcons().add(image);
            this.stage.setResizable(false);
            this.stage.initStyle(StageStyle.UNDECORATED);

            pane.requestFocus();
            pane.setOnMouseClicked(event ->{
                pane.requestFocus();
            });
    }

    public Stage getStage(){return this.stage;}
    public Pane getPane(){return this.pane;}


    @FXML
    private void publicDepoimento(MouseEvent event) {

        if(this.textMessage.getText().length()==0){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }

        Depoimento depoimento = new Depoimento();
        depoimento.setDepoimento(textMessage.getText());
        depoimento.setIdAmg(id);

        user.getDepoimentos().add(depoimento);
        _FriendProfile.clearPosts();
        _FriendProfile.initialize();

        cancel(null);

    }

    @FXML
    private void cancel(MouseEvent event) {

        _FriendProfile.getPane().effectProperty().set(null);
        _FriendProfile.getPane().toFront();
        _FriendProfile.getPane().setDisable(false);
        this.stage.close();
    }
}
