package com.infosiatec.controller.dto;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	@NotBlank(message="IDを入力してください。")
	@Pattern(regexp ="^(?=(.*[a-z])+)(?=(.*[0-9])+).{5,10}$", message="5~10文字の英文小文字・数字のみ使用できます。各自１文字以上入力してください。")
	private String username;
	
	@NotBlank(message="パスワードを入力してください。")
	@Pattern(regexp="(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}", message="8以上の文字、英文大・小文字・数字・特殊文字のみ使用できます。各自１文字以上入力してください。")
	private String password;
	
	@NotBlank(message="メールアドレスを入力してください。")
	@Pattern(regexp="^[a-zA-Z0-9+-\\\\_.]+@[a-zA-Z0-9-]+\\\\.[a-zA-Z0-9-.]+$",message="メールアドレスの形式ではありません。")
	private String email;
	
	@NotBlank(message="姓を入力してください。")
	private String familyname;
	
	@NotBlank(message="名を入力してください。")
	private String ownname;
	
	@NotBlank(message="姓（カナ）を入力してください。")
	private String familynameKana;
	
	@NotBlank(message="名（カナ）を入力してください。")
	private String ownnameKana;
	
}
