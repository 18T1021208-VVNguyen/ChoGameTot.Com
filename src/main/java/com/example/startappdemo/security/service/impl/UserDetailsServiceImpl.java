package com.example.startappdemo.security.service.impl;

import com.example.startappdemo.entity.RoleEntity;
import com.example.startappdemo.entity.UserEntity;
import com.example.startappdemo.repository.RoleRepository;
import com.example.startappdemo.repository.UserRepository;
import com.example.startappdemo.security.service.impl.UserDetailsImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

//        Set<RoleEntity> roles =  roleRepository.findRoleByUserId(user.getId());
        return UserDetailsImpl.build(user);
    }
}
