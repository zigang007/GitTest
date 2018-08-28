<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>审批请假</title>
<script type="text/javascript" src="${ctx}/resources/miniUI/boot.js"></script>
<script type="text/javascript">
	
	mini.parse();


function onDeptChanged(e) {
	var use_idear_value = e.selected.text;
	var app_idear =  mini.get("app_idear");
	app_idear.setText("");
	app_idear.setText(use_idear_value);
}

/**
 * 拒绝
 */
 function reject(e){
	var appValue = mini.get("app_idear").getText();
	if(typeof appValue == "undefined" || appValue == null || appValue.trim() == ""){
		mini.alert("请填写审批意见！");
		return false;
	}else{
		var leave_id = $("#leaveId").val();
		var app_idear = mini.get("app_idear").getText();
		$.ajax({
			type:"post",
			data:{"app_idear":app_idear},
			url:"${ctx}/admin/leave/appReject?leave_id="+leave_id,
			success:function(){
				alert("审批成功!");
				window.location.href ="/admin/leave/selectAuditLeave";
			},
			error:function(){
				mini.alert("审批失败！");
				return false;
			}
		});
	}
	
}
 /**
  * 同意
  */
  function agree(){
	  var leave_id = $("#leaveId").val();
	  var app_idear = mini.get("app_idear").getText();
		$.ajax({
			type:"post",
			data:{"app_idear":app_idear},
			url:"${ctx}/admin/leave/appAgree?leave_id="+leave_id,
			success:function(){
				alert("审批成功!");
				window.location.href ="/admin/leave/selectAuditLeave";
			},
			error:function(){
				mini.alert("审批失败！");
				return false;
			}
		});	
  }	
</script>
</head>

<body style="overflow: hidden; width: 98%; height: 100%;">
<div style="margin:5px auto;border:1px solid #000;width:60%;border-color: #11A9E2">
	<input type = "hidden" value = "${appLeave.leave_id }" id = "leaveId"/>
	<input type = "hidden" value = "${appLeave.app_user }" id = "leaveName"/>
<table border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;margin:10px 10px;">
    <tr>
        <td style="width:70px;">姓名：</td>
        <td style="width:50%">
            <input  class="mini-textbox" value="${appLeave.user_name }" readonly="readonly" />
        </td>
        <td style="width:70px;">部门：</td>
        <td style="width:50%">
            <input  class="mini-textbox" value="${appLeave.dept_name }" readonly="readonly" />
        </td>
    </tr>
    <tr>
       <td style="width:70px;">类型：</td>
        <td >
            <input class="mini-textbox" value="${appLeave.holiday_name }" readonly="readonly"/>
        </td>
        <td >时长：</td>
        <td >
            <input  class="mini-textbox" value="${appLeave.leave_time }" readonly="readonly" />
        </td>
    </tr>
    <tr>
       <td style="width:70px;">开始时间：</td>
        <td >
            <input class="mini-textbox" value="${appLeave.leave_begin_time }" readonly="readonly"/>
        </td>
        <td style="width:70px;">结束时间：</td>
        <td >
            <input  class="mini-textbox" value="${appLeave.leave_end_time }" readonly="readonly" />
        </td>
    </tr>
    <tr>
        <td >原因：</td>
        <td colspan="3" >
            <input  class="mini-textarea" style="height:180px; width: 662px" value="${appLeave.leave_cause }" readonly="readonly"/>
        </td>        
    </tr>
    <tr>
        <td >审批意见：</td>
        <td colspan="3" >
            <input id="app_idear" class="mini-textarea" style="height:120px; width: 662px" />
        </td>        
    </tr>
    <tr>
        <td >常用意见：</td>
        <td colspan="3" >
            <select id="use_idear" onvaluechanged="onDeptChanged" class="mini-combobox"  style="width: 662px" >
            	<option value = "pass">同意</option>
            	<option value = "no-pass" >不同意</option>
            </select>
        </td>        
    </tr>
</table>
    <div  style="margin:5px auto;text-align: center">
		<a class="mini-button" img="${ctx}/resources/res/images/accept.png" onclick = "agree">同意</a>&nbsp;
	    <a class="mini-button" img="${ctx}/resources/res/images/cancel.png" onclick = "reject">拒绝</a>
    </div>
</div>
</body>
</html>