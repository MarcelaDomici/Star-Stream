package Screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Body.Community;
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

public class EditCommunity {
    private Stage stage = new Stage();
    private String photo = null;
    private PublicsCommunityScreen _PublicsCommunityScreen;
    private Community community;
 

    @FXML
    private ImageView ImageCommunity;

    @FXML
    private TextArea TxtTextoC;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEditCom;

    @FXML
    private Button chooseImage;

    @FXML
    private ComboBox<String> comboBoxCom;

    @FXML
    private TextField txtNameC;

    public EditCommunity(PublicsCommunityScreen newScreen,Community newCommunity)throws Exception{

        _PublicsCommunityScreen = newScreen;
        community = newCommunity;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenEditCommunity.fxml"));
            loader.setController(this);
            Pane pane = loader.load();
            this.stage.setScene(new Scene(pane));
            this.stage.setTitle("Editar comunidade");
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

    @FXML
    private void initialize(){

        TxtTextoC.setText(community.getTxtCommunity());
        txtNameC.setText(community.getName());


        comboBoxCom.getItems().clear(); 
        comboBoxCom.getItems().addAll("Perfil público", "Perfil privado");

        String choiseVisib = community.getCommunityVisibility();
        if (comboBoxCom.getItems().contains(choiseVisib)) {
            comboBoxCom.setValue(choiseVisib); 
        } else {
            comboBoxCom.setValue("Perfil público"); 
        }


        try{
            if(community.getPhotoCommunity()!=null){
                ImageCommunity.setImage(new Image(new FileInputStream(community.getPhotoCommunity())));
                ImageCommunity.setFitHeight(100);
                ImageCommunity.setFitWidth(100);
                Circle circle = new Circle(50,50,50);
                ImageCommunity.setClip(circle);
            }

        }catch(Exception ie){
            ie.printStackTrace();
        }

    }


    @FXML
    private void EditCommunitybtn(MouseEvent event) {

        if(txtNameC.getText().length()==0 || TxtTextoC.getText().length()==0){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }

        community.setName(txtNameC.getText());
        community.setTxtCommunity(TxtTextoC.getText());
        community.setPhotoCommunity((photo==null)? null: photo);
        community.setCommunityVisibility(comboBoxCom.getValue());

        try{
            if(community.getPhotoCommunity()!=null){
                ImageCommunity.setImage(new Image(new FileInputStream(community.getPhotoCommunity())));
                ImageCommunity.setFitHeight(159);
                ImageCommunity.setFitWidth(159);
            }

        }catch(Exception ie){
            ie.printStackTrace();
        }

        _PublicsCommunityScreen.initialize();
        cancel(event);
    }

    @FXML
    private void cancel(MouseEvent event) {
        _PublicsCommunityScreen.getPane().effectProperty().set(null);
        _PublicsCommunityScreen.getPane().toFront();
        _PublicsCommunityScreen.getPane().setDisable(false);
        this.stage.close();
    }

    @FXML
    private void chooseImage(MouseEvent event)throws FileNotFoundException {

        FileChooser file = new FileChooser();
        file.setInitialDirectory(new File(System.getProperty("user.dir")));
        this.photo= file.showOpenDialog(stage).toPath().toString();
        ImageCommunity.setImage(new Image(new FileInputStream(photo)));

        Circle circle = new Circle(50,50,50);
        ImageCommunity.setClip(circle);
    }

}
