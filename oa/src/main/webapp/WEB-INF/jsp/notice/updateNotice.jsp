<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>捷途软件--智能办公--公告发布</title> 
<%@ include file="/WEB-INF/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/resources/miniUI/boot.js"></script>
<script src="${ctx}/resources/fileupload/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
/* function onDrawDate(e) {
	    var date = e.date;
	    var d = new Date();
	
	    if (date.getTime() < d.getTime()) {
	        e.allowSelect = false;
	    }
	}
	 */

</script>
</head>
<body>
<div class="container" style = "width: 80%;margin: 20px auto;padding: 10px;">
		 <h4>编辑公告内容：</h4>
		 <form  id = "noticeForm"   method="post">
		 <input type="hidden" name="notice_id" value="${notice.notice_id }">
		<table border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
		    <tr>
		        <td width="80px;"><span style="color:red;">*</span>公告类型：</td>
		        <td>
		            <select class = "mini-combobox" id="notice_type" name="notice_type" >
		            		<option value = "public" <c:if test="${notice.notice_type=='public' }">selected="selected"</c:if> >公共业务</option>
		            		<option value = "myself" <c:if test="${notice.notice_type=='myself' }">selected="selected"</c:if> >个人业务</option>
		            		<option value = "company" <c:if test="${notice.notice_type=='company' }">selected="selected"</c:if> >公司业务</option>
		            </select>
		        </td>
		    </tr>
		   
		    <tr>
		       <td><label><span style="color:red;">*</span>开始时间:</label></td>
				<td colspan="3">
					<input  type = "text" id = "beginTime" name="beginTime" class="mini-datepicker"   required="true" format="yyyy-MM-dd" value = "${notice.begin_time }"/>
      			<label>&nbsp;&nbsp;<span style="color:red;">*</span>结束时间:</label>
      			<input type = "text"  id = "endTime" name="endTime" class="mini-datepicker"   required="true" format="yyyy-MM-dd"  value = "${notice.end_time }"/>
				</td>
		    </tr>
		    <tr>
		    	<td><label><span style="color:red;">*</span>标题:</label></td>
		    	<td colspan="3"><input id="notice_title" width="100%" name = "notice_title" class="mini-textbox" emptyText="请输入标题" required="true" value = "${notice.notice_title }" /></td>
		    </tr>
		    <tr>
		    	<td><label><span style="color:red;">*</span>内容:</label></td>
		    	<td colspan="3"><textarea name="notice_content" id = "notice_content" style="width: 100%;height: 120px" class="mini-textarea" emptyText="请输入内容" required="true" >${notice.notice_content }</textarea></td>
		    </tr>
		     <tr>
		    	<td><label>上传附件:</label></td>
		    	<td colspan="3">
					 <%--  <div id="multiupload1" enctype="multipart/form-data"
					   class="uc-multiupload" style="width: 100%; height: 160px" 
				        flashurl="${ctx}/resources/fileupload/swfupload/swfupload.swf"
				        uploadurl="${ctx }/oa/fileUpload" _autoUpload="true" limitSize="30M"
       					 onuploaderror="onUploadError" onuploadsuccess="onUploadSuccess">
       					 
				    </div> --%>
				    <input class="mini-htmlfile" name="Fdata"  id="file1" style="width:300px;" />
				    <input type = "hidden" name = "uploadFileId" id = "fileId"  />
				    <a class="mini-button"  onclick="ajaxFileUpload()" >上传</a>
		    	</td>
		    </tr>
		    <tr>
		    	<td><label>附件:</label></td>
		    	<td colspan="3">
		    	<c:forEach items="${files }" var = "f">
		    		<div id="fileDiv" style="display: block;">
		    			<a href ="#" onclick = "fileDownload('${f.file_UUID_name}')" >${f.file_name}</a>&nbsp;&nbsp;<a href="#" onclick="deleteFile('${f.id}')" >删除</a>
		    	  	</div>
		    	  	</c:forEach>
				</td>
		    </tr>
		    <tr>
		    	<td colspan="4">
		    		<a class="mini-button"   onclick = "uploadNotice()" style = "float: right;">修改</a>
		    	</td>
		    </tr>
		</table>
		</form>
	</div>
	
	 <script type="text/javascript">
	 function ajaxFileUpload() {
	        var inputFile = $("#file1 > input:file")[0];
	        $.ajaxFileUpload({
	            url: '${ctx}/oa/fileUpload',                 //用于文件上传的服务器端请求地址
	            fileElementId: inputFile,               //文件上传域的ID
	            //data: { a: 1, b: true },            //附加的额外参数
	            dataType: 'text',                   //返回值类型 一般设置为json
	            success: function (data, status)    //服务器成功响应处理函数
	            {
	            	var jsonData= JSON.parse($(data).text());
	            	$("#fileId").val(jsonData.fileId);
	                alert("上传成功! ");

	            },
	            error: function (data, status, e)   //服务器响应失败处理函数
	            {
	                alert(e);
	            },
	            complete: function () {
	                var jq = $("#file1 > input:file");
	                jq.before(inputFile);
	                jq.remove();
	            }
	        });
	    }
	
	 function uploadNotice(){
	            var form = new mini.Form("#noticeForm");
	            form.validate();
	            if (form.isValid() == false) return;

	            //提交数据
	            $.ajax({
	            	 type:'post',
	                 dataType:'text',
	                 data:$("#noticeForm").serializeArray(),
	                 url: "${ctx}/notice/addNotice",
	                success: function(result) {
	                    alert("公告修改成功！");
	                    
	                },
	                error:function(result){
	                	 alert("很遗憾，公告修改失败！");
	                }
	            });
	 }
	 
	 function deleteFile(file_id){
		  $.ajax({
         	 type:'post',
              dataType:'text',
              url: "${ctx}/notice/deleteFile?file_id="+file_id,
             success: function(result) {
                $("#fileDiv").remove();
                 
             },
             error:function(result){
             	alert(result);
             }
         });
	 }
	 function fileDownload(fileName,filePath){
		 alert(filePath);
		 $.post('${ctx}/oa/fileDownload',{'fileName':fileName});
	 }
    </script>
</body>
</html>