package com.infosiatec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosiatec.model.Notice;
import com.infosiatec.model.User;
import com.infosiatec.repository.NoticeRepository;

@Service
public class NoticeService {
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Transactional(readOnly = true)
	public Page<Notice> お知らせリスト(Pageable pageable){
		return noticeRepository.findAll(pageable);
	}

	@Transactional(readOnly=true)
	public Object お知らせ詳細(int id) {
		return noticeRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("お知らせ詳細の呼出失敗：IDが見つかりませんでした。"+ id);
		});
	}
	
	@Transactional
	public void お知らせ削除(int id) {
		noticeRepository.deleteById(id);
	}

	@Transactional
	public void お知らせ作成(Notice notice, User user) {
		notice.setViewCount(0);
		notice.setUser(user);
		noticeRepository.save(notice);
	}

	@Transactional
	public void お知らせ修正(int id, Notice requestnNotice) {
		Notice notice = noticeRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("お知らせ修正失敗:IDが見つかりませんでした。"+id);
		});
		
		notice.setTitle(requestnNotice.getTitle());
		notice.setContent(requestnNotice.getContent());
	}
	
}
