package com.ssamal.starbucks_clone_api.v1.user.address.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.ShippingAddressDTO;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.vo.AddressReq.EditUserAddressReq;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.model.ServiceUser;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ship_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingAddress extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "alias", columnDefinition = "VARCHAR(10) NOT NULL")
    private String alias;

    @Column(name = "recipient", columnDefinition = "VARCHAR(10) NOT NULL")
    private String recipient;

    @Column(name = "zip_code")
    private Long zipCode;

    @Column(name = "base_address", columnDefinition = "VARCHAR(50) NOT NULL")
    private String baseAddress;

    @Column(name = "detail_address", columnDefinition = "VARCHAR(50) NOT NULL")
    private String detailAddress;

    @Column(name = "contact_info_1", columnDefinition = "VARCHAR(12) NOT NULL")
    private String contactInfo1;

    @Column(name = "contact_info_2", columnDefinition = "VARCHAR(12)")
    private String contactInfo2;

    @Column(name = "shipping_memo", columnDefinition = "VARCHAR(50)")
    private String shippingMemo;

    @Column(name = "is_default", columnDefinition = "bit(1) default 0")
    private boolean isDefaultAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private ServiceUser serviceUser;

    public static ShippingAddress of(ShippingAddressDTO dto) {
        return ModelMapperUtils.getModelMapper().map(dto, ShippingAddress.class);
    }

    public void editAddressInfo(EditUserAddressReq req) {
        this.alias = req.getAddressInfo().getAlias();
        this.recipient = req.getAddressInfo().getRecipient();
        this.zipCode = req.getAddressInfo().getZipCode();
        this.baseAddress = req.getAddressInfo().getBaseAddress();
        this.detailAddress = req.getAddressInfo().getDetailAddress();
        this.contactInfo1 = req.getAddressInfo().getContactInfo1();
        this.contactInfo2 = req.getAddressInfo().getContactInfo2();
        this.shippingMemo = req.getAddressInfo().getShippingMemo();
        this.isDefaultAddress = req.getAddressInfo().isDefaultAddress();
    }

}