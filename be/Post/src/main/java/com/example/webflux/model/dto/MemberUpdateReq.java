package com.example.webflux.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateReq {

    private Long id;
    private String account;
    private String password;

    // etc..
    public Map<String, String> getUpdateMap() {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .filter(e -> !e.getName().equals("id"))
                .collect(Collectors.toMap(
                        Field::getName, e -> {
                            try {
                                e.setAccessible(true);
                                return (String) e.get(this);
                            } catch (IllegalAccessException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                ));
    }
}
