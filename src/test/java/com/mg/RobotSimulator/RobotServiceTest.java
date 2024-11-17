package com.mg.RobotSimulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotServiceTest {

    private RobotService robotService;

    @BeforeEach
    void setUp() {
        robotService = new RobotService();
    }

    @Test
    void placeCommandTest() {
        assertEquals("", robotService.sendCommand("PLACE 1,2,NORTH"));
    }

    @Test
    void placeAndMoveCommandTest() {
        assertEquals("0,1,NORTH", robotService.sendCommand("PLACE 0,0,NORTH\n" +
                "MOVE\n" +
                "REPORT"));
    }

    @Test
    void placeAndLeftCommandTest() {
        assertEquals("0,0,WEST", robotService.sendCommand("PLACE 0,0,NORTH\n" +
                "LEFT\n" +
                "REPORT"));
    }

    @Test
    void placeAndRightCommandTest() {
        assertEquals("0,0,EAST", robotService.sendCommand("PLACE 0,0,NORTH\n" +
                "RIGHT\n" +
                "REPORT"));
    }

    @Test
    void severalStepsCommandWithoutReportTest() {
        assertEquals("", robotService.sendCommand("MOVE" +
                "MOVE\n" +
                "REPORT"));
    }

    @Test
    void severalStepsCommandTest_1() {
        assertEquals("3,3,NORTH", robotService.sendCommand("PLACE 1,2,EAST\n" +
                "MOVE\n" +
                "MOVE\n" +
                "LEFT\n" +
                "MOVE\n" +
                "REPORT"));
    }

    @Test
    void severalStepsCommandTest_2() {
        assertEquals("2,3,WEST", robotService.sendCommand("MOVE\n" +
                "MOVE\n" +
                "LEFT\n" +
                "PLACE 3,3,WEST\n" +
                "MOVE\n" +
                "REPORT"));
    }

    @Test
    void severalStepsWith2PlaceCommandTest() {
        assertEquals("2,3,WEST", robotService.sendCommand("PLACE 1,2,EAST\n" +
                "MOVE\n" +
                "MOVE\n" +
                "LEFT\n" +
                "PLACE 3,3,WEST\n" +
                "MOVE\n" +
                "REPORT"));
    }

    @Test
    void severalStepsWith2ReportCommandTest_1() {
        assertEquals("3,2,NORTH\n" +
                "3,3,NORTH", robotService.sendCommand("PLACE 1,2,EAST\n" +
                "MOVE\n" +
                "MOVE\n" +
                "LEFT\n" +
                "REPORT\n" +
                "MOVE\n" +
                "REPORT"));
    }

    @Test
    void severalStepsWith2ReportCommandTest_2() {
        assertEquals("3,2,NORTH", robotService.sendCommand("REPORT" +
                "PLACE 1,2,EAST\n" +
                "MOVE\n" +
                "MOVE\n" +
                "LEFT\n" +
                "REPORT"));
    }

    @Test
    void placeOutOfBoundsTest_1() {
        assertEquals("", robotService.sendCommand("PLACE 0,5,NORTH\n" +
                "REPORT"));
    }

    @Test
    void placeOutOfBoundsTest_2() {
        assertEquals("2,2,EAST", robotService.sendCommand("PLACE 0,5,NORTH\n" +
                "PLACE 2,2,NORTH\n" +
                "RIGHT\n" +
                "REPORT"));
    }

    @Test
    void moveOutOfBoundsTest() {
        assertEquals("0,4,NORTH", robotService.sendCommand("PLACE 0,4,NORTH\n" +
                "MOVE\n" +
                "REPORT"));
    }

    @Test
    void invalidCommandTest_1() {
        assertEquals("", robotService.sendCommand("MOVE"));
    }

    @Test
    void invalidCommandTest_2() {
        assertEquals("", robotService.sendCommand("REPORT"));
    }

    @Test
    void invalidCommandTest_3() {
        assertEquals("", robotService.sendCommand("STOP"));
    }

    @Test
    void invalidPlaceCommandTest() {
        assertEquals("Invalid format of PLACE command", robotService.sendCommand("PLACE 1,2"));
    }
}