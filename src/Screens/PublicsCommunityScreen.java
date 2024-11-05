package Screens;

import java.io.FileInputStream;

import Body.Community;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PublicsCommunityScreen {
    private static int id = 0;
    private Community community;
    private Pane pane;
    private Stage stage = new Stage();


    @FXML
    private HBox EditProfile;

    @FXML
    private HBox Hbox_to_ScreenChat;

    @FXML
    private HBox Hbox_to_ScreenFriends;

    @FXML
    private HBox Hbox_to_ScreenPublic;

    @FXML
    private ScrollPane scrollPosts;

    @FXML
    private VBox vboxPost;

    @FXML
    private TextArea txtTextCommunity;

    @FXML
    private ImageView btnEditarCommunity;

    @FXML
    private ImageView imageCommunity;

    @FXML
    private Label lblCommunity;

    public PublicsCommunityScreen(int newId, Community newCommunity)throws Exception{
        id = newId;
        community = newCommunity;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenCommunityPublications.fxml"));
        loader.setController(this);
        pane = loader.load();
        pane.requestFocus();
        stage.setScene(new Scene(pane));
        stage.setTitle("Posts da comunidade");
        stage.setResizable(false);

    }
    public Stage getStage() {
        return this.stage;
    }

    public Pane getPane() {
        return this.pane;
    }

    @FXML
    public void initialize(){

        txtTextCommunity.setText(community.getTxtCommunity());
        lblCommunity.setText(community.getName());

        try{

            if(community.getPhotoCommunity() != null){
                this.imageCommunity.setImage(new Image(new FileInputStream(community.getPhotoCommunity())));
                this.imageCommunity.setFitHeight(159);
                this.imageCommunity.setFitWidth(159);

                Circle circle = new Circle(75.5, 75.5, 75.5);
                imageCommunity.setClip(circle);
            }
        }catch (Exception ie) {
            ie.printStackTrace();
        }

    }


    @FXML
    private void backToHome(MouseEvent event)throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToChat(MouseEvent event) throws Exception{
        new ChatScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToEditProfile(MouseEvent event) throws Exception{

    }

    @FXML
    private void goToPublic(MouseEvent event) throws Exception{

    }

    @FXML
    private void goToFriends(MouseEvent event)throws Exception {
        new FriendsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToSettings(MouseEvent event)throws Exception {
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }
}
