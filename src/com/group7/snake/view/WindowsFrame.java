package com.group7.snake.view;

import com.group7.snake.controller.GameController;
import com.group7.snake.model.GameState;

import javax.swing.*;

public class WindowsFrame extends JFrame
{
    public WindowsFrame(GamePanel gamePanel, GameController controller){
        setTitle("Snake UTC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(gamePanel);

        pack();
        setLocationRelativeTo(null);
        gamePanel.addKeyListener(controller.getInputHandler());
        gamePanel.setFocusable(true);
    }
}
