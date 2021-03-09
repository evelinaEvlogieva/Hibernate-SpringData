package com.json.exersice.services.impl;

import com.json.exersice.domain.dtos.UserSeedDto;
import com.json.exersice.domain.entities.User;
import com.json.exersice.repositories.UserRepository;
import com.json.exersice.services.UserService;
import com.json.exersice.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, UserRepository userRepository) {
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        for (UserSeedDto userSeedDto : userSeedDtos) {
          if(!validatorUtil.isValid(userSeedDto)){
              this.validatorUtil.violations(userSeedDto)
                      .forEach(v -> System.out.println(v.getMessage()));

              continue;
          }

          User user = this.modelMapper.map(userSeedDto, User.class);

          this.userRepository.saveAndFlush(user);
        }

    }
}
