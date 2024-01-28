package com.bdiscount.bdiscountAuthentication.token.dao;

import com.bdiscount.bdiscountAuthentication.user.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {
    @Query(value = "SELECT t FROM TokenEntity t WHERE t.userId = :userId")
    TokenEntity findTokenByEmail(@Param("userId") UUID userId);
}
