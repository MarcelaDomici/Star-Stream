package Body;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private short id=0,receptor=0,sender=0;  
    private String txtMessage=null, photoToMessage=null;
    private LocalDateTime dateTimeSent;// mudei aqui
    private Post post=null;

    
    public Message() {
        this.dateTimeSent = LocalDateTime.now();  // Armazenar a data e hora atual
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public short getId() {
        return id;
    }
    public void setId(short id) {
        this.id = id;
    }
    public short getReceptor() {
        return receptor;
    }
    public void setReceptor(short receptor) {
        this.receptor = receptor;
    }
    public short getSender() {
        return sender;
    }
    public void setSender(short sender) {
        this.sender = sender;
    }
    public String getTxtMessage() {
        return txtMessage;
    }
    public void setTxtMessage(String txtMessage) {
        this.txtMessage = txtMessage;
    }
    public String getPhotoToMessage() {
        return photoToMessage;
    }
    public void setPhotoToMessage(String photoToMessage) {
        this.photoToMessage = photoToMessage;
    }


    public LocalDateTime getDateTimeSent() {
        return dateTimeSent;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dateTimeSent.format(formatter);
    }

}
