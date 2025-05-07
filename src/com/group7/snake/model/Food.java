package com.group7.snake.model;

import java.awt.Point;
import java.util.Random;

public class Food
{
    private Point position;
    private final Random random;
    public final int boardWidth;
    public final int boardHeight;

    public Food(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.random = new Random();
        spawn();
    }

    public void spawn() {
        position = new Point(random.nextInt(boardWidth), random.nextInt(boardHeight));
    }

    public Point getPosition() {
        return position;
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }
}
