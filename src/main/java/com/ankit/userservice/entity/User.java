package com.ankit.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users_micro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String userId;
    @Column(length=20)
    private String name;
    @Email
    private String email;
    @Column(length=100)
    private String about;

    @Transient
    private List<Rating> ratingList = new ArrayList<>();
}
