package Screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Body.Community;
import Body.ManagerCommunitys;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewCommunityScreen {
    private static int id = 0;
    private Stage stage = new Stage();
    private CommunityScreen _screenCommunity;
    private String photoC = null;
    private User user;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnChooseImage;

    @FXML
    private Button btnCreateCommunity;

    @FXML
    private ComboBox<String> comboVisibility;

    @FXML
    private ImageView imgNewCommunity;

    @FXML
    private TextField txtCommunityName;

    @FXML
    private TextArea txtCommunityText;

    public NewCommunityScreen(int newId, CommunityScreen newScreen) throws Exception {
        id = newId;
        _screenCommunity = newScreen;
        user = List_User.getPoint(2).user[id];

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenNewCommunity.fxml"));
        loader.setController(this);
        Pane pane = loader.load();
        this.stage.setScene(new Scene(pane));
        this.stage.setTitle("Editar Perfil");
        this.stage.setResizable(false);
        this.stage.initStyle(StageStyle.UNDECORATED);

        pane.requestFocus();
        pane.setOnMouseClicked(event -> {
            pane.requestFocus();
        });

        comboVisibility.getItems().addAll(new String[] {
                "Público", "Privado"
        });
    }

    public Stage getStage() {
        return this.stage;
    }

    @FXML
    private void chooseFile(MouseEvent event) throws FileNotFoundException {
        FileChooser file = new FileChooser();
        file.setInitialDirectory(new File(System.getProperty("user.dir")));
        this.photoC = file.showOpenDialog(stage).toPath().toString();
        this.imgNewCommunity.setImage(new Image(new FileInputStream(photoC)));

        Circle circle = new Circle(50, 50, 50);
        imgNewCommunity.setClip(circle);
    }

    @FXML
    private void createNewCommunity(MouseEvent event) throws Exception {

        if (txtCommunityName.getText().length() == 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }
        if (txtCommunityText.getText().length() == 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }

        Community community = new Community();
        community.setName(txtCommunityName.getText());
        community.setTxtCommunity(txtCommunityText.getText());
        community.setPhotoCommunity((photoC==null)? null: photoC);
        community.setIdOwner(id);
        community.setCommunityVisibility(comboVisibility.getValue());
        community.setId(ManagerCommunitys.allCommunitys.size());
        
        ManagerCommunitys.allCommunitys.add(community);
        user.getCommunitysUser().add(ManagerCommunitys.allCommunitys.size()-1);

        _screenCommunity.initialize();
        cancel(event);

    }

    @FXML
    private void cancel(MouseEvent event) throws Exception {
        _screenCommunity.getPane().effectProperty().set(null);
        _screenCommunity.getPane().toFront();
        _screenCommunity.getPane().setDisable(false);
        this.stage.close();
    }

}
