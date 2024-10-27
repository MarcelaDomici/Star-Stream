package Screens;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class JogoDaVelhaPC extends Application {
    private String jogadorAtual = "X"; 
    private String computador = "O";    
    private Button[][] botoes = new Button[3][3];
    private Text statusText = new Text("Sua vez! (X)");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color: black;"); 

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new Button();
                botoes[i][j].setMinSize(100, 100);
                botoes[i][j].setFont(Font.font("Arial", FontWeight.BOLD, 42));
                botoes[i][j].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #AAAAAA; -fx-border-width: 2px;");
                final int row = i;
                final int col = j;
                botoes[i][j].setOnAction(e -> jogar(row, col));
                grid.add(botoes[i][j], j, i);
            }
        }

        statusText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        statusText.setFill(Color.DARKBLUE);

        VBox root = new VBox(15, grid, statusText);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 350, 400);
        primaryStage.setTitle("Jogador vs Computador");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void jogar(int row, int col) {
        if (botoes[row][col].getText().isEmpty()) {
            botoes[row][col].setText(jogadorAtual);
            botoes[row][col].setTextFill(Color.rgb(123,56,255)); 
            if (verificaVencedor(jogadorAtual)) {
                statusText.setText("Você venceu!");
                desativarBotoes();
            } else if (tabuleiroCheio()) {
                statusText.setText("Empate!");
            } else {
                jogadorAtual = computador;
                statusText.setText("Computador está jogando...");
                computadorJoga();
            }
        }
    }

    private void computadorJoga() {
        Random rand = new Random();
        int row, col;

        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (!botoes[row][col].getText().isEmpty());

        botoes[row][col].setText(computador);
        botoes[row][col].setTextFill(Color.rgb(9, 215, 242)); 

        if (verificaVencedor(computador)) {
            statusText.setText("Computador venceu!");
            desativarBotoes();
        } else if (tabuleiroCheio()) {
            statusText.setText("Empate!");
        } else {
            jogadorAtual = "X";
            statusText.setText("Sua vez! (X)");
        }
    }

    private boolean verificaVencedor(String jogador) {
        for (int i = 0; i < 3; i++) {
      
            if ((botoes[i][0].getText().equals(jogador) &&
                 botoes[i][1].getText().equals(jogador) &&
                 botoes[i][2].getText().equals(jogador)) ||
                (botoes[0][i].getText().equals(jogador) &&
                 botoes[1][i].getText().equals(jogador) &&
                 botoes[2][i].getText().equals(jogador))) {
                return true;
            }
        }
       
        return (botoes[0][0].getText().equals(jogador) &&
                botoes[1][1].getText().equals(jogador) &&
                botoes[2][2].getText().equals(jogador)) ||
               (botoes[0][2].getText().equals(jogador) &&
                botoes[1][1].getText().equals(jogador) &&
                botoes[2][0].getText().equals(jogador));
    }

    private boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botoes[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void desativarBotoes() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setDisable(true);
            }
        }
    }
}