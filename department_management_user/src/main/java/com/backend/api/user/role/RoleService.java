package com.backend.api.user.role;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.api.user.exception.MyAPIException;

@Service
public class RoleService {
	@Autowired RoleRepository repo;
	
	public Set<Role> gerListRoleFromListRoleName(Set<Role> roleSet){
		return roleSet.stream().map((name) -> repo.findByRoleName(name.getRoleName()).orElseThrow(() -> new MyAPIException(HttpStatus.NOT_FOUND, "Role not found"))).collect(Collectors.toSet());	
	}
}
