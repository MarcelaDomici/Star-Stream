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

public class BrickBreakerGame extends Application {

    private static final int TILE_SIZE = 25;
    private static final int WIDTH = 12;
    private static final int HEIGHT = 24;
    private static final int PADDLE_WIDTH = 4;
    private static final int BALL_SIZE = 1;

    private int[][] bricks = new int[HEIGHT / 2][WIDTH];
    private double ballX = WIDTH / 2.0;
    private double ballY = HEIGHT - 4;
    private double ballDX = 0.15;
    private double ballDY = -0.15;
    private int paddleX = WIDTH / 2 - PADDLE_WIDTH / 2;
    private boolean gameOver = false;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Quebra-blocos");
        stage.show();

        for (int y = 0; y < bricks.length; y++) {
            for (int x = 0; x < bricks[y].length; x++) {
                bricks[y][x] = 1;
            }
        }

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT && paddleX > 0) {
                paddleX--;
            } else if (e.getCode() == KeyCode.RIGHT && paddleX + PADDLE_WIDTH < WIDTH) {
                paddleX++;
            }
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameOver) {
                    update();
                    draw(gc);
                }
            }
        }.start();
    }

    private void update() {
        ballX += ballDX;
        ballY += ballDY;

        if (ballX < 0 || ballX >= WIDTH)
            ballDX *= -1;
        if (ballY < 0)
            ballDY *= -1;

        if (ballY >= HEIGHT - 3 && ballX >= paddleX && ballX < paddleX + PADDLE_WIDTH) {
            ballDY *= -1;
            ballY = HEIGHT - 3;
        }

        int brickX = (int) ballX;
        int brickY = (int) ballY;
        if (brickY < bricks.length && brickX >= 0 && brickX < WIDTH && bricks[brickY][brickX] == 1) {
            bricks[brickY][brickX] = 0;
            ballDY *= -1;
        }

        if (ballY >= HEIGHT) {
            gameOver = true;
        }
    }

    private void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

        for (int y = 0; y < bricks.length; y++) {
            for (int x = 0; x < bricks[y].length; x++) {
                if (bricks[y][x] == 1) {
                    gc.setFill(Color.GRAY);
                    gc.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
                }
            }
        }

        gc.setFill(Color.WHITE);
        gc.fillOval(ballX * TILE_SIZE, ballY * TILE_SIZE, BALL_SIZE * TILE_SIZE, BALL_SIZE * TILE_SIZE);

        gc.setFill(Color.rgb(123, 56, 255));
        gc.fillRect(paddleX * TILE_SIZE, (HEIGHT - 2) * TILE_SIZE, PADDLE_WIDTH * TILE_SIZE, TILE_SIZE);

        if (gameOver) {
            gc.setFill(Color.RED);
            gc.fillText("Game Over", WIDTH * TILE_SIZE / 2.0 - 30, HEIGHT * TILE_SIZE / 2.0);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
