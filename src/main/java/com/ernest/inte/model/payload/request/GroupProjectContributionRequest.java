/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.model.payload.request;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ernest
 */
@Getter
@Setter
@Data
public class GroupProjectContributionRequest implements Serializable{

    private String groupInfor;

    private String projectCode;
    
    private String memberCode;

    private double amount;

    private String naration;
    
    private String paymentMode;
    
    private Date paymentDate;

  
}
