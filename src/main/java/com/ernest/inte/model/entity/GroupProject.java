/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.model.entity;


import com.ernest.inte.model.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ernest
 */
@Setter
@Getter
@Entity
@Table(name = "group_project")
public class GroupProject extends EntityModel {

    @JoinColumn(name = "group_infor")
    @ManyToOne
    private GroupInfor groupInfor;

    @Column(name = "project_name")
    private String projectName;
    
    @Column(name = "project_code")
    private String projectCode;
    
    @Column(name = "objective")
    private String objective;
    
    @Column(name = "narration")
    private String naration;
    
    @Column(name = "allow_contribution")
    private boolean allowContribution;
    
    @Column(name = "visible")
    private boolean visible;
}
