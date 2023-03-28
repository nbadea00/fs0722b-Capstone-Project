package com.backend.api.user.role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.api.user.exception.MyAPIException;
import com.backend.api.user.exception.ResourceNotFoundException;

@Service
public class RoleService {
	@Autowired RoleRepository repo;
	
	public Role save(Role args) {
		if(repo.existsByRoleName(args.getRoleName())) throw new MyAPIException(HttpStatus.NOT_FOUND, "role name already used");
		return repo.save(args);
	}
	
	public Role update(Role args, Long id) {	
		if(id == null) throw new MyAPIException(HttpStatus.NOT_FOUND, "missing id");
		repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "id",  id));
		if(repo.existsByRoleNameAndIdNot(args.getRoleName(), id)) throw new MyAPIException(HttpStatus.NOT_FOUND, "role name already used");
		return repo.save(args);
	}
	
	public Role findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "id",  id));
	}
	
	public String delById(Long id) {
		repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "id",  id));
		repo.deleteById(id);
		return "Skill remuved";
	}
	
	public List<Role> findAll() {
		return repo.findAll();
	}
	
	public Set<Role> gerListRoleFromListRoleName(Set<Role> roleSet){
		return roleSet.stream().map((name) -> repo.findByRoleName(name.getRoleName()).orElseThrow(() -> new MyAPIException(HttpStatus.NOT_FOUND, "Role not found"))).collect(Collectors.toSet());	
	}
}
