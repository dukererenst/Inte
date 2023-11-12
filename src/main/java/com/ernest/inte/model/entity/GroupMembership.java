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
@Table(name = "group_membership")
public class GroupMembership extends EntityModel {

    @JoinColumn(name = "group_infor")
    @ManyToOne
    private GroupInfor groupInfor;

    @JoinColumn(name = "membership")
    @ManyToOne
    private User membership;

    @Column(name = "secret")
    private boolean secret;
    
    @Column(name = "is_admin")
    private boolean isAdmin;
    
    @Column(name = "is_creator")
    private boolean isCreator;
}
