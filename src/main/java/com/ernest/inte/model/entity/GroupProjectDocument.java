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
@Table(name = "group_project_document")
public class GroupProjectDocument extends EntityModel {

    @JoinColumn(name = "group_infor")
    @ManyToOne
    private GroupInfor groupInfor;
    
    @JoinColumn(name = "group_project")
    @ManyToOne
    private GroupProject groupProject;

    @Column(name = "document_type")
    private String documentType;
    
    @Column(name = "documentName")
    private String documentName;
    
    @Column(name = "visible")
    private boolean visible;
}
