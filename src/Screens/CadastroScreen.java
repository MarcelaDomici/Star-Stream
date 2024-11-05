package Screens;

import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastroScreen {
    private Stage stage = new Stage();

    @FXML
    private CheckBox check1;

    @FXML
    private CheckBox check2;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtLocal;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtPassConfirm;

    @FXML
    private Button btnCadastrar;

    @FXML
    private PasswordField txtOcultPass;

    @FXML
    private PasswordField txtOcultPass1;

    @FXML
    private Hyperlink hyperlinkBack;

    @FXML
    private ComboBox<String> comboBoxCivil;

        public CadastroScreen()throws Exception{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenCadastro.fxml"));
            loader.setController(this);
            Pane pane = loader.load();
            stage.setScene(new Scene(pane));
            stage.setTitle("Tala Cadastro");
            stage.setResizable(false);
            this.txtOcultPass.setVisible(true);
            this.txtOcultPass1.setVisible(true);
            pane.requestFocus();
            pane.setOnMouseClicked(event ->{
                pane.requestFocus();
            });
            this.txtPass.setVisible(false);
            this.txtPassConfirm.setVisible(false);

            {
                this.comboBoxCivil.getItems().addAll(new String[]{
                    "Solteiro", "Namorando","Casado","Divorciado", "Viúvo"
                });
            }

        }
        public Stage getStage(){return this.stage;}

    @FXML
    private void BackToLogin(MouseEvent event)throws Exception {
        new LoginScreen().getStage().show();
        this.stage.close();
    }

    @FXML
    private void checkPass(MouseEvent event) {
            if(this.check1.isSelected()){
                this.txtOcultPass.setVisible(false);
                this.txtPass.setVisible(true);
                this.txtPass.setText(this.txtOcultPass.getText());
            }else{
                this.txtPass.setVisible(true);
                this.txtOcultPass.setVisible(true);
                this.txtOcultPass.setText(this.txtOcultPass1.getText());
            }
    }

    @FXML
    private void checkPassConfirm(MouseEvent event) {
            if(this.check2.isSelected()){
                this.txtOcultPass1.setVisible(false);
                this.txtPassConfirm.setVisible(true);
                this.txtPassConfirm.setText(txtOcultPass1.getText());
            }else{
                this.txtPassConfirm.setVisible(false);
                this.txtOcultPass1.setVisible(true);
                this.txtOcultPass1.setText(this.txtPassConfirm.getText());
            }
    }

    @FXML
    private void actionSignUser(MouseEvent event) throws Exception{
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

        if(this.txtEmail.getText().length()==0){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
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
        String pass = (this.txtPass.isVisible())? this.txtPass.getText(): this.txtOcultPass.getText();
        String pass2 = (this.txtPassConfirm.isVisible())?this.txtPassConfirm.getText(): this.txtOcultPass1.getText(); 
        if(!pass.equals(pass2)){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Senhas estão diferentes");
            alert.showAndWait();
            return;
        }

        if(List_User.getPoint(0)!=null){
            if(List_User.getPoint(0).checkExistUser(this.txtEmail.getText())){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Aviso!");
                alert.setHeaderText(null);
                alert.setContentText("Usuário já existente no Sistema");
                alert.showAndWait();
                return;
            }
        }

        User user = new User();
        user.setName(txtName.getText());
        user.setPassword(pass);
        user.setAge(Integer.parseInt(txtIdade.getText()));
        user.setCity(txtLocal.getText());
        user.setCivil(comboBoxCivil.getValue());
        user.setEmail(txtEmail.getText());
        List_User.getPoint(10).add(user);
        
        new LoginScreen().getStage().show();
        this.stage.close();
    }

}
