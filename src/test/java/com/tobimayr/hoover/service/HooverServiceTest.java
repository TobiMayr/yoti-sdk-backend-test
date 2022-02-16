package com.tobimayr.hoover.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobimayr.hoover.dto.HooverInputDto;
import com.tobimayr.hoover.dto.HooverResultDto;
import com.tobimayr.hoover.model.HooverInput;
import com.tobimayr.hoover.model.HooverResult;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.tobimayr.hoover.FileToStringUtils.getJsonContent;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class HooverServiceTest {

    private HooverService hooverService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private DatabaseService databaseService;

    @Test
    public void testHooverStart_ok() throws Exception {

        String inputJson = getJsonContent("1_input_success");
        String expectedOutputJson = getJsonContent("1_output_success");

        hooverService = new HooverService(modelMapper, databaseService);
        HooverInput hooverInput = getHooverInputFromString(inputJson);
        HooverResultDto expectedHooverResult = objectMapper.readValue(expectedOutputJson, HooverResultDto.class);

        assertEquals(expectedHooverResult, hooverService.start(hooverInput));
    }

    @Test
    public void testHooverStart_invalidCoords() throws Exception {

        String inputJson = getJsonContent("4_input_invalid_coords");
        String expectedOutputJson = getJsonContent("4_output_invalid_coords");

        hooverService = new HooverService(modelMapper, databaseService);
        HooverInput hooverInput = getHooverInputFromString(inputJson);
        HooverResult expectedHooverResult = objectMapper.readValue(expectedOutputJson, HooverResult.class);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> hooverService.start(hooverInput));
        assertEquals(expectedHooverResult.getError(), exception.getMessage());

    }

    @Test
    public void testHooverStartPatchOnCoords_ok() throws Exception {

        String inputJson = getJsonContent("5_input_success_patch_on_coords");
        String expectedOutputJson = getJsonContent("5_output_success_patch_on_coords");

        hooverService = new HooverService(modelMapper, databaseService);
        HooverInput hooverInput = getHooverInputFromString(inputJson);
        HooverResultDto expectedHooverResult = objectMapper.readValue(expectedOutputJson, HooverResultDto.class);

        assertEquals(expectedHooverResult, hooverService.start(hooverInput));
    }

    @Test
    public void testHooverEverything_ok() throws Exception {

        String inputJson = getJsonContent("6_input_success_hoover_everything");
        String expectedOutputJson = getJsonContent("6_output_success_hoover_everything");

        hooverService = new HooverService(modelMapper, databaseService);
        HooverInput hooverInput = getHooverInputFromString(inputJson);
        HooverResultDto expectedHooverResult = objectMapper.readValue(expectedOutputJson, HooverResultDto.class);

        assertEquals(expectedHooverResult, hooverService.start(hooverInput));
    }

    @SneakyThrows
    public HooverInput getHooverInputFromString(String inputJson) {
        HooverInputDto hooverInputDto = objectMapper.readValue(inputJson, HooverInputDto.class);
        return modelMapper.map(hooverInputDto, HooverInput.class);
    }

}
