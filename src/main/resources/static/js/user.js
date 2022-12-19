let user = {
	init: function() {
		$('#btn-save').on('click', () => {
			this.save();
		});

		$('#btn-update').on('click', () => {
			this.update();
		});

	},

	save: function() {
		//alert('user의 save함수 호출됨.');
		let data = {
			username: $('#username').val(),
			password: $('#password').val(),
			familyname: $('#familyname').val(),
			ownname: $('#ownname').val(),
			familynameKana: $('#familynameKana').val(),
			ownnameKana: $('#ownnameKana').val(),
			email: $('#email').val()
		};

		//console.log(data)
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			if(resp.status ==400){
				alert("入力情報をもう一度ご確認ください。")
				
				if(resp.data.hasOwnProperty('valid_username')){
					$('#valid_username').text(resp.data.valid_username);
					$('#valid_username').css('color','red');
				}
				else $('#valid_username').text('');
					
				if(resp.data.hasOwnProperty('valid_password')){
					$('#valid_password').text(resp.data.valid_password);
					$('#valid_password').css('color','red');
				}
				else $('#valid_password').text('');
				
				if(resp.data.hasOwnProperty('valid_email')){
					$('#valid_email').text(resp.data.valid_email);
					$('#valid_email').css('color','red');
				}
				else $('#valid_email').text('');

				if(resp.data.hasOwnProperty('valid_familyname')){
					$('#valid_familyname').text(resp.data.valid_familyname);
					$('#valid_familyname').css('color','red');
				}
				else $('#valid_familyname').text('');
			
				if(resp.data.hasOwnProperty('valid_ownname')){
					$('#valid_ownname').text(resp.data.valid_ownname);
					$('#valid_ownname').css('color','red');
				}
				else $('#valid_ownname').text('');			
				
				if(resp.data.hasOwnProperty('valid_familynameKana')){
					$('#valid_familynameKana').text(resp.data.valid_familynameKana);
					$('#valid_familynameKana').css('color','red');
				}
				else $('#valid_familynameKana').text('');
			
				if(resp.data.hasOwnProperty('valid_ownnameKana')){
					$('#valid_ownnameKana').text(resp.data.valid_ownnameKana);
					$('#valid_ownnameKana').css('color','red');
				}
				else $('#valid_ownnameKana').text('');		
			}
			else{
				alert("会員登録ができました。")
				location.href = "/";
			}
			
			//alert("회원 가입이 완료되었습니다.")
			//location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},


	update: function() {
		let data = {
			id: $('#id').val(),
			username: $('#username').val(),
			password: $('#password').val(),
			familyname: $('#familyname').val(),
			ownname: $('#ownname').val(),
			familynameKana: $('#familynameKana').val(),
			ownnameKana: $('#ownnameKana').val(),
			email: $('#email').val()
		};
		
		if(!data.password || data.password.trim()==="" || !data.email || !data.email.trim()===""||
		!data.familyname || !data.familyname.trim()===""||!data.ownname || !data.ownname.trim()===""||
		!data.familynameKana || !data.familynameKana.trim()===""||!data.ownnameKana|| !data.ownnameKana.trim()===""){
			alert("空白もしくは入力されていないところがあります。");
			return false;
		}
		if(!/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/.test(data.password)){
			$("#valid_password").text("8以上の文字、英文大・小文字・数字・特殊文字のみ使用できます。各自１文字以上入力してください。");
			$("#valid_password").css('color','red');
			return false;
		}
		if(!/^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(data.email)){
			$("#valid_email").text("メールアドレスの形式ではありません。");
			$("#valid_email").css('color','red');
			return false;			
		}
		
		$("#valid_password").text('');
		$("#valid_email").text('');
		
		console.log(JSON.stringify(data));
		$.ajax({
			type: "PUT",
			url: '/user',
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("会員情報変更に成功しました。")
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
	
	
}

user.init();