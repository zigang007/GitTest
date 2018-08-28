<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>捷途软件-知道前端</title> 
<%@ include file="/WEB-INF/taglib.jsp"%>

<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ui.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/index.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/images/favicon.ico" />
<link rel="stylesheet" href="${ctx}/css/smoothness/jquery.ui.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />

<%-- <link rel="stylesheet" href="${ctx}/css/style.css">
<link rel="stylesheet" href="${ctx}/resources/easyUI/easyui.css">
<script type="text/javascript" src="${ctx }/resources/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/easyUI/jquery.easyui.min.js"></script> --%>
</head>
<body>


<div id="header">
	<div class="header_main">
		<h1>知问</h1>
		<div class="header_search">
			<input type="text" name="search" class="search" />
		</div>
		<div class="header_button">
			<button id="search_button">查询</button>
		</div>
		<div class="header_member">
			<a href="###" id="reg_a">注册</a> | <a href="###" id="login_a">登录</a>
		</div>
	</div>
</div>


<form id="reg" action = "baidu.com" title="会员注册">
	<p>
		<label for="user">帐号：</label>
		<input type="text" name="user" class="text" id="user" title="请输入帐号，不小于
		2 位！" />
		<span class="star">*</span>
		</p>
		<p>
		<label for="pass">密码：</label>
		<input type="text" name="pass" class="text" id="pass" title="请输入密码，不小于
		6 位！" />
		<span class="star">*</span>
		</p>
		<p>
		<label for="email">邮箱：</label>
		<input type="text" name="email" class="text" id="email" title="请输入电子邮件！
		" />
		<span class="star">*</span>
		</p>
		<p>
		<label>性别：</label>
		<input type="radio" name="sex" id="male" checked="checked"><label
		for="male">男</label></input><input type="radio" name="sex" id="female"><label
		for="female">女</label></input>
		</p>
		<p>
		<label for="date">生日：</label>
		<input type="text" name="date" readonly="readonly" class="text" id="date" />
		</p>
</form>



</body>
</html>
