package com.group7.snake.model;

import com.group7.snake.model.Board;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Snake {
    private Point head;
    private ArrayList<Point> body;
    private Point lastTailPosition;
    private Direction direction;
    private boolean growing;


    public Snake(int startX, int startY) {
        body = new ArrayList<>();
        body.add(new Point(startX, startY));
        growing = false;
    }

    public void move(int boardWidth, int boardHeight) {
        System.out.println("Current direction: " + direction);
        Point head = getHead();
        Point newHead = new Point(head);
        System.out.println("Moving snake...");
        //head direction to move
        switch (direction) {
            case UP -> newHead.y--;
            case DOWN -> newHead.y++;
            case LEFT -> newHead.x--;
            case RIGHT -> newHead.x++;
        }
        System.out.println("New head after move: " + newHead);
        // Wrap around the board (classic snake) logic
        if (newHead.x < 0) {
            newHead.x = boardWidth - 1;
        } else if (newHead.x >= boardWidth) {
            newHead.x = 0;
        }
        if (newHead.y < 0) {
            newHead.y = boardHeight- 1;
        } else if (newHead.y >= boardHeight) {
            newHead.y = 0;
        }

        //select position of tail after move
        lastTailPosition = body.getLast();
        //add new head after move to body
        body.addFirst(newHead);

        //remove tail (move tail along) if body move and growing
        if (growing) {
            growing = false; // allow growth only for one frame
        } else {
            //if not growing, remove the tail, the second to last position become the tail
            body.removeLast();
        }
    }

    public void grow() {
        growing = true;
    }

    public boolean checkCollision() {
        Point head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                System.out.println("Collision found");
                return true; // Self-collision only
            }
        }
        return false;
    }

    public void reset(int x, int y) {
        //remove all part of body
        body.clear();
        //add head
        body.add(new Point(x, y));
        //set default
        growing = false;
        System.out.println("Reset called with: " + x + "," + y);
    }

    // Getters
    public Point getHead() { return body.getFirst(); }
    public List<Point> getBody() { return body; }
    public Direction getDirection() { return direction; }
    public Point getLastTailPosition() { return lastTailPosition; }

    public void setDirection(Direction direction) {
        System.out.println("Setting direction to: " + direction);
        this.direction = direction;
    }

}
