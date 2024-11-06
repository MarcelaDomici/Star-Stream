package Screens;

import java.io.FileInputStream;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File; 
import java.io.FileNotFoundException;

public class EditProfile {
    private Stage stage = new Stage();
    private static int id=0;
    private String photo = null;
    private ProfileScreen _ProfileScreen;
    private User user;
    private Label _cityUser;
    private Label _relationShipUser;
    private Label _nameUser;
    private ImageView _imageSetProfile;


    @FXML
    private Button btnCancel;

    @FXML
    private Button btnChosePhoto;

    @FXML
    private Button btnEditProfile;

    @FXML
    private ComboBox<String> comboBoxCivil;

    @FXML
    private ComboBox<String> comboBoxVisibilityProfile;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtLocal;

    @FXML
    private TextField txtName;

    @FXML
    private ImageView profileImage;


    public EditProfile(int newId, ProfileScreen newScreen, Label city, Label relationShip, Label nameUser, ImageView ProfileImg) throws Exception{
        id = newId;
        user = List_User.getPoint(id).user[id];
        photo = user.getPhotoProfile();
        _ProfileScreen = newScreen;
        _cityUser = city;
        _nameUser = nameUser;
        _relationShipUser = relationShip;
        _imageSetProfile = ProfileImg;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenEditProfile.fxml"));
            loader.setController(this);
            Pane pane = loader.load();
            this.stage.setScene(new Scene(pane));
            this.stage.setTitle("Editar Perfil");
            Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
            stage.getIcons().add(image);
            this.stage.setResizable(false);
            this.stage.initStyle(StageStyle.UNDECORATED);
 
            pane.requestFocus();
            pane.setOnMouseClicked(event ->{
                pane.requestFocus();
            });

    }

    @FXML
    public void initialize(){

        txtIdade.setText(Integer.toString(user.getAge()));
        txtLocal.setText(user.getCity());
        txtName.setText(user.getName());
 
        comboBoxCivil.getItems().clear(); 
        comboBoxCivil.getItems().addAll("Solteiro(a)", "Namorando", "Casado(a)", "Divorciado(a)", "Viúvo(a)");

        String userChoice = user.getCivil();
        if (comboBoxCivil.getItems().contains(userChoice)) {
            comboBoxCivil.setValue(userChoice); 
        } else {
            comboBoxCivil.setValue("Solteiro"); 
        }
        
        //combo de visibilidade do perfil 
        comboBoxVisibilityProfile.getItems().clear(); 
        comboBoxVisibilityProfile.getItems().addAll("Perfil público", "Perfil privado");

        String choiseVisib = user.getProfileVisibility();
        if (comboBoxVisibilityProfile.getItems().contains(choiseVisib)) {
            comboBoxVisibilityProfile.setValue(choiseVisib); 
        } else {
            comboBoxVisibilityProfile.setValue("Perfil público"); 
        }

        try{
            if(user.getPhotoProfile()!=null){
                this.profileImage.setImage(new Image(new FileInputStream(user.getPhotoProfile())));
                this.profileImage.setFitHeight(71);
                this.profileImage.setFitWidth(71);
                Circle circle = new Circle(35.5, 35.5, 35.5);
                profileImage.setClip(circle);
            }

        }catch(Exception ie){
            ie.printStackTrace();
        }

    }

    public Stage getStage(){return this.stage;}
        @FXML
        private void onlyNumber() {
        txtIdade.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if(!newValue.equals("")) Integer.parseInt(newValue);
            } catch (Exception ex) {
                txtIdade.setText(oldValue);
            }
        });
    }

    @FXML
        private void onlyChar() {
        txtName.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if(!newValue.equals("")){ 
                Integer.parseInt(newValue.substring(newValue.length()-1, newValue.length()));
                txtName.setText(oldValue);}
            } catch (Exception ex) {
                txtName.setText(newValue);
            }
        });
    }

    @FXML
    private void EditProfilebtn(MouseEvent event) {
        if(this.txtName.getText().length()==0){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }
        if(this.txtIdade.getText().length()==0){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }
        if(Integer.parseInt(txtIdade.getText()) < 16){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Ops! Parece que você ainda não tem a idade mínima \n necessária para usar o Star Stream. É preciso ter \n pelo menos 16 anos para acessar a nossa rede.");
            alert.showAndWait();
            return;
        }
        if(this.txtLocal.getText().length()==0){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }
        if(this.comboBoxCivil.getValue().equals("Selecione seu estado civil")){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }

        user.setName(txtName.getText());
        user.setAge(Integer.parseInt(txtIdade.getText()));
        user.setCity(txtLocal.getText());
        user.setCivil(comboBoxCivil.getValue());
        user.setProfileVisibility(comboBoxVisibilityProfile.getValue());
        user.setPhotoProfile((this.photo==null)? null: this.photo);
        _cityUser.setText(user.getCity());
        _nameUser.setText(user.getName());
        _relationShipUser.setText(user.getCivil());
        
        
        try{
            if(user.getPhotoProfile()!=null){
                this._imageSetProfile.setImage(new Image(new FileInputStream(user.getPhotoProfile())));
                this._imageSetProfile.setFitHeight(159);
                this._imageSetProfile.setFitWidth(159);
            }

        }catch(Exception ie){
            ie.printStackTrace();
        }
        //atualizar as minhas postagens
        
        _ProfileScreen.initialize(); 
        this.cancel(null);

    }

    @FXML
    private void chosePhoto(MouseEvent event) throws FileNotFoundException{
        FileChooser file = new FileChooser();
        file.setInitialDirectory(new File(System.getProperty("user.dir")));
        this.photo= file.showOpenDialog(stage).toPath().toString();
        this.profileImage.setImage(new Image(new FileInputStream(photo)));

        Circle circle = new Circle(35.5, 35.5, 35.5);
        profileImage.setClip(circle);
    }

    @FXML
    private void cancel(MouseEvent event) {
        _ProfileScreen.getPane().effectProperty().set(null);
        _ProfileScreen.getPane().toFront();
        _ProfileScreen.getPane().setDisable(false);
        this.stage.close();
    }
}
