package com.mg.RobotSimulator;

import org.springframework.stereotype.Service;

@Service
public class RobotService {


    String sendCommand(String commandsString) {
        String resultMessage = "";
        String resultPosition = "";
        String[] commands = commandsString.split("\n");

        Robot robot = new Robot(-1,-1,"");

        for (int i = 0; i < commands.length; i++) {

            if (commands[i].contains("PLACE")) {
                try {
                    String[] commandsSplit = commands[i].split(" ")[1].split(",");
                    int X = Integer.parseInt(commandsSplit[0]);
                    int Y = Integer.parseInt(commandsSplit[1]);
                    String F = commandsSplit[2];
                    if (robot.isValidPosition(X, Y, F))
                        robot = new Robot(X, Y, F);
                    else resultMessage = "Invalid format of PLACE command";
                } catch (Exception e) {
                    resultMessage = "Invalid format of PLACE command";
                }
            } else if (robot.getX() != -1) {
                switch (commands[i]) {
                    case "MOVE":
                        robot.moveRobot();
                        break;
                    case "LEFT":
                        robot.moveLeft();
                        break;
                    case "RIGHT":
                        robot.moveRight();
                        break;
                    case "REPORT":
                        resultPosition = resultPosition + robot.toString() + "\n";
                        break;
                    default:
                        resultMessage = "";
                }
            }
            else resultMessage = "";
        }
        if (!resultPosition.trim().isEmpty()) return resultPosition.trim();
        else return resultMessage;
    }


}
