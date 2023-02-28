package com.ssamal.starbucks_clone_api.v1.user.repository;

import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<ServiceUser, UUID> {
    Optional<ServiceUser> findByUserEmail(String userEmail);

}
