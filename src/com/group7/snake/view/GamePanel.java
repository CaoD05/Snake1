package com.group7.snake.view;

import com.group7.snake.model.*;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final int TILE_SIZE = 25;
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

        if (gameState.isGameOver()) {
            drawGameOver(g);
            return;
        }

        //drawLine(g);
        //drawFood(g);
        drawSnake(g);
        //drawScore(g);
    }

    private void drawLine(Graphics g) {
        for(int i = 0; i < gameState.getBoard().getHeight()/TILE_SIZE; i++) {
            //(x1, y1, x2, y2)
            g.drawLine(i*TILE_SIZE, 0, i*TILE_SIZE, gameState.getBoard().getHeight());
            g.drawLine(0, i*TILE_SIZE, gameState.getBoard().getWidth(), i*TILE_SIZE);
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
                g.setColor(Color.GREEN);
                first = false;
            }
            else {
                g.setColor(new Color(45, 180, 0));
            }
            g.fill3DRect(
                    segment.x * TILE_SIZE,
                    segment.y * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, true);
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
