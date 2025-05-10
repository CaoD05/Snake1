package com.group7.snake.model;

import java.awt.*;

public class defaultValue {
    public static final int TILE_SIZE = 25;

    public static int returnTile(){
        return TILE_SIZE;
    }

    public static final Point Spawn = new Point(TILE_SIZE / 2, TILE_SIZE / 2);

    public static Point returnSpawn(){
        return Spawn;
    }

    public static int returnSpawnX(){
        return Spawn.x;
    }

    public static int returnSpawnY(){
        return Spawn.y;
    }
}
