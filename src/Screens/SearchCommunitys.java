package Screens;

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
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenSearchCommunity.fxml"));
        loader.setController(this);
        Pane pane = loader.load();
        this.stage.setScene(new Scene(pane));
        this.stage.setTitle("Pesquisar Comunidades");
        this.stage.setResizable(false);
        this.stage.initStyle(StageStyle.UNDECORATED);

        pane.requestFocus();
        pane.setOnMouseClicked(event -> {
            pane.requestFocus();
        });

    }

    public Stage getStage() {
        return this.stage;
    }

    @FXML
    private void initialize(){
        VboxCommunitys.getChildren().clear();
    }


    public Map<Integer, Community> findCommunitiesByPartialName(ArrayList<Community> communityList, String partialName) {
        Map<Integer, Community> matchedCommunities = new HashMap<>();
        for (int i = 0; i < communityList.size(); i++) {
            Community community = communityList.get(i);

            if (community != null) {
                if (community.getName().toLowerCase().contains(partialName.toLowerCase())) {
                    matchedCommunities.put(i, community);
                }
            }
        }
        return matchedCommunities;
    }


    private void SearchCommunityTrue(){
        initialize();

        Map<Integer, Community> matchedCommunity = findCommunitiesByPartialName(ManagerCommunitys.getAllCommunitys(), txtSearchCommunity.getText());

        for (Map.Entry<Integer, Community> entry : matchedCommunity.entrySet()) {
            Community community = entry.getValue();
            int index = entry.getKey();
            //System.out.println("Usuário encontrado: " + user.getName() +", Índice: " + index);

            //é amg do usuario
        }
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

    }
}
