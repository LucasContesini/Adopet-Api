package com.example.adopet.controller.dto.validation;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ExceptionInfoDTO implements Serializable {
    private String type;
    private String message;

    public ExceptionInfoDTO(Exception ex) {
        if (ex.getClass().getName().split("\\.").length > 0)
            this.type = ex.getClass().getName().split("\\.")[ex.getClass().getName().split("\\.").length - 1];
        else
            this.type = ex.getClass().getTypeName();
        this.message = ex.getMessage();
    }
}