package com.ernest.inte.controller;

import com.ernest.inte.model.payload.response.ApiResponse;
import com.ernest.inte.security.dto.LoginRequest;
import com.ernest.inte.security.dto.LoginResponse;
import com.ernest.inte.security.jwt.JwtProperties;
import com.ernest.inte.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

	private final JwtTokenService jwtTokenService;
       private final JwtProperties jwtProperties;

	@PostMapping
	public ResponseEntity loginRequest(@Valid @RequestBody LoginRequest loginRequest) {

		 LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);
                if(loginResponse!=null)
                {
                 return ResponseEntity.ok(new ApiResponse(true, "00", loginResponse, "Successful"));    
                }
                else{
                   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Login Failed"));    
                }

		
	}

}
