package Screens;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Body.Community;
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

public class CommunityParticipants {
    private Community community;
    private Stage stage = new Stage();
    private Stage otherStage;
    private Pane pane;
    private Pane otherPane;
    private User userNow;
    private static int id = 0;

    @FXML
    private ImageView btnExit;

    @FXML
    private ScrollPane scrollMembers;

    @FXML
    private VBox vboxMembers;

    public CommunityParticipants(int newId, Community newCommunity, Pane newPane, Stage newStage) throws Exception {

        id = newId;
        community = newCommunity;
        otherPane = newPane;
        otherStage = newStage;
        userNow = List_User.getPoint(2).user[id];

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenCommunityParticipants.fxml"));
        loader.setController(this);
        pane = loader.load();
        this.stage.setScene(new Scene(pane));
        this.stage.setTitle("Usu´rios Bloqueados");
        Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
            stage.getIcons().add(image);
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
    private void initialize() {

        vboxMembers.getChildren().clear();

        try {

            if (community.getCommunityUsers().contains(id) || community.getIdOwner() == id) {
                ImageView imgIConFriend = (List_User.getPoint(2).user[id].getPhotoProfile() == null)
                        ? new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                        : new ImageView(new Image(
                                new FileInputStream(List_User.getPoint(2).user[id].getPhotoProfile())));
                imgIConFriend.setFitHeight(48);
                imgIConFriend.setFitWidth(48);

                Circle circle = new Circle(24, 24, 24);
                imgIConFriend.setClip(circle);

                // nome do amigo
                Label userName = new Label(List_User.getPoint(2).user[id].getName());
                userName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                userName.setPadding(new Insets(15, 0, 0, 10));

                HBox hBoxLeft = new HBox(10);
                hBoxLeft.getChildren().addAll(imgIConFriend, userName);
                hBoxLeft.setPadding(new Insets(5, 10, 5, 10));

                this.vboxMembers.getChildren().addAll(new Separator(), hBoxLeft, new Separator());
            }

            if (community.getIdOwner() != id) {
                participantsList(community.getIdOwner());
            }

            for (int i = 0; i <= community.getCommunityUsers().size() - 1; i++) {

                int index = community.getCommunityUsers().get(i);

                participantsList(index);

            }

        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }

    private void participantsList(int index) throws FileNotFoundException {

        if (userNow.checkFriend(index) == 0) {

            // imagem de perfil amigo
            ImageView imgIConFriend = (List_User.getPoint(2).user[index].getPhotoProfile() == null)
                    ? new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                    : new ImageView(new Image(
                            new FileInputStream(List_User.getPoint(2).user[index].getPhotoProfile())));
            imgIConFriend.setFitHeight(48);
            imgIConFriend.setFitWidth(48);

            Circle circle = new Circle(24, 24, 24);
            imgIConFriend.setClip(circle);

            // nome do amigo
            Label userName = new Label(List_User.getPoint(2).user[index].getName());
            userName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
            userName.setPadding(new Insets(15, 0, 0, 10));

            imgIConFriend.setCursor(Cursor.HAND);
            userName.setCursor(Cursor.HAND);

            String dep = "pode";
            String perfilVisi = "amigos <3";

            imgIConFriend.setOnMouseClicked(event -> {
                try {
                    new FriendProfile(id, index, perfilVisi, dep).getStage().show();
                    ExitEncaminharScren(event);
                    otherStage.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            userName.setOnMouseClicked(event -> {
                try {
                    new FriendProfile(id, index, perfilVisi, dep).getStage().show();
                    ExitEncaminharScren(event);
                    otherStage.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            HBox hBoxLeft = new HBox(10);
            hBoxLeft.getChildren().addAll(imgIConFriend, userName);
            hBoxLeft.setPadding(new Insets(5, 10, 5, 10));

            this.vboxMembers.getChildren().addAll(new Separator(), hBoxLeft, new Separator());
        } else {

            if (List_User.getPoint(2).user[id].checkFriend(index) != 0
                    &&
                    !List_User.getPoint(2).user[id].getSolicit().contains((Integer) index)
                    &&
                    index != id
                    &&
                    List_User.getPoint(2).checkExistUser(index) == 0
                    &&
                    !List_User.getPoint(2).user[id].getList_Solicit().contains((Integer) index)) {
                if (!List_User.getPoint(2).user[id].getUsersBlocks().containsKey(index)) {

                    HBox hBoxSugest = new HBox(2);
                    hBoxSugest.setSpacing(3);// conjunto da sugestao de amizade

                    // imagem de perfil amigo
                    ImageView imgIConFriend = (List_User.getPoint(2).user[index].getPhotoProfile() == null)
                            ? new ImageView(
                                    new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                            : new ImageView(new Image(
                                    new FileInputStream(List_User.getPoint(2).user[index].getPhotoProfile())));
                    imgIConFriend.setFitHeight(48);
                    imgIConFriend.setFitWidth(48);

                    Circle circle = new Circle(24, 24, 24);
                    imgIConFriend.setClip(circle);
                    // nome do amigo
                    Label userName = new Label(List_User.getPoint(2).user[index].getName());
                    userName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                    userName.setPadding(new Insets(15, 0, 0, 10));

                    imgIConFriend.setCursor(Cursor.HAND);
                    userName.setCursor(Cursor.HAND);

                    String dep = "nao pode";
                    String perfilVisi = List_User.getPoint(index).user[index].getProfileVisibility();
                    imgIConFriend.setOnMouseClicked(event -> {
                        try {
                            new FriendProfile(id, index, perfilVisi, dep).getStage().show();
                            ExitEncaminharScren(event);
                            otherStage.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    userName.setOnMouseClicked(event -> {
                        try {
                            new FriendProfile(id, index, perfilVisi, dep).getStage().show();
                            ExitEncaminharScren(event);
                            otherStage.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    // icone de enviar solicitação de amizade
                    ImageView imgAddFriend = new ImageView(
                            new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/add-friend.png")));
                    imgAddFriend.setFitHeight(30);
                    imgAddFriend.setFitWidth(30);
                    imgAddFriend.setPreserveRatio(true);

                    imgAddFriend.setCursor(Cursor.HAND);

                    imgAddFriend.setOnMouseClicked(event -> {
                        User _user = List_User.getPoint(0).user[index];
                        _user.sendSolict(id);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Aviso!");
                        alert.setContentText("Solicitação enviada com sucesso para: \n\t" + _user.getName());
                        alert.showAndWait();
                        _user = List_User.getPoint(index).user[id];
                        _user.getList_Solicit().add(index);

                        initialize();

                    });

                    VBox auxToImage = new VBox();
                    auxToImage.getChildren().add(imgAddFriend);

                    auxToImage.setPadding(new Insets(10, 0, 0, 0));
                    auxToImage.setAlignment(Pos.TOP_CENTER);

                    HBox hBoxLeft = new HBox(10);
                    hBoxLeft.getChildren().addAll(imgIConFriend, userName);

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    hBoxSugest = new HBox();
                    hBoxSugest.getChildren().addAll(hBoxLeft, spacer, auxToImage);
                    hBoxSugest.setPadding(new Insets(10, 15, 10, 10));

                    this.vboxMembers.getChildren().addAll(hBoxSugest);
                }
            }

            if (List_User.getPoint(2).user[id].getSolicit().contains((Integer) index)
                    || List_User.getPoint(2).user[id].getList_Solicit().contains((Integer) index)) {
                // imagem de perfil amigo
                ImageView imgIConFriend = (List_User.getPoint(2).user[index].getPhotoProfile() == null)
                        ? new ImageView(
                                new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                        : new ImageView(new Image(
                                new FileInputStream(List_User.getPoint(2).user[index].getPhotoProfile())));
                imgIConFriend.setFitHeight(48);
                imgIConFriend.setFitWidth(48);

                Circle circle = new Circle(24, 24, 24);
                imgIConFriend.setClip(circle);

                // nome do amigo
                Label userName = new Label(List_User.getPoint(2).user[index].getName());
                userName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                userName.setPadding(new Insets(15, 0, 0, 10));

                imgIConFriend.setCursor(Cursor.HAND);
                userName.setCursor(Cursor.HAND);

                String dep = "nao pode";
                String perfilVisi = List_User.getPoint(index).user[index].getProfileVisibility();

                imgIConFriend.setOnMouseClicked(event -> {
                    try {
                        new FriendProfile(id, index, perfilVisi, dep).getStage().show();
                        ExitEncaminharScren(event);
                        otherStage.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                userName.setOnMouseClicked(event -> {
                    try {
                        new FriendProfile(id, index, perfilVisi, dep).getStage().show();
                        ExitEncaminharScren(event);
                        otherStage.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                HBox hBoxLeft = new HBox(10);
                hBoxLeft.getChildren().addAll(imgIConFriend, userName);
                hBoxLeft.setPadding(new Insets(5, 10, 5, 10));

                this.vboxMembers.getChildren().addAll(new Separator(), hBoxLeft, new Separator());
            }

        }

    }

    @FXML
    void ExitEncaminharScren(MouseEvent event) throws Exception {
        this.otherPane.effectProperty().set(null);
        otherPane.setDisable(false);
        this.stage.close();
    }

}
