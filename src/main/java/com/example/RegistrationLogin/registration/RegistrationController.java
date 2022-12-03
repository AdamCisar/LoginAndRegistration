package com.example.RegistrationLogin.registration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class RegistrationController {
	
	private RegistrationService registrationService;
	

	@PostMapping(path = "/api/register")
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
		
	}
	
   @GetMapping(path = "/api/register/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
