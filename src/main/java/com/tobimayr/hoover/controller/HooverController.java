package com.tobimayr.hoover.controller;

import com.tobimayr.hoover.model.HooverInput;
import com.tobimayr.hoover.service.HooverService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/hoover")
public class HooverController {

    private final HooverService hooverService = new HooverService();

    @Operation(summary = "Starts hoovering")
    @PostMapping(value = "/start",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity start(@Parameter(description = "Valid start instructions")
                                @Valid @RequestBody HooverInput hooverInput) {

        log.info("In HooverController.start() method");

        return ResponseEntity.ok(hooverService.start(hooverInput));
    }
}