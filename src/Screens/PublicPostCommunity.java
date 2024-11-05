package Screens;

import java.io.File;
import java.io.FileInputStream;

import Body.Community;
import Body.Post;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PublicPostCommunity {
    private static int id = 0;
    private Stage stage = new Stage();
    private String photo = null;
    private PublicsCommunityScreen _screenCommunity;
    private Community community;

    @FXML
    private ImageView imagePost;

    @FXML
    private TextArea txtText;

    @FXML
    private TextField txtTitulo;

    public PublicPostCommunity(int newId, Community newCommunity, PublicsCommunityScreen newScreen) throws Exception {
        id = newId;
        community = newCommunity;
        _screenCommunity = newScreen;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenCommunityPublish.fxml"));
        loader.setController(this);
        Pane pane = loader.load();
        this.stage.setScene(new Scene(pane));
        this.stage.setTitle("Publicar");
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

    @FXML
    private void CancelPost(MouseEvent event) throws Exception {

        _screenCommunity.getPane().effectProperty().set(null);
        _screenCommunity.getPane().toFront();
        _screenCommunity.getPane().setDisable(false);
        this.stage.close();
    }

    private static short p = 0;

    @FXML
    private void PublicPost(MouseEvent event) throws Exception {

        if (txtText.getText().length() == 0 || txtTitulo.getText().length() == 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Aviso!");
            alert.setContentText("Contém Campos vazios");
            alert.showAndWait();
            return;
        }
        p++;
        Post post = new Post();
        post.setId(p);
        post.setIduser((short) id);
        post.setImagem((this.photo == null) ? null : this.photo);
        post.setTitle(txtTitulo.getText());
        post.setPostTxt(txtText.getText());
        community.getPostCommunity().add(post);

        //System.out.println(community.getPostCommunity().get(0).getTitle());

        _screenCommunity.initialize();
        CancelPost(event);
    }

    @FXML
    private void chooseFile(MouseEvent event) throws Exception {

        FileChooser file = new FileChooser();
        file.setInitialDirectory(new File(System.getProperty("user.dir")));
        this.photo = file.showOpenDialog(stage).toPath().toString();
        imagePost.setImage(new Image(new FileInputStream(photo)));
    }

}
