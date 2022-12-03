package com.example.RegistrationLogin.registration;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.RegistrationLogin.registration.token.Token;
import com.example.RegistrationLogin.registration.token.TokenService;
import com.example.RegistrationLogin.user.User;
import com.example.RegistrationLogin.user.UserRole;
import com.example.RegistrationLogin.user.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
	
	private final EmailValidator emailValidator;
	private final UserService userService;
	private final TokenService tokenService;
	
	
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

	@Transactional
	 public String confirmToken(String token) {
	       Token confirmToken = tokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));

	        if (confirmToken.getConfirmedAt() != null) {
	            throw new IllegalStateException("Email already confirmed!");
	        }

	        LocalDateTime expiredAt = confirmToken.getExpiresAt();

	        if (expiredAt.isBefore(LocalDateTime.now())) {
	            throw new IllegalStateException("Token expired!");
	        }

	        tokenService.setConfirmedAt(token);
	        
	        userService.enableUser(confirmToken.getUser().getEmail());
	        
	        return "Your account has been confirmed!";
	    }


	
}
