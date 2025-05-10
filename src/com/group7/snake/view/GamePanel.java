package com.group7.snake.view;

import com.group7.snake.model.*;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final int TILE_SIZE = defaultValue.returnTile();
    private final GameState gameState;

    public GamePanel(GameState gameState) {
        this.gameState = gameState;
        setPreferredSize(new Dimension(
                gameState.getBoard().getWidth() * TILE_SIZE,
                gameState.getBoard().getHeight() * TILE_SIZE
        ));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameState.isFirst()) {
            System.out.println("First");
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Press any arrow key to start!", 150, 300);// Adjust position as needed
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("ESC to pause",200, 440);
        }
        else {
            if (gameState.isGameOver()) {
                drawGameOver(g);
                return;
            }
            if (gameState.isPause()) {
                drawPause(g);
            }

            drawLine(g);
            drawFood(g);
            drawSnake(g);
            drawScore(g);
        }
    }

    private void drawPause(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Pause", 150, 300);
    }

    private void drawLine(Graphics g) {
        int pixelWidth = gameState.getBoard().getWidth() * TILE_SIZE;
        int pixelHeight = gameState.getBoard().getHeight() * TILE_SIZE;

        for (int i = 0; i <= gameState.getBoard().getWidth(); i++) {
            int x = i * TILE_SIZE;
            //g.drawLine(x, 0, x, pixelHeight); // vertical lines
        }

        for (int i = 0; i <= gameState.getBoard().getHeight(); i++) {
            int y = i * TILE_SIZE;
            //g.drawLine(0, y, pixelWidth, y); // horizontal lines
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(gameState.getFood().getX() * TILE_SIZE,
                gameState.getFood().getY() * TILE_SIZE,
                TILE_SIZE, TILE_SIZE);
    }

    private void drawSnake(Graphics g) {
        Snake snake = gameState.getSnake();
        boolean first = true;

        for(Point segment : snake.getBody()){
            if(first){
                System.out.println("Drawing head at: " + segment.x + ", " + segment.y);
                g.setColor(Color.GREEN);
                first = false;
            }
            else {
                g.setColor(Color.GREEN);
            }
            g.fill3DRect(
                    segment.x * TILE_SIZE,
                    segment.y * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, true);
        }

        g.setColor(Color.WHITE);
        int eyeSize = 6;
        int headX = gameState.getSnake().getHead().x;
        int headY = gameState.getSnake().getHead().y;
        switch (gameState.getSnake().getDirection()) {
            case Direction.RIGHT:
                g.fillOval(headX*TILE_SIZE + 15, headY*TILE_SIZE + 4, eyeSize, eyeSize);
                g.fillOval(headX*TILE_SIZE + 15, headY*TILE_SIZE + 16, eyeSize, eyeSize);
                break;
            case Direction.LEFT:
                g.fillOval(headX*TILE_SIZE + 15, headY*TILE_SIZE + 4, eyeSize, eyeSize);
                g.fillOval(headX*TILE_SIZE + 1, headY*TILE_SIZE + 16, eyeSize, eyeSize);
                break;
            case Direction.UP:
                g.fillOval(headX*TILE_SIZE + 4, headY*TILE_SIZE + 10, eyeSize, eyeSize);
                g.fillOval(headX*TILE_SIZE + 16, headY*TILE_SIZE + 10, eyeSize, eyeSize);
                break;
            case Direction.DOWN:
                g.fillOval(headX*TILE_SIZE + 4, headY*TILE_SIZE + 10, eyeSize, eyeSize);
                g.fillOval(headX*TILE_SIZE + 16, headY*TILE_SIZE + 10, eyeSize, eyeSize);
                break;
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + gameState.getScore(), 10, 20);
    }

    private void drawGameOver(Graphics g) {
        String gameOverText = "Game Over! Score: " + gameState.getScore();
        String restartText = "Move to restart";

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 30));

        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(gameOverText)) / 2;
        int y = getHeight() / 2 - 30;

        g.drawString(gameOverText, x, y);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));

        metrics = g.getFontMetrics();
        x = (getWidth() - metrics.stringWidth(restartText)) / 2;
        y = getHeight() / 2 + 30;

        g.drawString(restartText, x, y);
    }

}
