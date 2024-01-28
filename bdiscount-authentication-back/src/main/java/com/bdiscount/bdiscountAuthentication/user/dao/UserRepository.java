package com.bdiscount.bdiscountAuthentication.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query(value = "SELECT u FROM UserEntity u WHERE u.email = :email AND u.passwordHash = :psw")
    UserEntity getUserByEmailAndPassword(@Param("email") String email,@Param("psw") String psw);

    @Query(value = "select u FROM UserEntity u WHERE u.email = :email")
    UserEntity findByEmail(@Param("email") String email);
}
