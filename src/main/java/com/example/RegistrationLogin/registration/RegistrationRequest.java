package com.example.RegistrationLogin.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
public class RegistrationRequest {

	private final String name;
	private final String email;
	private final String password;
	
}
