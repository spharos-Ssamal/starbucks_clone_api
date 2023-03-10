package com.ssamal.starbucks_clone_api.v1.cart.repository;

import com.ssamal.starbucks_clone_api.v1.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserIdAndIsDeleted(UUID userId, boolean isDeleted);
}
