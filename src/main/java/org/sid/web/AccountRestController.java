package org.sid.web;

import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	@PostMapping("/register")
	public AppUser register(@RequestBody RegisterForm registerForm) {
		if(!registerForm.getPassword().equals(registerForm.getRepassword()))
			throw new RuntimeException("You must confirme your password!");
		AppUser user = accountService.findUserByUserName(registerForm.getUsername());
		if(user!=null) throw new RuntimeException("User already exists");
		AppUser appUser = new AppUser();
		appUser.setPassword(registerForm.getPassword());
		appUser.setUsername(registerForm.getUsername());
	    accountService.saveUser(appUser);
	    accountService.addRoleToUser(appUser.getUsername(), "USER");
	    return appUser;
	}
}
