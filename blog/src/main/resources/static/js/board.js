let index = {

	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
	},

	save: function() {
		let data = {
			title : $("#Title").val(),
			content : $("#summernote").val()
		};
			
	$.ajax({
			type: "post",
			url: "/api/board",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
			dataType: "json",  
		}).done(function(resp) { 
			alert("글쓰기 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert("글쓰기 실패하였습니다.");
			alert(JSON.stringify(error));
		});
		
	},
}

index.init();