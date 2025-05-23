package com.group7.snake.controller;

import com.group7.snake.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.group7.snake.view.GamePanel;


public class GameController {
    private final GameState gameState;
    private final GamePanel gamePanel;
    private final Timer gameTimer;
    private final Input input;
    private boolean isRunning;

    public GameController(GameState gameState,GamePanel gamePanel) {
        this.gameState = gameState;
        this.gamePanel = gamePanel;
        this.input = new Input(this);
        this.isRunning = false;

        // Game loop running at 10 FPS
        gameTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
            }
        });
    }

    public void startGame() {
        gameState.reset();
        isRunning = true;
        gameTimer.start();
        System.out.println("Game started!");
        gamePanel.repaint();
    }

    private void updateGame() {
        if (!isRunning) return;
        gameState.setFirst(false);
        if (gameState.getSnake().getDirection() == null) return;

        gameState.getSnake().move(defaultValue.returnTile(), defaultValue.returnTile()); // Move the snake

        // Check for collisions
        if (gameState.getSnake().checkCollision()) {

            gameOver();     // End game if self-collision
            return;
        }

        // Check if snake ate food
        Point head = gameState.getSnake().getHead();
        Point foodPos = gameState.getFood().getPosition();

        if (head.equals(foodPos)) {
            gameState.getSnake().grow();
            gameState.increaseScore();
            gameState.getFood().spawn();
        }

        gamePanel.repaint();
    }

    public void pauseGame() {
        if(gameState.isPause() && !gameState.isFirst()) {
            gameTimer.start();
            gameState.setPause(false);
            gamePanel.repaint();
        } else if (!gameState.isPause() && !gameState.isFirst()) {
            gameTimer.stop();
            gameState.setPause(true);
            gamePanel.repaint();
        }
    }

    public void gameOver() {
        isRunning = false;
        gameTimer.stop();
        gameState.setGameOver(true);
        gamePanel.repaint();
    }

//    public void changeDirection(Direction newDirection) {
//        // Prevent 180-degree turns
//        Direction current = gameState.getSnake().getDirection();
//        if ((current == Direction.UP && newDirection != Direction.DOWN) ||
//                (current == Direction.DOWN && newDirection != Direction.UP) ||
//                (current == Direction.LEFT && newDirection != Direction.RIGHT) ||
//                (current == Direction.RIGHT && newDirection != Direction.LEFT)) {
//            gameState.getSnake().setDirection(newDirection);
//        }
//    }

    public void changeDirection(Direction direction) {
        gameState.getSnake().setDirection(direction);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Input getInputHandler() {
        return input;
    }

    public GameState getGameState() {
        return gameState;
    }


}
