package com.example.adopet.model.animal;

import com.example.adopet.model.auth.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "follow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean liked;
    private boolean loved;
    @ManyToOne
    @JsonIgnore
    private Animal animal;
    @JsonIgnore
    @ManyToOne
    User user;

}
