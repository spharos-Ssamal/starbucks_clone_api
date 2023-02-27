package com.ssamal.starbucks_clone_api.user.entity;


import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUser extends BaseTimeEntity {
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

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role = UserRole.ROLE_USER;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false, updatable = false)
    private LocalDate birthday;

    @Column(name = "phone", columnDefinition = "varchar(20) Not NULL", unique = true)
    private String phoneNo;

    @Column(name = "is_agree", nullable = false)
    private boolean isAgree = false;

    @OneToMany(mappedBy = "serviceUser", cascade = CascadeType.ALL)
    private List<ShippingAddress> shipingAddressList = new ArrayList<>();

}

