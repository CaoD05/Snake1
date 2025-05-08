package com.group7.snake;

import com.group7.snake.controller.GameController;
import com.group7.snake.model.GameState;
import com.group7.snake.view.GamePanel;
import com.group7.snake.view.WindowsFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create game state (20x20 grid)
        GameState gameState = new GameState(20, 20);

        // Create controller
        GameController controller = new GameController(gameState);

        // Create and show GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            WindowsFrame frame = new WindowsFrame(gameState, controller);
            frame.setVisible(true);
            controller.startGame();
        });
    }
}
