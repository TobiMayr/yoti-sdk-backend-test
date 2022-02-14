package com.tobimayr.hoover.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HooverController.class)
public class HooverEndpointTest {

    private ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    private static String getJsonContent(String filename) {
        byte[] bytes = new ClassPathResource("/json/" + filename + ".json").getInputStream().readAllBytes();
        return new String(bytes);
    }

    @Autowired
    private MockMvc mvc;

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

        String inputJson = getJsonContent("2_input_invalid_missing_attribute");
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

    @Test
    public void testHooverStartPatchOnCoords_ok() throws Exception {

        String inputJson = getJsonContent("5_input_success_patch_on_coords");
        String expectedOutputJson = getJsonContent("5_output_success_patch_on_coords");

        mvc.perform(post("/hoover/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputJson));
    }
}
