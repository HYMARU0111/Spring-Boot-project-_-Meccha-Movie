package com.infosiatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosiatec.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/notice")
	public String notice(Model model, @PageableDefault(size=10 , sort = "id", direction=Sort.Direction.DESC)Pageable pageable ) {
		model.addAttribute("notices", noticeService.お知らせリスト(pageable));
		return "board/noticeBoard";
	}
	
	@GetMapping("/notice/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("notice", noticeService.お知らせ詳細(id));
		return "board/noticeDetail";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin/noticeForm")
	public String noticeForm() {
		return "/board/noticeForm";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/notice/{id}/updateNotice")
	public String updateNotice(@PathVariable int id, Model model) {
		model.addAttribute("notice", noticeService.お知らせ詳細(id));
		return "board/noticeUpdate";
	}
	
}
