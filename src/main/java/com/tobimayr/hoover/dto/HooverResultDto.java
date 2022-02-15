package com.tobimayr.hoover.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HooverResultDto {

    private int[] coords;

    private int patches;

}