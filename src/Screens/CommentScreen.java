package Screens;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Body.Comments;
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
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CommentScreen {
    private Stage stage = new Stage();
    private static int id = 0;
    private static int idComment = 0;
    private Post post;
    private Pane otherPane;
    private Hyperlink hyperPost;
    private Button otherButton;
    private Label qntLike;
    private User userNow;

    @FXML
    private ImageView ImageUser;

    @FXML
    private Button btnLike;

    @FXML
    private ImageView imageX;

    @FXML
    private Label lblUsername;

    @FXML
    private TextField txtNewComment;

    @FXML
    private TextArea txtPublicArgs;

    @FXML
    private Label txtTitlePost;

    @FXML
    private VBox vBoxComments;

    @FXML
    private Label lblDateTime;

    @FXML
    private ImageView ImageImgPost;

    @FXML
    private ScrollPane scroolPane;

    public CommentScreen(int idUser, Post postI, Pane otherScreen, Hyperlink hyper, Button principal, Label lblQntLike)
            throws Exception {
        id = idUser;
        post = postI;
        this.otherPane = otherScreen;
        hyperPost = hyper;
        this.otherButton = principal;
        this.hyperPost = hyper;
        this.qntLike = lblQntLike;
        userNow = List_User.getPoint(2).user[id];

        idComment = postI.getComments().size() - 1;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenComments.fxml"));
        loader.setController(this);
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        this.stage.setScene(scene);
        this.stage.setTitle("Comentários");
        Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
        stage.getIcons().add(image);
        this.stage.setResizable(false);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.setOnCloseRequest(event -> {
            this.backScreen(null);
        });

        pane.requestFocus();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER) {
                if (this.txtNewComment.getText().length() != 0) {
                    Comments comment = new Comments();
                    ++idComment;
                    comment.setId((short) idComment);
                    comment.setIdUser((short) idUser);
                    comment.setComment(this.txtNewComment.getText());
                    this.post.getComments().add(comment);
                    this.txtNewComment.setText("");
                    if (hyperPost != null)
                        hyperPost.setText("Comentários " + String.valueOf(post.getComments().size()));
                    this.vBoxComments.getChildren().clear();
                    try {
                        organize();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public Stage getStage() {
        return this.stage;
    }

    @FXML
    private void initialize() {

        scroolPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        scroolPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra horizontal do scroll
        scroolPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        this.lblUsername.setText(List_User.getPoint(id).user[this.post.getIduser()].getName());
        this.txtTitlePost.setText(this.post.getTitle());
        this.txtPublicArgs.setText(this.post.getPostTxt());
        lblDateTime.setText(post.getFormattedDateTime());

        if (this.post.checkLike(id) == 0) {
            this.btnLike.setStyle(
                    "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: white; -fx-background-color:red;");
        } else {
            this.btnLike.setStyle(
                    "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;");
        }
        try {
            if (this.post.getImagem() != null) {
                this.ImageImgPost.setImage(new Image(new FileInputStream(this.post.getImagem())));
            }
            String photo = List_User.getPoint(0).user[this.post.getIduser()].getPhotoProfile();
            if (photo != null) {
                this.ImageUser.setImage(new Image(new FileInputStream(photo)));
                this.ImageUser.setFitHeight(50);
                this.ImageUser.setFitWidth(50);

                Circle circle = new Circle(25, 25, 25);
                ImageUser.setClip(circle);

            }
            organize();

        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }

    private void organize() throws FileNotFoundException {
        User user[] = List_User.getPoint(id).user;

        if (!post.getComments().isEmpty()) {
            for (int i = post.getComments().size() - 1; i >= 0; --i) {

                int indexF = post.getComments().get(i).getIdUser();

                if (userNow.checkFriend(indexF) == 0) {

                    VBox vBox = new VBox(2);

                    // imagem de perfil amigo
                    ImageView imgIConFriend = (user[post.getComments().get(i).getIdUser()].getPhotoProfile() == null)
                            ? new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                            : new ImageView(new Image(new FileInputStream(
                                    user[post.getComments().get(i).getIdUser()].getPhotoProfile())));
                    imgIConFriend.setFitHeight(40);
                    imgIConFriend.setFitWidth(40);

                    Circle circle = new Circle(20, 20, 20);
                    imgIConFriend.setClip(circle);

                    // nome do amigo
                    Label userName = new Label(user[post.getComments().get(i).getIdUser()].getName());
                    userName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                    userName.setPadding(new Insets(10, 0, 0, 5));

                    imgIConFriend.setCursor(Cursor.HAND);
                    userName.setCursor(Cursor.HAND);

                    String dep = "pode";
                    String perfilVisi = "amigos <3";

                    int idAmigo = post.getComments().get(i).getIdUser();
                    imgIConFriend.setOnMouseClicked(event -> {
                        try {
                            new FriendProfile(id, idAmigo, perfilVisi, dep).getStage().show();
                            backScreen(event);
                            this.stage.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    userName.setOnMouseClicked(event -> {
                        try {
                            new FriendProfile(id, idAmigo, perfilVisi, dep).getStage().show();
                            backScreen(event);
                            this.stage.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    HBox hBoxLeft = new HBox(10);
                    hBoxLeft.getChildren().addAll(imgIConFriend, userName);
                    hBoxLeft.setPadding(new Insets(5, 10, 5, 10));

                    Label lblComment = new Label(this.post.getComments().get(i).getComment());
                    lblComment.setStyle("-fx-font-size: 12;");
                    lblComment.setPadding(new Insets(0, 0, 0, 40));

                    Text dateTimeText = new Text(post.getComments().get(i).getFormattedDateTime());
                    dateTimeText.setStyle("-fx-font-size: 10; -fx-fill: #666565;");
                    TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                    dateTimeFlow.setPadding(new Insets(0, 0, 0, 350));

                    vBox.getChildren().addAll(hBoxLeft, lblComment, dateTimeFlow);

                    this.vBoxComments.getChildren().addAll(new Separator(), vBox, new Separator());
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

                            VBox vBox = new VBox(2);

                            // imagem de perfil amigo
                            ImageView imgIConFriend = (user[post.getComments().get(i).getIdUser()]
                                    .getPhotoProfile() == null) ? new ImageView(
                                            new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                                            : new ImageView(new Image(new FileInputStream(
                                                    user[post.getComments().get(i).getIdUser()].getPhotoProfile())));
                            imgIConFriend.setFitHeight(40);
                            imgIConFriend.setFitWidth(40);

                            Circle circle = new Circle(20, 20, 20);
                            imgIConFriend.setClip(circle);

                            // nome do amigo
                            Label userName = new Label(user[post.getComments().get(i).getIdUser()].getName());
                            userName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                            userName.setPadding(new Insets(10, 0, 0, 5));

                            imgIConFriend.setCursor(Cursor.HAND);
                            userName.setCursor(Cursor.HAND);

                            String dep = "nao pode";
                            String perfilVisi = List_User.getPoint(2).user[indexF].getProfileVisibility();

                            int idAmigo = post.getComments().get(i).getIdUser();
                            imgIConFriend.setOnMouseClicked(event -> {
                                try {
                                    new FriendProfile(id, idAmigo, perfilVisi, dep).getStage().show();
                                    backScreen(event);
                                    this.stage.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            userName.setOnMouseClicked(event -> {
                                try {
                                    new FriendProfile(id, idAmigo, perfilVisi, dep).getStage().show();
                                    backScreen(event);
                                    this.stage.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            HBox hBoxLeft = new HBox(10);
                            hBoxLeft.getChildren().addAll(imgIConFriend, userName);
                            hBoxLeft.setPadding(new Insets(5, 10, 5, 10));

                            Label lblComment = new Label(this.post.getComments().get(i).getComment());
                            lblComment.setStyle("-fx-font-size: 12;");
                            lblComment.setPadding(new Insets(0, 0, 0, 40));

                            Text dateTimeText = new Text(post.getComments().get(i).getFormattedDateTime());
                            dateTimeText.setStyle("-fx-font-size: 10; -fx-fill: #666565;");
                            TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                            dateTimeFlow.setPadding(new Insets(0, 0, 0, 350));

                            vBox.getChildren().addAll(hBoxLeft, lblComment, dateTimeFlow);

                            this.vBoxComments.getChildren().addAll(new Separator(), vBox, new Separator());
                        }

                    }

                    if (List_User.getPoint(2).user[id].getSolicit().contains((Integer) indexF)
                            || List_User.getPoint(2).user[id].getList_Solicit().contains((Integer) indexF)) {

                        VBox vBox = new VBox(2);

                        // imagem de perfil amigo
                        ImageView imgIConFriend = (user[post.getComments().get(i).getIdUser()]
                                .getPhotoProfile() == null) ? new ImageView(
                                        new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                                        : new ImageView(new Image(new FileInputStream(
                                                user[post.getComments().get(i).getIdUser()].getPhotoProfile())));
                        imgIConFriend.setFitHeight(40);
                        imgIConFriend.setFitWidth(40);

                        Circle circle = new Circle(20, 20, 20);
                        imgIConFriend.setClip(circle);

                        // nome do amigo
                        Label userName = new Label(user[post.getComments().get(i).getIdUser()].getName());
                        userName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                        userName.setPadding(new Insets(10, 0, 0, 5));

                        imgIConFriend.setCursor(Cursor.HAND);
                        userName.setCursor(Cursor.HAND);

                        String dep = "nao pode";
                        String perfilVisi = List_User.getPoint(2).user[indexF].getProfileVisibility();

                        int idAmigo = post.getComments().get(i).getIdUser();
                        imgIConFriend.setOnMouseClicked(event -> {
                            try {
                                new FriendProfile(id, idAmigo, perfilVisi, dep).getStage().show();
                                backScreen(event);
                                this.stage.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        userName.setOnMouseClicked(event -> {
                            try {
                                new FriendProfile(id, idAmigo, perfilVisi, dep).getStage().show();
                                backScreen(event);
                                this.stage.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox hBoxLeft = new HBox(10);
                        hBoxLeft.getChildren().addAll(imgIConFriend, userName);
                        hBoxLeft.setPadding(new Insets(5, 10, 5, 10));

                        Label lblComment = new Label(this.post.getComments().get(i).getComment());
                        lblComment.setStyle("-fx-font-size: 12;");
                        lblComment.setPadding(new Insets(0, 0, 0, 40));

                        Text dateTimeText = new Text(post.getComments().get(i).getFormattedDateTime());
                        dateTimeText.setStyle("-fx-font-size: 10; -fx-fill: #666565;");
                        TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                        dateTimeFlow.setPadding(new Insets(0, 0, 0, 350));

                        vBox.getChildren().addAll(hBoxLeft, lblComment, dateTimeFlow);

                        this.vBoxComments.getChildren().addAll(new Separator(), vBox, new Separator());
                    }

                    if (indexF == id) {

                        VBox vBox = new VBox(2);

                        // imagem de perfil amigo
                        ImageView imgIConFriend = (user[post.getComments().get(i).getIdUser()]
                                .getPhotoProfile() == null) ? new ImageView(
                                        new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                                        : new ImageView(new Image(new FileInputStream(
                                                user[post.getComments().get(i).getIdUser()].getPhotoProfile())));
                        imgIConFriend.setFitHeight(40);
                        imgIConFriend.setFitWidth(40);

                        Circle circle = new Circle(20, 20, 20);
                        imgIConFriend.setClip(circle);

                        // nome do amigo
                        Label userName = new Label(user[post.getComments().get(i).getIdUser()].getName());
                        userName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                        userName.setPadding(new Insets(10, 0, 0, 5));

                        HBox hBoxLeft = new HBox(10);
                        hBoxLeft.getChildren().addAll(imgIConFriend, userName);
                        hBoxLeft.setPadding(new Insets(5, 10, 5, 10));

                        Label lblComment = new Label(this.post.getComments().get(i).getComment());
                        lblComment.setStyle("-fx-font-size: 12;");
                        lblComment.setPadding(new Insets(0, 0, 0, 40));

                        Text dateTimeText = new Text(post.getComments().get(i).getFormattedDateTime());
                        dateTimeText.setStyle("-fx-font-size: 10; -fx-fill: #666565;");
                        TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                        dateTimeFlow.setPadding(new Insets(0, 0, 0, 350));

                        vBox.getChildren().addAll(hBoxLeft, lblComment, dateTimeFlow);

                        this.vBoxComments.getChildren().addAll(new Separator(), vBox, new Separator());
                    }
                }

            }

        }

    }

    @FXML
    private void doLike(MouseEvent event) {
        if (post.checkLike(id) != 0) {
            btnLike.setStyle(
                    "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-background-color: red; -fx-text-fill:white;");
            post.addLike(id);
            if (otherButton != null)
                this.otherButton.setStyle(btnLike.getStyle());
        } else {
            btnLike.setStyle(
                    "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;");
            if (otherButton != null)
                this.otherButton.setStyle(btnLike.getStyle());
            post.removeLike(id);
        }
        if (qntLike != null)
            qntLike.setText(String.valueOf(this.post.getLikes()));
    }

    @FXML
    private void backScreen(MouseEvent event) {
        this.otherPane.effectProperty().set(null);
        otherPane.setDisable(false);
        this.stage.close();
    }

}
