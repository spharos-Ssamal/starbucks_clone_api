package com.ssamal.starbucks_clone_api.user.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email",
            columnDefinition = "varchar(45) Not NULL", unique = true)
    private String user_email;

    @Column(name = "user_id",
            unique = true, columnDefinition = "varchar(20) Not NULL")
    private String user_id;

    @Column(name = "password", columnDefinition = "varchar(20) Not NULL")
    private String user_password;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false, updatable = false)
    private Date birthday;

    @Column(name = "phone", columnDefinition = "varchar(20) Not NULL", unique = true)
    private String phone_no;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login", nullable = false)
    private Date last_login;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="join_day", nullable = false, updatable = false)
    private Date create_at;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<User_Shiping_Address> shiping_addressList = new ArrayList<>();

}

