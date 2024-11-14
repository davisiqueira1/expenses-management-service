package com.davi.expensemanagementservice.service;

import com.davi.expensemanagementservice.dtos.SignUpDTO;
import com.davi.expensemanagementservice.exception.UsernameAlreadyExistsException;
import com.davi.expensemanagementservice.model.AppUser;
import com.davi.expensemanagementservice.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public AuthService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username);
    }

    @Transactional
    public UserDetails signUp(SignUpDTO data) {
        if (appUserRepository.findByUsername(data.username()) != null) {
            throw new UsernameAlreadyExistsException("Username '" + data.username() + "' already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        AppUser appUser = new AppUser(
                data.username(),
                encryptedPassword,
                data.name(),
                data.email(),
                data.role(),
                data.expenses()
        );
        return appUserRepository.save(appUser);
    }
}
