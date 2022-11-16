package com.kidcools.testboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTag implements Serializable {
    private String tagName;
    private String tagDetailurl;
}
