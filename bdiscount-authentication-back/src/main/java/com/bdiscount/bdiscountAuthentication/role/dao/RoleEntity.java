package com.bdiscount.bdiscountAuthentication.role.dao;

import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(schema = "authentication", name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue
    public UUID id;
    @Column(name = "role_name")
    public String name;
}
