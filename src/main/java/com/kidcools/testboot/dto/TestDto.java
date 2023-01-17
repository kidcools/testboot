package com.kidcools.testboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TestDto {
    private String name;
    private BigDecimal money;
}
