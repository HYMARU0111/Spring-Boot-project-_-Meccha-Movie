package com.infosiatec.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {
	@Column(name = "creation_ date")
	@CreatedDate
	private String creationDate;
	
	@Column(name = "update_date")
	@LastModifiedDate
	private String updateDate;
	
	@PrePersist
	public void onPrePersist() {
		this.creationDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.updateDate = this.creationDate;
	}
	
	@PreUpdate
	public void onPreUpdate() {
		this.updateDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
