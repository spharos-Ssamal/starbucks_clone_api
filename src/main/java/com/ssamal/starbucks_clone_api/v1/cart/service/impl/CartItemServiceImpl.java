package com.ssamal.starbucks_clone_api.v1.cart.service.impl;

import com.ssamal.starbucks_clone_api.v1.cart.dto.CartItemDto;
import com.ssamal.starbucks_clone_api.v1.cart.entity.CartItem;
import com.ssamal.starbucks_clone_api.v1.cart.repository.CartItemRepository;
import com.ssamal.starbucks_clone_api.v1.cart.service.CartItemService;
import com.ssamal.starbucks_clone_api.v1.cart.vo.CartItemRes;
import com.ssamal.starbucks_clone_api.v1.cart.vo.CartItemReq;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.entity.repository.ServiceUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ServiceUserRepository userRepository;
    private final ProductRepository productRepository;
    @Override
    public void createCartItem(CartItemReq cartItemReq) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

        ServiceUser user = userRepository.findById(cartItemReq.getUser_id()).get();
        Product product = productRepository.findById(cartItemReq.getProduct_id()).get();

//        UserDTO userDto = userService.getUser(cartItemReq.getUser_id()); //modelMapper.map(userService.getUser(cartItemReq.getUser_id()), User.class);
//        User user = User.builder()
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .id(userDto.getId())
//                .build();
//        ProductDto productDto = productService.getProduct(cartItemReq.getProduct_id());//modelMapper.map(productService.getProduct(cartItemReq.getProduct_id()), Product.class);
//        Product product = Product.builder()
//                .id(productDto.getId())
//                .product_name(productDto.getProduct_name())
//                .product_price(productDto.getProduct_price())
//                .description(productDto.getDescription())
//                .inventory(productDto.getInventory())
//                .status(productDto.getStatus())
//                .build();
        CartItemDto cartItemDto = new CartItemDto(user,product, cartItemReq.getCount());
        CartItem cartItem = modelMapper.map(cartItemDto, CartItem.class);
        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public List<CartItemRes> getCartItemList(UUID user_id){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

        List<CartItem> cartItemList = cartItemRepository.findByUserId(user_id);
        List<CartItemRes> cartItemResList = new ArrayList<>();

        cartItemList.forEach(cartItem -> {
            CartItemRes cartItemRes = CartItemRes.builder().product(cartItem.getProduct()).count(cartItem.getCount()).build();
            cartItemResList.add(cartItemRes);
        });
//        cartItemList.forEach(cartItem -> {
//            CartItemDto cartItemDto = modelMapper.map(cartItem, CartItemDto.class);
//            cartItemDtos.add(cartItemDto);
//        });
        return cartItemResList;
    }

    @Override
    public CartItemDto updateCartItem(Long id, int count) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

        CartItem cartItem =  cartItemRepository.findById(id).get();
        CartItemDto cartItemDto = CartItemDto
                .builder()
                .id(cartItem.getId())
                .user(cartItem.getUser())
                .product(cartItem.getProduct())
                .count(count)
                .build();
        cartItem = modelMapper.map(cartItemDto, CartItem.class);
        cartItemRepository.save(cartItem);
//        CartItemDto cartItemDto = modelMapper.map(cartItem,CartItemDto.class);
        return cartItemDto;
    }

    @Override
    public String deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
        if(cartItemRepository.findById(id).isPresent()==true){
            return "삭제 실패";
        }
        return "삭제 완료";

    }


}



