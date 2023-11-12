/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.model.payload.response;

import com.ernest.inte.model.User;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ernest
 */
@Getter
@Setter
public class UserInforResponse {

    private String name;

    private String username;

    private String userCode;
    
    private String email;

    private String gender;

    private Date dateofBirth;

    private String nationality;

    public UserInforResponse() {
    }
    
    
    public UserInforResponse convertUser(User u)
    {
        UserInforResponse uir = new UserInforResponse();
        uir.setDateofBirth(u.getDateofBirth());
        uir.setEmail(u.getEmail());
        uir.setGender(u.getGender());
        uir.setName(u.getName());
        uir.setNationality(u.getNationality());
        uir.setUserCode(u.getUserCode());
        uir.setUsername(u.getUsername()); 
        return uir;
    }

}
