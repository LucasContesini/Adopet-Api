package com.example.adopet.util.exception;

public class DataNotFoundException extends Exception{
    public static final String USER_NOT_FOUND = "Usuário não encontrado";
    public static final String ANIMAL_NOT_FOUND = "Animal não encontrado";
    public DataNotFoundException(String message) {
        super(message);
    }
}
