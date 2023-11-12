/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.controller;

import com.ernest.inte.model.entity.GroupInfor;
import com.ernest.inte.model.entity.GroupMembership;
import com.ernest.inte.model.entity.GroupProject;
import com.ernest.inte.model.payload.request.GroupProjectRequest;
import com.ernest.inte.model.payload.request.GroupRequest;
import com.ernest.inte.model.payload.response.ApiResponse;
import com.ernest.inte.repository.GroupMemberRepository;
import com.ernest.inte.repository.GroupProjectRepository;
import com.ernest.inte.repository.GroupRepository;
import com.ernest.inte.repository.UserRepository;
import com.ernest.inte.utils.Function;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ernest
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group")
public class GroupProjectController implements Serializable {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupMemberRepository groupMemberRepository;
    @Autowired
    GroupProjectRepository groupProjectRepository;

    @PostMapping("/create/project")
    public ResponseEntity joinGroup(@Valid @RequestBody GroupProjectRequest joinRequest, HttpServletRequest request) {
        GroupInfor groupInfor = groupRepository.getReferenceById(joinRequest.getGroupInfor());
        if (groupInfor != null) {

            GroupProject groupProject = new GroupProject();

            groupProject.setId(Function.generateId());
            groupProject.setAllowContribution(joinRequest.isAllowContribution());
            groupProject.setGroupInfor(groupInfor);
            groupProject.setNaration(joinRequest.getNaration());
            groupProject.setObjective(joinRequest.getObjective());
            groupProject.setProjectCode(joinRequest.getProjectCode());
            groupProject.setProjectName(joinRequest.getProjectName());

            if (groupProjectRepository.saveAndFlush(groupProject) != null) {

                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", groupProject, "Successfully created " + joinRequest.getProjectName()));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error occured"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Not Found"));
        }
    }
    
    
     @GetMapping("project/by_group/list/{id}")
    public ResponseEntity getProjectByGroupId(@Valid @PathParam(value = "id") String id,HttpServletRequest request) {
         GroupInfor groupProject = groupRepository.getReferenceById(id);
        if (groupProject != null) {
            List<GroupProject> groupProjectsList = groupProjectRepository.findByGroupInfor(groupProject);
            
             return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", groupProjectsList, "Group Project(s) Found")); 
        }
        else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Not Found")); 
        }
        
    }
    
     @GetMapping("project/{id}")
    public ResponseEntity getProjectById(@Valid @PathParam(value = "id") String id,HttpServletRequest request) {
         GroupProject groupProject = groupProjectRepository.getReferenceById(id);
        if (groupProject != null) {
           
            
             return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", groupProject, "Group Project(s) Found")); 
        }
        else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Not Found")); 
        }
        
    }

    @PostMapping("/update/project/{id}")
    public ResponseEntity joinGroup(@Valid @PathParam(value = "id") String id, @RequestBody GroupProjectRequest joinRequest, HttpServletRequest request) {
        GroupProject groupProject = groupProjectRepository.getReferenceById(id);
        if (groupProject != null) {
            GroupInfor groupInfor = groupRepository.getReferenceById(joinRequest.getGroupInfor());
            if (groupInfor != null) {
                groupProject.setAllowContribution(joinRequest.isAllowContribution());
                groupProject.setGroupInfor(groupInfor);
                groupProject.setNaration(joinRequest.getNaration());
                groupProject.setObjective(joinRequest.getObjective());
                groupProject.setProjectCode(joinRequest.getProjectCode());
                groupProject.setProjectName(joinRequest.getProjectName());

                if (groupProjectRepository.saveAndFlush(groupProject) != null) {

                    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", groupProject, "Successfully created " + joinRequest.getProjectName()));
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error occured"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Not Found"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Project Not Found"));
        }
    }

}
