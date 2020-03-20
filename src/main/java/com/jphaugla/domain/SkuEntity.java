package com.jphaugla.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuEntity {

    private String key;
    private Map<String, String> attributes = new HashMap<>();
}
