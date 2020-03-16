package com.galvanize.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OfficerRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void postOfficer() throws Exception {
        String input = "{\"rank\":\"COMMODORE\", \"first\":\"Test\", \"last\":\"Captain\"}";
        mockMvc.perform(post("/officers").content(input).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first").value("Test"));
    }

    @Test
    public void findAllOfficers() throws Exception{
        mockMvc.perform(get("/officers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andDo(print());
    }

    @Test
    public void findOfficerByID() throws Exception{
        mockMvc.perform(get("/officer?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first").value("James"));
    }

    @Test
    public void changeOfficerRank() throws Exception{
        mockMvc.perform(get("/changerank?id=1&rank=COMMODORE"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rank").value("COMMODORE"));
    }

    @Test
    public void deleteById() throws Exception{
        mockMvc.perform(delete("/officers?id=1&rank=COMMODORE"))
                .andExpect(status().isOk());
    }
}