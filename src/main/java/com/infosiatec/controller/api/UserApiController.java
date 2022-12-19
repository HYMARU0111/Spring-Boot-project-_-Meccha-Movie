package com.infosiatec.controller.api;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosiatec.controller.dto.ResponseDto;
import com.infosiatec.controller.dto.UserDto;
import com.infosiatec.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	public UserApiController(UserService userService, AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
	}
	
//	@PostMapping("/auth/joinProc")
//	public String save(@RequestBody @Valid User user, Errors errors, Model model) {
//		if(errors.hasErrors()) {
//			model.addAttribute("user", user);
//			Map<String, String> validationResult = userService.validateUser(errors);
//			for(String key : validationResult.keySet()) {
//				model.addAttribute(key, validationResult.get(key));
//			}
//			return "user/joinForm";
//		}
//		userService.会員登録(user);
//		return "redirect:/auth/loginForm";
//	}
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<?> join(@Valid @RequestBody UserDto userDto,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String , String> validatorResult = userService.validateHandling(bindingResult);
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(),validatorResult);
		}
		userService.会員登録(userDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@GetMapping("/duplicate")
	public @ResponseBody ResponseDto<String> duplicate(@RequestParam String username){
		boolean result = userService.existsByUsername(username);
		String dbUsername="";
		
		if(result) {
			dbUsername=username;
		}
		log.info("DB username:"+dbUsername);
		return new ResponseDto<String>(HttpStatus.OK.value(),dbUsername);
	}
	
//	@PostMapping("/auth/joinProc")
//	public ResponseDto<Integer> save(@Valid @RequestBody UserDto userDto, ) {
//		System.out.println("UserApiController: save호출됨");
//		userService.会員登録(user);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//	}
//	
	@PutMapping("/user")
	public ResponseDto<?> update(@RequestBody UserDto userDto){
		userService.会員修正(userDto);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
