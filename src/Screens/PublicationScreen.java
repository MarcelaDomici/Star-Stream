package Screens;

import Body.ManagerPosts;
import Body.Post;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 

public class PublicationScreen {
    private Stage stage = new Stage();
    private static int id =0;
    private String photo = null;
    private Uptable _uptableScreen;  // Usar a interface

    @FXML
    private ImageView photoPublic;

    @FXML
    private Button btnFile;
    
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnPublic;

    @FXML
    private ImageView imageSetProfile;

    @FXML
    private TextArea txtPublic;

    @FXML
    private TextField txtTitlePost;

        public PublicationScreen(int i, Stage ownerStage, Uptable uptableScreen) throws Exception{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenPublication.fxml"));
            loader.setController(this);
            Pane pane = loader.load();
            stage.setScene(new Scene(pane));
            stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
            stage.setTitle("Publicar");
            Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
            stage.getIcons().add(image);
            stage.setResizable(false);
            pane.requestFocus();
            pane.setOnMouseClicked(event->{
                pane.requestFocus();
            });

            // Definir o stage principal como "dono" da nova janela
            stage.initOwner(ownerStage);  // Aqui a janela é "filha" da tela que a chamou
            stage.initModality(javafx.stage.Modality.WINDOW_MODAL);  // Faz com que a janela seja modal
            pane.requestFocus();
            pane.setOnMouseClicked(event -> {
                pane.requestFocus();
            });

            id=i;
            this._uptableScreen = uptableScreen;  // Inicializar com a tela que chamou
    
            this.photoPublic.setOnDragOver(event -> {
                System.out.println(event.getDragboard().getString());
                if(event.getGestureSource() != this.photoPublic && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(javafx.scene.input.TransferMode.COPY);
                }
                event.consume();
                
            });

        }

    public Stage getStage(){return this.stage;}

   
    private static short i=0;
    
    @FXML
    private void PublicPost(MouseEvent event)throws Exception {
        if(this.txtPublic.getText().length()==0 || this.txtTitlePost.getText().length()==0){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Aviso!");
            alert.setContentText("Contém Campos vazios");
            alert.showAndWait();
            return;
        }
            ++i;
            Post post = new Post();
            post.setId(i);
            post.setIduser((short)id);
            post.setImagem((this.photo==null)? null: this.photo);
            post.setTitle(this.txtTitlePost.getText());
            post.setPostTxt(this.txtPublic.getText());
            List_User.getPoint(0).user[id].getPosts().add(post);
            ManagerPosts.geralPosts.add(post);
            this.stage.close();

            // Chamar o método de atualização na tela que chamou
        if (_uptableScreen != null) {
            _uptableScreen.update();  // Atualiza a tela que chamou
        }
    }

    @FXML
    private void chooseFile(MouseEvent event)throws FileNotFoundException {
        FileChooser file = new FileChooser();
        file.setInitialDirectory(new File(System.getProperty("user.dir")));
        this.photo= file.showOpenDialog(stage).toPath().toString();
        this.photoPublic.setImage(new Image(new FileInputStream(photo)));
    }

     @FXML
    private void backToLogin(MouseEvent event)throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToSettings(MouseEvent event)throws Exception {
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void CancelPost(MouseEvent event)throws Exception {
        this.stage.close();
    }

    @FXML
    private void goToProfile(MouseEvent event) throws Exception {
        new ProfileScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToChat(MouseEvent event) throws Exception{
        new ChatScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    void goToProfileTop(MouseEvent event) throws Exception {
        new ProfileScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToFriends(MouseEvent event)throws Exception {
        new FriendsScreen(id).getStage().show();
        this.stage.close();
    }
}
