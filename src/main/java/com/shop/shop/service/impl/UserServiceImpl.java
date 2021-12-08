package com.shop.shop.service.impl;

import com.shop.shop.configuration.exception.NotFoundException;
import com.shop.shop.configuration.exception.user.EmailNotAvailableException;
import com.shop.shop.configuration.exception.user.ConfirmPasswordMismatchException;
import com.shop.shop.configuration.exception.user.NoLoggedUserException;
import com.shop.shop.configuration.exception.user.PasswordMismatchException;
import com.shop.shop.dto.RegistryDTO;
import com.shop.shop.entity.Role;
import com.shop.shop.entity.User;
import com.shop.shop.repository.UserRepository;
import com.shop.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    private  User save(User user){
        return repository.save(user);
    }

    @Override
    public User createUser(RegistryDTO registryDTO) {
        User byEmail = getByEmail(registryDTO.getEmail());
        if (byEmail!=null){
            throw  new EmailNotAvailableException("Email " + registryDTO.getEmail() + " is already taken");
        }
        if (!registryDTO.getPassword().equals(registryDTO.getConfirmPassword())){
            throw  new ConfirmPasswordMismatchException("Wrong input.Password mismatch");
        }
        User user = new User();
        user.setEmail(registryDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registryDTO.getPassword()));
        user.setRole(Role.ROLE_USER);
        return save(user);
    }

    public User getByEmailAndPassword(String email, String password){
        User user = getByEmail(email);
        if (user==null){
            throw new NotFoundException("User with email " + email + " has not been found");
        }
        //passwordEncoder can encode but cannot revert the encryption
        //here we check an encrypted password not the original one
        boolean matchConfirmed = passwordEncoder.matches(password, user.getPassword());
        if(matchConfirmed){
            return user;
        }
        throw new PasswordMismatchException("Wrong password");
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        //todo add
        User user = (User) principal;
        String currentUserEmail = user.getEmail();
        return getByEmail(currentUserEmail);
        //throw new NoLoggedUserException("No logged user");
    }
}