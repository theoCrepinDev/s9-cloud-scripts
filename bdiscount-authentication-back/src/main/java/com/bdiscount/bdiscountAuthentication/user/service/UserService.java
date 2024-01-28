package com.bdiscount.bdiscountAuthentication.user.service;

import com.bdiscount.bdiscountAuthentication.dto.LoginRequest;
import com.bdiscount.bdiscountAuthentication.dto.RegisterRequest;
import com.bdiscount.bdiscountAuthentication.dto.TokenRequest;
import com.bdiscount.bdiscountAuthentication.user.dto.UserDto;
import org.springframework.stereotype.Service;


public interface UserService {
    UserDto register(RegisterRequest request);

    UserDto login(LoginRequest request);

    UserDto checkToken(TokenRequest request);
}
