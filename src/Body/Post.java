package Body;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Post {
    private short id, iduser;
    private String title, postTxt, imagem;
    private LocalDateTime dateTimeSent;
    private ArrayList<Comments> commentsList = new ArrayList<>();
    private ArrayList<Integer> likes = new ArrayList<>();

    public Post(){
        this.dateTimeSent = LocalDateTime.now();  // Armazenar a data e hora atual
    }

    public void addLike(int idUser){
        if(this.checkLike(idUser)!=0)this.likes.add(idUser);
    }
    public void removeLike(int idUser){
        short index =0;
        for(var i : this.likes){
            if(i.intValue()==idUser){
                likes.remove(index);
                return;
            }
            ++index;
        }
    }

    public byte checkLike(int idUser){
        for (var element : this.likes) {
            if(element.intValue()==idUser){
                return 0;
            }
        }
        return 1;
    }
    public int getLikes(){return this.likes.size();}

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    public ArrayList<Comments> getComments(){return this.commentsList;}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPostTxt() {
        return postTxt;
    }
    public void setPostTxt(String postTxt) {
        this.postTxt = postTxt;
    }
    public short getId() {
        return id;
    }
    public void setId(short id) {
        this.id = id;
    }
    public short getIduser() {
        return iduser;
    }
    public void setIduser(short iduser) {
        this.iduser = iduser;
    }

    
    public LocalDateTime getDateTimeSent() {
        return dateTimeSent;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dateTimeSent.format(formatter);
    }
}
