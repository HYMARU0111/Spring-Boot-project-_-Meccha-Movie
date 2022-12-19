package com.infosiatec.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.infosiatec.model.User;

import lombok.Data;

@Data
public class PrincipalDetail implements UserDetails{
	

	private User user;
	private Map<String, Object> attributes;

	
	public PrincipalDetail(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }
	
	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getId() {
		return user.getId();
	}
	public String getFamilyname() {
		return user.getFamilyname();
	}
	
	public String getOwnname() {
		return user.getOwnname();
	}
	
	public String getFamilynameKana() {
		return user.getFamilynameKana();
	}
	
	public String getOwnnameKana() {
		return user.getOwnnameKana();
	}
	
	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	public String getEmail() {
		return user.getEmail();
	}
	
	
	//계정만료 여부 (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정 잠겨있는지 여부(true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	//비밀번호 만료여부 (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 활성화
	@Override
	public boolean isEnabled() {
		return true; 
	}
	

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{
			return "ROLE_"+user.getRole();
		});
		
		return collectors;
	}

}
