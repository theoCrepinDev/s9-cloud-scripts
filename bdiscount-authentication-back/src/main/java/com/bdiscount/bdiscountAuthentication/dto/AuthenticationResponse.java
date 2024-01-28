package com.bdiscount.bdiscountAuthentication.dto;

import com.bdiscount.bdiscountAuthentication.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private Boolean isValid;
    private String message;
    private String token;
    private UserDto user;


}
