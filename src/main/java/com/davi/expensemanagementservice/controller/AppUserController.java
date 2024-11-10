package com.davi.expensemanagementservice.controller;

import com.davi.expensemanagementservice.model.AppUser;
import com.davi.expensemanagementservice.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/add")
    public ResponseEntity<AppUser> create(@RequestBody AppUser appUser) {
        AppUser newUser = appUserService.create(appUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> get(@PathVariable Long id) {
        AppUser user = appUserService.read(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> update(@PathVariable Long id, @RequestBody AppUser appUser) {
        AppUser updatedUser = appUserService.update(id, appUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appUserService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
