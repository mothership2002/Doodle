package com.example.webflux.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBy {

    private String columnName;
    private Sort.Direction direction;
}

