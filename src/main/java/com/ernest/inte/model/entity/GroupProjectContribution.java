/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.model.entity;


import com.ernest.inte.model.User;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ernest
 */
@Setter
@Getter
@Entity
@Table(name = "group_project_contribution")
public class GroupProjectContribution extends EntityModel {

    @JoinColumn(name = "group_infor")
    @ManyToOne
    private GroupInfor groupInfor;
    
    @JoinColumn(name = "group_membership")
    @ManyToOne
    private GroupMembership groupMembership;
    
    @JoinColumn(name = "group_project")
    @ManyToOne
    private GroupProject groupProject;
    
    @JoinColumn(name = "member")
    @ManyToOne
    private User member;

    @Column(name = "amount_paid")
    private Double amountPaid;
    
    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    
    @Column(name = "payment_mode")
    private String paymentMode;
    
    @Column(name = "narration")
    private String naration;
    
   
}
