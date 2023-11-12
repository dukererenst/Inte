/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ernest
 */
@Setter
@Getter
@Entity
@Table(name = "group_infor")
public class GroupInfor extends EntityModel {
    
    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_code")
    private String groupCode;
    
    @Column(name = "group_icon")
    private String groupIcon;

    @Column(name = "secret")
    private boolean secret; 
}
