package Screens;

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

import java.io.FileInputStream;


public class BlocksScreen {
    private static int id = 0;
    private Stage stage = new Stage();
    private static FriendsScreen _FriendsScreen;
    private Pane pane;
    private User user;

    @FXML
    private ImageView ExitBlockScreen;

    @FXML
    private ScrollPane ScrollBlock;

    @FXML
    private VBox VboxBlock;

    public BlocksScreen(int newId, FriendsScreen newScreen)throws Exception{
        id = newId;
        _FriendsScreen = newScreen;
        user = List_User.getPoint(2).user[id];

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenBlockUsers.fxml"));
            loader.setController(this);
            pane = loader.load();
            this.stage.setScene(new Scene(pane));
            this.stage.setTitle("Depoimento");
            this.stage.setResizable(false);
            this.stage.initStyle(StageStyle.UNDECORATED);

            pane.requestFocus();
            pane.setOnMouseClicked(event ->{
                pane.requestFocus();
            });
    }

    public Stage getStage(){return this.stage;}
    public Pane getPane(){return this.pane;}

    @FXML
    private void initialize(){

        try{

            VboxBlock.getChildren().clear();
            ExitBlockScreen.setCursor(Cursor.HAND);
            
            for (Integer userId : user.getUsersBlocks().keySet()) {
                Boolean isBlocked = user.getUsersBlocks().get(userId);
                System.out.println("User ID: " + userId + ", Blocked: " + isBlocked);

                if(isBlocked == false){

                    int idBlock = userId;

                    User userBlock = List_User.getPoint(2).user[idBlock];

                    HBox hBoxSugest = new HBox(2);
                    hBoxSugest.setSpacing(3);//conjunto da sugestao de amizade

                    //imagem de perfil amigo
                    ImageView imgIConFriend = (userBlock.getPhotoProfile()==null)?new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                    :
                    new ImageView(new Image(new FileInputStream(userBlock.getPhotoProfile()))) ;
                    imgIConFriend.setFitHeight(50);
                    imgIConFriend.setFitWidth(50);

                    Circle circle = new Circle(25, 25, 25);
                    imgIConFriend.setClip(circle);

                    //nome do amigo
                    Label userName = new Label(userBlock.getName());
                    userName.setStyle("-fx-font-family: Poppins; -fx-font-size: 16px");
                    userName.setPadding(new Insets(15,0,0,10));

                    imgIConFriend.setCursor(Cursor.HAND);
                    userName.setCursor(Cursor.HAND);

                    String dep = "nao pode";
                    String perfilVisi = List_User.getPoint(2).user[idBlock].getProfileVisibility();
                    imgIConFriend.setOnMouseClicked(event->{
                        try {
                            new FriendProfile(id, idBlock, perfilVisi, dep).getStage().show();
                            ExitBlockScreen(event);
                            this.stage.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    userName.setOnMouseClicked(event->{
                        try {
                            new FriendProfile(id, idBlock, perfilVisi, dep).getStage().show();
                            ExitBlockScreen(event);
                            this.stage.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    //icone de enviar solicitação de amizade
                    ImageView imgUnblockUser = new ImageView(new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/unblock_user.png")));
                    imgUnblockUser.setFitHeight(30);
                    imgUnblockUser.setFitWidth(30);
                    imgUnblockUser.setPreserveRatio(true);

                    imgUnblockUser.setCursor(Cursor.HAND);

                    Tooltip tooltipUnlock = new Tooltip("Desbloquear Usuário");

                    Tooltip.install(imgUnblockUser, tooltipUnlock);

                    imgUnblockUser.setOnMouseClicked(event->{

                        List_User.getPoint(0).user[id].removeBlock(idBlock);//usuario
                        List_User.getPoint(0).user[idBlock].removeBlock(id);//amigo

                        initialize();
                        _FriendsScreen.initialize();
                    });

                    VBox auxToImage = new VBox();
                    auxToImage.getChildren().add(imgUnblockUser);

                    auxToImage.setPadding(new Insets(20, 0, 0, 0));
                    auxToImage.setAlignment(Pos.CENTER);

                    HBox hBoxLeft = new HBox(10); 
                    hBoxLeft.getChildren().addAll(imgIConFriend, userName);

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS); 

                    hBoxSugest = new HBox();
                    hBoxSugest.getChildren().addAll(hBoxLeft, spacer, auxToImage);
                    hBoxSugest.setPadding(new Insets(10, 15, 10, 10)); 
                    
                    this.VboxBlock.getChildren().addAll(hBoxSugest);
                }

            }
            

        }catch(Exception ie){
            ie.printStackTrace();
        }
    }
    

    @FXML
    public void ExitBlockScreen(MouseEvent event) {

        _FriendsScreen.getPane().effectProperty().set(null);
        _FriendsScreen.getPane().toFront();
        _FriendsScreen.getPane().setDisable(false);
        this.stage.close();
    }
}
