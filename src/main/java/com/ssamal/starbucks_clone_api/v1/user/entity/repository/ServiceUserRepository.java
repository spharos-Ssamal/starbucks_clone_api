package com.ssamal.starbucks_clone_api.v1.user.entity.repository;

import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ServiceUserRepository extends JpaRepository<ServiceUser, UUID> {
    Optional<ServiceUser> findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUsername(String username);
    boolean existsByUserNickname(String userNickname);
}
