package com.ssamal.starbucks_clone_api.user.entity;


import com.ssamal.starbucks_clone_api.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "email",
            columnDefinition = "varchar(45) Not NULL", unique = true)
    private String userEmail;

    @Column(name = "username",
            unique = true, columnDefinition = "varchar(20) Not NULL")
    private String userName;

    @Column(name = "nickname", unique = true, columnDefinition = "varchar(20) Not NULL")
    private String userNickname;

    @Column(name = "password", columnDefinition = "varchar(20) Not NULL")
    private String userPassword;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false, updatable = false)
    private LocalDate birthday;

    @Column(name = "phone", columnDefinition = "varchar(20) Not NULL", unique = true)
    private String phoneNo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserShippingAddress> shipingAddressList = new ArrayList<>();

}

