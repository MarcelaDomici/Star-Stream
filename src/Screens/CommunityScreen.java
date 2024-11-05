package Screens;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Body.Community;
import Body.ManagerCommunitys;
import Body.ManagerPosts;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CommunityScreen {
    private static int id = 0;
    private Stage stage = new Stage();
    private Pane pane;
    private User userNow;

    @FXML
    private HBox Hbox_to_ScreenChat;

    @FXML
    private HBox Hbox_to_ScreenFriends;

    @FXML
    private HBox Hbox_to_ScreenGames;

    @FXML
    private HBox Hbox_to_ScreenNewCommunity;

    @FXML
    private ImageView exitToHome;

    @FXML
    private HBox hBoxUserProfile;

    @FXML
    private HBox hboxSearchCom;

    @FXML
    private ScrollPane scrollComun;

    @FXML
    private ScrollPane scrollMyCom;

    @FXML
    private Label userName;

    @FXML
    private VBox vBoxComun;

    @FXML
    private VBox vBoxMyCom;

    @FXML
    private ImageView viewSettings;

    public CommunityScreen(int newId) throws Exception {
        id = newId;
        userNow = List_User.getPoint(2).user[id];

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenCommunity.fxml"));
        loader.setController(this);
        pane = loader.load();
        pane.requestFocus();
        stage.setScene(new Scene(pane));
        stage.setTitle("Comunidades");
        stage.setResizable(false);

    }

    public Stage getStage() {
        return this.stage;
    }

    public Pane getPane() {
        return this.pane;
    }

    @FXML
    public void initialize() {

        scrollComun.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        scrollComun.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra horizontal do scroll
        scrollComun.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra vertical

        scrollMyCom.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        scrollMyCom.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra horizontal do scroll
        scrollMyCom.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra vertical

        vBoxComun.getChildren().clear();
        vBoxMyCom.getChildren().clear();

        // Comunidades em geral
        try {

            for (int i = ManagerCommunitys.allCommunitys.size() - 1; i >= 0; --i) {

                // participa da comunidade ou é dono
                if (userNow.checkCommunity(i) == 0) {

                    Community community = ManagerCommunitys.allCommunitys.get(i);

                    HBox hBoxCom = new HBox(2);
                    hBoxCom.setSpacing(3);// conjunto da comunidade

                    // imagem de comunidade
                    ImageView imgIConCom = (community.getPhotoCommunity() == null)
                            ? new ImageView(
                                    new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PerfilComun.png")))
                            : new ImageView(
                                    new Image(new FileInputStream(community.getPhotoCommunity())));
                    imgIConCom.setFitHeight(50);
                    imgIConCom.setFitWidth(50);

                    Circle circle = new Circle(25, 25, 25);
                    imgIConCom.setClip(circle);

                    // nome da comunidade
                    Label comName = new Label(community.getName());
                    comName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                    comName.setPadding(new Insets(15, 0, 0, 10));

                    imgIConCom.setCursor(Cursor.HAND);
                    comName.setCursor(Cursor.HAND);

                    imgIConCom.setOnMouseClicked(event -> {
                        try {
                            new PublicsCommunityScreen(id, community).getStage().show();
                            this.stage.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    comName.setOnMouseClicked(event -> {
                        try {

                            new PublicsCommunityScreen(id, community).getStage().show();
                            this.stage.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    HBox hBoxLeft = new HBox(10);
                    hBoxLeft.getChildren().addAll(imgIConCom, comName);

                    hBoxCom = new HBox();
                    hBoxCom.getChildren().addAll(hBoxLeft);
                    hBoxCom.setPadding(new Insets(10, 15, 10, 10));

                    this.vBoxComun.getChildren().addAll(hBoxCom, new Separator());

                } else {
                    Community community = ManagerCommunitys.allCommunitys.get(i);

                    HBox hBoxCom = new HBox(2);
                    hBoxCom.setSpacing(3);// conjunto da comunidade

                    // imagem de comunidade
                    ImageView imgIConCom = (community.getPhotoCommunity() == null)
                            ? new ImageView(
                                    new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PerfilComun.png")))
                            : new ImageView(
                                    new Image(new FileInputStream(community.getPhotoCommunity())));
                    imgIConCom.setFitHeight(50);
                    imgIConCom.setFitWidth(50);

                    Circle circle = new Circle(25, 25, 25);
                    imgIConCom.setClip(circle);

                    // nome da comunidade
                    Label comName = new Label(community.getName());
                    comName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                    comName.setPadding(new Insets(15, 0, 0, 10));

                    imgIConCom.setCursor(Cursor.HAND);
                    comName.setCursor(Cursor.HAND);

                    imgIConCom.setOnMouseClicked(event -> {
                        try {

                            new PublicsCommunityScreen(id, community).getStage().show();
                            this.stage.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    comName.setOnMouseClicked(event -> {
                        try {

                            new PublicsCommunityScreen(id, community).getStage().show();
                            this.stage.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    // icone de add comunidade
                    ImageView imgAddCommunity = new ImageView(
                            new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/add_com.png")));
                    imgAddCommunity.setFitHeight(30);
                    imgAddCommunity.setFitWidth(30);
                    imgAddCommunity.setPreserveRatio(true);

                    imgAddCommunity.setCursor(Cursor.HAND);

                    imgAddCommunity.setOnMouseClicked(event -> {

                    });

                    VBox auxToImage = new VBox();
                    auxToImage.getChildren().add(imgAddCommunity);

                    auxToImage.setPadding(new Insets(10, 0, 0, 0));
                    auxToImage.setAlignment(Pos.TOP_CENTER);

                    HBox hBoxLeft = new HBox(10);
                    hBoxLeft.getChildren().addAll(imgIConCom, comName);

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    hBoxCom = new HBox();
                    hBoxCom.getChildren().addAll(hBoxLeft, spacer, auxToImage);
                    hBoxCom.setPadding(new Insets(10, 15, 10, 10));

                    this.vBoxComun.getChildren().addAll(hBoxCom, new Separator());

                }

            }

        } catch (Exception ie) {
            ie.printStackTrace();
        }

        // hbox minhas comunidades
        try {

            for (int i = ManagerCommunitys.allCommunitys.size() - 1; i >= 0; --i) {

                if (userNow.getCommunitysUser().contains((Integer) i)) {

                    Community community = ManagerCommunitys.allCommunitys.get(i);

                    HBox hBoxCom = new HBox(2);
                    hBoxCom.setSpacing(3);// conjunto da comunidade

                    // imagem de comunidade
                    ImageView imgIConCom = (community.getPhotoCommunity() == null)
                            ? new ImageView(
                                    new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PerfilComun.png")))
                            : new ImageView(
                                    new Image(new FileInputStream(community.getPhotoCommunity())));
                    imgIConCom.setFitHeight(50);
                    imgIConCom.setFitWidth(50);

                    Circle circle = new Circle(25, 25, 25);
                    imgIConCom.setClip(circle);

                    // nome da comunidade
                    Label comName = new Label(community.getName());
                    comName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                    comName.setPadding(new Insets(15, 0, 0, 10));

                    imgIConCom.setCursor(Cursor.HAND);
                    comName.setCursor(Cursor.HAND);

                    imgIConCom.setOnMouseClicked(event -> {
                        try {

                            new PublicsCommunityScreen(id, community).getStage().show();
                            this.stage.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    comName.setOnMouseClicked(event -> {
                        try {

                            new PublicsCommunityScreen(id, community).getStage().show();
                            this.stage.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    // icone de add comunidade
                    ImageView imgLeaveCommunity = new ImageView(
                            new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/octicon_x.png")));
                    imgLeaveCommunity.setFitHeight(30);
                    imgLeaveCommunity.setFitWidth(30);
                    imgLeaveCommunity.setPreserveRatio(true);

                    imgLeaveCommunity.setCursor(Cursor.HAND);

                    imgLeaveCommunity.setOnMouseClicked(event -> {

                    });

                    VBox auxToImage = new VBox();
                    auxToImage.getChildren().add(imgLeaveCommunity);

                    auxToImage.setPadding(new Insets(10, 0, 0, 0));
                    auxToImage.setAlignment(Pos.TOP_CENTER);

                    HBox hBoxLeft = new HBox(10);
                    hBoxLeft.getChildren().addAll(imgIConCom, comName);

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    hBoxCom = new HBox();
                    hBoxCom.getChildren().addAll(hBoxLeft, spacer, auxToImage);
                    hBoxCom.setPadding(new Insets(10, 15, 10, 10));

                    this.vBoxMyCom.getChildren().addAll(hBoxCom, new Separator());
                }

            }
        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }

    @FXML
    private void backToLogin(MouseEvent event) throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToChat(MouseEvent event) throws Exception {
        new ChatScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToFriends(MouseEvent event) throws Exception {

        new FriendsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToGames(MouseEvent event) throws Exception {
        new GamesScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToProfile(MouseEvent event) throws Exception {
        new ProfileScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToNewCommunity(MouseEvent event) throws Exception {
        new NewCommunityScreen(id, this).getStage().show();
        pane.effectProperty().set(new MotionBlur(3.0, 15.0));
        pane.setDisable(true);
    }

    @FXML
    private void goToSearchUserScreen(MouseEvent event) throws Exception {
        new SearchCommunitys(id, this).getStage().show();
        pane.effectProperty().set(new MotionBlur(3.0, 15.0));
        pane.setDisable(true);
    }

    @FXML
    private void goToSettings(MouseEvent event) throws Exception {
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }

}
