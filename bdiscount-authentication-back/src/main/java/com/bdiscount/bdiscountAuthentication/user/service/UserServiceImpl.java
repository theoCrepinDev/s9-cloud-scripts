package com.bdiscount.bdiscountAuthentication.user.service;

import com.bdiscount.bdiscountAuthentication.dto.LoginRequest;
import com.bdiscount.bdiscountAuthentication.dto.RegisterRequest;
import com.bdiscount.bdiscountAuthentication.dto.TokenRequest;
import com.bdiscount.bdiscountAuthentication.role.dao.RoleEntity;
import com.bdiscount.bdiscountAuthentication.role.dao.RoleRepository;
import com.bdiscount.bdiscountAuthentication.token.dao.TokenEntity;
import com.bdiscount.bdiscountAuthentication.token.dao.TokenRepository;
import com.bdiscount.bdiscountAuthentication.user.dao.UserEntity;
import com.bdiscount.bdiscountAuthentication.user.dao.UserRepository;
import com.bdiscount.bdiscountAuthentication.user.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final TokenRepository tokenRepository;

    private final SecretKey key = Keys.hmacShaKeyFor("clefSignatureTokensDelongeur très importantes".getBytes());

    @Autowired
    public UserServiceImpl(
            final UserRepository repository,
            final RoleRepository roleRepository,
            final TokenRepository tokenRepository
    ) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public UserDto register(RegisterRequest request) {
        RoleEntity userRole = roleRepository.findUserRole();
        UserEntity userCreation = new UserEntity();
        userCreation.address = request.getAddress();
        userCreation.email = request.getEmail();
        userCreation.name = request.getName();
        userCreation.passwordHash = request.getPassword();
        userCreation.phoneNumber = request.getPhoneNumber();
        userCreation.roles = List.of(userRole);

        if (repository.findByEmail(request.getEmail()) != null) {
            throw new IllegalArgumentException("Already exist");
        }

        userCreation = repository.save(userCreation);

        return UserDto.builder()
                .email(userCreation.email)
                .address(userCreation.address)
                .roles(userCreation.roles)
                .phoneNumber(userCreation.phoneNumber)
                .name(userCreation.name)
                .build();
    }

    @Override
    public UserDto login(LoginRequest request) {
        UserEntity loggedUser = this.repository.getUserByEmailAndPassword(request.getEmail(), request.getPassword());

        if (loggedUser == null) {
            return null;
        }
        TokenEntity oldToken  = this.tokenRepository.findTokenByEmail(loggedUser.id);
        if(oldToken != null){
            this.tokenRepository.delete(oldToken);

        }

        return createToken(loggedUser);
    }

    @Override
    public UserDto checkToken(TokenRequest request) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(key)
                .build();

        Jws<Claims> claims = parser.parseClaimsJws(request.getToken());
        final String email = claims.getBody().getSubject();
        final UserEntity user = repository.findByEmail(email);
        if (user == null) return null;
        final boolean isEqual =
                Objects.equals(user.name, claims.getBody().get("name", String.class))
                        && Objects.equals(user.phoneNumber, claims.getBody().get("phone", String.class))
                        && user.address.equals(claims.getBody().get("address", String.class));
        if (!isEqual) return null;
        final TokenEntity token = tokenRepository.findTokenByEmail(user.id);

        if (token == null) return null;
        return UserDto.builder()
                .token(token.token)
                .roles(user.roles)
                .phoneNumber(user.phoneNumber)
                .id(user.id)
                .name(user.name)
                .address(user.address)
                .email(user.email)
                .build();

    }

    public UserDto createToken(UserEntity user) {
        final UserDto.UserDtoBuilder userDto = UserDto.builder()
                .name(user.name)
                .id(user.id)
                .roles(user.roles)
                .address(user.address)
                .phoneNumber(user.phoneNumber)
                .email(user.email);
        final String token = Jwts.builder()
                .subject(user.email)
                .claim("name", user.name)
                .claim("phone", user.phoneNumber)
                .claim("address", user.address)
                .claim("roles", user.roles.stream().map(r -> r.name).toList())
                .signWith(key)
                .compact();

        //SAVE TOKEN
        final TokenEntity tokenBuild = new TokenEntity();
        tokenBuild.userId = user.id;
        tokenBuild.token = token;
        this.tokenRepository.save(tokenBuild);

        return userDto.token(token)
                .build();
    }
}
