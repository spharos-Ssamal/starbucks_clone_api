package com.ssamal.starbucks_clone_api.v1.user.cart.repository;

import com.ssamal.starbucks_clone_api.v1.user.cart.entity.CartItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserIdAndIsDeleted(UUID userId, boolean isDeleted);

    Optional<CartItem> findByProductIdAndIsDeleted(Long productId, boolean isDeleted);

    Optional<CartItem> findByIdAndIsDeleted(Long cartId, boolean isDeleted);

    Long deleteAllByProductId(Long productId);

}
