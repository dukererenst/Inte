package com.ernest.inte.model;

import com.ernest.inte.model.entity.EntityModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import lombok.*;

import javax.persistence.*;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User extends EntityModel {

    @Column(name = "full_name")
    private String name;

    @Column(unique = true, name = "username")
    private String username;
    
    @Column(unique = true, name = "user_code")
    private String userCode;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "email_address")
    private String email;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateofBirth;
    
    @Column(name = "nationality")
    private String nationality;

     @JsonIgnore
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
