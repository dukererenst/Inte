/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.model.payload.request;

import com.ernest.inte.model.entity.GroupInfor;
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
public class GroupProjectRequest {

    private String groupInfor;

    private String projectName;

    private String projectCode;

    private String objective;

    private String naration;

    private boolean allowContribution;

    private boolean visible;
}
