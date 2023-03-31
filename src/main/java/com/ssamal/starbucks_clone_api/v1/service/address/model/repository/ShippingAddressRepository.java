package com.ssamal.starbucks_clone_api.v1.service.address.model.repository;

import com.ssamal.starbucks_clone_api.v1.service.address.model.ShippingAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    Optional<ShippingAddress> findByServiceUserIdAndIsDefaultAddress(UUID userId,
        boolean isDefaultAddress);

    List<ShippingAddress> findAllByServiceUserIdAndIsDeletedOrderByIsDefaultAddressDescIdAsc(UUID userId, Boolean isDeleted);

    boolean existsByServiceUserIdAndIsDefaultAddress(UUID userId, boolean isDefaultAddress);
}
