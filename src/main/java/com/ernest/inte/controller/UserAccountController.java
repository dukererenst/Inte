/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.controller;

import com.ernest.inte.model.User;
import com.ernest.inte.model.payload.response.ApiResponse;
import com.ernest.inte.model.payload.response.UserInforResponse;
import com.ernest.inte.repository.UserRepository;
import com.ernest.inte.security.dto.RegistrationRequest;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ernest
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/user_info")
public class UserAccountController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity getUserInformation(HttpServletRequest request) {
        User user = authenticatedUser();
        if (user != null) {
            UserInforResponse inforResponse = new UserInforResponse();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", inforResponse.convertUser(user), "Successful"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error occured"));
        }
    }

    @PostMapping
    public ResponseEntity updateUserInfor(@Valid @RequestBody UserInforResponse user, HttpServletRequest request) {
        User u = authenticatedUser();
        u.setDateofBirth(user.getDateofBirth());
        u.setName(user.getName());
        u.setNationality(user.getNationality());
        u.setGender(user.getGender()); 
        if (userRepository.save(u) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", user, "Updated Successful"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error occured"));
        }
    }

    public User authenticatedUser() {
        Date startDate = new Date();
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User userop = userRepository.findByUsername(userDetails.getUsername());
        if (userop != null) {
            user = userop;

        }
        Date end = new Date();

        return user;
    }
}
