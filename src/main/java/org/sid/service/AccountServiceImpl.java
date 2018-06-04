package org.sid.service;

import org.sid.dao.RoleRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public AppUser saveUser(AppUser appUser) {
		String hashPW = bCryptPasswordEncoder.encode(appUser.getPassword());
		appUser.setPassword(hashPW);
		return userRepository.save(appUser);
	}
	
	@Override
	public AppRole saveRole(AppRole appRole) {
		return roleRepository.save(appRole);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppRole appRole = roleRepository.findByRoleName(roleName);
		AppUser appUser = userRepository.findByUsername(username);
		appUser.getRoles().add(appRole);
		
	}

	@Override
	public AppUser findUserByUserName(String username) {
		return userRepository.findByUsername(username);
	}

}
