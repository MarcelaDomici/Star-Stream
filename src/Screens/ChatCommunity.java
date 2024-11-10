package Screens;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import Body.Chat;
import Body.Community;
import Body.Message;
import Body.Post;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ChatCommunity {
    private static int id = 0;
    private Stage stage = new Stage();
    private Pane pane;
    public Community community;
    Chat chat;

    @FXML
    private HBox Hbox_to_ScreenMembers;

    @FXML
    private HBox Hbox_to_ScreenPublic;

    @FXML
    private HBox Hbox_to_ScreenSeePublics;

    @FXML
    private ScrollPane ScrollViewChat;

    @FXML
    private Button btnSendMessage;

    @FXML
    private ImageView imageCommunity;

    @FXML
    private Label lblCommunity;

    @FXML
    private TextField mensageToChat;

    @FXML
    private TextArea txtTextCommunity;

    @FXML
    private VBox vboxViewChat;

    public ChatCommunity(int newId, Community newComunnity) throws Exception {
        id = newId;
        community = newComunnity;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenChatCommunity.fxml"));
        loader.setController(this);
        this.pane = loader.load();
        this.stage.setScene(new Scene(pane));
        this.stage.setTitle("Chat");
        Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
        stage.getIcons().add(image);
        this.stage.setResizable(false);

        this.stage.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER && this.mensageToChat.getText().length() != 0) {
                try {
                    this.sendMessage(this.mensageToChat.getText());
                    mensageToChat.requestFocus();
                } catch (Exception ie) {
                    ie.printStackTrace();
                }
            }
        });
    }

    public Stage getStage() {
        return this.stage;
    }

    public Pane getPane() {
        return this.pane;
    }

    @FXML
    public void initialize() {

        try {

            txtTextCommunity.setText(community.getTxtCommunity());
            lblCommunity.setText(community.getName());

            if (community.getPhotoCommunity() != null) {
                this.imageCommunity.setImage(new Image(new FileInputStream(community.getPhotoCommunity())));
                this.imageCommunity.setFitHeight(159);
                this.imageCommunity.setFitWidth(159);

                Circle circle = new Circle(75.5, 75.5, 75.5);
                imageCommunity.setClip(circle);
            }

            this.genareteViewChat();
        } catch (Exception ei) {
            ei.printStackTrace();
        }
    }

    public void genareteViewChat() throws FileNotFoundException {
        this.vboxViewChat.getChildren().clear();

        if (community.getChat().size() != 0) {

            ArrayList<Message> chatC = community.getChat().getMessages();
            // System.out.println(chatC.size());

            int lastSenderId = -1;

            for (int i = 0; i < chatC.size(); ++i) {
                int index = i;

                VBox vBox = new VBox(1);
                vBox.setSpacing(3);

                ContextMenu contecContextMenu = new ContextMenu();
                MenuItem menu = new MenuItem("Delete");
                MenuItem menu1 = new MenuItem("Edit");
                contecContextMenu.getItems().addAll(menu1, menu);

                menu1.setOnAction(event -> {
                    try {
                        new EditMessage(0, community.getChat(), chatC.get(index), null, this).getStage().show();
                        this.pane.effectProperty().set(new MotionBlur(3.0, 15.0));
                        pane.setDisable(true);

                    } catch (Exception ie) {
                        ie.printStackTrace();
                    }
                });

                menu.setOnAction(event -> {
                    community.getChat().remove(chatC.get(index).getId());
                    try {
                        this.genareteViewChat();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                // userNow mensagens
                if (chatC.get(i).getSender() == id) {

                    vBox.setOnMouseClicked(event -> {
                        if (event.getButton().name().compareTo("SECONDARY") == 0) {
                            contecContextMenu.show(vBox, event.getScreenX(), event.getScreenY());
                        }
                    });

                    vBox.setPadding(new Insets(10, 10, 10, this.vboxViewChat.getPrefWidth() /
                            2));

                    Text text = new Text(chatC.get(i).getTxtMessage());
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

                    VBox messageBox = new VBox();

                    // verificando proxima mensagem
                    if (i + 1 < chatC.size()) {
                        int nextSenderId = chatC.get(i + 1).getSender();

                        if (nextSenderId == id) {

                            messageBox.setSpacing(3);
                            messageBox.getChildren().addAll(textFlow);
                        } else {

                            Text dateTimeText = new Text(chatC.get(i).getFormattedDateTime());
                            dateTimeText.setStyle("-fx-font-size: 11.5; -fx-fill: gray;");
                            TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                            dateTimeFlow.setPadding(new Insets(0, 0, 0, 380));

                            messageBox.setSpacing(3);
                            messageBox.getChildren().addAll(textFlow, dateTimeFlow);
                        }
                    } else {
                        Text dateTimeText = new Text(chatC.get(i).getFormattedDateTime());
                        dateTimeText.setStyle("-fx-font-size: 11.5; -fx-fill: gray;");
                        TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                        dateTimeFlow.setPadding(new Insets(0, 0, 0, 380));

                        messageBox.setSpacing(3);
                        messageBox.getChildren().addAll(textFlow, dateTimeFlow);
                    }

                    vBox.getChildren().addAll(messageBox);

                    lastSenderId = chatC.get(index).getSender();

                    // outro participante da comunidade
                } else {

                    User userMember = List_User.getPoint(2).user[chatC.get(index).getSender()];
                    int currentSenderId = chatC.get(index).getSender();

                    // primeira mensagem
                    if (currentSenderId != lastSenderId) {

                        // imagem perfil
                        ImageView imgIconProfile = (userMember.getPhotoProfile() == null)
                                ? new ImageView(
                                        new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                                : new ImageView(new Image(new FileInputStream(userMember.getPhotoProfile())));

                        imgIconProfile.setFitHeight(45);
                        imgIconProfile.setFitWidth(45);
                        Circle circle = new Circle(22.5, 22.5, 22.5);
                        imgIconProfile.setClip(circle);

                        VBox auxToImage = new VBox(imgIconProfile);
                        auxToImage.setPadding(new Insets(10, 0, 0, 0));
                        auxToImage.setAlignment(Pos.TOP_CENTER);

                        // nome do usuario
                        Label lblName = new Label(userMember.getName());
                        lblName.setStyle("-fx-font-size: 15; -fx-fill: #888888;");
                        lblName.setPadding(new Insets(0, 0, 0, 18));

                        HBox hBoxName = new HBox(lblName);
                        hBoxName.setSpacing(10);

                        // mensagem
                        vBox.setPadding(new Insets(10, this.vboxViewChat.getPrefWidth() / 2, 12, 12));
                        Text text = new Text(chatC.get(i).getTxtMessage());
                        text.setStyle("-fx-font-size: 17;");
                        text.setFill(Paint.valueOf("rgb(255,255,255)"));
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setStyle(
                                "-fx-background-radius: 20px;" +
                                        "-fx-background-color: #323739;" +
                                        "-fx-border-color: transparent;" +
                                        "-fx-border-radius: 20px;");
                        textFlow.setPadding(new Insets(5, 20, 5, 20));
                        textFlow.setMaxWidth(400);
                        textFlow.setPrefWidth(400);

                        VBox messageBox = new VBox();

                        // verificando proxima mensagem
                        if (i + 1 < chatC.size()) {
                            int nextSenderId = chatC.get(i + 1).getSender();
                            if (nextSenderId == currentSenderId) {

                                messageBox.setSpacing(3);
                                messageBox.getChildren().addAll(hBoxName, textFlow);
                            } else {

                                Text dateTimeText = new Text(chatC.get(i).getFormattedDateTime());
                                dateTimeText.setStyle("-fx-font-size: 11.5; -fx-fill: gray;");
                                TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                                dateTimeFlow.setPadding(new Insets(0, 0, 0, 15));

                                messageBox.setSpacing(3);
                                messageBox.getChildren().addAll(hBoxName, textFlow, dateTimeFlow);
                            }
                        } else {
                            Text dateTimeText = new Text(chatC.get(i).getFormattedDateTime());
                            dateTimeText.setStyle("-fx-font-size: 11.5; -fx-fill: gray;");
                            TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                            dateTimeFlow.setPadding(new Insets(0, 0, 0, 15));

                            messageBox.setSpacing(3);
                            messageBox.getChildren().addAll(hBoxName, textFlow, dateTimeFlow);
                        }

                        HBox hBox = new HBox();
                        hBox.setSpacing(10);
                        hBox.getChildren().addAll(auxToImage, messageBox);

                        vBox.getChildren().addAll(hBox);
                        vBox.setPadding(new Insets(10, this.vboxViewChat.getPrefWidth() / 2, 10, 10));

                        // proxima mensagem do mesmo usuario
                    } else {

                        HBox auxToImage = new HBox();
                        auxToImage.setSpacing(5);

                        Region spacer = new Region();
                        spacer.setPrefWidth(45); // Largura igual ao tamanho da imagem de perfil
                        spacer.setMinWidth(45);
                        spacer.setMaxWidth(45);
                        auxToImage.getChildren().add(spacer);

                        vBox.setPadding(new Insets(10, this.vboxViewChat.getPrefWidth() / 2, 12, 12));
                        Text text = new Text(chatC.get(i).getTxtMessage());
                        text.setStyle("-fx-font-size: 17;");
                        text.setFill(Paint.valueOf("rgb(255,255,255)"));
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setStyle(
                                "-fx-background-radius: 20px;" +
                                        "-fx-background-color: #323739;" +
                                        "-fx-border-color: transparent;" +
                                        "-fx-border-radius: 20px;");
                        textFlow.setPadding(new Insets(5, 20, 5, 20));

                        textFlow.setMaxWidth(400); // Ajuste o valor conforme necessário
                        textFlow.setPrefWidth(400); // Define a largura desejada

                        VBox messageBox = new VBox();

                        // verificando proxima mensagem
                        if (i + 1 < chatC.size()) {
                            int nextSenderId = chatC.get(i + 1).getSender();

                            if (nextSenderId == currentSenderId) {

                                messageBox.setSpacing(3);
                                messageBox.getChildren().addAll(textFlow);
                            } else {

                                Text dateTimeText = new Text(chatC.get(i).getFormattedDateTime());
                                dateTimeText.setStyle("-fx-font-size: 11.5; -fx-fill: gray;");
                                TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                                dateTimeFlow.setPadding(new Insets(0, 0, 0, 15));

                                messageBox.setSpacing(3);
                                messageBox.getChildren().addAll(textFlow, dateTimeFlow);
                            }
                        } else {
                            Text dateTimeText = new Text(chatC.get(i).getFormattedDateTime());
                                dateTimeText.setStyle("-fx-font-size: 11.5; -fx-fill: gray;");
                                TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                                dateTimeFlow.setPadding(new Insets(0, 0, 0, 15));

                                messageBox.setSpacing(3);
                                messageBox.getChildren().addAll(textFlow, dateTimeFlow);
                        }

                        // HBox para alinhar imagem e caixa de mensagem
                        HBox hBox = new HBox();
                        hBox.setSpacing(10);
                        hBox.getChildren().addAll(auxToImage, messageBox);

                        // Adicionando tudo ao vBox principal
                        vBox.getChildren().addAll(hBox);
                        vBox.setPadding(new Insets(10, this.vboxViewChat.getPrefWidth() / 2, 10, 10));
                    }

                    lastSenderId = chatC.get(index).getSender();

                }

                this.vboxViewChat.getChildren().add(vBox);

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

    private void sendMessage(String newMeString) throws Exception {

        chat = community.getChat();
        Message message = new Message();
        message.setSender((short) id);
        message.setReceptor((short) 99);
        message.setTxtMessage(this.mensageToChat.getText());
        chat.add(message);

        this.mensageToChat.clear();
        initialize();
        this.pane.requestFocus();
    }

    // voltar ´para comunidades
    @FXML
    private void backToHome(MouseEvent event) throws Exception {
        new CommunityScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToFriends(MouseEvent event) {

    }

    // participantes
    @FXML
    private void goToParticipants(MouseEvent event) throws Exception {

        new CommunityParticipants(id, community, pane, stage).getStage().show();
        pane.effectProperty().set(new MotionBlur(3.0, 15.0));
        pane.setDisable(true);

    }

    @FXML
    private void goToPublic(MouseEvent event) throws Exception {

        new PublicPostCommunity(id, community, null, this).getStage().show();
        pane.effectProperty().set(new MotionBlur(3.0, 15.0));
        pane.setDisable(true);

    }

    @FXML
    private void goToPublics(MouseEvent event) throws Exception {

        new PublicsCommunityScreen(id, community).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToSettings(MouseEvent event) throws Exception {
        new SettingsScreen(id).getStage().show();
        stage.close();
    }

    @FXML
    private void sendMessage(MouseEvent event) throws Exception {
        sendMessage(this.mensageToChat.getText());
    }
}