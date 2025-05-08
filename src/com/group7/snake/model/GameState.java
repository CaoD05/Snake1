package com.group7.snake.model;

import java.util.Observable;

public class GameState extends Observable {
    private final Snake snake;
    private final Food food;
    private final Board board;
    private int score;
    private boolean gameOver;

    public GameState(int boardWidth, int boardHeight) {
        this.board = new Board(boardWidth, boardHeight);
        this.snake = new Snake(boardWidth / 2, boardHeight / 2);
        this.food = new Food(boardWidth, boardHeight);
        this.score = 0;
        this.gameOver = false;
    }

    public void update() {
        setChanged();
        notifyObservers();
    }

    public void reset() {
        snake.reset(250, 250);
        food.spawn();
        score = 0;
        gameOver = false;
        update();
    }

    // Getters
    public Snake getSnake() { return snake; }
    public Food getFood() { return food; }
    public Board getBoard() { return board; }
    public int getScore() { return score; }
    public boolean isGameOver() { return gameOver; }

    public void increaseScore() {
        score += 1;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
