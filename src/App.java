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

public class App extends Application{

    public static void main(String... args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {

        //-------------------- USERS ---------------------


        // user marcela 0
        User user = new User();
        user.setAge(19);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro(a)");
        user.setEmail("marcela@gmail.com");
        user.setPassword("1234");
        user.setName("Marcela");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\marcela.PNG");
        List_User.getPoint(20).add(user); // inicialização da lista de usuarios

        Post post = new Post();
        post.setId((short) 0);
        post.setIduser((short) 0);
        post.setImagem("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\download_(1).jpg");
        post.setTitle("Titulo para Testes");
        post.setPostTxt(
                "Texto para testes do Posts Texto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes");
        List_User.getPoint(0).user[0].getPosts().add(post);
        ManagerPosts.geralPosts.add(post);

        //user Manuela 1
        user = new User();
        user.setAge(19);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro(a)");
        user.setEmail("manuela@gmail.com");
        user.setPassword("1234");
        user.setName("Manuela");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\manu.jpg");
        List_User.getPoint(5).add(user);

        //user Madu 2
        user = new User();
        user.setAge(18);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro(a)");
        user.setEmail("madu@gmail.com");
        user.setPassword("1234");
        user.setName("Maria Eduarda");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\madu.jpg");
        List_User.getPoint(5).add(user);

        //user Daniel 3
        user = new User();
        user.setAge(42);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Casado(a)");
        user.setEmail("daniel@gmail.com");
        user.setPassword("1234");
        user.setName("Daniel");
        user.setProfileVisibility("Perfil privado");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\daniel.PNG");
        List_User.getPoint(5).add(user);

        //user carlos 4
        user = new User();
        user.setAge(25);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Namorando");
        user.setEmail("carlos@gmail.com");
        user.setPassword("1234");
        user.setName("Carlos");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\carlos.jpg");
        List_User.getPoint(5).add(user);

        //user carolina 5 fora
        user = new User();
        user.setAge(21);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Namorando");
        user.setEmail("carolina@gmail.com");
        user.setPassword("1234");
        user.setName("Carolina");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\carolina.jpg");
        List_User.getPoint(5).add(user);

        //user Samuel 6
        user = new User();
        user.setAge(22);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro(a)");
        user.setEmail("samuel@gmail.com");
        user.setPassword("1234");
        user.setName("Samuel");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\samuel.jpeg");
        List_User.getPoint(5).add(user);

        //user Felipe 7 fora
        user = new User();
        user.setAge(27);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro(a)");
        user.setEmail("felipe@gmail.com");
        user.setPassword("1234");
        user.setName("Felipe");
        //user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\download.jpg");
        List_User.getPoint(5).add(user);

        //user Natalia 8 fora
        user = new User();
        user.setAge(18);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Namorando");
        user.setEmail("nathalia@gmail.com");
        user.setPassword("1234");
        user.setName("Nathalia");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\nathalia.jpg");
        List_User.getPoint(5).add(user);
 
        //user Thais 9
        user = new User();
        user.setAge(29);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Casado(a)");
        user.setEmail("thais@gmail.com");
        user.setPassword("1234");
        user.setName("Thais");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\thais.jpeg");
        List_User.getPoint(5).add(user);

        //user julia 10 
        user = new User();
        user.setAge(21);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Namorando");
        user.setEmail("julia@gmail.com");
        user.setPassword("1234");
        user.setName("Julia");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Profile\\julia.jpeg");
        List_User.getPoint(5).add(user);


        //-------------------------- AMIZADES --------------------------------


        //amizades marcela
        List_User.getPoint(0).user[0].AddFriends(1); //manuela
        List_User.getPoint(0).user[1].AddFriends(0); //marcela

        List_User.getPoint(0).user[0].AddFriends(2); //madu
        List_User.getPoint(0).user[2].AddFriends(0); //marcela

        List_User.getPoint(0).user[0].AddFriends(9); //thais
        List_User.getPoint(0).user[9].AddFriends(0); //marcela

        List_User.getPoint(0).user[0].AddFriends(10); //julia
        List_User.getPoint(0).user[10].AddFriends(0); //marcela

        //amizades daniel
        List_User.getPoint(0).user[3].AddFriends(6); //samuel
        List_User.getPoint(0).user[6].AddFriends(3); //daniel

        List_User.getPoint(0).user[3].AddFriends(7); //felipe
        List_User.getPoint(0).user[7].AddFriends(3); //daniel


        //-------------------------- DEPOIMENTOS --------------------------------


        //depoimentos marcela
        Depoimento dep1 = new Depoimento();
        dep1.setDepoimento("Amigo onde esta?");
        dep1.setIdAmg(1);
        Depoimento dep2 = new Depoimento();
        dep2.setDepoimento("Amigo estou aqui...");
        dep2.setIdAmg(0);

        List_User.getPoint(2).user[0].getDepoimentos().add(dep1); //marcela
        List_User.getPoint(2).user[1].getDepoimentos().add(dep2); //manuela

        dep1 = new Depoimento();
        dep1.setDepoimento("Amigo onde esta?");
        dep1.setIdAmg(10);
        dep2 = new Depoimento();
        dep2.setDepoimento("Amigo estou aqui...");
        dep2.setIdAmg(0);
        
        List_User.getPoint(2).user[0].getDepoimentos().add(dep1); //marcela
        List_User.getPoint(2).user[10].getDepoimentos().add(dep2); //julia

        dep1 = new Depoimento();
        dep1.setDepoimento("Amigo onde esta?");
        dep1.setIdAmg(9);
        dep2 = new Depoimento();
        dep2.setDepoimento("Amigo estou aqui...");
        dep2.setIdAmg(0);
        
        List_User.getPoint(2).user[0].getDepoimentos().add(dep1); //marcela
        List_User.getPoint(2).user[9].getDepoimentos().add(dep2); //thais


        //-------------------------- COMUNIDADES --------------------------------


        //comunidade toy story, dona: Manuela
        Community community = new Community();
        community.setName("Toy Story");
        community.setTxtCommunity("Ao infinito e além...");
        community.setPhotoCommunity("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Community\\#TOYYYYY#STORYYY.jpg");
        community.setIdOwner(1);
        community.setCommunityVisibility("Público");
        community.setId(0);

        ManagerCommunitys.allCommunitys.add(community);
        List_User.getPoint(2).user[1].AddCommunity(0);


        //comunidade Monsters inc. dona: Marcela
        community = new Community();
        community.setName("Monsters inc.");
        community.setTxtCommunity("No susto e no grito fazemos bonito!");
        community.setPhotoCommunity("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Community\\monters.jpg");
        community.setIdOwner(0);
        community.setCommunityVisibility("Público");
        community.setId(1);

        ManagerCommunitys.allCommunitys.add(community);
        List_User.getPoint(2).user[0].AddCommunity(1);


        //comunidade smelly cat, dona: Marcela
        community = new Community();
        community.setName("Smelly cat");
        community.setTxtCommunity("Smelly Cat, Smelly Cat,\r\n" + //
                        "What are they feeding you?\r\n" + //
                        "Smelly Cat, Smelly Cat\r\n" + //
                        "It's not your fault");
        community.setPhotoCommunity("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Community\\Pheobe Buffay.jpg");
        community.setIdOwner(0);
        community.setCommunityVisibility("Público");
        community.setId(2);

        ManagerCommunitys.allCommunitys.add(community);
        List_User.getPoint(2).user[0].AddCommunity(2);

        post = new Post();
        post.setId((short)1);
        post.setIduser((short) 0);
        post.setImagem("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\download (2).jpg");
        post.setTitle("Teste teste teste teste");
        post.setPostTxt("Teste teste teste teste teste teste teste teste ");
        community.getPostCommunity().add(post);

        //comunidade I love Justin Bieber, dona: Amanda

        //comunidade Alunos e ex-alunos Fatec Cruzeiro, dono: Daniel
        community = new Community();
        community.setName("Alunos e ex-alunos da Fatec Cruzeiro");
        community.setTxtCommunity("Olá a todos fatecanos. Sejam todos bem \nvindos!");
        community.setPhotoCommunity("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Community\\fatec.PNG");
        community.setIdOwner(3);
        community.setCommunityVisibility("Público");
        community.setId(3);

        ManagerCommunitys.allCommunitys.add(community);
        List_User.getPoint(2).user[3].AddCommunity(3);

        //comunidade Star wars, dono: Carlos, em uma galaxia muito muito distante...
        community = new Community();
        community.setName("Star wars");
        community.setTxtCommunity("em uma galaxia muito muito distante...");
        community.setPhotoCommunity("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Community\\starWars.jpg");
        community.setIdOwner(6);
        community.setCommunityVisibility("Público");
        community.setId(4);

        ManagerCommunitys.allCommunitys.add(community);
        List_User.getPoint(2).user[6].AddCommunity(4);


        //comunidade Profeta diário(HP) , dona: Carolina, "É Leviosa, não Leviosá!" -Hermione Granger
        //plataforma 9 3/4
        community = new Community();
        community.setName("Plataforma 9 3/4");
        community.setTxtCommunity("\"É Leviosa, não Leviosá!\" \n-Hermione Granger");
        community.setPhotoCommunity("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Community\\hp.jpg");
        community.setIdOwner(5);
        community.setCommunityVisibility("Público");
        community.setId(5);

        ManagerCommunitys.allCommunitys.add(community);
        List_User.getPoint(2).user[5].AddCommunity(5);

        //comunidade Marvel News, dona: Maria Eduarda, Avengers assemble "Com grandes poderes vêm grandes responsabilidades." – Tio Ben (Spider-Man)
        community = new Community();
        community.setName("Marvel News");
        community.setTxtCommunity("\"Com grandes poderes vêm grandes \nresponsabilidades.\" \n– Tio Ben (Spider-Man)");
        community.setPhotoCommunity("\\Users\\Dell\\Desktop\\version Star Stream\\Social-Media-Data-Structures\\Photos\\Community\\marvel.jpg");
        community.setIdOwner(5);
        community.setCommunityVisibility("Público");
        community.setId(5);

        ManagerCommunitys.allCommunitys.add(community);
        List_User.getPoint(2).user[5].AddCommunity(5);

        //comunidade stranger things, dona: Marcela, friends don't lie

        new LoginScreen().getStage().show();
    }
}
