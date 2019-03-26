package com.myApp.Dao;

import org.springframework.data.repository.CrudRepository;

import com.myApp.domain.Security.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {

	Role findByName(String name);
}
