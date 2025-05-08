package com.group7.snake.controller;

import com.group7.snake.model.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
    private final GameController controller;

    public Input(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        if (!controller.isRunning()) {
            controller.startGame(); // First, reset everything

            // Then handle first direction input
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> controller.changeDirection(Direction.UP);
                case KeyEvent.VK_DOWN -> controller.changeDirection(Direction.DOWN);
                case KeyEvent.VK_LEFT -> controller.changeDirection(Direction.LEFT);
                case KeyEvent.VK_RIGHT -> controller.changeDirection(Direction.RIGHT);
                default -> { return; }
            }
            return;
        }

        // If game is already running, allow direction change (prevent 180° turn)
        Direction current = controller.getGameState().getSnake().getDirection();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (current != Direction.DOWN) controller.changeDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                if (current != Direction.UP) controller.changeDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                if (current != Direction.RIGHT) controller.changeDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                if (current != Direction.LEFT) controller.changeDirection(Direction.RIGHT);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
