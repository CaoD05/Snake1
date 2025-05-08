package com.group7.snake.controller;

import com.group7.snake.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameController {
    private final GameState gameState;
    private final Timer gameTimer;
    private final Input input;
    private boolean isRunning;

    public GameController(GameState gameState) {
        this.gameState = gameState;
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
    }

    private void updateGame() {
        if (!isRunning) return;

        gameState.getSnake().move(gameState.getBoard().getWidth(), gameState.getBoard().getHeight()); // Move the snake

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

        gameState.update(); // Notify observers (e.g., the View)

    }

    public void gameOver() {
        isRunning = false;
        gameTimer.stop();
        gameState.setGameOver(true);
        gameState.update();
    }

    public void restartGame() {
        startGame();
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
