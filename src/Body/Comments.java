package Body;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comments {
    private short id, idUser;
    private String comment;
    private LocalDateTime dateTimeSent;

    public Comments(){
        this.dateTimeSent = LocalDateTime.now();  // Armazenar a data e hora atual
    }

    public short getId() {
        return id;
    }
    public void setId(short id) {
        this.id = id;
    }
    public short getIdUser() {
        return idUser;
    }
    public void setIdUser(short idUser) {
        this.idUser = idUser;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }  
    
    
    public LocalDateTime getDateTimeSent() {
        return dateTimeSent;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dateTimeSent.format(formatter);
    }
}
