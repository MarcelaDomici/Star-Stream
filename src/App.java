import Body.Depoimento;
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

        //user marcela
        User user = new User();
        user.setAge(19);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro");
        user.setEmail("mar");
        user.setPassword("1234");
        user.setName("Marcela");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\versionVS\\Social-Media-Data-Structures\\Photos\\lilo.PNG");
        List_User.getPoint(10).add(user); //inicialização da lista de usuarios

        Depoimento dep1 = new Depoimento();
        dep1.setDepoimento("Amigo onde esta?");
        dep1.setIdAmg(1);
        Depoimento dep2 = new Depoimento();
        dep2.setDepoimento("Amigo estou aqui...");
        dep2.setIdAmg(0);

        user = new User();
        user.setAge(19);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro");
        user.setEmail("manu");
        user.setPassword("1234");
        user.setName("Manuela");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\versionVS\\Social-Media-Data-Structures\\Photos\\Snoopy.jpg");
        List_User.getPoint(5).add(user);

       // user.getList_Solicit().add(0);
            List_User.getPoint(0).user[0].AddFriends(1);
            List_User.getPoint(0).user[1].AddFriends(0);

        List_User.getPoint(2).user[0].getDepoimentos().add(dep1);
        List_User.getPoint(2).user[1].getDepoimentos().add(dep2);

        user = new User();
        user.setAge(18);
        user.setCity("Cruzeiro-SP");
        user.setCivil("Solteiro");
        user.setEmail("madu");
        user.setPassword("1234");
        user.setName("Maria Eduarda");
        user.setPhotoProfile("\\Users\\Dell\\Desktop\\versionVS\\Social-Media-Data-Structures\\Photos\\download.jpg");
        List_User.getPoint(5).add(user);

            

        /*
        User user = new User();
        user.setAge(21);
        user.setCity("Taubaté-SP");
        user.setCivil("Solteiro");
        user.setEmail("samuel@gmail.com");
        user.setPassword("1234");
        user.setName("Samuel");
        user.setPhotoProfile("/home/samuel/Desktop/Project_Java/Project_social_media/src/Screens/samuelphoto.jpeg");
        List_User.getPoint(5).add(user);
        
        {
            user = new User();
            user.setAge(21);
            user.setCity("Cruzeiro-SP");
            user.setCivil("Solteiro");
            user.setEmail("jose@gmail.com");
            user.setPassword("1234");
            user.setName("Samuel Lucas");
           // user.setPhotoProfile("/home/samuel/Desktop/Project_Java/Project_social_media/src/Screens/airplane.jpg");
            List_User.getPoint(5).add(user);
            List_User.getPoint(0).user[0].getSolicit().add(1);
            user.getList_Solicit().add(0);
            //List_User.getPoint(0).user[0].AddFriends(1);
            //List_User.getPoint(0).user[1].AddFriends(0);
        }
            {
                Chat chat = new Chat();
                Message message =  new Message();
                message.setId((short)0);
                message.setSender((short)0);
                message.setReceptor((short)1);
                message.setTxtMessage("User 0 A amizade consegue ser tão complexa. Deixa uns desanimados, outros bem felizes. É a alimentação dos fracos É o reino dos fortes.");
                chat.add(message);
                chat.add(message);
                
                List_User.getPoint(0).user[0].getChats().put(1, chat);
                List_User.getPoint(0).user[1].getChats().put(0, chat);
                List_User.getPoint(0).user[0].getDequeChat().add(1);
                List_User.getPoint(0).user[1].getDequeChat().add(0);


                message =  new Message();
                message.setId((short)0);
                message.setSender((short)1);
                message.setReceptor((short)0);
                message.setTxtMessage("user 1 A amizade consegue ser tão complexa. Deixa uns desanimados, outros bem felizes. É a alimentação dos fracos É o reino dos fortes.");
                chat.add(message);
                chat.add(message);
                
                System.out.println(List_User.getPoint(0).user[0].getChats().get(1).getLastMessage().getTxtMessage());

                //List_User.getPoint(0).user[1].getChats().put(0, chat);
                //List_User.getPoint(0).user[0].getChats().put(1, chat);                
            }

        {
            user = new User();
            user.setAge(21);
            user.setCity("Lorena-SP");
            user.setCivil("Solteiro");
            user.setEmail("benedita@gmail.com");
            user.setPassword("1234");
            user.setName("Benedita");
            //user.setPhotoProfile("./airplane.jpg");
            List_User.getPoint(5).add(user);
            List_User.getPoint(0).user[0].AddFriends(2);
            List_User.getPoint(0).user[2].AddFriends(0);
        }

        {
            user = new User();
            user.setAge(21);
            user.setCity("Pinda-SP");
            user.setCivil("Solteiro");
            user.setEmail("kleber@gmail.com");
            user.setPassword("1234");
            user.setName("Kleber");
            user.setPhotoProfile("/home/samuel/Desktop/Project_Java/Project_social_media/src/Screens/samuelphoto.jpeg");
            List_User.getPoint(5).add(user);
            List_User.getPoint(0).user[0].getSolicit().add(3);
            List_User.getPoint(0).user[0].getSolicit().add(1);
        }

        {
            user = new User();
            user.setAge(21);
            user.setCity("Taubaté-SP");
            user.setCivil("Solteiro");
            user.setEmail("zeca@gmail.com");
            user.setPassword("1234");
            user.setName("Zeca");
            //user.setPhotoProfile("./airplane.jpg");
            List_User.getPoint(5).add(user);
        }

        Post post = new Post();
        post.setId((short)0);
        post.setIduser((short)0);
        
        post.setImagem("/home/samuel/Desktop/Project_Java/Project_social_media/src/Screens/airplane.jpg");
        post.setTitle("Titulo para Testes");
        post.setPostTxt("Texto para testes do Posts Texto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do Posts");

        {
        Comments comment = new Comments();
        comment.setId((short)0);
        comment.setIdUser((short)0);
        comment.setComment("Comentários");
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);

        }
        
        List_User.getPoint(0).user[0].getPosts().add(post);
        ManagerPosts.geralPosts.add(post);
        //new Examples();
        */
        new LoginScreen().getStage().show();
    }
}
