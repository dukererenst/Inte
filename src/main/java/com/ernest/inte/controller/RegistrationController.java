package com.ernest.inte.controller;

import com.ernest.inte.model.payload.response.ApiResponse;
import com.ernest.inte.security.dto.RegistrationRequest;
import com.ernest.inte.security.dto.RegistrationResponse;
import com.ernest.inte.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity registrationRequest(@Valid @RequestBody RegistrationRequest registrationRequest) {

        RegistrationResponse registrationResponse = userService.registration(registrationRequest);
        if (registrationResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", registrationRequest, "Registration Successful"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Registration Failed"));
        }

    }

}
