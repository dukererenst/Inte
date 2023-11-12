/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.model.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ernest
 */
@Getter
@Setter
@AllArgsConstructor
public class GroupRequest {
   
    private String groupName;
    private String groupCode;
    private boolean secret;
}
