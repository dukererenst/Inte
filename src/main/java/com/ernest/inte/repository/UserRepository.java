package com.ernest.inte.repository;

import com.ernest.inte.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String username);
        
	User findByUserCode(String userCode);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

}
