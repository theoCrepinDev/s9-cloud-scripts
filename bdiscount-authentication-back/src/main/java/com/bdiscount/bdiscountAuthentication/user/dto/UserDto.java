package com.bdiscount.bdiscountAuthentication.user.dto;

import com.bdiscount.bdiscountAuthentication.role.dao.RoleEntity;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private List<RoleEntity> roles;
    private String token;
}
