package com.ssamal.starbucks_clone_api.v1.user.category.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import lombok.Builder.Default;

@Entity
@Table(name = "category")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;

    @Column(name = "sizable")
    @Default
    private boolean isSizable = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Default
    private Set<Category> children = new HashSet<>();

}
