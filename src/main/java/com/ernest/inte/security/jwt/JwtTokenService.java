package com.ernest.inte.security.jwt;

import com.ernest.inte.security.mapper.UserMapper;
import com.ernest.inte.security.service.UserService;
import com.ernest.inte.model.User;
import com.ernest.inte.security.dto.AuthenticatedUserDto;
import com.ernest.inte.security.dto.LoginRequest;
import com.ernest.inte.security.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final UserService userService;

    private final JwtTokenManager jwtTokenManager;

    private final AuthenticationManager authenticationManager;

    public LoginResponse getLoginResponse(LoginRequest loginRequest) {

        try {
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
           

                authenticationManager.authenticate(usernamePasswordAuthenticationToken);

                AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByUsername(username);

                User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
                String token = jwtTokenManager.generateToken(user);
                long expiry = jwtTokenManager.jwtProperties.getExpirationMinute() * 60 * 1000;

                log.info("{} has successfully logged in!", user.getUsername());

                return new LoginResponse(token, expiry);
          
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
