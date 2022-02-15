package com.tobimayr.hoover.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobimayr.hoover.dto.HooverResultDto;
import com.tobimayr.hoover.service.HooverService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static com.tobimayr.hoover.FileToStringUtils.getJsonContent;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HooverController.class)
public class HooverEndpointTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HooverService hooverService;

    @Test
    public void testHooverStart_ok() throws Exception {

        String inputJson = getJsonContent("1_input_success");
        String expectedOutputJson = getJsonContent("1_output_success");
        HooverResultDto hooverResultDto = new HooverResultDto(new int[] {1, 3}, 1);
        when(hooverService.start(any())).thenReturn(hooverResultDto);

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
}
