package com.kepes.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ManagerEventController.class)
class ManagerEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test response to 'local' greeting endpoint")
    public void testGreetingEndpoint() throws Exception {
        mockMvc.perform(get("/api/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, from Zoltan's Event Manager!"));
    }

}