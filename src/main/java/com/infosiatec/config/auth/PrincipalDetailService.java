package com.infosiatec.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infosiatec.model.User;
import com.infosiatec.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	//password부분은 알아서 처리되므로 username이 DB에 있는지만 확인해주면 된다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("該当のユーザーを見つかりませんでした。" + username);
				});
		
		return new PrincipalDetail(principal);//시큐리티 세션에 유저 정보가 저장된다.
	}
	
}
