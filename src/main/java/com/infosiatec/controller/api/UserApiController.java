package com.infosiatec.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosiatec.controller.dto.ResponseDto;
import com.infosiatec.model.User;
import com.infosiatec.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user, HttpSession session) {
		System.out.println("UserApiController: save호출됨");
		userService.会員登録(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
}
