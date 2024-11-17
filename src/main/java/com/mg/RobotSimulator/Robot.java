package com.mg.RobotSimulator;

public class Robot {
    private int X = -1;
    private int Y = -1;
    private Direction F;

    public enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    public Robot(int x, int y, String f) {
        if (isValidPosition(x, y, f)) {
            this.X = x;
            this.Y = y;
            this.F = Direction.valueOf(f);
        }

    }

    void moveRobot() {
        switch (F) {
            case NORTH:
                Y = Math.min(Y + 1, 4);
                break;
            case EAST:
                X = Math.min(X + 1, 4);
                break;
            case SOUTH:
                Y = Math.max(Y - 1, 0);
                break;
            case WEST:
                X = Math.max(X - 1, 0);
                break;
        }
    }

    void turnLeft() {
        switch (F) {
            case NORTH:
                F = Direction.WEST;
                break;
            case EAST:
                F = Direction.NORTH;
                break;
            case SOUTH:
                F = Direction.EAST;
                break;
            case WEST:
                F = Direction.SOUTH;
                break;
        }
    }

    void turnRight() {
        switch (F) {
            case NORTH:
                F = Direction.EAST;
                break;
            case EAST:
                F = Direction.SOUTH;
                break;
            case SOUTH:
                F = Direction.WEST;
                break;
            case WEST:
                F = Direction.NORTH;
                break;
        }
    }


    boolean isValidPosition(int x, int y, String f) {
        if (x < 0 || x > 4 || y < 0 || y > 4)
            return false;
        try {
            Direction.valueOf(f);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Direction getF() {
        return F;
    }

    @Override
    public String toString() {
        return X +
                "," + Y +
                "," + F;
    }
}
