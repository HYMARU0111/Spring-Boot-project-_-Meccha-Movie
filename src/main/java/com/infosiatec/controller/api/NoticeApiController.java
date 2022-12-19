package com.infosiatec.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosiatec.config.auth.PrincipalDetail;
import com.infosiatec.controller.dto.ResponseDto;
import com.infosiatec.model.Notice;
import com.infosiatec.service.NoticeService;

@RestController
public class NoticeApiController {
	@Autowired
	private NoticeService noticeService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/api/admin/notice")
	public ResponseDto<Integer> noticeSave(@RequestBody Notice notice, @AuthenticationPrincipal PrincipalDetail principal){
		noticeService.お知らせ作成(notice, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("api/admin/notice/{id}")
	public ResponseDto<Integer> noticeDelete(@PathVariable int id){
		noticeService.お知らせ削除(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/api/admin/notice/{id}")
	public ResponseDto<Integer> noticeUpdate(@PathVariable int id,@RequestBody Notice notice){
		noticeService.お知らせ修正(id,notice);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
