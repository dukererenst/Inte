/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.controller;

import com.ernest.inte.model.User;
import com.ernest.inte.model.entity.GroupInfor;
import com.ernest.inte.model.entity.GroupMembership;
import com.ernest.inte.model.payload.request.GroupAddRequest;
import com.ernest.inte.model.payload.request.GroupJoinRequest;
import com.ernest.inte.model.payload.request.GroupRequest;
import com.ernest.inte.model.payload.response.ApiResponse;
import com.ernest.inte.repository.GroupMemberRepository;
import com.ernest.inte.repository.GroupRepository;
import com.ernest.inte.repository.UserRepository;
import com.ernest.inte.utils.Function;
import java.io.Serializable;
import java.nio.channels.MembershipKey;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import lombok.Getter;
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
@RequestMapping("/api/v1/membership")
public class MemberController implements Serializable {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupMemberRepository groupMemberRepository;

    @PostMapping("/create/group")
    public ResponseEntity joinGroup(@Valid @RequestBody GroupRequest joinRequest, HttpServletRequest request) {
        GroupInfor groupInfor = new GroupInfor();
        groupInfor.setGroupCode(joinRequest.getGroupCode());
        groupInfor.setGroupName(joinRequest.getGroupName());
        groupInfor.setId(Function.generateId());
        groupInfor.setSecret(joinRequest.isSecret());
        if (groupRepository.saveAndFlush(groupInfor) != null) {
            createGroupMembership(groupInfor);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", groupInfor, "Successfully created " + joinRequest.getGroupName()));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error occured"));
        }
    }

    @GetMapping("/list/group")
    public ResponseEntity joinGroup(HttpServletRequest request) {
      List<GroupInfor> groupInforsList = groupRepository.findAll();
        if (groupInforsList.isEmpty()==false) {
           
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", groupInforsList, "Group Listed"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error occured"));
        }
    }

    @PostMapping("/join/group")
    public ResponseEntity joinGroup(@Valid @RequestBody GroupJoinRequest joinRequest, HttpServletRequest request) {
        GroupInfor infor = groupRepository.getReferenceById(joinRequest.getGroupId());
        if (infor != null) {
            GroupMembership membership = new GroupMembership();
            membership.setGroupInfor(infor);
            membership.setMembership(authenticatedUser());
            membership.setId(Function.generateId());
            GroupMembership gm = groupMemberRepository.save(membership);
            if (gm != null) {
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", gm, "Successfully Join " + infor.getGroupName()));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error occured"));
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Not Found"));
        }
    }

    @GetMapping("my/group/list")
    public ResponseEntity showMyGroup(HttpServletRequest request) {
        List<GroupMembership> groupMembershipsList = groupMemberRepository.findMyGroupInfor(authenticatedUser());
        if (groupMembershipsList.isEmpty() == false) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", groupMembershipsList, "Group(s) Found"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "01", null, "Group(s) Not Found"));
        }
    }

    @PostMapping("/remove/member/group/{groupmembershipId}")
    public ResponseEntity removeFromGroup(@Valid @PathParam(value = "groupmembershipId") String groupmembershipId, HttpServletRequest request) {
        GroupMembership infor = groupMemberRepository.getReferenceById(groupmembershipId);
        if (infor != null) {
            infor.setDeleted(true);
            infor.setDeletedBy(authenticatedUser().getId());
            infor.setUpdated(true);
            infor.setUpdatedOn(new Date());
            if (groupMemberRepository.save(infor) != null) {
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", null, "Membership Deleted Successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error during Deletion"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Not Found"));
        }

    }

    @PostMapping("/add/member/group")
    public ResponseEntity addMemberToGroup(@Valid @RequestBody GroupAddRequest joinRequest, HttpServletRequest request) {
        GroupInfor infor = groupRepository.getReferenceById(joinRequest.getGroupId());
        if (infor != null) {
            Optional<User> user = userRepository.findById(joinRequest.getUserId());
            if (user.isPresent()) {
                GroupMembership membership = new GroupMembership();
                membership.setGroupInfor(infor);
                membership.setMembership(user.get());
                membership.setId(infor.getId() + "_" + user.get().getId());
                if (groupMemberRepository.saveAndFlush(membership) != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", joinRequest, "Successfully Join " + infor.getGroupName()));
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error occured"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "User not Found"));
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Not Found"));
        }
    }

    @PostMapping("/make/member/group/admin/{groupmembershipId}")
    public ResponseEntity makeMemberAdmin(@Valid @PathParam(value = "groupmembershipId") String groupmembershipId, HttpServletRequest request) {
        GroupMembership infor = groupMemberRepository.getReferenceById(groupmembershipId);
        if (infor != null) {
            infor.setAdmin(true);
            infor.setUpdated(true);
            infor.setUpdatedBy(authenticatedUser().getId());
            infor.setUpdatedOn(new Date());
            if (groupMemberRepository.save(infor) != null) {
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", null, "Membership Updated Successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error during Deletion"));
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "01", null, "Group Not Found"));
        }
    }

    @PostMapping("/remove/member/group/admin/{groupmembershipId}")
    public ResponseEntity removeMemberAdmin(@Valid @PathParam(value = "groupmembershipId") String groupmembershipId, HttpServletRequest request) {
        GroupMembership infor = groupMemberRepository.getReferenceById(groupmembershipId);
        if (infor != null) {

            infor.setAdmin(false);
            infor.setUpdated(true);
            infor.setUpdatedBy(authenticatedUser().getId());
            infor.setUpdatedOn(new Date());
            if (groupMemberRepository.save(infor) != null) {
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "00", null, "Membership Updated Successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "01", null, "Error during Deletion"));
            }

        } else {
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

    public void createGroupMembership(GroupInfor infor) {
        GroupMembership membership = new GroupMembership();
        membership.setMembership(authenticatedUser());
        membership.setAdmin(true);
        membership.setCreator(true);
        membership.setGroupInfor(infor);
        membership.setId(infor.getId() + "_" + membership.getMembership().getId());
        groupMemberRepository.saveAndFlush(membership);
    }

}
