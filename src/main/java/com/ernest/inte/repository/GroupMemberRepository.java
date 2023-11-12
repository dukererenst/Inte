package com.ernest.inte.repository;

import com.ernest.inte.model.User;
import com.ernest.inte.model.entity.GroupInfor;
import com.ernest.inte.model.entity.GroupMembership;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface GroupMemberRepository extends JpaRepository<GroupMembership, String> {

//	User findByUsername(String username);
//
//	boolean existsByEmail(String email);
//
//	boolean existsByUsername(String username);
     @Query("select msn from GroupMembership msn where msn.groupInfor = :groupInfor and  msn.membership = :membership")
    List<GroupMembership> findByMerchantBranch(@Param("groupInfor") GroupInfor groupInfor, @Param("membership") User membership);
    
     @Query("select msn from GroupMembership msn where msn.membership = :membership")
    List<GroupMembership> findMyGroupInfor( @Param("membership") User membership);
    
     @Query("select msn from GroupMembership msn where msn.membership = :membership and msn.isCreator =:isCreator")
    List<GroupMembership> findMyCreatedGroupInfor( @Param("membership") User membership, @Param("isCreator") boolean isCreator);
    
     @Query("select msn from GroupMembership msn where msn.membership = :membership and msn.isAdmin =:isAdmin")
    List<GroupMembership> findMyAdminGroupInfor( @Param("membership") User membership, @Param("isAdmin") boolean isAdmin);

}
