package com.example.RegistrationLogin.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.RegistrationLogin.registration.EmailValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptpswd;
	

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
		
		return "You have been registrated!";
	}

}