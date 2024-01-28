package com.bdiscount.bdiscountAuthentication.role.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    @Query(value = "SELECT r FROM RoleEntity r where r.name = 'USER'")
    RoleEntity findUserRole();
}
