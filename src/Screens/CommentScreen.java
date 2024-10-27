package Screens;

import java.io.FileInputStream;

import Body.Comments;
import Body.Post;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CommentScreen {
    private Stage stage = new Stage();
    private static int id=0;
    private static int idComment=0;
    private Post post;
    private Pane otherPane;
    private Hyperlink hyperPost;
    private Button otherButton;
    private Label qntLike;

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
    private ImageView ImageImgPost;

        public CommentScreen(int idUser, Post postI, Pane otherScreen, Hyperlink hyper, Button principal,Label lblQntLike)throws Exception{
            id=idUser;
            post=postI;
            this.otherPane=otherScreen;
            hyperPost=hyper;
            this.otherButton=principal;
            this.hyperPost=hyper;
            this.qntLike=lblQntLike;

            idComment=postI.getComments().size()-1;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenComments.fxml"));
            loader.setController(this);
            Pane pane = loader.load();
            Scene scene = new Scene(pane);
            this.stage.setScene(scene);
            this.stage.setTitle("Comentários"); 
            this.stage.setResizable(false);  
            this.stage.initStyle(StageStyle.UNDECORATED);
            this.stage.setOnCloseRequest(event->{
                this.backScreen(null);
            });

            pane.requestFocus();

            scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode()==KeyCode.ENTER) {
                    if(this.txtNewComment.getText().length()!=0){
                        Comments comment = new Comments();
                        ++idComment;
                        comment.setId((short)idComment);
                        comment.setIdUser((short)idUser);
                        comment.setComment(this.txtNewComment.getText());
                        this.post.getComments().add(comment);
                        this.txtNewComment.setText("");
                        this.hyperPost.setText("Comentários "+String.valueOf(post.getComments().size()));
                        this.vBoxComments.getChildren().clear();
                        organize();
                    }
                }
            });
        }

    public Stage getStage(){return this.stage;}

    @FXML
    private void initialize(){
        this.lblUsername.setText(List_User.getPoint(id).user[this.post.getIduser()].getName());
        this.txtTitlePost.setText(this.post.getTitle());
        this.txtPublicArgs.setText(this.post.getPostTxt());

        if(this.post.checkLike(id)==0){
            this.btnLike.setStyle(
            "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: white; -fx-background-color:red;"
            );
        }else{
            this.btnLike.setStyle(
            "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;"
            );
        }
        try{
            if(this.post.getImagem()!=null){
                this.ImageImgPost.setImage(new Image(new FileInputStream(this.post.getImagem())));
            }
            String photo = List_User.getPoint(0).user[this.post.getIduser()].getPhotoProfile(); 
            if(photo!=null){
                this.ImageUser.setImage(new Image(new FileInputStream(photo)));
                this.ImageUser.setFitHeight(50);
                this.ImageUser.setFitWidth(50);

                // Criar um círculo para o clipping
                Circle circle = new Circle(25, 25, 25); // O raio do círculo é metade do tamanho da imagem (53 / 2)

                // Aplicar o círculo como um clip na ImageView
                ImageUser.setClip(circle);
                
            }
            organize();
        }catch(Exception ie){
            ie.printStackTrace();
        }
    }
    
    private void organize(){
        User user[] = List_User.getPoint(id).user;
        for(int i =post.getComments().size()-1;i>=0;--i){
            VBox vBox = new VBox(2);
            Label nameUserLabel = new Label(user[post.getComments().get(i).getIdUser()].getName());
            nameUserLabel.setStyle(
                "-fx-font-weight: bold;"
            );
            Label lblComment = new Label(this.post.getComments().get(i).getComment()); 
            vBox.getChildren().addAll(new Separator(),nameUserLabel,lblComment,new Separator());
            this.vBoxComments.getChildren().add(vBox);
        }
    }

    @FXML
    private void doLike(MouseEvent event) {
        if(post.checkLike(id)!=0){
            btnLike.setStyle(
                "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-background-color: red; -fx-text-fill:white;"
            );
            post.addLike(id);
            this.otherButton.setStyle(btnLike.getStyle());
          }else{
            btnLike.setStyle(
                "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;"
            );
            this.otherButton.setStyle(btnLike.getStyle());
            post.removeLike(id);
          }
        qntLike.setText(String.valueOf(this.post.getLikes()));
    }

    @FXML
    private void backScreen(MouseEvent event) {
        this.otherPane.effectProperty().set(null);
        otherPane.setDisable(false);
        this.stage.close();
    }


}
