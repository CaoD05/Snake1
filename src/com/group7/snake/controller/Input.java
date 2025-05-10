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

//        if (!controller.isRunning()) {
//            // FIRST TIME ONLY: show instructions, wait for input
//            if (controller.getGameState().isFirst()) {
//                switch (e.getKeyCode()) {
//                    case KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> {
//                        controller.getGameState().setFirst(false);
//                        controller.changeDirection(keyToDirection(e.getKeyCode()));
//                        controller.startGame(); // Start the game
//                    }
//                }
//            }
//            return; // Do nothing on first frame if not arrow
//        }

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
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE && controller.isRunning()) {
            controller.pauseGame();
        }
    }

    private Direction keyToDirection(int keyCode) {
        return switch (keyCode) {
            case KeyEvent.VK_UP -> Direction.UP;
            case KeyEvent.VK_DOWN -> Direction.DOWN;
            case KeyEvent.VK_LEFT -> Direction.LEFT;
            case KeyEvent.VK_RIGHT -> Direction.RIGHT;
            default -> null;
        };
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
