/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "countries")
public class Country implements Serializable {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
    
    @Column(name = "dial_code")
    private String dialCode;

}
