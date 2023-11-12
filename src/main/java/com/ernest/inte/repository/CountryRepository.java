package com.ernest.inte.repository;

import com.ernest.inte.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface CountryRepository extends JpaRepository<Country, String> {

//	User findByUsername(String username);
//
//	boolean existsByEmail(String email);
//
//	boolean existsByUsername(String username);

}
