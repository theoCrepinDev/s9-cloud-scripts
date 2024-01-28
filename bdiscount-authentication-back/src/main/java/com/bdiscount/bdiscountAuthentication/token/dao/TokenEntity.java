package com.bdiscount.bdiscountAuthentication.token.dao;

import com.bdiscount.bdiscountAuthentication.user.dao.UserEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(schema = "authentication", name = "token")
public class TokenEntity {
    @Id
    @GeneratedValue
    public UUID id;

    @Column(name = "user_id")
    public UUID userId;

    public String token;

    @Column(name = "creation_date")
    public int creationDate;

    @Column(name = "expiration_date")
    public int expirationDate;
}
