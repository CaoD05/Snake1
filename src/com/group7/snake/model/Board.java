package com.group7.snake.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Board {
    private int boardWidth = 800;
    private int boardHeight = boardWidth;

    public Board(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
    }

    public int getWidth() {
        return boardWidth;
    }

    public int getHeight() {
        return boardHeight;
    }
}
