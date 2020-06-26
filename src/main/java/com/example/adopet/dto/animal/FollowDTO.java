package com.example.adopet.dto.animal;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FollowDTO implements Serializable {
    private int userId;
    private int animalId;
    private boolean like;
    private boolean love;
}
