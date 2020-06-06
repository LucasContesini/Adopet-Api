package com.example.adopet.dto.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BeanValidationDTO implements Serializable {

    private String field;
    private String error;
}
