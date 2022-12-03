package com.example.RegistrationLogin.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;
	
	public void saveToken(Token token) {
		tokenRepository.save(token);
	}
	

    public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return tokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
	
}
