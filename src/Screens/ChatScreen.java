package Screens;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import com.jgoodies.common.collect.LinkedListModel;
import Body.Chat;
import Body.Message;
import Body.Post;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ChatScreen {
    private Stage stage = new Stage();
    private Pane pane;
    private static int id = 0, idFriend = -1;
    private List<Integer> chats;

    @FXML
    private ScrollPane ScrollViewChat;

    @FXML
    private HBox Hbox_to_ScreenFriends;

    @FXML
    private HBox Hbox_to_ScreenPublic;

    @FXML
    private ImageView exitToHome;

    @FXML
    private HBox hBoxUserProfile;

    @FXML
    private ImageView imageSetProfile;

    @FXML
    private TextField mensageToChat;

    @FXML
    private VBox vboxSelectChat;

    @FXML
    private VBox vboxStartChat;

    @FXML
    private VBox vboxViewChat;

    @FXML
    private ImageView viewSettings;

    @FXML
    private Button btnSendMessage;

    @FXML
    private ImageView imageComunity;

    @FXML
    private ImageView imageFriends;

    @FXML
    private ImageView imageProfile;

    @FXML
    private ImageView imagePublication;

    @FXML
    private Label lblNameFriend;

    private ImageView chatProfileImage;

    public ChatScreen(int i) throws Exception {
        id = i;
        this.chats = new LinkedListModel<>(List_User.getPoint(0).user[id].getDequeChat());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenChat.fxml"));
        loader.setController(this);
        this.pane = loader.load();
        this.stage.setScene(new Scene(pane));
        this.stage.setTitle("Chat");
        this.stage.setResizable(false);

        pane.requestFocus();

        this.stage.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER && this.mensageToChat.getText().length() != 0) {
                try {
                    this.sendMessage(idFriend, this.mensageToChat.getText());
                } catch (Exception ie) {
                    ie.printStackTrace();
                }
            }
        });

    }

    @FXML
    private void initialize() {

        Tooltip tooltipComunity = new Tooltip("Comunidade");
        Tooltip tooltipFriends = new Tooltip("Amigos");
        Tooltip tooltipProfile = new Tooltip("Perfil");
        Tooltip tooltipPublication = new Tooltip("Publicação");

        Tooltip.install(imageComunity, tooltipComunity);
        Tooltip.install(imageFriends, tooltipFriends);
        Tooltip.install(imageProfile, tooltipProfile);
        Tooltip.install(imagePublication, tooltipPublication);

        chatProfileImage = imageSetProfile;
        imageSetProfile.setVisible(false);

        try {
            this.genarateChats();
            this.genarateNewChats();
        } catch (Exception ei) {
            ei.printStackTrace();
        }
    }

    public Stage getStage() {
        return this.stage;
    }

    public Pane getPane() {
        return this.pane;
    }

    // gerar o chat em si
    public void genareteViewChat(int friendUserId) {
        idFriend = friendUserId;
        this.vboxViewChat.getChildren().clear();
        User user = List_User.getPoint(id).user[id];

        if (!user.getChats().containsKey(friendUserId))
            return;
        if (user.getChats().size() != 0) {

            ArrayList<Message> chat = user.getChats().get(friendUserId).getMessages();

            for (int i = 0; i < chat.size(); ++i) {
                int index = i;

                Post thePost = chat.get(index).getPost();

                if (thePost != null) {

                    VBox vBox = new VBox(2);

                    ContextMenu contecContextMenu = new ContextMenu();
                    MenuItem menu = new MenuItem("Delete");
                    contecContextMenu.getItems().addAll(menu);

                    menu.setOnAction(event -> {
                        user.getChats().get(friendUserId).remove(chat.get(index).getId());
                        this.genareteViewChat(friendUserId);
                    });

                    if (chat.get(i).getSender() == id) {

                        vBox.setOnMouseClicked(event -> {
                            if (event.getButton().name().compareTo("SECONDARY") == 0) {
                                contecContextMenu.show(vBox, event.getScreenX(), event.getScreenY());
                            }
                        });

                        vBox.setPadding(new Insets(10, 10, 10, this.vboxViewChat.getPrefWidth() / 2));

                        Hyperlink postLink = new Hyperlink("Ver post: "+thePost.getTitle());
                        postLink.setCache(false);
                        postLink.setCacheShape(false);
                        postLink.setBorder(null);
                        postLink.setFocusTraversable(false);
                        postLink.setPadding(new Insets(6, 0, 0, 2));
                        postLink.setUnderline(true);
                        postLink.setStyle("-fx-font-size: 17; -fx-text-fill: lightblue;");
                        postLink.setOnMouseEntered(event -> postLink.setStyle("-fx-font-size: 17; -fx-text-fill: white;"));
                        postLink.setOnMouseExited(event -> postLink.setStyle("-fx-font-size: 17; -fx-text-fill: white;"));
                        postLink.setOnAction(event -> postLink.setStyle("-fx-font-size: 17; -fx-text-fill: white;"));

                        postLink.setOnMouseClicked(event -> {

                            try {
                                new CommentScreen(id, thePost, pane, null, null, null).getStage().show();
                                pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                pane.setDisable(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        TextFlow textFlow = new TextFlow(postLink);
                        textFlow.setStyle(
                                "-fx-background-radius: 20px;" +
                                        "-fx-background-color: rgb(123,56,255);" +
                                        "-fx-border-color: transparent;" +
                                        "-fx-border-radius: 20px;");
                        textFlow.setCursor(Cursor.HAND);
                        textFlow.setPadding(new Insets(5, 20, 5, 20));

                        Text dateTimeText = new Text(chat.get(i).getFormattedDateTime());
                        dateTimeText.setStyle("-fx-font-size: 12; -fx-fill: gray;");
                        TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                        dateTimeFlow.setPadding(new Insets(0, 0, 0, 308));

                        vBox.getChildren().addAll(textFlow, dateTimeFlow);

                    } else {

                        vBox.setPadding(new Insets(10, this.vboxViewChat.getPrefWidth() / 2, 10, 10));

                        Hyperlink postLink = new Hyperlink("Ver post: "+thePost.getTitle());
                        postLink.setCache(false);
                        postLink.setCacheShape(false);
                        postLink.setBorder(null);
                        postLink.setFocusTraversable(false);
                        postLink.setPadding(new Insets(6, 0, 0, 2));
                        postLink.setUnderline(true);
                        postLink.setStyle("-fx-font-size: 17; -fx-text-fill: darkblue;");
                        postLink.setOnMouseEntered(event -> postLink.setStyle("-fx-font-size: 17; -fx-text-fill: black;"));
                        postLink.setOnMouseExited(event -> postLink.setStyle("-fx-font-size: 17; -fx-text-fill: black;"));
                        postLink.setOnAction(event -> postLink.setStyle("-fx-font-size: 17; -fx-text-fill: black;"));

                        postLink.setOnMouseClicked(event -> {

                            try {
                                new CommentScreen(id, thePost, pane, null, null, null).getStage().show();
                                pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                                pane.setDisable(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        TextFlow textFlow = new TextFlow(postLink);
                        textFlow.setStyle(
                                "-fx-background-radius: 20px;" +
                                        "-fx-background-color: rgb(211,211,211);" +
                                        "-fx-border-color: transparent;" +
                                        "-fx-border-radius: 20px;");
                        textFlow.setPadding(new Insets(5, 20, 5, 20));

                        Text dateTimeText = new Text(chat.get(i).getFormattedDateTime());
                        dateTimeText.setStyle("-fx-font-size: 12; -fx-fill: gray;");
                        TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                        dateTimeFlow.setPadding(new Insets(0, 0, 0, 15));

                        vBox.getChildren().addAll(textFlow, dateTimeFlow);
                    }

                    this.vboxViewChat.getChildren().add(vBox);

                } else {

                    VBox vBox = new VBox(2);

                    ContextMenu contecContextMenu = new ContextMenu();
                    MenuItem menu = new MenuItem("Delete");
                    MenuItem menu1 = new MenuItem("Edit");
                    contecContextMenu.getItems().addAll(menu1, menu);

                    menu1.setOnAction(event -> {
                        try {
                            new EditMessage(idFriend, user.getChats().get(idFriend), chat.get(index), this).getStage()
                                    .show();
                            this.pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                            pane.setDisable(true);
                        } catch (Exception ie) {
                            ie.printStackTrace();
                        }
                    });

                    menu.setOnAction(event -> {
                        user.getChats().get(friendUserId).remove(chat.get(index).getId());
                        this.genareteViewChat(friendUserId);
                    });

                    if (chat.get(i).getSender() == id) {

                        vBox.setOnMouseClicked(event -> {
                            if (event.getButton().name().compareTo("SECONDARY") == 0) {
                                contecContextMenu.show(vBox, event.getScreenX(), event.getScreenY());
                            }
                        });

                        vBox.setPadding(new Insets(10, 10, 10, this.vboxViewChat.getPrefWidth() / 2));
                        Text text = new Text(chat.get(i).getTxtMessage());
                        text.setStyle("-fx-font-size: 17;");
                        text.setFill(Paint.valueOf("rgb(255,255,255)"));
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setStyle(
                                "-fx-background-radius: 20px;" +
                                        "-fx-background-color: rgb(123,56,255);" +
                                        "-fx-border-color: transparent;" +
                                        "-fx-border-radius: 20px;");
                        textFlow.setCursor(Cursor.HAND);
                        textFlow.setPadding(new Insets(5, 20, 5, 20));

                        Text dateTimeText = new Text(chat.get(i).getFormattedDateTime());
                        dateTimeText.setStyle("-fx-font-size: 12; -fx-fill: gray;");
                        TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                        dateTimeFlow.setPadding(new Insets(0, 0, 0, 308));

                        vBox.getChildren().addAll(textFlow, dateTimeFlow);
                    } else {

                        vBox.setPadding(new Insets(10, this.vboxViewChat.getPrefWidth() / 2, 10, 10));
                        Text text = new Text(chat.get(i).getTxtMessage());
                        text.setStyle("-fx-font-size: 17;");
                        text.setFill(Paint.valueOf("rgb(0,0,0)"));
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setStyle(
                                "-fx-background-radius: 20px;" +
                                        "-fx-background-color: rgb(211,211,211);" +
                                        "-fx-border-color: transparent;" +
                                        "-fx-border-radius: 20px;");
                        textFlow.setPadding(new Insets(5, 20, 5, 20));

                        Text dateTimeText = new Text(chat.get(i).getFormattedDateTime());
                        dateTimeText.setStyle("-fx-font-size: 12; -fx-fill: gray;");
                        TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                        dateTimeFlow.setPadding(new Insets(0, 0, 0, 15));

                        vBox.getChildren().addAll(textFlow, dateTimeFlow);
                    }

                    this.vboxViewChat.getChildren().add(vBox);
                }

            }

            new Thread() {
                @SuppressWarnings("removal")
                public void run() {
                    try {
                        while (true) {
                            Thread.sleep(100);
                            if ((int) ScrollViewChat.getVvalue() != 1) {
                                ScrollViewChat.setVvalue(1);
                                if ((int) ScrollViewChat.getVvalue() != 0)
                                    stop();
                            } else if ((int) ScrollViewChat.getVvalue() != 0)
                                stop();
                        }
                    } catch (Exception ie) {
                        ie.printStackTrace();
                    }
                }
            }.start();
        }
    }

    // gerar lista de amigos que ja se tem conversa
    public void genarateChats() throws FileNotFoundException {
        this.chats = new LinkedListModel<>(List_User.getPoint(0).user[id].getDequeChat());
        this.vboxSelectChat.getChildren().clear();

        for (int i = 0; i < List_User.getPoint(i).user[id].getDequeChat().size(); ++i) {
            User user = List_User.getPoint(id).user[this.chats.get(i)];
            HBox Hbox = new HBox();
            Hbox.setPadding(new Insets(3, 0, 0, 3));
            Hbox.setCursor(Cursor.HAND);
            Hbox.setStyle("-fx-border-color: white; " +
                    "-fx-border-radius: 7px; " +
                    "-fx-border-width: 3;");
            Hbox.setOnMouseEntered(event -> {
                Hbox.setStyle("-fx-border-color: rgb(123,56,255); " +
                        "-fx-border-radius: 7px; " +
                        "-fx-border-width: 3;");
            });
            Hbox.setOnMouseExited(event -> {
                Hbox.setStyle("-fx-border-color: white; " +
                        "-fx-border-radius: 7px; " +
                        "-fx-border-width: 3;");
            });

            int idAmg = List_User.getPoint(0).getId(user.getEmail());
            Hbox.setOnMouseClicked(event -> {

                this.genareteViewChat(idAmg);
                lblNameFriend.setText(List_User.getPoint(0).user[idAmg].getName());
                imageSetProfile.setVisible(true);
                try {
                    // if(List_User.getPoint(id).user[idAmg].getPhotoProfile()!=null){
                    /*
                     * imageSetProfile.setImage(new Image(new
                     * FileInputStream(user.getPhotoProfile())));
                     * imageSetProfile.setFitHeight(53);
                     * imageSetProfile.setFitWidth(53);
                     */

                    Image image = (user.getPhotoProfile() != null)
                            ? new Image((new FileInputStream(user.getPhotoProfile())))
                            : new Image((getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")));

                    imageSetProfile.setImage(image);
                    imageSetProfile.setFitHeight(53);
                    imageSetProfile.setFitWidth(53);

                    Circle circle = new Circle(26.5, 26.5, 26.5);
                    imageSetProfile.setClip(circle);

                } catch (Exception ie) {
                    ie.printStackTrace();
                }

            });

            String dep = "pode";
            String perfilVisi = "Amigos <3";
            imageSetProfile.setOnMouseClicked(event -> {
                try {
                    new FriendProfile(id, idAmg, perfilVisi, dep).getStage().show();
                    this.stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            lblNameFriend.setOnMouseClicked(event -> {
                try {
                    new FriendProfile(id, idAmg, perfilVisi, dep).getStage().show();
                    this.stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            ImageView img = (user.getPhotoProfile() != null)
                    ? new ImageView(new Image(new FileInputStream(user.getPhotoProfile())))
                    : new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")));

            img.setFitHeight(50);
            img.setFitWidth(50);

            Circle circle = new Circle(25, 25, 25);
            img.setClip(circle);

            VBox vBox = new VBox(1);
            Label label = new Label(user.getName());
            label.setStyle(
                    "-fx-font-family: Poppins;" +
                            "-fx-font-size: 16;" +
                            "-fx-font-weight: bold;");
            label.setPadding(new Insets(10, 0, 0, 5));

            String message;
            if(user.getChats().get(id).getLastMessage().getPost()==null){
                message = user.getChats().get(id).getLastMessage().getTxtMessage();
            }else {message = user.getChats().get(id).getLastMessage().getPost().getTitle();}

            Label msg = new Label(message);
            msg.setStyle(
                    "");
            msg.setPadding(new Insets(0, 0, 5, 8));

            vBox.getChildren().addAll(label, msg);
            Hbox.getChildren().addAll(img, vBox);
            this.vboxSelectChat.getChildren().add(Hbox);
        }
    }

    // lista de amigos que nao se tem a conversa ainda
    private void genarateNewChats() throws FileNotFoundException {
        this.vboxStartChat.getChildren().clear();
        ArrayDeque<Integer> users = new ArrayDeque<>();
        User user1 = List_User.getPoint(id).user[id];
        for (int i = 0; i <= user1.getFriends().size(); ++i) {
            if (!user1.getChats().containsKey(i) && user1.checkFriend(i) == 0 && i != id) {
                users.add(i);
            }
        }

        for (int i = 0; i < users.size(); ++i) {
            User user = List_User.getPoint(id).user[users.element() + i];
            HBox Hbox = new HBox();
            Hbox.setPadding(new Insets(3, 0, 2, 3));
            Hbox.setCursor(Cursor.HAND);
            Hbox.setStyle("-fx-border-color: white; " +
                    "-fx-border-radius: 7px; " +
                    "-fx-border-width: 3;");

            int idAmg = List_User.getPoint(0).getId(user.getEmail());
            Hbox.setOnMouseClicked(event -> {
                this.vboxViewChat.getChildren().clear();

                this.genareteViewChat(idAmg);
                lblNameFriend.setText(List_User.getPoint(0).user[idAmg].getName());
                imageSetProfile.setVisible(true);
                try {

                    Image image = (user.getPhotoProfile() != null)
                            ? new Image((new FileInputStream(user.getPhotoProfile())))
                            : new Image((getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")));

                    imageSetProfile.setImage(image);
                    imageSetProfile.setFitHeight(53);
                    imageSetProfile.setFitWidth(53);

                    Circle circle = new Circle(26.5, 26.5, 26.5);
                    imageSetProfile.setClip(circle);

                } catch (Exception ie) {
                    ie.printStackTrace();
                }
            });

            String dep = "pode";
            String perfilVisi = "Amigos <3";
            imageSetProfile.setOnMouseClicked(event -> {
                try {
                    new FriendProfile(id, idAmg, perfilVisi, dep).getStage().show();
                    this.stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            lblNameFriend.setOnMouseClicked(event -> {
                try {
                    new FriendProfile(id, idAmg, perfilVisi, dep).getStage().show();
                    this.stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Hbox.setOnMouseEntered(event -> {
                Hbox.setStyle("-fx-border-color: rgb(123,56,255); " +
                        "-fx-border-radius: 7px; " +
                        "-fx-border-width: 3;");
            });
            Hbox.setOnMouseExited(event -> {
                Hbox.setStyle("-fx-border-color: white; " +
                        "-fx-border-radius: 7px; " +
                        "-fx-border-width: 3;");
            });

            ImageView img = (user.getPhotoProfile() != null)
                    ? new ImageView(new Image(new FileInputStream(user.getPhotoProfile())))
                    : new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")));
            img.setFitHeight(50);
            img.setFitWidth(50);

            // Criar um círculo para o clipping
            Circle circle = new Circle(25, 25, 25); // O raio do círculo é metade do tamanho da imagem (53 / 2)

            // Aplicar o círculo como um clip na ImageView
            img.setClip(circle);

            VBox vBox = new VBox(1);
            Label label = new Label(user.getName());
            label.setStyle(
                    "-fx-font-family: Poppins;" +
                            "-fx-font-size: 16;" +
                            "-fx-font-weight: bold;");
            label.setPadding(new Insets(10, 0, 0, 5));

            vBox.getChildren().addAll(label);
            Hbox.getChildren().addAll(img, vBox);
            this.vboxStartChat.getChildren().add(Hbox);
        }
    }

    private void sendMessage(int idFriend, String newMeString) throws Exception {
        User user = List_User.getPoint(idFriend).user[id];
        User userFriend = List_User.getPoint(idFriend).user[idFriend];
        Chat chat;
        if (!user.getChats().containsKey(idFriend) && !userFriend.getChats().containsKey(id)) {
            chat = new Chat();
            user.getChats().put(idFriend, chat);
            user.getDequeChat().add(idFriend);
            userFriend.getChats().put(id, chat);
            userFriend.getDequeChat().add(id);
        }
        chat = user.getChats().get(idFriend);
        Message message = new Message();
        message.setSender((short) id);
        message.setReceptor((short) idFriend);
        message.setTxtMessage(this.mensageToChat.getText());
        chat.add(message);

        user.getDequeChat().remove((Integer) idFriend);
        user.getDequeChat().addFirst(idFriend);
        userFriend.getDequeChat().remove((Integer) id);
        userFriend.getDequeChat().addFirst((Integer) id);

        this.mensageToChat.clear();
        this.genarateChats();
        this.genareteViewChat(idFriend);
        this.genarateNewChats();
        this.pane.requestFocus();
    }

    @FXML
    private void backToLogin(MouseEvent event) throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToFriends(MouseEvent event) throws Exception {
        new FriendsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToProfile(MouseEvent event) throws Exception {
        new ProfileScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToPublic(MouseEvent event) throws Exception {
        try {
            // Crie a tela de publicação e passe a HomeScreen como parâmetro
            PublicationScreen publicationScreen = new PublicationScreen(id, this.getStage(), null);
            publicationScreen.getStage().show();

            // Efeito de MotionBlur
            pane.effectProperty().set(new MotionBlur(3.0, 15.0));

            publicationScreen.getStage().setOnHidden(event1 -> {
                pane.effectProperty().set(null); // Remove o efeito após fechar
            });

        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }

    @FXML
    private void goToSettings(MouseEvent event) throws Exception {
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void sendMessage(MouseEvent event) {
        if (this.mensageToChat.getText().length() != 0) {
            try {
                this.sendMessage(idFriend, this.mensageToChat.getText());

                this.mensageToChat.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
