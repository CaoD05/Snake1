package com.group7.snake;

import com.game.snake.controller.GameController;
import com.game.snake.model.GameState;
import com.game.snake.view.GameFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create game state
        GameState gameState = new GameState(20, 20);

        // Create controller
        GameController controller = new GameController(gameState);

        // Create and show GUI
        SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame(gameState, controller);
            frame.setVisible(true);
            controller.startGame();
        });
    }
}