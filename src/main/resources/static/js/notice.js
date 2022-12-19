let notice = {
	init: function(){
		$('#btn-notice-save').on('click',()=>{
			this.noticeSave();
		});		
		$('#btn-notice-update').on('click',()=>{
			this.noticeUpdate();
		});	
		
		$('#btn-notice-delete').on('click',()=>{
			this.noticeDelete();
		});	
	},
	noticeSave: function() {
		let data = {
			title: $('#notice-title').val(),
			content:$("#notice-content").val()
		};

		$.ajax({
			type: "POST",
			url: '/api/admin/notice',
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("お知らせ作成が完了しました。")
			location.href = `/notice`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	
	noticeUpdate: function() {
		
		let data = {
			title: $('#notice-title').val(),
			content:$('#notice-content').val()
		};

		let noticeId = $('#noticeId').val();
		
		$.ajax({
			type: "PUT",
			url: '/api/admin/notice/'+noticeId,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("お知らせ修正が完了しました。")
			location.href = `/notice/${noticeId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},
	
	noticeDelete: function() {
		let noticeId = $('#noticeId').val();

		$.ajax({
			type: "DELETE",
			url: `/api/admin/notice/${noticeId}`,
			dataType: "json"
		}).done(function(resp) {
			alert("お知らせが削除されました。")
			location.href = "/notice";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

notice.init();
