package com.infosiatec.controller.dto;



import com.infosiatec.model.RoleType;
import com.infosiatec.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	
	private String username;
	private String password;
	private String email;
	private String familyname;
	private String ownname;
	private String familynameKana;
	private String ownnameKana;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.familyname(familyname)
				.ownname(ownname)
				.familynameKana(familynameKana)
				.ownnameKana(ownnameKana)
				.role(RoleType.USER)
				.build();
	}
}
