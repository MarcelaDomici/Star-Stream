package Structs;

import Body.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class List_User {
    public User user[];
    private int lastUser = -1;
    private static List_User point;
    private List_User(int newValues){
        user = new User[newValues];
    }

    public static List_User getPoint(int newValues){
        if(point==null&& newValues!=0){
            point = new List_User(newValues);
        }
        return point;
    }

    public User[] getListaDeUsuarios(){return user;}

    public void add(User user){
        if((this.lastUser+1)==this.user.length){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Lista está cheia!");
            alert.showAndWait();
        }else{
            ++this.lastUser;
            this.user[lastUser]=user;
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Aviso!");
            alert.setHeaderText(null);
            alert.setContentText("Usuário Cadastrado com Sucesso!");
            alert.showAndWait();
        }
    }
    public void show(){
        for(int i =0;i<=this.lastUser;++i){
            System.out.println(user[i].getEmail()+"\n"+user[i].getPassword());
        }
    }
    public boolean checkExistUser(String email){
        for(int i =0;i<=this.lastUser;++i){
            if(this.user[i].getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    public boolean checkUser(String email, String password){
        for(int i =0;i<=this.lastUser;++i){
            if(this.user[i].getEmail().equals(email) && this.user[i].getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public int getId(String email){
        for(int i =0;i<=this.lastUser;++i){
            if(this.user[i].getEmail().equals(email)){
                return i;
            }
        }
        return -1;
    }

    public int getId(String email, String password){
        for(int i =0;i<=this.lastUser;++i){
            if(this.user[i].getEmail().equals(email) && this.user[i].getPassword().equals(password)){
                return i;
            }
        }
        return -1;
    }
    public byte checkExistUser(int id){
        if(this.lastUser>=id)return 0;
        return -1;
    }
    public int getSizeUsers(){
        return(this.lastUser);
    }
}
