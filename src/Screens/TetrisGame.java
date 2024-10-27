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

import java.util.Random;

public class TetrisGame extends Application {

    private static final int TILE_SIZE = 25;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;
    private static final int[][][] SHAPES = {
            {{1, 1, 1, 1}}, // I
            {{1, 1}, {1, 1}}, // O
            {{0, 1, 0}, {1, 1, 1}}, // T
            {{1, 0, 0}, {1, 1, 1}}, // L
            {{0, 0, 1}, {1, 1, 1}}, // J
            {{0, 1, 1}, {1, 1, 0}}, // S
            {{1, 1, 0}, {0, 1, 1}}  // Z
    };

    private int[][] board = new int[HEIGHT][WIDTH];
    private Piece currentPiece;
    private long lastFallTime;
    private boolean gameOver = false;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bloquinhos");
        stage.show();

        
        scene.setOnKeyPressed(e -> {
            if (!gameOver) {
                if (e.getCode() == KeyCode.LEFT) {
                    currentPiece.moveLeft(board);
                } else if (e.getCode() == KeyCode.RIGHT) {
                    currentPiece.moveRight(board);
                } else if (e.getCode() == KeyCode.DOWN) {
                    currentPiece.moveDown(board);
                } else if (e.getCode() == KeyCode.UP) {
                    currentPiece.rotate(board);
                }
            }
        });

        
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastFallTime > 500_000_000) {
                    if (!currentPiece.moveDown(board)) {
                        addPieceToBoard();
                        removeFullLines();
                        if (!spawnNewPiece()) {
                            gameOver = true;
                        }
                    }
                    lastFallTime = now;
                }
                draw(gc);
            }
        }.start();

        spawnNewPiece();
        scene.getRoot().requestFocus(); 
    }

    private boolean spawnNewPiece() {
        currentPiece = new Piece(SHAPES[new Random().nextInt(SHAPES.length)], WIDTH / 2 - 1, 0);
        return currentPiece.canSpawn(board);
    }

    private void addPieceToBoard() {
        for (int y = 0; y < currentPiece.shape.length; y++) {
            for (int x = 0; x < currentPiece.shape[y].length; x++) {
                if (currentPiece.shape[y][x] == 1) {
                    board[currentPiece.y + y][currentPiece.x + x] = 1;
                }
            }
        }
    }

    private void removeFullLines() {
        for (int y = HEIGHT - 1; y >= 0; y--) {
            boolean fullLine = true;
            for (int x = 0; x < WIDTH; x++) {
                if (board[y][x] == 0) {
                    fullLine = false;
                    break;
                }
            }
            if (fullLine) {
                for (int newY = y; newY > 0; newY--) {
                    board[newY] = board[newY - 1];
                }
                board[0] = new int[WIDTH];
                y++;
            }
        }
    }

    private void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (board[y][x] == 1) {
                    gc.setFill(Color.GRAY);
                    gc.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
                }
            }
        }

        gc.setFill(Color.rgb(123,56,255));
        for (int y = 0; y < currentPiece.shape.length; y++) {
            for (int x = 0; x < currentPiece.shape[y].length; x++) {
                if (currentPiece.shape[y][x] == 1) {
                    gc.fillRect((currentPiece.x + x) * TILE_SIZE, (currentPiece.y + y) * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
                }
            }
        }

        if (gameOver) {
            gc.setFill(Color.RED);
            gc.fillText("Game Over", WIDTH * TILE_SIZE / 2.0, HEIGHT * TILE_SIZE / 2.0);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

class Piece {

    public int[][] shape;
    public int x, y;

    public Piece(int[][] shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }

    public boolean canSpawn(int[][] board) {
        return canMoveTo(board, x, y);
    }

    public boolean moveDown(int[][] board) {
        if (canMoveTo(board, x, y + 1)) {
            y++;
            return true;
        }
        return false;
    }

    public void moveLeft(int[][] board) {
        if (canMoveTo(board, x - 1, y)) {
            x--;
        }
    }

    public void moveRight(int[][] board) {
        if (canMoveTo(board, x + 1, y)) {
            x++;
        }
    }

    public void rotate(int[][] board) {
        int[][] rotated = new int[shape[0].length][shape.length];
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[y].length; x++) {
                rotated[x][shape.length - y - 1] = shape[y][x];
            }
        }
        if (canMoveTo(board, x, y, rotated)) {
            shape = rotated;
        }
    }

    private boolean canMoveTo(int[][] board, int x, int y) {
        return canMoveTo(board, x, y, shape);
    }

    private boolean canMoveTo(int[][] board, int x, int y, int[][] shape) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    int boardX = x + col;
                    int boardY = y + row;
                    if (boardX < 0 || boardX >= board[0].length || boardY >= board.length || board[boardY][boardX] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}


