package com.group7.snake.view;

import com.group7.snake.view.GamePanel;
import javax.swing.*;

public class WindowsFrame
{
    public WindowsFrame(){
        int windowWidth = 800;
        int windowHeight = 600;
        JFrame frame = new JFrame("Snake");
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        //
    }
}
