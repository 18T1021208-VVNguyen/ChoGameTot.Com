package com.example.startappdemo.service.impl;

import com.example.startappdemo.entity.RoleEntity;
import com.example.startappdemo.entity.UserEntity;
import com.example.startappdemo.entity.UserRoleEntity;
import com.example.startappdemo.model.ERole;
import com.example.startappdemo.model.RegisterUserModel;
import com.example.startappdemo.repository.RoleRepository;
import com.example.startappdemo.repository.UserRepository;
import com.example.startappdemo.repository.UserRoleRepository;
import com.example.startappdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private  ModelMapper mapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void saveUser(RegisterUserModel model) {
        UserEntity userEntity = mapper.map(model, UserEntity.class);

        if( !this.existsByEmail(model.getEmail()) && !this.existsByUserName(model.getUserName()) ){
            userEntity.setPassword(passwordEncoder.encode(model.getPassword()));

          userRepository.save(userEntity);

            RoleEntity roleEntity = roleRepository.findByRoleName(ERole.ROLE_USER)
                    .orElseThrow( () -> new RuntimeException("Error: Role is not found."));

            UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                    .role(roleEntity)
                    .user(userEntity)
                    .build();
            userRoleRepository.save(userRoleEntity);
        }

    }

    @Override
    public Boolean existsByUserName(String username) {
        return userRepository.existsByUserName(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
