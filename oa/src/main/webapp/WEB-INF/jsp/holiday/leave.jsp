<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>OA办公管理系统-用户请假</title>
<%@ include file="/WEB-INF/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<script type="text/javascript" src="${ctx}/resources/miniUI/boot.js"></script>
<script type="text/javascript" src="${ctx}/resources/miniUI/ColumnsMenu.js"></script>
<script type="text/javascript">
var showTip = function(tip){
	  mini.parse();
     
      
	 $.messager.show({
			title:'请假状态',
			msg:"<span style='color:red;'>"+tip+"</span>",
			showType:'slide'
	});
}
/** 文档加载完成*/
$(function(){
	 if("${tip}"){
		 $.messager.show({
				title:'请假状态',
				msg:"<span style='color:red;'>${tip}</span>",
				showType:'slide'
			});
	 }
	 if("${action}" =="show"){
		 $(".mini-button").hide(); 
		 var leave_status = $("#leave_status").val();
		 /* >1是已经做了审批 */
		 if(leave_status >1){
			 $("#table_his").attr("style","display:block;");
		 }
		
		
		 
	 }else{
		 $(".mini-button").show(); 
	 }
});
     
//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}
function onDrawDate(e) {
    var date = e.date;
    var d = new Date();

    if (date.getTime() < d.getTime()) {
        e.allowSelect = false;
    }
}


/** 存为草稿*/
function btn_submit_cg(){
	 var form = new mini.Form("addLeaveForm");
	 var o = form.getData();            
     form.validate();
     
     if (form.isValid() == false) return;
   
     var startTime = o.begin_time;
     var endTime =o.end_time;
     if(endTime<startTime){
    	 alert("结束时间不能大于开始时间！");
    	 return;
     }
	$.ajax({
			url : "${ctx}/admin/leave/addLeaveTo_cg?leave_status=0",
    		type : "post",
    		dataType : "text",
            data: $("#addLeaveForm").serialize(),
    		async : false,
    		success : function(data){
    			alert("存为草稿成功！");
    			var user_id ="${sessionScope.user_session.userId}";
    			var leaveId= $("#leave_id").val();
    			if(leaveId !=""){
    				
    			}else{
    				window.location.href="${ctx}/admin/leave/myLeave?user_id="+user_id;
    			}
    			
    		},error : function(){
    			alert("存为草稿失败！");
    			return;
    		}
			
		});
}

/** 提交*/
function btn_submit_tj(){
	 var form = new mini.Form("addLeaveForm");
	 var o = form.getData();            
     form.validate();
     if (form.isValid() == false) return;
     var startTime = o.begin_time;
     var endTime =o.end_time;
     if(endTime<startTime){
    	 alert("结束时间不能大于开始时间！");
    	 return;
     }
	$.ajax({
			url : "${ctx}/admin/leave/addLeaveTo_cg?leave_status=1",
    		type : "post",
    		dataType : "text",
            data: $("#addLeaveForm").serialize(),
    		async : false,
    		success : function(data){
    			alert("提交成功,请等待领导审批！");
    			var user_id ="${sessionScope.user_session.userId}";
    			var leaveId= $("#leave_id").val();
    			if(leaveId !=""){
    				
    			}else{
    				window.location.href="${ctx}/admin/leave/myLeave?user_id="+user_id;
    			}
    		},error : function(){
    			alert("提交失败");
    			return;
    		}
			
		});
}


</script>
</head>
<body style="background: #F5FAFA;">
	<center>
		<form id="addLeaveForm" 
			method="post">
			<input type = "hidden" name="user_id" id = "user_id" value = "${sessionScope.user_session.userId}">
			<input type ="hidden" name = "leave_id" id ="leave_id" value="${leave.LEAVE_ID }" />
			<input type ="hidden" name = "leave_status" id ="leave_status" value="${leave.leave_status }" />
			<table class="table-condensed">
				<tbody>
					<tr>
						<td><label>请假用户:</label></td>
						<td><input type="text" id="code" readonly="readonly" name="code"
							value="${sessionScope.user_session.name}"  class="mini-textbox" style="width: 100%" ></td>
						<td><label>请假类型:</label></td>
						<td><input name="leave_type" class="mini-combobox" style="width:100%" valueField="code" textField="name" value = "${leave.leave_type }"
                            url="${ctx}/admin/leave/findAllHoliday" required="true"
                             emptyText="请选择请假类型"/></td>	
					</tr>
					<tr>
						<td><label>请假开始时间:</label></td>
						<td>
							<input  type = "text" id = "beginTime" name="begin_time" class="mini-datepicker"  allowInput="false" showTodayButton="false" ondrawdate="onDrawDate" style="width:200px;"  required="true"
        							format="yyyy-MM-dd H:mm" timeFormat="H:mm" showTime="true" showOkButton="true" showClearButton="false" value = "${leave.leave_begin_time }"/>
						</td>
						<td><label>请假结束时间:</label></td>
						<td>
							<input type = "text"  id = "endTime" name="end_time" class="mini-datepicker"  allowInput="false" showTodayButton="false" ondrawdate="onDrawDate" style="width:200px;"  required="true"
        							format="yyyy-MM-dd H:mm" timeFormat="H:mm" showTime="true" showOkButton="true" showClearButton="false" value = "${leave.leave_end_time }"/>
						</td>
					</tr>
					<tr>
					<td><label>请假原因:</label></td>
						<td colspan="3">
							<textarea id="leave_cause"  required="true" name="leave_cause" class="mini-textarea" style="width: 100%;height: 120px">${leave.leave_cause }</textarea>
						</td>
					</tr>
					
					<tr id="choseAppUser">
					<td><label>请选择审批人:</label></td>
						<td colspan="3">
							<input name="app_user" id = "app_user" class="mini-combobox" required="true" style="width:100%" valueField="userId" textField="name" value="${leave.app_user }"
                            url="${ctx}/admin/leave/findAllUsers" required="true"
                             emptyText="请选择请审批人员"/>
						</td>
					</tr>
				</tbody>
			</table>
			<div align="center" style="margin-top: 20px;">
				<a  onclick="btn_submit_cg()" class="mini-button">存为草稿</a>
				<a onclick="btn_submit_tj()" class="mini-button">提交</a>
			</div>
		</form>
		<table id="table_his" border="1px solid #ccc" style="display: none;" >
				<thead style="background-color: #E3E3E3">
					<tr>
						<th width="8%">序号</th>
						<th width="10%">审批人</th>
						<th width="58%">审批意见</th>
						<th  width="30%">审批时间</th>
					</tr>
				</thead>
				<c:forEach items="${hisList }" var="his" varStatus="status">
					<tr>
					    <td>${status.index+1 }</td>
					    <td>${his.app_user_name }</td>
					    <td>${his.app_idear }</td>
					    <td>${his.app_time}</td>
				   </tr>
				</c:forEach> 
	</table>
	
	</center>
	

</body>
</html>