package com.example.RegistrationLogin.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.RegistrationLogin.registration.token.Token;
import com.example.RegistrationLogin.registration.token.TokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptpswd;
	private final TokenService tokenService;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
                        
	}
	
	public String signUpUser(User user) {
		boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
		if (userExists) {
			throw new IllegalStateException("Email already exists");
		}
		
		String encodedPswd = bCryptpswd.encode(user.getPassword());
		
		user.setPassword(encodedPswd);
		
		userRepository.save(user);
		
		String tokenUuid = UUID.randomUUID().toString();
		Token token = new Token(
					tokenUuid,
					LocalDateTime.now(),
					LocalDateTime.now().plusMinutes(15),
					user
				);
				
		tokenService.saveToken(token);
		
		return tokenUuid;
	}

	public int enableUser(String email) {
		return userRepository.enableUser(email);
	}

}