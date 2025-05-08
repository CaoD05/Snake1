package com.group7.snake;

import com.group7.snake.controller.GameController;
import com.group7.snake.model.GameState;
import com.group7.snake.view.GamePanel;
import com.group7.snake.view.WindowsFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create game state (20x20 grid)
        GameState gameState = new GameState(25, 25);

        GamePanel gamePanel = new GamePanel(gameState);

        // Create controller
        GameController controller = new GameController(gameState, gamePanel);

        // Create and show GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            WindowsFrame frame = new WindowsFrame(gamePanel, controller);
            frame.setVisible(true);
            controller.startGame();
        });
    }
}
