package com.infosiatec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosiatec.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	//SELECT *FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);	
	
	boolean existsByUsername(String username);
}
