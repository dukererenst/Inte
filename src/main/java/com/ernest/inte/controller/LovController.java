/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.controller;

import com.ernest.inte.model.payload.response.ApiResponse;
import com.ernest.inte.repository.CountryRepository;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ernest
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lov")
public class LovController implements Serializable{
   
     @Autowired
     CountryRepository countryRepository ;
    
      @GetMapping("/countries")
    public ResponseEntity joinGroup(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", countryRepository.findAll(Sort.by("name")), "Successfully "));
    }
}
