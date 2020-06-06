package com.example.adopet.dto.validation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExceptionInfoWithFieldsDTO extends ExceptionInfoDTO {

    private List<BeanValidationDTO> errors;

    public ExceptionInfoWithFieldsDTO(Exception ex) {
        super(ex);
    }
}