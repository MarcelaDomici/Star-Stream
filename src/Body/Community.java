package Body;

import java.util.ArrayList;


public class Community {
    private String name, txtCommunity, CommunityVisibility = "Perfil público", photoCommunity = null;
    private int idOwner, id;
    private ArrayList<Post> postCommunity = new ArrayList<>();
    private ArrayList<Integer> CommunityUsers = new ArrayList<>();
    private Chat chat = new Chat();

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTxtCommunity() {
        return txtCommunity;
    }
    public void setTxtCommunity(String txtCommunity) {
        this.txtCommunity = txtCommunity;
    }
    public String getCommunityVisibility() {
        return CommunityVisibility;
    }
    public void setCommunityVisibility(String communityVisibility) {
        CommunityVisibility = communityVisibility;
    }
    public String getPhotoCommunity() {
        return photoCommunity;
    }
    public void setPhotoCommunity(String photoCommunity) {
        this.photoCommunity = photoCommunity;
    }
    public int getIdOwner() {
        return idOwner;
    }
    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }


    public Chat getChat() { return chat;}
    
    public ArrayList<Post> getPostCommunity() {
        return postCommunity;
    }

    public ArrayList<Integer> getCommunityUsers() {
        return CommunityUsers;
    }

    
}
