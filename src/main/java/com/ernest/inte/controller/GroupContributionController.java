/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.controller;

import com.ernest.inte.model.User;
import com.ernest.inte.model.entity.GroupInfor;
import com.ernest.inte.model.entity.GroupProject;
import com.ernest.inte.model.entity.GroupProjectContribution;
import com.ernest.inte.model.payload.request.GroupProjectContributionRequest;
import com.ernest.inte.model.payload.request.GroupProjectRequest;
import com.ernest.inte.model.payload.response.ApiResponse;
import com.ernest.inte.repository.GroupMemberRepository;
import com.ernest.inte.repository.GroupProjectContributionRepository;
import com.ernest.inte.repository.GroupProjectRepository;
import com.ernest.inte.repository.GroupRepository;
import com.ernest.inte.repository.UserRepository;
import com.ernest.inte.utils.Function;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("/api/v1/group/project/contribution")
public class GroupContributionController implements Serializable{

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupMemberRepository groupMemberRepository;
    @Autowired
    GroupProjectRepository groupProjectRepository;
    @Autowired
    GroupProjectContributionRepository groupProjectContributionRepository;

    @PostMapping("/payment")
    public ResponseEntity contributionPayment(@Valid @RequestBody GroupProjectContributionRequest joinRequest, HttpServletRequest request) {

        GroupInfor groupInfor = groupRepository.getReferenceById(joinRequest.getGroupInfor());
        if (groupInfor != null) {

            GroupProject groupProject = groupProjectRepository.getReferenceById(joinRequest.getProjectCode());
            if (groupProject != null) {
                User user = userRepository.findByUserCode(joinRequest.getMemberCode());
                if(user!=null)
                {
                GroupProjectContribution contribution = new GroupProjectContribution();
                contribution.setAmountPaid(joinRequest.getAmount());
                contribution.setGroupInfor(groupInfor);
                contribution.setMember(user); 
                //contribution.set(groupInfor);
                contribution.setGroupProject(groupProject);
                contribution.setNaration(joinRequest.getNaration());
                contribution.setPaymentDate(joinRequest.getPaymentDate());
                contribution.setPaymentMode(joinRequest.getPaymentMode());
                contribution.setId(Function.generateId()); 
                if (groupProjectContributionRepository.save(contribution) != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", joinRequest, "Successfully created "));
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error occured"));
                }
                }
                else{
                   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "User Not Found"));  
                }

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Project Not Found"));
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Not Found"));
        }
    }
    
     @GetMapping("/{groupProjectId}/my_payment")
    public ResponseEntity mycontribution(@Valid @PathParam("groupProjectId") String groupProjectId, HttpServletRequest request) {
         GroupProject groupProject = groupProjectRepository.getReferenceById(groupProjectId);
         if(groupProject!=null)
         {
           User user = authenticatedUser();
             
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", groupProjectContributionRepository.findByGroupInfor(groupProject.getGroupInfor(), user), "Contribution List"));
         }
         else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Not Found"));  
         }
    }
    
    
    

    public User authenticatedUser() {
        Date startDate = new Date();
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User userop = userRepository.findByUsername(userDetails.getUsername());
        if (userop != null) {
            user = userop;

        }
        Date end = new Date();

        return user;
    }
}
