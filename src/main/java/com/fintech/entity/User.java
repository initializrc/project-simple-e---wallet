package com.fintech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user")
@Where(clause = "status = 'ACTIVE'")
public class User extends Persistence {

    private static final long serialVersionUID = -2857856493667292634L;

    public enum Type {
        ADMIN, MERCHANT, CUSTOMER;
    }

    @NotNull
    private String username;

    @Size(min = 5, max = 50)
    @Column(length = 50, unique = true)
    private String email;

    @NotNull
    private String fullName;

    @NotNull
    @Column(length = 25, unique = true)
    private String phoneNumber;

    @JsonIgnore
    @NotNull
    @Size(min = 8)
    @Column
    private String password;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Type type;

}
