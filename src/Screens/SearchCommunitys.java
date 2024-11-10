package Screens;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanAttributeInfo;

import Body.Community;
import Body.ManagerCommunitys;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
import javafx.stage.StageStyle;

public class SearchCommunitys {
    private static int id = 0;
    private User userNow;
    private CommunityScreen _screenCommunity;
    private Stage stage = new Stage();

    @FXML
    private ImageView ExitScreen;

    @FXML
    private ScrollPane ScrollCommunitys;

    @FXML
    private VBox VboxCommunitys;

    @FXML
    private ImageView imgSearchCommunity;

    @FXML
    private TextField txtSearchCommunity;

    public SearchCommunitys(int newId, CommunityScreen newScreen) throws Exception {
        id = newId;
        _screenCommunity = newScreen;
        userNow = List_User.getPoint(2).user[id];

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenSearchCommunity.fxml"));
        loader.setController(this);
        Pane pane = loader.load();
        this.stage.setScene(new Scene(pane));
        this.stage.setTitle("Pesquisar comunidades");
        Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
        stage.getIcons().add(image);
        this.stage.setResizable(false);
        this.stage.initStyle(StageStyle.UNDECORATED);

        pane.requestFocus();
        pane.setOnMouseClicked(event -> {
            pane.requestFocus();
        });

        this.txtSearchCommunity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    searchCommunityTrue();
                } catch (Exception ie) {
                    ie.printStackTrace();
                }
            }
        });

    }

    public Stage getStage() {
        return this.stage;
    }

    @FXML
    private void initialize() {
        VboxCommunitys.getChildren().clear();
    }

    public Map<Integer, Community> findCommunitiesByPartialName(ArrayList<Community> communitiesList,
            String partialName, int id) {
        Map<Integer, Community> matchedcommunities = new HashMap<>();
        for (int i = 0; i < communitiesList.size(); i++) {
            Community community = communitiesList.get(i);

            if (community != null) {
                if (community.getName().toLowerCase().contains(partialName.toLowerCase())) {
                    matchedcommunities.put(i, community);
                }
            }
        }
        return matchedcommunities;
    }

    private void searchCommunityTrue() throws FileNotFoundException {

        initialize();

        Map<Integer, Community> matchedCommunities = findCommunitiesByPartialName(ManagerCommunitys.getAllCommunitys(),
                txtSearchCommunity.getText(), id);

        if (!matchedCommunities.isEmpty()) {

            for (Map.Entry<Integer, Community> entry : matchedCommunities.entrySet()) {
                Community community = entry.getValue();
                int index = entry.getKey();

                // participa da comunidade ou é dono
                if (userNow.checkCommunity(index) == 0 || community.getCommunityUsers().contains(id)) {

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
                            ExitSearchCommunitysScreen(event);
                            _screenCommunity.getStage().close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    comName.setOnMouseClicked(event -> {
                        try {

                            new PublicsCommunityScreen(id, community).getStage().show();
                            this.stage.close();
                            ExitSearchCommunitysScreen(event);
                            _screenCommunity.getStage().close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    HBox hBoxLeft = new HBox(10);
                    hBoxLeft.getChildren().addAll(imgIConCom, comName);

                    hBoxCom = new HBox();
                    hBoxCom.getChildren().addAll(hBoxLeft);
                    hBoxCom.setPadding(new Insets(10, 15, 10, 10));

                    this.VboxCommunitys.getChildren().addAll(hBoxCom, new Separator());

                } else {

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
                            ExitSearchCommunitysScreen(event);
                            _screenCommunity.getStage().close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    comName.setOnMouseClicked(event -> {
                        try {

                            new PublicsCommunityScreen(id, community).getStage().show();
                            ExitSearchCommunitysScreen(event);
                            _screenCommunity.getStage().close();

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

                    Tooltip tooltipAddCommunity = new Tooltip("Participar");
                    Tooltip.install(imgAddCommunity, tooltipAddCommunity);

                    imgAddCommunity.setCursor(Cursor.HAND);

                    imgAddCommunity.setOnMouseClicked(event -> {

                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Aviso!");
                        alert.setContentText("Parabéns, você agora faz parte da comunidade " + community.getName()
                                + "! \nSeja bem-vindo(a) e aproveite para se conectar com \noutros membros!");
                        alert.showAndWait();

                        community.getCommunityUsers().add(id);
                        initialize();
                        
                        try {
                            searchCommunityTrue();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
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

                    this.VboxCommunitys.getChildren().addAll(hBoxCom, new Separator());

                }

            }
        }

        _screenCommunity.initialize();
    }

    @FXML
    private void ExitSearchCommunitysScreen(MouseEvent event) {
        _screenCommunity.getPane().effectProperty().set(null);
        _screenCommunity.getPane().toFront();
        _screenCommunity.getPane().setDisable(false);
        this.stage.close();
    }

    @FXML
    private void SearchCommunity(MouseEvent event) throws Exception {
        searchCommunityTrue();
    }
}
