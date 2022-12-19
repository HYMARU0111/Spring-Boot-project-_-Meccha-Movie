package com.infosiatec.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

//	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
//	private List<Review> reviews = new ArrayList<Review>();
//
//	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//	private List<Comment> comments = new ArrayList<Comment>();

	@Column(nullable = false, length = 30, unique = true)
	//@NotBlank(message="IDを入力してください。")
	//@Pattern(regexp ="^(?=(.*[a-z])+)(?=(.*[0-9])+).{5,10}$", message="5~10文字の英文小文字・数字のみ使用できます。各自１文字以上入力してください。")
	private String username;

	@Column(nullable = false, length = 100)
	//@NotBlank(message="パスワードを入力してください。")
	//@Pattern(regexp="^(?=(.*[a-z])+)(?=(.*[A-Z])+)(?=(.*[0-9])+)(?=(.*[!#$%&@*])+).{7,14}$", message="7~14文字の英文小文字・数字・特殊文字(!#$%&@*)のみ使用できます。各自１文字以上入力してください。")
	private String password;

	@Column(length = 30)
	private String familyname;

	@Column(length = 30)
	private String ownname;

	@Column(length = 30)
	private String familynameKana;

	@Column(length = 30)
	private String ownnameKana;

	@Column(nullable = false, length = 30, unique = true)
	//@NotBlank(message="メールアドレスを入力してください。")
	//@Pattern(regexp="^[a-zA-Z0-9_+-]+(.[a-zA-Z0-9_+-]+)*@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$",message="メールアドレスの形式ではありません。")
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoleType role;

	@CreationTimestamp
	@Column(name = "creation_date")
	private Timestamp createDate;

	@UpdateTimestamp
	@Column(name = "update_date")
	private Timestamp updateDate;

	public void update(String password, String familyname, String ownname
			,String familynameKana, String ownnameKana, String email) {
		this.password = password;
		this.familyname = familyname;
		this.ownname = ownname;
		this.familynameKana = familynameKana;
		this.ownnameKana = ownnameKana;
		this.email = email;
	}
	
}
