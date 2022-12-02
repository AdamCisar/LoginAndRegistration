package com.example.RegistrationLogin.registration;

import org.springframework.stereotype.Service;

import com.example.RegistrationLogin.user.User;
import com.example.RegistrationLogin.user.UserRole;
import com.example.RegistrationLogin.user.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
	
	private final EmailValidator emailValidator;
	private final UserService userService;
	
	
	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if (!isValidEmail) {
			throw new IllegalStateException("Email is not valid");
		}
		return userService.signUpUser(new User(
					request.getName(),
					request.getEmail(),
					request.getPassword(),
					UserRole.USER
				));
	}

}
