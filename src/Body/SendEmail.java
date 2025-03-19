package Body;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import Structs.List_User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.Random;

import java.io.StringWriter;
import java.io.PrintWriter;

public class SendEmail{

	public SendEmail(){

	}
			
	public String generatePassword() {
        Random random = new Random();
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }

        return sb.toString();
    }

    public String saveNewPasswordEmail(String email, int id) {
        String senha = new SendEmail().generatePassword();
        
        List_User.getPoint(2).user[id].setPassword(senha);
        
        return senha;
    }

    public void sendPasswordEmail(String userEmail, int id) {

        String meuEmail = ""; //adicionar email
        String minhaSenha = ""; //adicionar senha do email

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
        email.setSSLOnConnect(true);

        String senha = new SendEmail().saveNewPasswordEmail(userEmail, id);

        try {
            email.setFrom(meuEmail);
            email.setSubject("Star Stream - Nova senha");
            email.setMsg("Conforme foi solicitado, segue sua nova senha para acessar o aplicativo. Senha: " + senha);
            email.addTo(userEmail); // para quem enviar
            email.send();
        } catch (EmailException ei) {
        }
    }
}
