package com.example.adopet.util.exception;

public class DataNotFoundException extends Exception{
    public static final String USER_NOT_FOUND = "Usuário não encontrado";
    public DataNotFoundException(String message) {
        super(message);
    }
}
