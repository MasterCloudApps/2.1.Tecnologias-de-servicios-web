package es.mastercloudapps.jwt_example.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
	@NotBlank
  	private String username;

	@NotBlank
	private String password;
}
