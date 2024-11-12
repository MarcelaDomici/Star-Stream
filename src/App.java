import Body.Chat;
import Body.Community;
import Body.Depoimento;
import Body.ManagerCommunitys;
import Body.ManagerPosts;
import Body.Message;
import Body.Post;
import Body.User;
import Screens.LoginScreen;
import Structs.List_User;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String... args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {

        // -------------------- USERS ---------------------

        // user marcela 0
        User user = new User();
        user.setAge(19);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro(a)");
        user.setEmail("marcela@gmail.com");
        user.setPassword("1234");
        user.setName("Marcela");
        user.setPhotoProfile(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\marcela.PNG");
        List_User.getPoint(20).add(user); // inicialização da lista de usuarios

       
        // user Manuela 1
        user = new User();
        user.setAge(19);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro(a)");
        user.setEmail("manuela@gmail.com");
        user.setPassword("1234");
        user.setName("Manuela");
        user.setPhotoProfile(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\manu.jpg");
        List_User.getPoint(5).add(user);

        // user Madu 2
        user = new User();
        user.setAge(18);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro(a)");
        user.setEmail("madu@gmail.com");
        user.setPassword("1234");
        user.setName("Maria Eduarda");
        user.setPhotoProfile(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\madu.jpg");
        List_User.getPoint(5).add(user);

        
        // user Daniel 3
        user = new User();
        user.setAge(42);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Casado(a)");
        user.setEmail("daniel@gmail.com");
        user.setPassword("1234");
        user.setName("Daniel");
        user.setProfileVisibility("Perfil privado");
        user.setPhotoProfile(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\daniel.PNG");
        List_User.getPoint(5).add(user);

        // user Samuel 4
        user = new User();
        user.setAge(22);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro(a)");
        user.setEmail("samuel@gmail.com");
        user.setPassword("1234");
        user.setName("Samuel");
        user.setPhotoProfile(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\samuel.jpeg");
        List_User.getPoint(5).add(user);

        
        // user Thais 5
        user = new User();
        user.setAge(29);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Casado(a)");
        user.setEmail("thais@gmail.com");
        user.setPassword("1234");
        user.setName("Thais");
        user.setPhotoProfile(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\thais.jpeg");
        List_User.getPoint(5).add(user);

        // user julia 6
        user = new User();
        user.setAge(21);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Namorando");
        user.setEmail("julia@gmail.com");
        user.setPassword("1234");
        user.setName("Julia");
        user.setPhotoProfile(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\julia.jpeg");
        List_User.getPoint(5).add(user);

        /* 
        // user carlos 4
        user = new User();
        user.setAge(25);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Namorando");
        user.setEmail("carlos@gmail.com");
        user.setPassword("1234");
        user.setName("Carlos");
        user.setPhotoProfile(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\carlos.jpg");
        List_User.getPoint(5).add(user);

        // user carolina 5 fora
        user = new User();
        user.setAge(21);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Namorando");
        user.setEmail("carolina@gmail.com");
        user.setPassword("1234");
        user.setName("Carolina");
        user.setPhotoProfile(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\carolina.jpg");
        List_User.getPoint(5).add(user);

        // user Felipe 7 fora
        user = new User();
        user.setAge(27);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro(a)");
        user.setEmail("felipe@gmail.com");
        user.setPassword("1234");
        user.setName("Felipe");
        // user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star
        // Stream\\Social-Media-Data-Structures\\Photos\\download.jpg");
        List_User.getPoint(5).add(user);

        // user Natalia 8 fora
        user = new User();
        user.setAge(18);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Namorando");
        user.setEmail("nathalia@gmail.com");
        user.setPassword("1234");
        user.setName("Nathalia");
        user.setPhotoProfile(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\nathalia.jpg");
        List_User.getPoint(5).add(user);*/

        
        //-------------------------- POSTS --------------------------------

        //Samuel
        Post post = new Post();
        post.setId((short) 0);
        post.setIduser((short) 4);
        post.setImagem("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Posts\\iot.jpg");
        post.setTitle("Internet das Coisas: Sua Casa Mais Inteligente");
        post.setPostTxt(
                "Com quais dispositivos você conectaria sua casa?");
        List_User.getPoint(0).user[4].getPosts().add(post);
        ManagerPosts.geralPosts.add(post);

        //marcela
        post = new Post();
        post.setId((short) 1);
        post.setIduser((short) 0);
        post.setImagem("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Posts\\starWars.jpg");
        post.setTitle("Qual é o Seu Jedi Favorito?");
        post.setPostTxt(
                "Se você pudesse escolher um Jedi para ser seu mestre, quem seria? Luke, Yoda, Ahsoka… ou outro? Comente o nome do seu favorito e diga o porquê!");
        List_User.getPoint(0).user[0].getPosts().add(post);
        ManagerPosts.geralPosts.add(post);

        //madu
        post = new Post();
        post.setId((short) 2);
        post.setIduser((short) 2);
        post.setImagem("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Posts\\hogwards.jpg");
        post.setTitle("Qual Casa de Hogwarts Mais te Representa?");
        post.setPostTxt(
                "Qual é a sua casa? Comente e diga por que você é Corvinal, Grifinória, Lufa-Lufa ou Sonserina!");
        List_User.getPoint(0).user[2].getPosts().add(post);
        ManagerPosts.geralPosts.add(post);


        // -------------------------- AMIZADES --------------------------------

        // amizades marcela
        List_User.getPoint(0).user[0].AddFriends(1); // manuela
        List_User.getPoint(0).user[1].AddFriends(0); // marcela

        List_User.getPoint(0).user[0].AddFriends(2); // madu
        List_User.getPoint(0).user[2].AddFriends(0); // marcela

        List_User.getPoint(0).user[0].AddFriends(6); // julia
        List_User.getPoint(0).user[6].AddFriends(0); // marcela

        // amizades daniel
        List_User.getPoint(0).user[3].AddFriends(4); // samuel
        List_User.getPoint(0).user[4].AddFriends(3); // daniel

        // -------------------------- DEPOIMENTOS --------------------------------

        // depoimentos marcela
        Depoimento dep1 = new Depoimento();
        dep1.setDepoimento("Se a amizade fosse uma competição, eu teria ganho, mas você \nainda é minha favorita.");
        dep1.setIdAmg(1);
        Depoimento dep2 = new Depoimento();
        dep2.setDepoimento("Amigo estou aqui...");
        dep2.setIdAmg(0);

        List_User.getPoint(2).user[0].getDepoimentos().add(dep1); // marcela
        List_User.getPoint(2).user[1].getDepoimentos().add(dep2); // manuela

        dep1 = new Depoimento();
        dep1.setDepoimento("Bora no shibas?");
        dep1.setIdAmg(6);
        dep2 = new Depoimento();
        dep2.setDepoimento("Amigo estou aqui...");
        dep2.setIdAmg(0);

        List_User.getPoint(2).user[0].getDepoimentos().add(dep1); // marcela
        List_User.getPoint(2).user[6].getDepoimentos().add(dep2); // julia


        // -------------------------- COMUNIDADES --------------------------------

        // comunidade Star wars, dono: Madu, em uma galaxia muito muito distante...
        Community community = new Community();
        community.setName("Star wars");
        community.setTxtCommunity("em uma galaxia muito muito distante...");
        community.setPhotoCommunity(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Community\\starWars.jpg");
        community.setIdOwner(2);
        community.setCommunityVisibility("Público");
        community.setId(0);

        ManagerCommunitys.allCommunitys.add(community);
        List_User.getPoint(2).user[2].AddCommunity(0);
        

        // comunidade Alunos e ex-alunos Fatec Cruzeiro, dono: Samuel
        community = new Community();
        community.setName("Alunos e ex-alunos da Fatec Cruzeiro");
        community.setTxtCommunity("Olá a todos fatecanos. Sejam todos bem \nvindos!");
        community.setPhotoCommunity(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Community\\fatec.PNG");
        community.setIdOwner(4);//dono
        community.setCommunityVisibility("Público");
        community.setId(1);//id comunidade

        ManagerCommunitys.allCommunitys.add(community);
        List_User.getPoint(2).user[4].AddCommunity(1);

        post = new Post();
        post.setId((short) 0);
        post.setIduser((short) 4);
        post.setImagem(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\posts\\cubo.jpg");
        post.setTitle("Se Você Tivesse Que Repetir Uma Matéria, Qual Seria?");
        post.setPostTxt("Existe alguma matéria que você curtiu tanto que faria de novo? Comente abaixo!");
        community.getPostCommunity().add(post);


        // comunidade plataforma 9 3/4 , dona: Marcela, "É Leviosa, não Leviosá!" Hermione Granger
        community = new Community();
        community.setName("Plataforma 9 3/4");
        community.setTxtCommunity("\"É Leviosa, não Leviosá!\" \n-Hermione Granger");
        community.setPhotoCommunity(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Community\\hp.jpg");
        community.setIdOwner(0);
        community.setCommunityVisibility("Público");
        community.setId(2);

        ManagerCommunitys.allCommunitys.add(community);
        List_User.getPoint(2).user[0].AddCommunity(2);


        // comunidade Marvel News, dona: Maria Eduarda, Avengers assemble "Com grandes
        // poderes vêm grandes responsabilidades." – Tio Ben (Spider-Man)
        community = new Community();
        community.setName("Marvel News");
        community.setTxtCommunity("\"Com grandes poderes vêm grandes \nresponsabilidades.\" \n– Tio Ben (Spider-Man)");
        community.setPhotoCommunity(
                "\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Community\\marvel.jpg");
        community.setIdOwner(2);
        community.setCommunityVisibility("Público");
        community.setId(3);

        ManagerCommunitys.allCommunitys.add(community);
        List_User.getPoint(2).user[2].AddCommunity(3);

        new LoginScreen().getStage().show();
    }
}
