package com.example.RegistrationLogin.registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegistrationLogin.user.UserService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/api/register")
@AllArgsConstructor
public class RegistrationController {
	
	private RegistrationService registrationService;
	

	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
		
	}

}
