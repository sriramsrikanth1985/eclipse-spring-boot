package com.example.user.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.registration.dao.UserDAO;

@Repository
public interface UserJpaRepository extends JpaRepository<UserDAO, Long> {
	UserDAO findByuname(String uname);
	//select u from UserDTO u, where u.name equals :name"
}
