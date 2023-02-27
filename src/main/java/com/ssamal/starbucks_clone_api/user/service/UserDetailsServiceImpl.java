package com.ssamal.starbucks_clone_api.user.service;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.user.entity.ServiceUser;
import com.ssamal.starbucks_clone_api.user.entity.repository.ServiceUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ServiceUserRepository serviceUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ServiceUser user = serviceUserRepository.findByUserEmail(username)
                .orElseThrow(() -> new CustomException(CustomError.USER_NOT_FOUND));

        return User.builder()
                .username(user.getUserName())
                .password(user.getUserPassword())
                .roles(user.getRole().toString())
                .build();
    }
}
