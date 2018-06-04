package org.sid.service;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;

public interface AccountService {
	public AppRole saveRole(AppRole appRole);
	public void addRoleToUser(String username, String roleName);
	public AppUser findUserByUserName(String username);
	public AppUser saveUser(AppUser appUser);
}
