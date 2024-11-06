package Screens;

import java.io.FileInputStream;
import java.util.Optional;

import Body.Depoimento;
import Body.Post;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class ProfileScreen implements Uptable{
    private Stage stage = new Stage();
    private static int id=0;
    private Pane pane;
    private User userNow;

    @FXML
    private HBox EditProfile;

    @FXML
    private HBox Hbox_to_ScreenFriends;

    @FXML
    private HBox Hbox_to_ScreenPublic;

    @FXML
    private Label cityUser;

    @FXML
    private ImageView exitToHome;

    @FXML
    private ImageView imageSetProfile;

    @FXML
    private Label nameUser;

    @FXML
    private Label relationShipUser;

    @FXML
    private VBox vBoxPrincipal;

    @FXML
    private ImageView viewSettings;

     @FXML
    private ScrollPane scroolPane = new ScrollPane();

    @FXML
    private ScrollPane scroolPaneDep;

    @FXML
    private VBox vBoxDepoiment;

    
        public ProfileScreen(int newId)throws Exception{
            id=newId;
            userNow = List_User.getPoint(2).user[id];

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenProfile.fxml"));
            loader.setController(this);
            pane = loader.load();
            this.stage.setScene(new Scene(pane));
            this.stage.setTitle("Perfil");
            Image image = new Image(getClass().getResource("/Screens/ScreensFXML/Imagens/logoStar1.png").toExternalForm());
            stage.getIcons().add(image);
            this.stage.setResizable(false);

            pane.requestFocus();

        }

    public Stage getStage(){return this.stage;}
    public Pane getPane(){return this.pane;}
    
    @FXML    
    public void initialize(){

        vBoxPrincipal.getChildren().clear();
        vBoxDepoiment.getChildren().clear();

        scroolPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        scroolPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra horizontal do scroll
        scroolPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra vertical

        scroolPaneDep.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        scroolPaneDep.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra horizontal do scroll
        scroolPaneDep.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra vertical


        //infos do perfil
        User user = List_User.getPoint(id).user[id];
        this.nameUser.setText(user.getName());
        this.cityUser.setText(user.getCity());
        this.relationShipUser.setText(user.getCivil());


        //imagem de perfil
        try{
            if(user.getPhotoProfile()!=null){
                this.imageSetProfile.setImage(new Image(new FileInputStream(user.getPhotoProfile())));
                this.imageSetProfile.setFitHeight(159);
                this.imageSetProfile.setFitWidth(159);

                Circle circle = new Circle(75.5, 75.5, 75.5);
                imageSetProfile.setClip(circle);
            }


            //vbox postagens do Profile
            this.vBoxPrincipal.setSpacing((double)10);
            for(int i = user.getPosts().size()-1;i>=0;--i){
                Post post = user.getPosts().get(i);   
                VBox vBox = new VBox(5);
                HBox hBox = new HBox(2);
                hBox.setSpacing(3);// estoy aqui 


                //imagem perfil da postagem
                ImageView imageView = (user.getPhotoProfile()!=null)? new ImageView(new Image(new FileInputStream(user.getPhotoProfile()))) 
                :
                new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.PNG")))
                ;

                imageView.setFitHeight(50);
                imageView.setFitWidth(50);

                Circle circle = new Circle(25, 25, 25);
                imageView.setClip(circle);
                
                imageView.setPreserveRatio(true);

                // label com nameUser
                Label nameUser = new Label(List_User.getPoint(0).user[id].getName());
                nameUser.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");
                nameUser.setPadding(new Insets(13,0,0,11));//user
                hBox.getChildren().addAll(imageView,nameUser);

                //titulo da publicação
                vBox.setStyle("-fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: white;");
                Label titlePost = new Label(post.getTitle());
                titlePost.setStyle("-fx-font-weight: bold; -fx-font-size: 14px");
                titlePost.setPadding(new Insets(10,0,5,100));
                titlePost.setWrapText(true);

                //texto da postagem em si
                Text text = new Text(post.getPostTxt());
                TextFlow txtPost = new TextFlow();
                txtPost.setStyle("-fx-font-size: 14px");
                txtPost.setMaxWidth(400);
                txtPost.setCache(false);
                txtPost.setCacheShape(false);
                txtPost.setCenterShape(false);
                txtPost.setFocusTraversable(false);
                txtPost.getChildren().add(text);
                

                //imagem postagem
                HBox box = new HBox(1);
                ImageView image;
                if(post.getImagem()!=null){
                    image = new ImageView(new Image(new FileInputStream(post.getImagem())));
                    image.setFitHeight(400);
                    image.setFitWidth(400);
                    image.setPreserveRatio(true);
                    box.getChildren().add(image);
                    box.setPadding(new Insets(10,0,0,100));
                }
                
                int likesQnt = post.getLikes();
                Label lblQntLikes = new Label(String.valueOf(likesQnt));
                lblQntLikes.setPadding(new Insets(3,0,0,2));

                Button like = new Button("   ");
                like.setCursor(Cursor.HAND);
                like.setStyle(
                "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\";"
                );
                if(post.checkLike(id)==0){
                    like.setStyle(
                        "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-background-color: red; -fx-text-fill:white;"
                    );
                    post.addLike(id);
                }else{
                    like.setStyle(
                        "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;"
                    );
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
                 this.vBoxPrincipal.getChildren().add(vBox);
            }
 

        }catch(Exception ie){
            ie.printStackTrace();
        }

        try{
            
            //depoimentos do profile
            this.vBoxDepoiment.setSpacing((double)10);
            for(int i = user.getDepoimentos().size()-1;i>=0;--i){

                Depoimento depoimento = user.getDepoimentos().get(i);  

                int indexF = depoimento.getIdAmg();

                if(userNow.checkFriend(indexF) == 0){
                
                    VBox vBox = new VBox(5);//conjunto do depoimento
                    HBox hBoxProfile = new HBox(2); //icon perfil, nome, lixeira
                    hBoxProfile.setSpacing(3);

                    int idepAMG = depoimento.getIdAmg();

                    // Imagem do perfil da postagem
                    ImageView imageIcon = (List_User.getPoint(id).user[idepAMG].getPhotoProfile() != null) 
                        ? new ImageView(new Image(new FileInputStream(List_User.getPoint(id).user[idepAMG].getPhotoProfile()))) 
                        : new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.PNG")));

                    imageIcon.setFitHeight(50);
                    imageIcon.setFitWidth(50);
                    Circle circle = new Circle(25, 25, 25);
                    imageIcon.setClip(circle);
                    imageIcon.setPreserveRatio(true);

                    // label nome
                    Label nameUser = new Label(List_User.getPoint(0).user[idepAMG].getName());
                    nameUser.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");
                    nameUser.setPadding(new Insets(13, 0, 0, 11)); 

                    imageIcon.setCursor(Cursor.HAND);
                    nameUser.setCursor(Cursor.HAND);

                    String dep = "pode";
                    String perfilVisi = "amigos <3";
                    imageIcon.setOnMouseClicked(event->{
                        try {
                            new FriendProfile(id, indexF, perfilVisi, dep).getStage().show();
                            this.stage.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    nameUser.setOnMouseClicked(event->{
                        try {
                            new FriendProfile(id, indexF, perfilVisi, dep).getStage().show();
                            this.stage.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    // Lixeira
                    ImageView imageTrash = new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/deletar_dep.PNG")));

                    imageTrash.setFitHeight(30);
                    imageTrash.setFitWidth(30);
                    imageTrash.setPreserveRatio(true);

                    imageTrash.setCursor(Cursor.HAND);

                    imageTrash.setOnMouseClicked(event->{

                        Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Aviso!");
                            alert.setHeaderText(null);
                            alert.setContentText("Tem certeza que deseja excluir este depoimento?");

                            ButtonType buttonTypeOne = new ButtonType("Sim");
                            ButtonType buttonTypeTwo = new ButtonType("Cancelar");

                            alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeTwo);

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == buttonTypeOne){
                            user.getDepoimentos().remove(depoimento);
                            initialize(); 
                            }
                        
                    });

                    HBox hBoxLeft = new HBox(10); 
                    hBoxLeft.getChildren().addAll(imageIcon, nameUser);

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS); 

                    hBoxProfile = new HBox();
                    hBoxProfile.getChildren().addAll(hBoxLeft, spacer, imageTrash);
                    hBoxProfile.setPadding(new Insets(10, 15, 10, 10)); 

                    
                    //texto depoimento
                    Text text = new Text(depoimento.getDepoimento());
                    TextFlow txtDep = new TextFlow();
                    txtDep.setStyle("-fx-font-size: 14px");
                    txtDep.setCache(false);
                    txtDep.setCacheShape(false);
                    txtDep.setCenterShape(false);
                    txtDep.setFocusTraversable(false);
                    txtDep.getChildren().add(text);

                    HBox box2 = new HBox(3);
                    box2.getChildren().addAll(txtDep);
                    box2.setPadding(new Insets(0,0,0,90));
                    txtDep.setPrefWidth(800);

                    //data e hora depoimento
                    Text dateTimeText = new Text(depoimento.getFormattedDateTime());
                    dateTimeText.setStyle("-fx-font-size: 12; -fx-fill: gray;");
                    TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                    dateTimeFlow.setPadding(new Insets(0, 0, 0, 500));

                    //adicionando tudo
                    vBox.setStyle("-fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: white;");
                    vBox.getChildren().addAll(hBoxProfile,box2,dateTimeFlow); 
                    this.vBoxDepoiment.getChildren().add(vBox);

                }
            }


        }catch(Exception ie){
            ie.printStackTrace();
        }
    }

    private void generateLikes(Post post, Button like, Label showLike, int likesQnt) {
        if(post.checkLike(id)!=0){
            like.setStyle(
                "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-background-color: red; -fx-text-fill:white;"
            );
            post.addLike(id);
          }else{
            like.setStyle(
                "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;"
            );
            post.removeLike(id);
          }
          likesQnt=post.getLikes();
          showLike.setText(String.valueOf(likesQnt));
    }


    @FXML
    private void backToHome(MouseEvent event) throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToFriends(MouseEvent event)throws Exception {
        new FriendsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToPublic(MouseEvent event)throws Exception {
        try {
            // Crie a tela de publicação e passe a HomeScreen como parâmetro
            PublicationScreen publicationScreen = new PublicationScreen(id, this.getStage(), this);
            publicationScreen.getStage().show();
    
            // Efeito de MotionBlur
            pane.effectProperty().set(new MotionBlur(3.0, 15.0));
    
            publicationScreen.getStage().setOnHidden(event1 -> {
                pane.effectProperty().set(null);  // Remove o efeito após fechar
            });
    
        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }

    @FXML
    private void goToCommunity(MouseEvent event) throws Exception{

        new CommunityScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToSettings(MouseEvent event)throws Exception {
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToChat(MouseEvent event) throws Exception{
        new ChatScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToEditProfile(MouseEvent event) {

        try{
            new EditProfile(id, this, cityUser, relationShipUser, nameUser, imageSetProfile).getStage().show();
            pane.effectProperty().set(new MotionBlur(3.0,15.0));
            pane.setDisable(true);
            }catch(Exception ie){
            ie.printStackTrace();   
            }
    }

    @Override
    public void update() {
        initialize();
    }
}
