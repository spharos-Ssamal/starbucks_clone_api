package com.ssamal.starbucks_clone_api.user.repository;

import com.ssamal.starbucks_clone_api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByuserNickname(String username);

}
