package com.ssamal.starbucks_clone_api.user.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "ship_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user_id"})
public class User_Shiping_Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "default_adress", length = 200, nullable = false)
    private boolean default_adress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

}