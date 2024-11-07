package Screens;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Optional;

import Body.ManagerPosts;
import Body.Post;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class HomeScreen implements Uptable {

    private Stage stage = new Stage();
    private Pane pane;
    private static int id = 0;
    private User userNow;

    @FXML
    private HBox Hbox_goToCommunity;

    @FXML
    private HBox hBoxUserProfile;

    @FXML
    private HBox Hbox_to_ScreenFriends;

    @FXML
    private HBox Hbox_to_ScreenPublic;

    @FXML
    private ImageView imageSetProfile;

    @FXML
    private ImageView exitToHome;

    @FXML
    private VBox vboxPost;

    @FXML
    private Label myUserName;

    @FXML
    private TextArea luckyDay;

    @FXML
    private ScrollPane scrollPosts;

    @FXML
    private ImageView viewSettings;

    @FXML
    private HBox Hbox_to_ScreenChat;

    @FXML
    private HBox Hbox_to_ScreenGames;

    @FXML
    private VBox vboxSugest;

    @FXML
    private ScrollPane scrollSugest;

    public HomeScreen(int newId) throws Exception {
        id = newId;
        userNow = List_User.getPoint(2).user[id];

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenHome.fxml"));
        loader.setController(this);
        pane = loader.load();
        pane.requestFocus();
        stage.setScene(new Scene(pane));
        stage.setTitle("Início");
        Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
            stage.getIcons().add(image);
        stage.setResizable(false);
    }

    public Stage getStage() {
        return this.stage;
    }

    @FXML
    private void initialize() {

        vboxPost.getChildren().clear();
        vboxSugest.getChildren().clear();

        this.myUserName.setText(List_User.getPoint(0).user[id].getName());

        try {
            this.vboxPost.setSpacing((double) 10);
            for (int i = ManagerPosts.geralPosts.size() - 1; i >= 0; --i) {

                Post post = ManagerPosts.geralPosts.get(i);
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
                        txtPost.setMaxWidth(400);//tamanho do texto post
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

        // sugestao de amizade

        try {

            ArrayList<Integer> segs = new ArrayList<>();
            for (int i = 0; i <= List_User.getPoint(i).getSizeUsers(); ++i) {
                if (List_User.getPoint(i).user[id].checkFriend(i) != 0
                        &&
                        !List_User.getPoint(i).user[id].getSolicit().contains((Integer) i)
                        &&
                        i != id
                        &&
                        List_User.getPoint(i).checkExistUser(i) == 0
                        &&
                        !List_User.getPoint(i).user[id].getList_Solicit().contains((Integer) i)) {
                    if (!List_User.getPoint(i).user[id].getUsersBlocks().containsKey(i)) {
                        segs.add(i);
                    }
                }
            }

            // sugestao de amizade
            User[] user = List_User.getPoint(id).user;

            for (int i = segs.size() - 1; i >= 0; --i) {
                HBox hBoxSugest = new HBox(2);
                hBoxSugest.setSpacing(3);// conjunto da sugestao de amizade

                // imagem de perfil amigo
                ImageView imgIConFriend = (user[segs.get(0)].getPhotoProfile() == null)
                        ? new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                        : new ImageView(new Image(new FileInputStream(user[segs.get(0)].getPhotoProfile())));
                imgIConFriend.setFitHeight(50);
                imgIConFriend.setFitWidth(50);

                Circle circle = new Circle(25, 25, 25);
                imgIConFriend.setClip(circle);

                // nome do amigo
                Label userName = new Label(user[segs.get(0)].getName());
                userName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                userName.setPadding(new Insets(15, 0, 0, 10));

                imgIConFriend.setCursor(Cursor.HAND);
                userName.setCursor(Cursor.HAND);

                int index = segs.get(0);

                String dep = "nao pode";
                String perfilVisi = List_User.getPoint(index).user[index].getProfileVisibility();
                imgIConFriend.setOnMouseClicked(event -> {
                    try {
                        new FriendProfile(id, index, perfilVisi, dep).getStage().show();
                        this.stage.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                userName.setOnMouseClicked(event -> {
                    try {
                        new FriendProfile(id, index, perfilVisi, dep).getStage().show();
                        this.stage.close();
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
                    this.initialize();
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
                segs.remove(0);

                this.vboxSugest.getChildren().addAll(hBoxSugest);
            }

        } catch (Exception ie) {
            ie.printStackTrace();
        }

        new Thread() {
            @SuppressWarnings("removal")
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(100);
                        if (scrollPosts.getVvalue() != 0.0) {
                            scrollPosts.setVvalue(0.0);
                            stop();
                        }
                        if (!stage.isShowing())
                            stop();
                    }
                } catch (Exception ie) {
                    ie.printStackTrace();
                }
            }
        }.start();
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
    private void backToLogin(MouseEvent event) throws Exception {
        pane.effectProperty().set(new MotionBlur(3.0, 15.0));

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aviso!");
        alert.setHeaderText("Fazer Log Off");
        alert.setContentText("Gostaria de fazer log off?");

        ButtonType buttonTypeOne = new ButtonType("Sim");
        ButtonType buttonTypeTwo = new ButtonType("Voltar ao Sistema");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            new LoginScreen().getStage().show();
            this.stage.close();
        } else {
            pane.effectProperty().set(null);
        }
    }

    @FXML
    void goToGames(MouseEvent event) throws Exception {

        new GamesScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToSettings(MouseEvent event) throws Exception {
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToFriends(MouseEvent event) throws Exception {
        new FriendsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToPublic(MouseEvent event) throws Exception {
        try {
            // Crie a tela de publicação e passe a HomeScreen como parâmetro
            PublicationScreen publicationScreen = new PublicationScreen(id, this.getStage(), this);
            publicationScreen.getStage().show();

            pane.effectProperty().set(new MotionBlur(3.0, 15.0));
            publicationScreen.getStage().setOnHidden(event1 -> {
                pane.effectProperty().set(null); 
            });

        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }

    @FXML
    void goToCommunity(MouseEvent event) throws Exception{

        new CommunityScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToProfile(MouseEvent event) throws Exception {
        new ProfileScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToChat(MouseEvent event) throws Exception {
        new ChatScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    void goToProfileTop(MouseEvent event) throws Exception {
        new ProfileScreen(id).getStage().show();
        this.stage.close();
    }

    @Override
    public void update() {
        initialize();
    }

}
