package Screens;

import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SettingsScreen {
    private Stage stage = new Stage();
    private static int id=0;

    @FXML
    private ImageView exitToHome;

    @FXML
    private ImageView imageSetProfile;

    @FXML
    private ImageView eyePass;

    @FXML
    private Button ChangeData;

    @FXML
    private Button DeleteAccount;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPass;

    @FXML
    private PasswordField txtpassOcult;

        public SettingsScreen(int i)throws Exception{
            id=i;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./ScreensFXML/ScreenSettings.fxml"));
            loader.setController(this);
            Pane pane = loader.load();
            stage.setScene(new Scene(pane));
            stage.setTitle("Settings");
            Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
            stage.getIcons().add(image);
            stage.setResizable(false);
            pane.requestFocus();
            pane.setOnMouseClicked(event->{
                pane.requestFocus();
            });
        }
    public Stage getStage(){
        return this.stage;
    }

    @FXML
    private void deleteData(MouseEvent event)throws Exception {

    }

    @FXML
        private void showPassword(MouseEvent event) {
            if(txtpassOcult.isVisible()){
                this.eyePass.setImage(new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/eye-off.png")));
                this.txtPass.setText(this.txtpassOcult.getText());
                this.txtPass.setVisible(true);
                this.txtpassOcult.setVisible(false);
            }else{
                this.eyePass.setImage(new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/eye.png")));
                this.txtpassOcult.setText(this.txtPass.getText());
                this.txtpassOcult.setVisible(true);
                this.txtPass.setVisible(false);
            }
        }

    @FXML
    private void setNewData(MouseEvent event) throws Exception{
        if(txtEmail.getText().length()!=0||txtPass.getText().length()!=0){
            if(txtEmail.getText().length()!=0){
                List_User.getPoint(0).user[id].setEmail(this.txtEmail.getText());
            }
            if(txtPass.getText().length()!=0){
                List_User.getPoint(0).user[id].setPassword(txtPass.getText());
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Aviso!");
            alert.setContentText("Dados Alterados com Sucesso!!!");
            alert.setHeaderText(null);
            alert.showAndWait();
            new LoginScreen().getStage().show();
            this.stage.close();
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setContentText("Campos vazios");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    private void backToLogin(MouseEvent event)throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }

}
