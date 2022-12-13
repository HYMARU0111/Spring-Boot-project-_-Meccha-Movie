let review = {
	init: function(){
		$('#btn-review-save').on('click',()=>{
			this.reviewSave();
		});		
		$('#btn-review-update').on('click',()=>{
			this.reviewUpdate();
		});	
	},
	reviewSave: function() {
		let data = {
			title: $('#review-title').val(),
			content: $('#review-content').val(),
			rating: $('#review-rating').val()
		};

		let movieId = $('#movieId').val();
		
		$.ajax({
			type: "POST",
			url: `/api/movie/${movieId}/reviews`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("レビュー作成が完了しました。")
			location.href = `/movie/${movieId}`;
		}).fail(function() {
			alert(JSON.stringify(error));
		});

	},
	

	
	reviewDelete: function(movieId, reviewId) {
		$.ajax({
			type: "DELETE",
			url: `/api/movie/${movieId}/reviews/${reviewId}`,
			dataType: "json"
		}).done(function(resp) {
			alert("レビューが削除されました。")
			location.href = `/movie/${movieId}`;
		}).fail(function() {
			alert(JSON.stringify(error));
		});
	}
}

review.init();

$(document).ready(function(){
	$(".review-form").hide();
	$("#btn-review-create").on("click",function(){
		$(".review-form").toggle();
	});
});