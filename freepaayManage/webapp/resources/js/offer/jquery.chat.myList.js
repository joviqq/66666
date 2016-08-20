var xhr;

function changeTab(type) {
	$(".class_tab").removeClass("class_tab");
	$("#"+type).addClass("class_tab");
	getData(type,1,10);
}

function turnPage(number) {
	var type=$(".class_tab").attr("livalue");
	getData(type,number,10);
	$(".page_num").fadeOut(100);
}

function getData(type,pageNum,pageSize) {
	if(xhr!=null){
		xhr.abort();
	}
	xhr=$.ajax({
		url : webUrl + "/manage/" + type+"/"+pageNum+"/"+pageSize,
		type : "get",
		cache : false,
		async : true,
		success : function(ret) {
			$("#dataDiv").html(ret);
		},
		error : function(retMsg) {
			$("#dataDiv").html(ret);
		}
	});
}