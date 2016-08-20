var defaultMsgText = "请录入您的议价留言，最大为300个字符！按Ctrl+Enter提交！";

function setTotalMoney() {
	var totalMoney = Math.floor(parseFloat($("#NEW_NUM").val())
			* parseFloat($("#NEW_PRICE").val()));
	$("#showBuytotalMoney").html(totalMoney);
}

function checkCanBuy() {
	var NEW_PRICE = $("#NEW_PRICE").val();
	if (NEW_PRICE == "") {
		alert("请输入价格");
		$("#NEW_PRICE").focus();
		return 0;
	}
	var highPrice = $(".class_highPrice").html();
	var lowPrice = $(".class_lowPrice").html();
	var orderPrice = ($(".class_orderPrice").val());
	var conTradeType = $(".class_contradeType").val();
	if (conTradeType == "A") {
		/* 如果报盘方向是销售/卖出 */
		if (parseFloat(NEW_PRICE) > parseFloat(orderPrice)) {
			alert("你定制的卖出价格不能高于" + orderPrice);
			return 0;
		}
	} else {
		/* 如果报盘方向是采购/买入 */
		if (parseFloat(NEW_PRICE) < parseFloat(orderPrice)) {
			alert("你定制的买入价格不能低于" + orderPrice);
			return 0;
		}
	}
	if(highPrice==""){}else{
		if(parseFloat(NEW_PRICE) > parseFloat(highPrice)){
			alert("错误：你定的价格比之前的最高价"+highPrice+"还高！");
			return 0;
		}
	}
	if(lowPrice==""){}else{
		if(parseFloat(NEW_PRICE) < parseFloat(lowPrice)){
			alert("错误：你定的价格比之前的最低价"+lowPrice+"还低！");
			return 0;
		}
	}
	var msgText = $("#MSG_TEXT").val();
	if(msgText.length>1000){
		alert("最多能输入1000个字符，请一次不要打这么多字!");
		$("#MSG_TEXT").focus();
		return 0;
	}
	if (confirm("询盘价" + NEW_PRICE + "" + $("#currencyTypeNumUnit").html() + "，您确认发送？")) {
		submitForm();
	}
}


function switchCustomer(obj) {
	$(".class_customerNameSet").removeClass("class_RedFont");
	$(obj).addClass("class_RedFont");
	$(".class_tradeCustomerKey").val($(".class_RedFont span").html());
	checkOfferRecord(1);
}


function writeOfferRecord(ret){
	var json1=(ret);
	var json = eval(json1);
	var str="";
	$(".class_highPrice").html(json.highPrice);
	$(".class_lowPrice").html(json.lowPrice);
	for(i=0;i<json.manageRecord.length;i++){
		var jsonmanageRecord=json.manageRecord[i];
		if(jsonmanageRecord.manageChoice=="2"){
			str+="<div class=\"mt20 p10\">";
			str+="<div class=\"other1 fr\">";
			str+="<div class=\"ico_jc1\"></div>";
			str+="<div class=\"manage_name\">"+jsonmanageRecord.manageName+"</div>";
			str+="价格：<font class=\"cred fb\">"+jsonmanageRecord.managePrice+" </font>";
			str+=jsonmanageRecord.manageCurrency;
			str+="/"+jsonmanageRecord.manageNumUnit;
			str+="； 数量：<font class=\"cred fb\">"+jsonmanageRecord.manageNum+"</font>";
			str+=jsonmanageRecord.manageNumUnit;
			str+="<p>"+jsonmanageRecord.manageMsgText+"</p>";
			str+="</div>";
			str+="<div class=\"cb\"></div>";
			str+="</div>";
		}else{
			str+="<div class=\"mt20 p10\">";
			str+="<div class=\"other fl\">";
			str+="<div class=\"manage_name1\">"+jsonmanageRecord.manageName+"</div>";
			str+="<div class=\"ico_jc\"></div>";
			str+="价格：<font class=\"cred fb\">"+jsonmanageRecord.managePrice+" </font>";
			str+=jsonmanageRecord.manageCurrency;
			str+="/"+jsonmanageRecord.manageNumUnit;
			str+="； 数量：<font class=\"cred fb\">"+jsonmanageRecord.manageNum+"</font>";
			str+=jsonmanageRecord.manageNumUnit;
			str+="<p>"+jsonmanageRecord.manageMsgText+"</p>";
			str+="</div>";
			str+="<div class=\"cb\"></div>";
			str+="</div>";
		}
	}
	$("#manageRecord").html(str);
}


function checkOfferRecord(type) {
	setTotalMoney();
	var conobjKey = $(".class_conobjKey").val();
	var selectedCustomerKey = $(".class_RedFont span").html();
	var ownerKey = $(".class_tbConobjCustomerKey").val();
	if (selectedCustomerKey == undefined) {
		selectedCustomerKey = $(".class_currentUserKey").val();
	}
	var urlCurrentUserKey= $(".class_currentUserKey").val();
	if(type==1){
		$("#manageRecord").html("<image src=\"" + webUrl + "/resources/images/icon/loader.gif\" />");
	}
	$.ajax({
		url : webUrl + "/manage/record/" + conobjKey + "/" + ownerKey
				+ "/" + selectedCustomerKey + "/" + urlCurrentUserKey,
		type : "get",
		cache : false,
		async : true,
		success : function(ret) {
			if($("#manageRecord").html().indexOf("<image src=")>0){$("#manageRecord").html("");};
			if ($("#manageRecordJsonHidden").html() == ret) {
			} else {
				$("#manageRecordJsonHidden").html(ret);
				writeOfferRecord(ret);
			}
		},
		error : function(retMsg) {
			//$("#manageRecord").html("");
		}
	});
}


function submitForm() {
	var conTradeType = $(".class_contradeType").val();
	if ($(".class_tbConobjCustomerKey").val() == $(".class_currentUserKey").val()) {
		/* 如果是报盘人点击 */
		if (conTradeType == "A") {
			/* 如果报盘方向是销售/卖出，则把当前价设成最高价，以后出价不得高于此价 */
			$(".class_highPrice").html($("#NEW_PRICE").val());
		} else {
			/* 如果报盘方向是采购/买入 ，则把当前价设成最低价，以后出价不得低于此价*/
			$(".class_lowPrice").html($("#NEW_PRICE").val());
		}
	} else {
		/* 如果是询盘人点击 */
		if (conTradeType == "A") {
			/* 如果报盘方向是销售/卖出 ，则把当前价设成最低价，以后出价不得低于此价*/
			$(".class_lowPrice").html($("#NEW_PRICE").val());
		} else {
			/* 如果报盘方向是采购/买入，则把当前价设成最高价，以后出价不得高于此价 */
			$(".class_highPrice").html($("#NEW_PRICE").val());
		}
	}
	var msgText = $("#MSG_TEXT").val();
	if(msgText==defaultMsgText){
		$("#MSG_TEXT").val("");
	}
	$.ajax({
		url : webUrl + "/manage/submit",
		type : "post",
		cache : false,
		async : true,
		dataType : "json",
		data : $("#submitForm").formSerialize(),
		success : function(ret) {
			addToOfferRecord();
			checkOfferRecord(0);
		},
		error : function(retMsg) {
			addToOfferRecord();
			checkOfferRecord(0);
		}
	});
}



function addToOfferRecord() {
	var str = "";
	var msgText = $("#MSG_TEXT").val();
	if (msgText.indexOf(defaultMsgText) > 0) {
		msgText = "稍后显示...";
	}
	if ($(".class_tbConobjCustomerKey").val() == $(".class_currentUserKey").val()) {
		str = "<div class=\"mt20 p10\"><div class=\"other fl\"><div class=\"manage_name1\">我</div><div class=\"ico_jc\"></div>价格：<font class=\"cred fb\">";
		str += $("#NEW_PRICE").val() + "</font> ";
		str += $("#currencyTypeNumUnit").html()
				+ "； 数量：<font class=\"cred fb\">" + $("#NEW_NUM").val()
				+ "</font>" + $("#numUnit").html() + "<p>" + msgText
				+ "</p></div><div class=\"cb\"></div></div>";
	} else {
		str = "<div class=\"mt20 p10\"><div class=\"other1 fr\"><div class=\"manage_name\">我</div><div class=\"ico_jc1\"></div>价格：<font class=\"cred fb\">";
		str += $("#NEW_PRICE").val() + "</font> ";
		str += $("#currencyTypeNumUnit").html()
				+ "； 数量：<font class=\"cred fb\">" + $("#NEW_NUM").val()
				+ "</font>" + $("#numUnit").html() + "<p>" + msgText
				+ "</p></div><div class=\"cb\"></div></div>";
	}
	$("#manageRecord").prepend("======================");
	$("#manageRecord").prepend(str);
	// $("#NEW_PRICE").val("");
	$("#MSG_TEXT").val("");
	$("#MSG_TEXT").focus();
}
