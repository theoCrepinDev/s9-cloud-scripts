package com.bdiscount.bdiscountAuthentication.user.dao;

import com.bdiscount.bdiscountAuthentication.role.dao.RoleEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "authentication", name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    public UUID id;

    public String name;
    @Column(name = "phone_number")
    public String phoneNumber;
    @Column(name = "password_hash")
    public String passwordHash;
    public String email;
    public String address;
    @ManyToMany
    @JoinTable(
            schema = "authentication",
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public List<RoleEntity> roles;
}