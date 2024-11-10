package com.davi.expensemanagementservice.service;

import com.davi.expensemanagementservice.exception.AppUserNotFoundException;
import com.davi.expensemanagementservice.model.AppUser;
import com.davi.expensemanagementservice.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    public AppUser create(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Transactional
    public AppUser read(Long id) {
        var opt = appUserRepository.findById(id);
        if (opt.isEmpty()) {
            throw new AppUserNotFoundException("User with id :: " + id + " not found");
        }

        return opt.get();
    }

    @Transactional
    public AppUser update(Long id, AppUser user) {
        var opt = appUserRepository.findById(id);
        if (opt.isEmpty()) {
            throw new AppUserNotFoundException("User with id :: " + id + " not found");
        }

        AppUser updateUser = opt.get();
        if (user.getExpenses() != null) updateUser.setExpenses(user.getExpenses());
        if (user.getName() != null) updateUser.setName(user.getName());
        if (user.getEmail() != null) updateUser.setEmail(user.getEmail());
        return appUserRepository.save(updateUser);
    }

    @Transactional
    public void delete(Long id) {
        appUserRepository.deleteById(id);
    }
}
