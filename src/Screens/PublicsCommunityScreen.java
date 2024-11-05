package Screens;

import java.io.FileInputStream;

import Body.Community;
import Body.ManagerPosts;
import Body.Post;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class PublicsCommunityScreen {
    private static int id = 0;
    private Community community;
    private Pane pane;
    private Stage stage = new Stage();
    private User userNow;

    @FXML
    private HBox EditProfile;

    @FXML
    private HBox Hbox_to_ScreenChat;

    @FXML
    private HBox Hbox_to_ScreenMembers;

    @FXML
    private HBox Hbox_to_ScreenChatCom;

    @FXML
    private HBox Hbox_to_ScreenSolicit;

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

    @FXML
    private HBox Hbox_to_ScreenPublic;

    public PublicsCommunityScreen(int newId, Community newCommunity) throws Exception {
        id = newId;
        community = newCommunity;
        userNow = List_User.getPoint(2).user[id];

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
    public void initialize() {

        btnEditarCommunity.setVisible(false);
        btnEditarCommunity.setDisable(true);

        Hbox_to_ScreenSolicit.setVisible(false);
        Hbox_to_ScreenSolicit.setDisable(true);

        Hbox_to_ScreenPublic.setVisible(false);
        Hbox_to_ScreenPublic.setDisable(true);

        Hbox_to_ScreenChatCom.setVisible(false);
        Hbox_to_ScreenChatCom.setDisable(true);

        Hbox_to_ScreenMembers.setVisible(false);
        Hbox_to_ScreenMembers.setDisable(true);

        if (id == community.getIdOwner()) {

            btnEditarCommunity.setVisible(true);
            btnEditarCommunity.setDisable(false);

            Hbox_to_ScreenSolicit.setVisible(true);
            Hbox_to_ScreenSolicit.setDisable(false);

            Hbox_to_ScreenPublic.setVisible(true);
            Hbox_to_ScreenPublic.setDisable(false);

            Hbox_to_ScreenChatCom.setVisible(true);
            Hbox_to_ScreenChatCom.setDisable(false);

            Hbox_to_ScreenMembers.setVisible(true);
            Hbox_to_ScreenMembers.setDisable(false);
        }

        if (community.getCommunityUsers().contains(id)) {

            Hbox_to_ScreenPublic.setVisible(true);
            Hbox_to_ScreenPublic.setDisable(false);

            Hbox_to_ScreenChatCom.setVisible(true);
            Hbox_to_ScreenChatCom.setDisable(false);

            Hbox_to_ScreenMembers.setVisible(true);
            Hbox_to_ScreenMembers.setDisable(false);
        }

        txtTextCommunity.setText(community.getTxtCommunity());
        lblCommunity.setText(community.getName());

        try {

            if (community.getPhotoCommunity() != null) {
                this.imageCommunity.setImage(new Image(new FileInputStream(community.getPhotoCommunity())));
                this.imageCommunity.setFitHeight(159);
                this.imageCommunity.setFitWidth(159);

                Circle circle = new Circle(75.5, 75.5, 75.5);
                imageCommunity.setClip(circle);
            }
        } catch (Exception ie) {
            ie.printStackTrace();
        }

        vboxPost.getChildren().clear();

        scrollPosts.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        scrollPosts.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra horizontal do scroll
        scrollPosts.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra vertical

        // posts comunidade

        try {
            this.vboxPost.setSpacing((double) 10);
            for (int i = community.getPostCommunity().size() - 1; i >= 0; --i) {

                Post post = community.getPostCommunity().get(i);
                int indexF = post.getIduser();

                if (userNow.checkFriend(indexF) == 0) {

                    VBox vBox = new VBox(5);
                    HBox hBox = new HBox(2);
                    hBox.setSpacing(3);

                    String photo = ((List_User.getPoint(i).user[post.getIduser()].getPhotoProfile()) != null)
                            ? List_User.getPoint(i).user[post.getIduser()].getPhotoProfile()
                            : "ScreensFXML/Imagens/PERFIL.PNG";

                    ImageView imageView = ((List_User.getPoint(i).user[post.getIduser()].getPhotoProfile()) != null)
                            ? new ImageView(new Image(new FileInputStream(photo)))
                            : new ImageView(
                                    new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")));

                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);

                    // Criar um círculo para o clipping
                    Circle circle = new Circle(25, 25, 25); // O raio do círculo é metade do tamanho da imagem (53 / 2)

                    // Aplicar o círculo como um clip na ImageView
                    imageView.setClip(circle);

                    imageView.setPreserveRatio(true);

                    Label nameUser = new Label(List_User.getPoint(0).user[post.getIduser()].getName());
                    nameUser.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");// nameuser
                    nameUser.setPadding(new Insets(13, 0, 0, 10));
                    hBox.getChildren().addAll(imageView, nameUser);

                    imageView.setCursor(Cursor.HAND);
                    nameUser.setCursor(Cursor.HAND);

                    String dep = "pode";
                    String perfilVisi = "amigos <3";

                    imageView.setOnMouseClicked(event -> {
                        try {
                            new FriendProfile(id, indexF, perfilVisi, dep).getStage().show();
                            this.stage.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    nameUser.setOnMouseClicked(event -> {
                        try {
                            new FriendProfile(id, indexF, perfilVisi, dep).getStage().show();
                            this.stage.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    vBox.setStyle(
                            "-fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: white;");
                    Label titlePost = new Label(post.getTitle());
                    titlePost.setStyle("-fx-font-weight: bold; -fx-font-size: 14px");// titulo
                    titlePost.setPadding(new Insets(10, 0, 5, 100));
                    titlePost.setWrapText(true);

                    // texto public
                    Text text = new Text(post.getPostTxt());
                    TextFlow txtPost = new TextFlow();
                    txtPost.setStyle("-fx-font-size: 14px");
                    txtPost.setMaxWidth(400);
                    txtPost.getChildren().add(text);
                    txtPost.setCache(false);
                    txtPost.setCacheShape(false);
                    txtPost.setCenterShape(false);
                    txtPost.setFocusTraversable(false);
                    txtPost.getStylesheets()
                            .add(this.getClass().getResource("ScreensFXML/CSS/TxtAreaToPosts.css").toString());
                    txtPost.setOnMouseClicked(event -> {
                        this.pane.requestFocus();
                    });

                    HBox box = new HBox(1);
                    box.setPadding(new Insets(10, 0, 0, 100));

                    if (post.getImagem() != null) {
                        ImageView image = new ImageView(new Image(new FileInputStream(post.getImagem())));
                        image.setFitHeight(400);
                        image.setFitWidth(400);
                        box.getChildren().add(image);
                        image.setPreserveRatio(true);
                    }

                    int likesQnt = post.getLikes();
                    Label lblQntLikes = new Label(String.valueOf(likesQnt));
                    lblQntLikes.setPadding(new Insets(3, 0, 0, 2));

                    Button like = new Button("   ");
                    if (post.checkLike(id) == 0) {
                        like.setStyle(
                                "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-background-color: red; -fx-text-fill:white;");
                        post.addLike(id);
                    } else {
                        like.setStyle(
                                "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;");
                    }

                    Hyperlink comment = new Hyperlink("Comentários " + String.valueOf(post.getComments().size()));
                    comment.setCache(false);
                    comment.setCacheShape(false);
                    comment.setBorder(null);
                    comment.setFocusTraversable(false);
                    comment.setPadding(new Insets(6, 0, 0, 2));
                    comment.setUnderline(false);
                    comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;");
                    comment.setOnMouseEntered(
                            event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));
                    comment.setOnMouseExited(event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));
                    comment.setOnAction(event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));

                    like.setOnMouseClicked(event -> generateLikes(post, like, lblQntLikes, likesQnt));

                    comment.setOnMouseClicked(event -> {
                        try {
                            new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                            pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                            pane.setDisable(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    ImageView comentImg = new ImageView(
                            new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/comment_post.PNG")));
                    comentImg.setFitHeight(30);
                    comentImg.setFitWidth(30);
                    comentImg.setPreserveRatio(true);
                    comentImg.setCursor(Cursor.HAND);

                    comentImg.setOnMouseClicked(event -> {
                        try {
                            new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                            pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                            pane.setDisable(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    HBox boxComents = new HBox(2);
                    boxComents.getChildren().addAll(comentImg, comment);

                    boxComents.setCursor(Cursor.HAND);
                    boxComents.setOnMouseClicked(event -> {
                        try {
                            new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                            pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                            pane.setDisable(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    ImageView encaminharImg = new ImageView(
                            new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/share_icon.PNG")));
                    encaminharImg.setFitHeight(30);
                    encaminharImg.setFitWidth(30);
                    encaminharImg.setPreserveRatio(true);

                    Label encaminharMSG = new Label("Encaminhar");
                    encaminharMSG.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;");
                    encaminharMSG.setPadding(new Insets(6, 0, 0, 2));

                    HBox boxEncaminhar = new HBox(2);
                    boxEncaminhar.getChildren().addAll(encaminharImg, encaminharMSG);
                    boxEncaminhar.setCursor(Cursor.HAND);

                    boxEncaminhar.setOnMouseClicked(event -> {
                        try {
                            new EncaminharScreen(id, pane, post).getStage().show();
                            pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                            pane.setDisable(true);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    HBox boxLikes = new HBox(2);
                    boxLikes.getChildren().addAll(like, lblQntLikes);

                    Region spacer1 = new Region();
                    Region spacer2 = new Region();
                    Region spacer3 = new Region();
                    spacer1.setPrefWidth(60);
                    spacer2.setPrefWidth(60);
                    spacer3.setPrefWidth(95);

                    HBox box1 = new HBox();
                    box1.getChildren().addAll(spacer3, boxLikes, spacer1, boxComents, spacer2, boxEncaminhar);
                    box1.setPadding(new Insets(10, 15, 10, 10));

                    HBox box2 = new HBox(3);
                    box2.getChildren().addAll(txtPost);
                    box2.setPadding(new Insets(0, 0, 0, 100));
                    txtPost.setPrefWidth(800);

                    vBox.getChildren().addAll(hBox, titlePost, box2, box, box1);
                    this.vboxPost.getChildren().add(vBox);

                } else {

                    if (List_User.getPoint(2).user[id].checkFriend(indexF) != 0
                            &&
                            !List_User.getPoint(2).user[id].getSolicit().contains((Integer) indexF)
                            &&
                            indexF != id
                            &&
                            List_User.getPoint(2).checkExistUser(indexF) == 0
                            &&
                            !List_User.getPoint(2).user[id].getList_Solicit().contains((Integer) indexF)) {
                        if (!List_User.getPoint(2).user[id].getUsersBlocks().containsKey(indexF)) {

                            VBox vBox = new VBox(5);
                            HBox hBox = new HBox(2);
                            hBox.setSpacing(3);

                            String photo = ((List_User.getPoint(i).user[post.getIduser()].getPhotoProfile()) != null)
                                    ? List_User.getPoint(i).user[post.getIduser()].getPhotoProfile()
                                    : "ScreensFXML/Imagens/PERFIL.PNG";

                            ImageView imageView = ((List_User.getPoint(i).user[post.getIduser()]
                                    .getPhotoProfile()) != null) ? new ImageView(new Image(new FileInputStream(photo)))
                                            : new ImageView(new Image(
                                                    getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")));

                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);

                            Circle circle = new Circle(25, 25, 25);
                            imageView.setClip(circle);

                            imageView.setPreserveRatio(true);

                            Label nameUser = new Label(List_User.getPoint(0).user[post.getIduser()].getName());
                            nameUser.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");// nameuser
                            nameUser.setPadding(new Insets(13, 0, 0, 10));
                            hBox.getChildren().addAll(imageView, nameUser);

                            imageView.setCursor(Cursor.HAND);
                            nameUser.setCursor(Cursor.HAND);

                            String dep = "nao pode";
                            String perfilVisi = List_User.getPoint(2).user[indexF].getProfileVisibility();

                            imageView.setOnMouseClicked(event -> {
                                try {
                                    new FriendProfile(id, indexF, perfilVisi, dep).getStage().show();
                                    this.stage.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            nameUser.setOnMouseClicked(event -> {
                                try {
                                    new FriendProfile(id, indexF, perfilVisi, dep).getStage().show();
                                    this.stage.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            vBox.setStyle(
                                    "-fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: white;");
                            Label titlePost = new Label(post.getTitle());
                            titlePost.setStyle("-fx-font-weight: bold; -fx-font-size: 14px");// titulo
                            titlePost.setPadding(new Insets(10, 0, 5, 100));
                            titlePost.setWrapText(true);

                            // texto public
                            Text text = new Text(post.getPostTxt());
                            TextFlow txtPost = new TextFlow();
                            txtPost.setStyle("-fx-font-size: 14px");
                            txtPost.setMaxWidth(400);
                            txtPost.getChildren().add(text);
                            txtPost.setCache(false);
                            txtPost.setCacheShape(false);
                            txtPost.setCenterShape(false);
                            txtPost.setFocusTraversable(false);
                            txtPost.getStylesheets()
                                    .add(this.getClass().getResource("ScreensFXML/CSS/TxtAreaToPosts.css").toString());
                            txtPost.setOnMouseClicked(event -> {
                                this.pane.requestFocus();
                            });

                            HBox box = new HBox(1);
                            box.setPadding(new Insets(10, 0, 0, 100));

                            if (post.getImagem() != null) {
                                ImageView image = new ImageView(new Image(new FileInputStream(post.getImagem())));
                                image.setFitHeight(400);
                                image.setFitWidth(400);
                                box.getChildren().add(image);
                                image.setPreserveRatio(true);
                            }

                            int likesQnt = post.getLikes();
                            Label lblQntLikes = new Label(String.valueOf(likesQnt));
                            lblQntLikes.setPadding(new Insets(3, 0, 0, 2));

                            Button like = new Button("   ");
                            if (post.checkLike(id) == 0) {
                                like.setStyle(
                                        "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-background-color: red; -fx-text-fill:white;");
                                post.addLike(id);
                            } else {
                                like.setStyle(
                                        "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;");
                            }

                            Hyperlink comment = new Hyperlink(
                                    "Comentários " + String.valueOf(post.getComments().size()));
                            comment.setCache(false);
                            comment.setCacheShape(false);
                            comment.setBorder(null);
                            comment.setFocusTraversable(false);
                            comment.setPadding(new Insets(6, 0, 0, 2));
                            comment.setUnderline(false);
                            comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;");
                            comment.setOnMouseEntered(
                                    event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));
                            comment.setOnMouseExited(
                                    event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));
                            comment.setOnAction(
                                    event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));

                            like.setOnMouseClicked(event -> generateLikes(post, like, lblQntLikes, likesQnt));

                            comment.setOnMouseClicked(event -> {
                                try {
                                    new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                                    pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                    pane.setDisable(true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            ImageView comentImg = new ImageView(
                                    new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/comment_post.PNG")));
                            comentImg.setFitHeight(30);
                            comentImg.setFitWidth(30);
                            comentImg.setPreserveRatio(true);
                            comentImg.setCursor(Cursor.HAND);

                            comentImg.setOnMouseClicked(event -> {
                                try {
                                    new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                                    pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                    pane.setDisable(true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            HBox boxComents = new HBox(2);
                            boxComents.getChildren().addAll(comentImg, comment);

                            boxComents.setCursor(Cursor.HAND);
                            boxComents.setOnMouseClicked(event -> {
                                try {
                                    new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                                    pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                    pane.setDisable(true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            ImageView encaminharImg = new ImageView(
                                    new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/share_icon.PNG")));
                            encaminharImg.setFitHeight(30);
                            encaminharImg.setFitWidth(30);
                            encaminharImg.setPreserveRatio(true);

                            Label encaminharMSG = new Label("Encaminhar");
                            encaminharMSG.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;");
                            encaminharMSG.setPadding(new Insets(6, 0, 0, 2));

                            HBox boxEncaminhar = new HBox(2);
                            boxEncaminhar.getChildren().addAll(encaminharImg, encaminharMSG);
                            boxEncaminhar.setCursor(Cursor.HAND);

                            boxEncaminhar.setOnMouseClicked(event -> {
                                try {
                                    new EncaminharScreen(id, pane, post).getStage().show();
                                    pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                    pane.setDisable(true);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            HBox boxLikes = new HBox(2);
                            boxLikes.getChildren().addAll(like, lblQntLikes);

                            Region spacer1 = new Region();
                            Region spacer2 = new Region();
                            Region spacer3 = new Region();
                            spacer1.setPrefWidth(60);
                            spacer2.setPrefWidth(60);
                            spacer3.setPrefWidth(95);

                            HBox box1 = new HBox();
                            box1.getChildren().addAll(spacer3, boxLikes, spacer1, boxComents, spacer2, boxEncaminhar);
                            box1.setPadding(new Insets(10, 15, 10, 10));

                            HBox box2 = new HBox(3);
                            box2.getChildren().addAll(txtPost);
                            box2.setPadding(new Insets(0, 0, 0, 100));
                            txtPost.setPrefWidth(800);

                            vBox.getChildren().addAll(hBox, titlePost, box2, box, box1);
                            this.vboxPost.getChildren().add(vBox);

                        }

                    }

                    if (List_User.getPoint(2).user[id].getSolicit().contains((Integer) indexF)
                            || List_User.getPoint(2).user[id].getList_Solicit().contains((Integer) indexF)) {

                        VBox vBox = new VBox(5);
                        HBox hBox = new HBox(2);
                        hBox.setSpacing(3);

                        String photo = ((List_User.getPoint(i).user[post.getIduser()].getPhotoProfile()) != null)
                                ? List_User.getPoint(i).user[post.getIduser()].getPhotoProfile()
                                : "ScreensFXML/Imagens/PERFIL.PNG";

                        ImageView imageView = ((List_User.getPoint(i).user[post.getIduser()].getPhotoProfile()) != null)
                                ? new ImageView(new Image(new FileInputStream(photo)))
                                : new ImageView(
                                        new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")));

                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        Circle circle = new Circle(25, 25, 25);
                        imageView.setClip(circle);

                        imageView.setPreserveRatio(true);

                        Label nameUser = new Label(List_User.getPoint(0).user[post.getIduser()].getName());
                        nameUser.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");// nameuser
                        nameUser.setPadding(new Insets(13, 0, 0, 10));
                        hBox.getChildren().addAll(imageView, nameUser);

                        imageView.setCursor(Cursor.HAND);
                        nameUser.setCursor(Cursor.HAND);

                        String dep = "nao pode";
                        String perfilVisi = List_User.getPoint(2).user[indexF].getProfileVisibility();

                        imageView.setOnMouseClicked(event -> {
                            try {
                                new FriendProfile(id, indexF, perfilVisi, dep).getStage().show();
                                this.stage.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        nameUser.setOnMouseClicked(event -> {
                            try {
                                new FriendProfile(id, indexF, perfilVisi, dep).getStage().show();
                                this.stage.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        vBox.setStyle(
                                "-fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: white;");
                        Label titlePost = new Label(post.getTitle());
                        titlePost.setStyle("-fx-font-weight: bold; -fx-font-size: 14px");// titulo
                        titlePost.setPadding(new Insets(10, 0, 5, 100));
                        titlePost.setWrapText(true);

                        // texto public
                        Text text = new Text(post.getPostTxt());
                        TextFlow txtPost = new TextFlow();
                        txtPost.setStyle("-fx-font-size: 14px");
                        txtPost.setMaxWidth(400);
                        txtPost.getChildren().add(text);
                        txtPost.setCache(false);
                        txtPost.setCacheShape(false);
                        txtPost.setCenterShape(false);
                        txtPost.setFocusTraversable(false);
                        txtPost.getStylesheets()
                                .add(this.getClass().getResource("ScreensFXML/CSS/TxtAreaToPosts.css").toString());
                        txtPost.setOnMouseClicked(event -> {
                            this.pane.requestFocus();
                        });

                        HBox box = new HBox(1);
                        box.setPadding(new Insets(10, 0, 0, 100));

                        if (post.getImagem() != null) {
                            ImageView image = new ImageView(new Image(new FileInputStream(post.getImagem())));
                            image.setFitHeight(400);
                            image.setFitWidth(400);
                            box.getChildren().add(image);
                            image.setPreserveRatio(true);
                        }

                        int likesQnt = post.getLikes();
                        Label lblQntLikes = new Label(String.valueOf(likesQnt));
                        lblQntLikes.setPadding(new Insets(3, 0, 0, 2));

                        Button like = new Button("   ");
                        if (post.checkLike(id) == 0) {
                            like.setStyle(
                                    "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-background-color: red; -fx-text-fill:white;");
                            post.addLike(id);
                        } else {
                            like.setStyle(
                                    "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;");
                        }

                        Hyperlink comment = new Hyperlink("Comentários " + String.valueOf(post.getComments().size()));
                        comment.setCache(false);
                        comment.setCacheShape(false);
                        comment.setBorder(null);
                        comment.setFocusTraversable(false);
                        comment.setPadding(new Insets(6, 0, 0, 2));
                        comment.setUnderline(false);
                        comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;");
                        comment.setOnMouseEntered(
                                event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));
                        comment.setOnMouseExited(
                                event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));
                        comment.setOnAction(event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));

                        like.setOnMouseClicked(event -> generateLikes(post, like, lblQntLikes, likesQnt));

                        comment.setOnMouseClicked(event -> {
                            try {
                                new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                                pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                pane.setDisable(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        ImageView comentImg = new ImageView(
                                new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/comment_post.PNG")));
                        comentImg.setFitHeight(30);
                        comentImg.setFitWidth(30);
                        comentImg.setPreserveRatio(true);
                        comentImg.setCursor(Cursor.HAND);

                        comentImg.setOnMouseClicked(event -> {
                            try {
                                new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                                pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                pane.setDisable(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox boxComents = new HBox(2);
                        boxComents.getChildren().addAll(comentImg, comment);

                        boxComents.setCursor(Cursor.HAND);
                        boxComents.setOnMouseClicked(event -> {
                            try {
                                new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                                pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                pane.setDisable(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        ImageView encaminharImg = new ImageView(
                                new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/share_icon.PNG")));
                        encaminharImg.setFitHeight(30);
                        encaminharImg.setFitWidth(30);
                        encaminharImg.setPreserveRatio(true);

                        Label encaminharMSG = new Label("Encaminhar");
                        encaminharMSG.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;");
                        encaminharMSG.setPadding(new Insets(6, 0, 0, 2));

                        HBox boxEncaminhar = new HBox(2);
                        boxEncaminhar.getChildren().addAll(encaminharImg, encaminharMSG);
                        boxEncaminhar.setCursor(Cursor.HAND);

                        boxEncaminhar.setOnMouseClicked(event -> {
                            try {
                                new EncaminharScreen(id, pane, post).getStage().show();
                                pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                pane.setDisable(true);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox boxLikes = new HBox(2);
                        boxLikes.getChildren().addAll(like, lblQntLikes);

                        Region spacer1 = new Region();
                        Region spacer2 = new Region();
                        Region spacer3 = new Region();
                        spacer1.setPrefWidth(60);
                        spacer2.setPrefWidth(60);
                        spacer3.setPrefWidth(95);

                        HBox box1 = new HBox();
                        box1.getChildren().addAll(spacer3, boxLikes, spacer1, boxComents, spacer2, boxEncaminhar);
                        box1.setPadding(new Insets(10, 15, 10, 10));

                        HBox box2 = new HBox(3);
                        box2.getChildren().addAll(txtPost);
                        box2.setPadding(new Insets(0, 0, 0, 100));
                        txtPost.setPrefWidth(800);

                        vBox.getChildren().addAll(hBox, titlePost, box2, box, box1);
                        this.vboxPost.getChildren().add(vBox);

                    }

                    // meu post
                    if (indexF == id) {
                        VBox vBox = new VBox(5);
                        HBox hBox = new HBox(2);
                        hBox.setSpacing(3);

                        String photo = ((List_User.getPoint(i).user[post.getIduser()].getPhotoProfile()) != null)
                                ? List_User.getPoint(i).user[post.getIduser()].getPhotoProfile()
                                : "ScreensFXML/Imagens/PERFIL.PNG";

                        ImageView imageView = ((List_User.getPoint(i).user[post.getIduser()].getPhotoProfile()) != null)
                                ? new ImageView(new Image(new FileInputStream(photo)))
                                : new ImageView(
                                        new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")));

                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        Circle circle = new Circle(25, 25, 25);
                        imageView.setClip(circle);

                        imageView.setPreserveRatio(true);

                        Label nameUser = new Label(List_User.getPoint(0).user[post.getIduser()].getName());
                        nameUser.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");// nameuser
                        nameUser.setPadding(new Insets(13, 0, 0, 10));
                        hBox.getChildren().addAll(imageView, nameUser);

                        vBox.setStyle(
                                "-fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: white;");
                        Label titlePost = new Label(post.getTitle());
                        titlePost.setStyle("-fx-font-weight: bold; -fx-font-size: 14px");// titulo
                        titlePost.setPadding(new Insets(10, 0, 5, 100));
                        titlePost.setWrapText(true);

                        // texto public
                        Text text = new Text(post.getPostTxt());
                        TextFlow txtPost = new TextFlow();
                        txtPost.setStyle("-fx-font-size: 14px");
                        txtPost.setMaxWidth(400);// tamanho do texto post
                        txtPost.getChildren().add(text);
                        txtPost.setCache(false);
                        txtPost.setCacheShape(false);
                        txtPost.setCenterShape(false);
                        txtPost.setFocusTraversable(false);
                        txtPost.getStylesheets()
                                .add(this.getClass().getResource("ScreensFXML/CSS/TxtAreaToPosts.css").toString());
                        txtPost.setOnMouseClicked(event -> {
                            this.pane.requestFocus();
                        });

                        HBox box = new HBox(1);
                        box.setPadding(new Insets(10, 0, 0, 100));

                        if (post.getImagem() != null) {
                            ImageView image = new ImageView(new Image(new FileInputStream(post.getImagem())));
                            image.setFitHeight(400);
                            image.setFitWidth(400);
                            box.getChildren().add(image);
                            image.setPreserveRatio(true);
                        }

                        int likesQnt = post.getLikes();
                        Label lblQntLikes = new Label(String.valueOf(likesQnt));
                        lblQntLikes.setPadding(new Insets(3, 0, 0, 2));

                        Button like = new Button("   ");
                        if (post.checkLike(id) == 0) {
                            like.setStyle(
                                    "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-background-color: red; -fx-text-fill:white;");
                            post.addLike(id);
                        } else {
                            like.setStyle(
                                    "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;");
                        }

                        Hyperlink comment = new Hyperlink("Comentários " + String.valueOf(post.getComments().size()));
                        comment.setCache(false);
                        comment.setCacheShape(false);
                        comment.setBorder(null);
                        comment.setFocusTraversable(false);
                        comment.setPadding(new Insets(6, 0, 0, 2));
                        comment.setUnderline(false);
                        comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;");
                        comment.setOnMouseEntered(
                                event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));
                        comment.setOnMouseExited(
                                event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));
                        comment.setOnAction(event -> comment.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;"));

                        like.setOnMouseClicked(event -> generateLikes(post, like, lblQntLikes, likesQnt));

                        comment.setOnMouseClicked(event -> {
                            try {
                                new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                                pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                pane.setDisable(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        ImageView comentImg = new ImageView(
                                new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/comment_post.PNG")));
                        comentImg.setFitHeight(30);
                        comentImg.setFitWidth(30);
                        comentImg.setPreserveRatio(true);
                        comentImg.setCursor(Cursor.HAND);

                        comentImg.setOnMouseClicked(event -> {
                            try {
                                new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                                pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                pane.setDisable(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox boxComents = new HBox(2);
                        boxComents.getChildren().addAll(comentImg, comment);

                        boxComents.setCursor(Cursor.HAND);
                        boxComents.setOnMouseClicked(event -> {
                            try {
                                new CommentScreen(id, post, pane, comment, like, lblQntLikes).getStage().show();
                                pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                pane.setDisable(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        ImageView encaminharImg = new ImageView(
                                new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/share_icon.PNG")));
                        encaminharImg.setFitHeight(30);
                        encaminharImg.setFitWidth(30);
                        encaminharImg.setPreserveRatio(true);

                        Label encaminharMSG = new Label("Encaminhar");
                        encaminharMSG.setStyle("-fx-font-size: 12.5px; -fx-text-fill: black;");
                        encaminharMSG.setPadding(new Insets(6, 0, 0, 2));

                        HBox boxEncaminhar = new HBox(2);
                        boxEncaminhar.getChildren().addAll(encaminharImg, encaminharMSG);
                        boxEncaminhar.setCursor(Cursor.HAND);

                        boxEncaminhar.setOnMouseClicked(event -> {
                            try {
                                new EncaminharScreen(id, pane, post).getStage().show();
                                pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                pane.setDisable(true);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox boxLikes = new HBox(2);
                        boxLikes.getChildren().addAll(like, lblQntLikes);

                        Region spacer1 = new Region();
                        Region spacer2 = new Region();
                        Region spacer3 = new Region();
                        spacer1.setPrefWidth(60);
                        spacer2.setPrefWidth(60);
                        spacer3.setPrefWidth(95);

                        HBox box1 = new HBox();
                        box1.getChildren().addAll(spacer3, boxLikes, spacer1, boxComents, spacer2, boxEncaminhar);
                        box1.setPadding(new Insets(10, 15, 10, 10));

                        HBox box2 = new HBox(3);
                        box2.getChildren().addAll(txtPost);
                        box2.setPadding(new Insets(0, 0, 0, 100));
                        txtPost.setPrefWidth(800);

                        vBox.getChildren().addAll(hBox, titlePost, box2, box, box1);
                        this.vboxPost.getChildren().add(vBox);

                    }
                }
            }

        } catch (Exception ie) {
            ie.printStackTrace();
        }

    }

    private void generateLikes(Post post, Button like, Label lblLike, int likesQnt) {
        if (post.checkLike(id) != 0) {
            like.setStyle(
                    "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-background-color: red; -fx-text-fill:white;");
            post.addLike(id);
        } else {
            like.setStyle(
                    "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;");
            post.removeLike(id);
        }
        likesQnt = post.getLikes();
        lblLike.setText(String.valueOf(likesQnt));
    }

    @FXML
    private void backToHome(MouseEvent event) throws Exception {
        new CommunityScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    void goToPublic(MouseEvent event) throws Exception {

        new PublicPostCommunity(id, community, this).getStage().show();
        pane.effectProperty().set(new MotionBlur(3.0, 15.0));
        pane.setDisable(true);
    }

    @FXML
    private void goToEditCommunity(MouseEvent event) throws Exception {
        new EditCommunity(this, community).getStage().show();
        pane.effectProperty().set(new MotionBlur(3.0, 15.0));
        pane.setDisable(true);
    }

    @FXML
    private void goToEditProfile(MouseEvent event) throws Exception {

    }

    @FXML
    private void goToSolicit(MouseEvent event) throws Exception {

    }

    @FXML
    private void goToFriends(MouseEvent event) throws Exception {
        new FriendsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToSettings(MouseEvent event) throws Exception {
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }
}
