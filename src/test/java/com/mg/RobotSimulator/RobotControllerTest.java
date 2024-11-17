package com.mg.RobotSimulator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RobotControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPlaceCommand() throws Exception {

        mockMvc.perform(post("/command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("PLACE 1,2 NORTH"))
                .andExpect(status().isOk());

    }

    @Test
    void testPlaceMoveCommand() throws Exception {

        mockMvc.perform(post("/command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("PLACE 0,0,NORTH\n" +
                                "MOVE\n" +
                                "REPORT"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0,1,NORTH")));

    }

    @Test
    void testPlaceMoveCommand1() throws Exception {

        mockMvc.perform(post("/command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("PLACE 0,0,NORTH\n" +
                                "LEFT\n" +
                                "REPORT"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0,0,WEST")));

    }

    @Test
    void testPlaceMoveCommand2() throws Exception {

        mockMvc.perform(post("/command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("PLACE 1,2,EAST\n" +
                                "MOVE\n" +
                                "MOVE\n" +
                                "LEFT\n" +
                                "MOVE\n" +
                                "REPORT"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("3,3,NORTH")));

    }
}