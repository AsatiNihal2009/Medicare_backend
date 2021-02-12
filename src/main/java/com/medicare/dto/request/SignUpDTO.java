package com.medicare.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {

	private String username;
	private List<String> role;
	private String password;
}
