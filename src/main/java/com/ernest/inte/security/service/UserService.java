package com.ernest.inte.security.service;

import com.ernest.inte.model.User;
import com.ernest.inte.security.dto.AuthenticatedUserDto;
import com.ernest.inte.security.dto.RegistrationRequest;
import com.ernest.inte.security.dto.RegistrationResponse;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
