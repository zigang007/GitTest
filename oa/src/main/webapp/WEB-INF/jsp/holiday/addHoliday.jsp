<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>办公管理系统-添加用户</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
<meta name="Keywords" content="keyword1,keyword2,keyword3" />
<meta name="Description" content="网页信息的描述" />
<meta name="Author" content="fkjava.org" />
<meta name="Copyright" content="All Rights Reserved." />
<link href="${ctx}/fkjava.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet"
	href="${ctx }/resources/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript"
	src="${ctx }/resources/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/jquery/jquery-migrate-1.2.1.min.js"></script>
<!-- 导入bootStrap的库 -->
<script type="text/javascript"
	src="${ctx}/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/easyUI/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${ctx}/resources/easyUI/easyui.css">
<script type="text/javascript">
	$(function() {

		// 如果有提示就弹出来 
		if ("${tip}") {
			 parent.showTip("${tip}");
		}
		
		//若果是修改
		var code = $("#code").val();
		if(code !="" && code !=null){
			//将假期编码设置为不可修改
			$("#code").attr("readonly",true);
			//提交按钮设置为编辑
			$("#btn_submit").text("编辑");
		}else{
			//将假期编码设置为不可修改
			$("#code").attr("readonly",false);
			//提交按钮设置为编辑
			$("#btn_submit").text("添加");
		}
		//若果是查看
		if("${action}" == "show"){
			$("#btn_submit").hide();
			$("#btn_reset").hide();
		}

		/**  添加用户，提交表单函数 */
		$("#btn_submit").click(function() {
			// 对表单中所有字段做校验
			var userId = $("#code");
			var name = $("#name");
			
			var msg = "";
			if ($.trim(userId.val()) == "") {
				msg += "假期编号不能为空!";
				userId.focus();
			} else if (!/^^\d{1,3}$/.test(userId.val())) {
				msg += "假期编号必须是1-3位数字!";
				userId.focus();
			} else if ($.trim(name.val()) == "") {
				msg += "假期名称不能为空!";
				name.focus();
			} 
			// 直接提交
			if (msg != "") {
				$.messager.alert("提示", msg, "error");
			} else {
				$("#addUserForm").submit();
			}
		});
	});
</script>
</head>
<body style="background: #F5FAFA;">
	<center>
		<form id="addUserForm" action="${ctx}/admin/leave/addLeaveType"
			method="post">
			<table class="table-condensed">
				<tbody>
					<tr>
						<td><label>假期编码:</label></td>
						<td><input type="text" id="code" readonly="readonly" name="code"
							value="${holiday.code}" class="form-control" ></td>
						<td><label>假期名称:</label></td>
						<td><input type="text" id="name" name="name"
							value="${holiday.name}" class="form-control" ></td>
					</tr>
					<tr>
						<td><label>假期期限:</label></td>
						<td><input type="text" id="time" name="time"
							value="${holiday.time}" class="form-control"></td>
						<td><label>享受人员范围:</label></td>
						<td>
							<textarea rows="5" cols="10" id="range" name="range" class="form-control">${holiday.range}</textarea>
						</td>
					</tr>
					<tr>
						<td><label>假期规定:</label></td>
						<td>
							<textarea rows="5" cols="10" id="fix" name="fix" class="form-control">${holiday.fix}</textarea>
						</td>
						<td><label>说明:</label></td>
						<td>
							<textarea rows="5" cols="10" id="remark" name="remark" class="form-control">${holiday.remark}</textarea>
						</td>
					</tr>

				</tbody>
			</table>
			<div align="center" style="margin-top: 20px;">
				<a id="btn_submit" class="btn btn-info"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;添加</a>
				<button type="reset" id = "btn_reset" class="btn btn-danger">
					<span class="glyphicon glyphicon-remove"></span>&nbsp;重置
				</button>
			</div>
		</form>

	</center>
</body>
</html>
