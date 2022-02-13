package com.tobimayr.hoover.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@WebMvcTest(HooverController.class)
public class HooverEndpointTest {

    private static String getJsonContent(String filename) {
        return String.valueOf(new ClassPathResource("/json/" + filename + ".json"));
    }

    @Autowired
    protected MockMvc mvc;

    @Test
    public void testHooverStart_ok() throws Exception {

        String inputJson = getJsonContent("1_input_success");
        String expectedOutputJson = getJsonContent("1_output_success");

        mvc.perform(post("/hoover/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputJson));
    }

    @Test
    public void testHooverStart_missingAttribute() throws Exception {

        String inputJson = getJsonContent("input_2_invalid_missing_attribute");
        String expectedOutputJson = getJsonContent("2_output_invalid_missing_attribute");

        mvc.perform(post("/hoover/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedOutputJson));
    }

    @Test
    public void testHooverStart_invalidInstructions() throws Exception {

        String inputJson = getJsonContent("3_input_invalid_instructions");
        String expectedOutputJson = getJsonContent("3_output_invalid_instructions");

        mvc.perform(post("/hoover/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedOutputJson));
    }

    @Test
    public void testHooverStart_invalidCoords() throws Exception {

        String inputJson = getJsonContent("4_input_invalid_coords");
        String expectedOutputJson = getJsonContent("4_output_invalid_coords");

        mvc.perform(post("/hoover/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedOutputJson));
    }

}
