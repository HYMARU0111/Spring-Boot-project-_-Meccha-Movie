package com.infosiatec.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.infosiatec.config.auth.PrincipalDetail;
import com.infosiatec.controller.dto.UserDto;
import com.infosiatec.model.RoleType;
import com.infosiatec.model.User;
import com.infosiatec.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Transactional(readOnly = true)
	public Map<String, String> validateHandling(BindingResult bindingResult) {
		Map<String, String> validatorResult = new HashMap<>();
		
		for(FieldError error : bindingResult.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		
		return validatorResult;
	}
	
	@Transactional(readOnly = true)
	public Map<String, String> validateHandling(Errors errors){
		Map<String, String> validatorResult = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		
		return validatorResult;
	}
	
	@Transactional(readOnly=true)
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	@Transactional
	public void 会員登録(User user) {
		if(existsByUsername(user.getUsername())) {
			throw new IllegalStateException("すでに存在しているIDです。");
		}
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user);	
	}
	@Transactional
	public void 会員登録(UserDto userDto) {
		User user = User.builder()
				.username(userDto.getUsername())
				.password(encoder.encode(userDto.getPassword()))
				.email(userDto.getEmail())
				.familyname(userDto.getFamilyname())
				.ownname(userDto.getOwnname())
				.familynameKana(userDto.getFamilynameKana())
				.ownnameKana(userDto.getOwnnameKana())
				.role(RoleType.USER)
				.build();
		userRepository.save(user);
	}

	public User findById(int id) {

		return userRepository.findById(id).get();
	}

//	@Transactional
//	public void 会員修正(UserDto userDto, int id) {
//		User entity= userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("会員が見つかりませんでした。"+id);
//		});
//		
//		User user = userDto.toEntity();
//		String rawPassword = user.getPassword();
//		String encPassword = encoder.encode(rawPassword);
//		entity.setPassword(encPassword);
//		entity.setEmail(user.getEmail());
//	}
	
//	public void 会員修正(User user) {
//		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
//			return new IllegalArgumentException("会員が見つかりませんでした。");
//		});
//		String rawPassword = user.getPassword();
//		String encPassword = encoder.encode(rawPassword);
//		persistance.setPassword(encPassword);
//		persistance.setEmail(user.getEmail());
//		}
	@Transactional
	public void 会員修正(UserDto userDto) {
		User userEntity = userRepository.findByUsername(userDto.getUsername()).orElseThrow(()->
				new IllegalArgumentException("会員が見つかりませんでした。"+ userDto.getUsername()));
		
		userEntity.setPassword(encoder.encode(userDto.getPassword()));
		userEntity.setEmail(userDto.getEmail());
		userEntity.setFamilyname(userDto.getFamilyname());
		userEntity.setOwnname(userDto.getOwnname());
		userEntity.setFamilynameKana(userDto.getFamilynameKana());
		userEntity.setOwnnameKana(userDto.getOwnnameKana());
	}
}
