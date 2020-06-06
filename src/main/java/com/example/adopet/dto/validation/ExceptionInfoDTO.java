package com.example.adopet.dto.validation;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ExceptionInfoDTO implements Serializable {
    private String error;
    private String message;

    public ExceptionInfoDTO(Exception ex) {
        if (ex.getClass().getName().split("\\.").length > 0)
            this.error = ex.getClass().getName().split("\\.")[ex.getClass().getName().split("\\.").length - 1];
        else
            this.error = ex.getClass().getTypeName();
        this.message = ex.getMessage();
    }
}