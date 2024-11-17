package com.mg.RobotSimulator;

public class Robot {
    private int X;
    private int Y;
    private String F;


    public Robot(int x, int y, String f) {
        this.X = x;
        this.Y = y;
        this.F = f;

    }

    void moveRobot() {
        switch (F) {
            case "NORTH":
                Y = Math.min(Y + 1, 4);
                break;
            case "EAST":
                X = Math.min(X + 1, 4);
                break;
            case "SOUTH":
                Y = Math.max(Y - 1, 0);
                break;
            case "WEST":
                X = Math.max(X - 1, 0);
                break;
        }
    }

    void moveLeft() {
        switch (F) {
            case "NORTH":
                F = "WEST";
                break;
            case "EAST":
                F = "NORTH";
                break;
            case "SOUTH":
                F = "EAST";
                break;
            case "WEST":
                F = "SOUTH";
                break;
        }
    }

    void moveRight() {
        switch (F) {
            case "NORTH":
                F = "EAST";
                break;
            case "EAST":
                F = "SOUTH";
                break;
            case "SOUTH":
                F = "WEST";
                break;
            case "WEST":
                F = "NORTH";
                break;
        }
    }

    boolean isValidPosition(int x, int y, String f) {
        if (x < 0 || x > 4 || y < 0 || y > 4)
            return false;
        if (!f.equals("NORTH") || !f.equals("EAST") || !f.equals("SOUTH") || !f.equals("WEST"))
            return true;
        return false;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        this.X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public String getF() {
        return F;
    }

    public void setF(String f) {
        this.F = f;
    }

    @Override
    public String toString() {
        return X +
                "," + Y +
                "," + F;
    }
}
