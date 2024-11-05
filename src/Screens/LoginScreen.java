package Screens;

import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginScreen {
    private Stage stage = new Stage();
    private Pane pane;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnEntrar;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPass;

    @FXML
    private ImageView eyePass;

    @FXML
    private PasswordField passOcult;

    @FXML
    private Hyperlink linkEmail;
        
        public LoginScreen()throws Exception{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenLogin.fxml"));
            loader.setController(this);
            pane = loader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.setTitle("Tela login:");
            stage.setResizable(false);
            pane.toFront();
            pane.requestFocus();
            pane.setOnMouseClicked(event ->{
                pane.requestFocus();
            });
            this.txtPass.setVisible(false);
            this.passOcult.setLayoutX(this.txtPass.getLayoutX());
            this.passOcult.setLayoutY(this.txtPass.getLayoutY());

/* 
                {
                    this.txtEmail.setText("mar");
                    this.passOcult.setText("1234");
                }
*/
            
        }

        public Stage getStage(){
            return this.stage;
        }

        @FXML
        private void enterToHome(MouseEvent event) throws Exception{
            String txt = (this.passOcult.isVisible())?this.passOcult.getText():this.txtPass.getText();
            if(this.txtEmail.getText().length()==0 || txt.length()==0){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Aviso");
                alert.setHeaderText(null);
                alert.setContentText("Há campos vazios");
                alert.showAndWait();
                return;
            }
                if(List_User.getPoint(0).checkUser(this.txtEmail.getText(), (this.passOcult.isVisible())?this.passOcult.getText():this.txtPass.getText())){
                    new HomeScreen(List_User.getPoint(0).getId(this.txtEmail.getText(),(this.passOcult.isVisible())?this.passOcult.getText():this.txtPass.getText())).getStage().show();
                    this.stage.close();
                }else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Aviso!");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuário ou Senha inválidos");
                    alert.showAndWait();
                }
        }
        
        @FXML
        private void enterToSign(MouseEvent event) throws Exception {
            new CadastroScreen().getStage().show();
            this.stage.close();
        }
        
        @FXML
        private void showPassword(MouseEvent event) {
            if(passOcult.isVisible()){
                this.eyePass.setImage(new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/eye.png")));
                this.txtPass.setText(this.passOcult.getText());
                this.txtPass.setVisible(true);
                this.passOcult.setVisible(false);
            }else{
                this.eyePass.setImage(new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/eye-off.png")));
                this.passOcult.setText(this.txtPass.getText());
                this.passOcult.setVisible(true);
                this.txtPass.setVisible(false);
            }
        }
        @FXML
        private void getNewPassword(MouseEvent event)throws Exception {
            new GenarateEmail(this.pane).getStage().show();
            pane.effectProperty().set(new MotionBlur(3.0,15.0));
            pane.setDisable(true);
        }
}
