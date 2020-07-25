package com.example.adopet.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class FirebaseUserDTO implements Serializable {
    private String uid;
}
