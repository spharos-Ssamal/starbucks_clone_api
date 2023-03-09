package com.ssamal.starbucks_clone_api.v1.user.entity.repository;

import com.ssamal.starbucks_clone_api.v1.user.entity.ShippingAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    Optional<ShippingAddress> findByServiceUserIdAndIsDefaultAddress(UUID userId,
        boolean isDefaultAddress);

    List<ShippingAddress> findAllByServiceUserId(UUID userId);

    boolean existsByServiceUserIdAndIsDefaultAddress(UUID userId, boolean isDefaultAddress);
}
