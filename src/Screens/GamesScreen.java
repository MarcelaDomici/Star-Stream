package Screens;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GamesScreen {
    private Stage stage = new Stage();
    private static int id = 0;

    @FXML
    private ImageView exitToHome;

    @FXML
    private ImageView viewSettings;

    @FXML
    private VBox Vbox_Bloquinhos;

    @FXML
    private VBox Vbox_Cobrinha;

    @FXML
    private VBox Vbox_JogoVelha;

    @FXML
    private VBox Vbox_Pong;

    @FXML
    private VBox Vbox_QuebraBlocos;

    @FXML
    private VBox Vbox_SpaceInvaders;

    public GamesScreen(int newId) throws Exception{
        
        id = newId;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenGames.fxml"));
            loader.setController(this);
            Pane pane = loader.load();
            this.stage.setScene(new Scene(pane));
            this.stage.setTitle("Jogos");
            Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
            stage.getIcons().add(image);
            this.stage.setResizable(false);

            pane.requestFocus();
    }
    public Stage getStage(){return this.stage;}

    @FXML
    void goToBloquinhos(MouseEvent event) {
        TetrisGame tetrisGame = new TetrisGame();
            Stage tetrisStage = new Stage();
            try {
                tetrisGame.start(tetrisStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    void goToSpaceInvaders(MouseEvent event) {

    }

    @FXML
    void goToCobrinha(MouseEvent event) {
        SnakeGame snakeGame = new SnakeGame();
            Stage snakeStage = new Stage();
            try {
                snakeGame.start(snakeStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    void goToJogoVelha(MouseEvent event) {

        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Jogo da Velha");
            alert.setHeaderText(null);
            alert.setContentText("Escolha uma opção de jogo");

            ButtonType buttonTypeOne = new ButtonType("Localmente");
            ButtonType buttonTypeTwo = new ButtonType("Contra o computador");

            alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeTwo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                JogoDaVelha jogo = new JogoDaVelha();
                Stage jogoStage = new Stage();
                try{
                    jogo.start(jogoStage);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }

            if(result.get() == buttonTypeTwo){
                JogoDaVelhaPC jogo = new JogoDaVelhaPC();
                Stage jogoStage = new Stage();
                try{
                    jogo.start(jogoStage);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        
    }

    @FXML
    void goToPong(MouseEvent event) {

        Pong pong = new Pong();
            Stage pongStage = new Stage();
            try {
                pong.start(pongStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    void goToQuebraBlocos(MouseEvent event) {

        BrickBreakerGame game = new BrickBreakerGame();
            Stage gameStage = new Stage();
            try {
                game.start(gameStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    private void backToHome(MouseEvent event) throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToSettings(MouseEvent event) throws Exception {
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }
}
