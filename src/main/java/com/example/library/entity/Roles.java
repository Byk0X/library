package com.example.library.entity;

import com.example.library.RolesEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RolesEnum role;


    public Roles() {

    }

    public Long getId() {
        return this.id;
    }

    public RolesEnum getRole() {
        return this.role;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }


}
