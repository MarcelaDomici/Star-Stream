package Screens;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends Application {

    private static final int TILE_SIZE = 25;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;

    private List<int[]> snake = new ArrayList<>();
    private int[] food = new int[2];
    private String direction = "RIGHT";
    private boolean gameOver = false;
    private long lastMoveTime;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Snake Game");
        stage.show();

        
        snake.add(new int[]{WIDTH / 2, HEIGHT / 2});
        snake.add(new int[]{WIDTH / 2 - 1, HEIGHT / 2});
        snake.add(new int[]{WIDTH / 2 - 2, HEIGHT / 2});

        
        spawnFood();

        
        scene.setOnKeyPressed(e -> {
            if (!gameOver) {
                if (e.getCode() == KeyCode.LEFT && !direction.equals("RIGHT")) {
                    direction = "LEFT";
                } else if (e.getCode() == KeyCode.RIGHT && !direction.equals("LEFT")) {
                    direction = "RIGHT";
                } else if (e.getCode() == KeyCode.UP && !direction.equals("DOWN")) {
                    direction = "UP";
                } else if (e.getCode() == KeyCode.DOWN && !direction.equals("UP")) {
                    direction = "DOWN";
                }
            }
        });

        
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastMoveTime > 200_000_000) {
                    if (!gameOver) {
                        moveSnake();
                        if (checkCollision()) {
                            gameOver = true;
                        }
                    }
                    draw(gc);
                    lastMoveTime = now;
                }
            }
        }.start();
    }

    private void moveSnake() {
        int[] head = snake.get(0);
        int[] newHead = new int[2];

        
        switch (direction) {
            case "LEFT" -> newHead = new int[]{head[0] - 1, head[1]};
            case "RIGHT" -> newHead = new int[]{head[0] + 1, head[1]};
            case "UP" -> newHead = new int[]{head[0], head[1] - 1};
            case "DOWN" -> newHead = new int[]{head[0], head[1] + 1};
        }

        
        snake.add(0, newHead);

        
        if (newHead[0] == food[0] && newHead[1] == food[1]) {
            spawnFood(); 
        } else {
            snake.remove(snake.size() - 1); 
        }
    }

    private void spawnFood() {
        Random rand = new Random();
        boolean validPosition = false;

        while (!validPosition) {
            food[0] = rand.nextInt(WIDTH);
            food[1] = rand.nextInt(HEIGHT);
            validPosition = true;

            
            for (int[] part : snake) {
                if (part[0] == food[0] && part[1] == food[1]) {
                    validPosition = false;
                    break;
                }
            }
        }
    }

    private boolean checkCollision() {
        int[] head = snake.get(0);

        
        if (head[0] < 0 || head[0] >= WIDTH || head[1] < 0 || head[1] >= HEIGHT) {
            return true;
        }

        
        for (int i = 1; i < snake.size(); i++) {
            if (head[0] == snake.get(i)[0] && head[1] == snake.get(i)[1]) {
                return true;
            }
        }

        return false;
    }

    private void draw(GraphicsContext gc) {
       
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

        
        gc.setFill(Color.rgb(123,56,255));
        for (int[] part : snake) {
            gc.fillRect(part[0] * TILE_SIZE, part[1] * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
        }

       
        gc.setFill(Color.RED);
        gc.fillRect(food[0] * TILE_SIZE, food[1] * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);

        
        if (gameOver) {
            gc.setFill(Color.WHITE);
            gc.fillText("Game Over",100, 250);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}


