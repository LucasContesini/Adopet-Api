package com.example.adopet.util;

import com.example.adopet.dto.validation.BeanValidationDTO;
import com.example.adopet.dto.validation.ExceptionInfoWithFieldsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class BeanValidationHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionInfoWithFieldsDTO handle(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        ExceptionInfoWithFieldsDTO exceptionInfoWithFieldsDTO = formatException(exception, fieldErrors);
        return exceptionInfoWithFieldsDTO;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ExceptionInfoWithFieldsDTO handleBind(BindException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        ExceptionInfoWithFieldsDTO exceptionInfoWithFieldsDTO = formatException(exception, fieldErrors);
        exceptionInfoWithFieldsDTO.setError("MethodArgumentNotValidException");
        return exceptionInfoWithFieldsDTO;

    }

    private ExceptionInfoWithFieldsDTO formatException(Exception exception, List<FieldError> fieldErrors) {
        ExceptionInfoWithFieldsDTO exceptionInfoDTO = new ExceptionInfoWithFieldsDTO(exception);
        List<BeanValidationDTO> beanValidationDTO = new ArrayList<>();
        fieldErrors.forEach(fieldError -> {
            BeanValidationDTO error = new BeanValidationDTO(fieldError.getField(), fieldError.getDefaultMessage());
            beanValidationDTO.add(error);
        });
        exceptionInfoDTO.setErrors(beanValidationDTO);
        return exceptionInfoDTO;
    }
}