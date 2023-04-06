package com.ssamal.starbucks_clone_api.v1.user.serviceuser.model;


import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.v1.user.address.model.ShippingAddress;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserReq.RegisterReq;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.enums.UserRole;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import lombok.Builder.Default;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private String username;

    @Column(name = "nickname", unique = true, columnDefinition = "varchar(20) Not NULL")
    private String userNickname;

    @Column(name = "password")
    private String userPassword;

    @Column(name = "role", nullable = false, columnDefinition = "varchar(15) default 'ROLE_USER'")
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false, updatable = false)
    private LocalDate birthday;

    @Column(name = "phone", columnDefinition = "varchar(20) Not NULL", unique = true)
    private String phoneNo;

    @Column(name = "is_agree", nullable = false)
    private Boolean isAgree;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @OneToMany(mappedBy = "serviceUser")
    @Default
    private List<ShippingAddress> addressList = new ArrayList<>();

    public void updateLoginTime() {
        this.lastLogin = LocalDateTime.now();
    }
    public static ServiceUser newUser(RegisterReq req) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return ServiceUser.builder()
            .userEmail(req.getUserEmail())
            .username(req.getUserName())
            .userNickname(req.getUserNickname())
            .userPassword(passwordEncoder.encode(req.getPassword()))
            .birthday(req.getBirthday())
            .phoneNo(req.getPhoneNo())
            .isAgree(req.isAgree())
            .role(UserRole.USER)
            .build();
    }

}

