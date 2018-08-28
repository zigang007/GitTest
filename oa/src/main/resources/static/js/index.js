$(function () {

	$('#search_button').button();
	
	/*
	同时打开两个对话框
	$('#reg').dialog();
	$('#login').dialog();
	*/
	
	$('#reg').dialog({
		title : '知问注册',
		width : 320,
		height : 380,
		resizable : false,
		modal : true,
		closeText : '关闭',
		autoOpen : false,
		buttons : {
			'提交' : function () {
				$(this).submit();
			},
			'取消' : function () {
				$(this).dialog('close');
			}
		},
	});
//	$('#reg').buttonset();
	
	//日期样式
	$('#date').datepicker({
		changeMouth:true,
		changeYear:true,
		maxDate : 0,
		yearSuffix:'',
		yearRange : '1950:2020',
		
		
	});
	

/*	$('#reg input[title]').tooltip({
		show:false,
		hide:false,
		position : {
			my : 'left center',
			at : 'right+5 center'
			}
	});*/
	
	$('#reg_a').click(function () {
		$('#reg').dialog('open');
	});
	
	$('#search_button').button({
		icons : {
		primary : 'ui-icon-search',
		}
		});
	
	//邮箱自动补全
	$('#email').autocomplete({
		autoFocus : true,
		delay : 0,
		source : function (request, response) {
		var hosts = ['qq.com','163.com', '163.com', 'gmail.com', 'hotmail.com'], //起始
		term = request.term, //获取输入值
		ix = term.indexOf('@'), //@
		name = term, //用户名
		host = '', //域名
		result = []; //结果
		//结果第一条是自己输入
		result.push(term);
		if (ix > -1) { //如果有@的时候
		name = term.slice(0, ix); //得到用户名
		host = term.slice(ix + 1); //得到域名
		}
		if (name) {
		//得到找到的域名
		var findedHosts = (host ? $.grep(hosts, function (value, index) {
		return value.indexOf(host) > -1;
		}) : hosts),
		//最终列表的邮箱
		findedResults = $.map(findedHosts, function (value, index) {
		return name + '@' + value;
		});
		//增加一个自我输入
		result = result.concat(findedResults);
		}
		response(result);
		},
	});
	
	//验证
	$('#reg').validate({
		rules : {
			user : {
				required : true,
				minlength : 2,
				//remote : 'user.php',
			}, 
			pass : {
				required : true,
				minlength : 6,
				remote : {
					url : 'user.php',
					type : 'POST',
					dataType : 'json',
					data : {
						user : function () {
							return $('#user').val();
						},
					},
				},
			}
		},
		messages : {
			user : {
				required : '帐号不得为空！',
				minlength : jQuery.format('帐号不得小于{0}位！'),
				remote : '帐号被占用！',
			},
			pass : {
				required : '密码不得为空！',
				minlength : jQuery.format('密码不得小于{0}位！'),
				remote : '帐号或密码不正确！',
			},
		}
	});
});


























