package Body;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.jgoodies.common.collect.LinkedListModel;
import java.util.List;

public class User {

    private String name, password, city, civil, email , photoProfile=null;
    private String profileVisibility = "Perfil público";//publico
    private int age;
    private ArrayList<Post> postUser = new ArrayList<>();
    private ArrayList<Integer> friends = new ArrayList<>();
    private ArrayList<Integer> solicit = new ArrayList<>();
    private Map<Integer, Boolean> blockUser=new HashMap<>();
    private LinkedListModel<Integer> list_solicit = new LinkedListModel<>();
    private ArrayDeque<Integer> dequeChat = new ArrayDeque<>();
    private Map<Integer,Chat> chat = new HashMap<>();
    private ArrayList<Depoimento> depoimento = new ArrayList<>();
    private ArrayList<Integer> communitysUser = new ArrayList<>();
    
    public List<Integer> getList_Solicit(){return this.list_solicit;}
    public ArrayDeque<Integer> getDequeChat(){return this.dequeChat;}
    public Map<Integer,Chat> getChats(){return this.chat;}

    public Map<Integer,Boolean> getUsersBlocks(){return this.blockUser;}
    public void setBlock(int id, boolean p){this.blockUser.put(id, p);}
    public boolean Check_User_Blocked(int i){return this.blockUser.get((Integer)i);}
    public void removeBlock(int userId) { this.blockUser.remove(userId);}

    public void AddCommunity(int IdCommunity){
        communitysUser.add(IdCommunity);
    }

    public void removeCommunity(int idCommunity){
        for(int i =0;i<communitysUser.size();++i){
            if(communitysUser.get(i)==idCommunity){
                communitysUser.remove(i);
                return;
            }
        }
    }

    public int checkCommunity(int id){
        for(int i =0;i<communitysUser.size();++i){
            if(communitysUser.get(i)==id) return 0;
        }
        return 1;
    }

    public void AddFriends(int idFriend){
        this.friends.add(idFriend);
    }
    
    public void removeFriends(int idFriend){
        for(int i =0;i<this.friends.size();++i){
            if(this.friends.get(i)==idFriend){
                this.friends.remove(i);
                return;
            }
        }
    }
    
    public ArrayList<Integer> getFriends(){
        return this.friends;
    }
    public byte checkFriend(int id){
        for(int i =0;i<this.friends.size();++i){
            if(this.friends.get(i)==id) return 0;
        }
        return 1;
    }

    public void sendSolict(int idFriend){
        solicit.add(idFriend);
    }
    public void removeSolicit(int idFriend){
        for(int i =0;i<this.solicit.size();++i){
            if(idFriend==this.solicit.get(i)){
                this.solicit.remove(i);
                return;
            }
        }
    }
    public ArrayList<Integer> getSolicit(){
        return this.solicit;
    }

    public String getPhotoProfile() {
        return photoProfile;
    }
    public void setPhotoProfile(String photoProfile) {
        this.photoProfile = photoProfile;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCivil() {
        return civil;
    }
    public void setCivil(String civil) {
        this.civil = civil;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getProfileVisibility() {
        return profileVisibility;
    }
    public void setProfileVisibility(String profileVisibility) {
        this.profileVisibility = profileVisibility;
    }

    
    public ArrayList<Integer> getCommunitysUser() {return communitysUser;}
    public ArrayList<Post> getPosts(){return this.postUser;}
    public ArrayList<Depoimento> getDepoimentos(){return depoimento;}

}
