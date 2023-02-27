package com.ssamal.starbucks_clone_api.user.entity.repository;

import com.ssamal.starbucks_clone_api.user.entity.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ServiceUserRepository extends JpaRepository<ServiceUser, UUID> {
    Optional<ServiceUser> findByUserEmail(String userEmail);
}
