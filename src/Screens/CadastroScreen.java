package Screens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView eyePass;

    @FXML
    private ImageView eyePass1;

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

    public CadastroScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenCadastro.fxml"));
        loader.setController(this);
        Pane pane = loader.load();
        stage.setScene(new Scene(pane));
        stage.setTitle("Cadastro");
        Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
        stage.getIcons().add(image);
        stage.setResizable(false);
        this.txtOcultPass.setVisible(true);
        this.txtOcultPass1.setVisible(true);
        pane.requestFocus();
        pane.setOnMouseClicked(event -> {
            pane.requestFocus();
        });
        this.txtPass.setVisible(false);
        this.txtPassConfirm.setVisible(false);

        {
            this.comboBoxCivil.getItems().addAll(new String[] {
                    "Solteiro(a)", "Namorando", "Casado(a)", "Divorciado(a)", "Viúvo(a)"
            });
        }

    }

    public Stage getStage() {
        return this.stage;
    }

    @FXML
    private void BackToLogin(MouseEvent event) throws Exception {
        new LoginScreen().getStage().show();
        this.stage.close();
    }

    @FXML
    private void checkPass(MouseEvent event) {
    }

    private void checkPassConfirm(MouseEvent event) {

    }

    @FXML
    private void showPassword(MouseEvent event) {
        if (txtOcultPass.isVisible()) {
            this.eyePass.setImage(new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/eye-off.png")));
            this.txtPass.setText(this.txtOcultPass.getText());
            this.txtPass.setVisible(true);
            this.txtOcultPass.setVisible(false);
        } else {
            this.eyePass.setImage(new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/eye.png")));
            this.txtOcultPass.setText(this.txtPass.getText());
            this.txtOcultPass.setVisible(true);
            this.txtPass.setVisible(false);
        }
    }

    @FXML
    private void showPassword2(MouseEvent event) {
        if (txtOcultPass1.isVisible()) {
            this.eyePass1.setImage(new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/eye-off.png")));
            this.txtPassConfirm.setText(this.txtOcultPass1.getText());
            this.txtPassConfirm.setVisible(true);
            this.txtOcultPass1.setVisible(false);
        } else {
            this.eyePass1.setImage(new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/eye.png")));
            this.txtOcultPass1.setText(this.txtPassConfirm.getText());
            this.txtOcultPass1.setVisible(true);
            this.txtPassConfirm.setVisible(false);
        }
    }

    @FXML
    private void onlyNumber() {
        txtIdade.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.equals(""))
                    Integer.parseInt(newValue);
            } catch (Exception ex) {
                txtIdade.setText(oldValue);
            }
        });
    }

    @FXML
    private void onlyChar() {
        txtName.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.equals("")) {
                    Integer.parseInt(newValue.substring(newValue.length() - 1, newValue.length()));
                    txtName.setText(oldValue);
                }
            } catch (Exception ex) {
                txtName.setText(newValue);
            }
        });
    }

    @FXML
    private boolean validateEmail() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(txtEmail.getText());
        if (m.find() && m.group(0).equals(txtEmail.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validação de e-mail");
            alert.setHeaderText(null);
            alert.setContentText("Formato do e-mail incorreto, por favor, corrija e tente novamente.");
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void actionSignUser(MouseEvent event) throws Exception {
        if (this.txtName.getText().length() == 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }
        if (this.txtIdade.getText().length() == 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }
        if (Integer.parseInt(txtIdade.getText()) < 16) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Ops! Parece que você ainda não tem a idade mínima \n necessária para usar o Star Stream. É preciso ter \n pelo menos 16 anos para acessar a nossa rede.");
            alert.showAndWait();
            return;
        }

        if (this.txtEmail.getText().length() == 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }
        if (this.txtLocal.getText().length() == 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }
        if (this.comboBoxCivil.getValue().equals("Selecione seu estado civil")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Contém Campos Vázios");
            alert.showAndWait();
            return;
        }
        String pass = (this.txtPass.isVisible()) ? this.txtPass.getText() : this.txtOcultPass.getText();
        String pass2 = (this.txtPassConfirm.isVisible()) ? this.txtPassConfirm.getText() : this.txtOcultPass1.getText();
        if (!pass.equals(pass2)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Senhas estão diferentes");
            alert.showAndWait();
            return;
        }

        if (List_User.getPoint(0) != null) {
            if (List_User.getPoint(0).checkExistUser(this.txtEmail.getText())) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Aviso!");
                alert.setHeaderText(null);
                alert.setContentText("Usuário já existente no Sistema");
                alert.showAndWait();
                return;
            }
        }

        if (validateEmail()) {
            try {
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
            } catch (Exception e) {
                return;
            }
        }
    }

}
