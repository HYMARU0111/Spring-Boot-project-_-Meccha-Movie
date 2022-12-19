package com.infosiatec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosiatec.model.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Integer>{

}
