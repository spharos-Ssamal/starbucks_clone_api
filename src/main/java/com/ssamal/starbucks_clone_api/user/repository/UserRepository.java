package com.ssamal.starbucks_clone_api.user.repository;

import com.ssamal.starbucks_clone_api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByuserNickname(String userName);

}
