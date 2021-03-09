package com.json.exersice.services;

import com.json.exersice.domain.dtos.UserSeedDto;

public interface UserService {
   void seedUsers(UserSeedDto[] userSeedDtos);
}
