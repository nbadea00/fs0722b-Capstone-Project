package com.backend.api.user.role;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.backend.api.user.role.ERole;
import com.backend.api.user.role.Role;
import com.backend.api.user.role.RoleService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(locations="classpath:test.properties")
public class RoleServiceTest {

	@Autowired RoleService roleService;
	
	private static Long id;
	
	@Test
	@Order(1)
	public void saveNewRoleTest() {
		Role r = new Role();
		r.setRoleName(ERole.TEST);
		
		roleService.save(r);
		id = r.getId();
		assertTrue(id != null);
	}
	
	@Test
	@Order(2)
	public void findByIdRoleTest() {
		roleService.findById(id);
	}
	
	@Test
	@Order(3)
	public void updateRoleTest() {
		Role r = roleService.findById(id);
		r.setRoleName(ERole.NEW_TEST);
		roleService.update(r, id);
		assertTrue(roleService.findById(id).getRoleName().equals(ERole.NEW_TEST));
	}
	
	@Test
	@Order(4)
	public void delByIdRoleTest() {
		roleService.delById(id);
		roleService.findById(id);
	}
}
