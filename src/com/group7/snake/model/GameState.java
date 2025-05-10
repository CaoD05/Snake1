package com.group7.snake.model;

public class GameState  {
    private final Snake snake;
    private final Food food;
    private final Board board;
    private int score;
    private boolean gameOver;
    private boolean Pause;
    private boolean isFirst = true;

    public GameState(int boardWidth, int boardHeight) {
        this.board = new Board(boardWidth, boardHeight);
        this.snake = new Snake(defaultValue.returnSpawnX(), defaultValue.returnSpawnY());
        this.food = new Food(boardWidth, boardHeight);
        this.score = 0;
        this.gameOver = false;
        this.Pause = false;
    }

    public void reset() {
        snake.reset();
        food.spawn();
        score = 0;
        gameOver = false;
        Pause = false;
    }

    // Getters
    public Snake getSnake() { return snake; }
    public Food getFood() { return food; }
    public Board getBoard() { return board; }
    public int getScore() { return score; }
    public boolean isGameOver() { return gameOver; }
    public boolean isPause() { return Pause; }

    public void increaseScore() {
        score += 1;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setPause(boolean pause) {
        this.Pause = pause;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        this.isFirst = first;
    }
}
