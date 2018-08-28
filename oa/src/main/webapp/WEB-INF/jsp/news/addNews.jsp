<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>捷途软件--智能办公--新闻发布</title> 
<%@ include file="/WEB-INF/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/resources/miniUI/boot.js"></script>
<script src="${ctx}/resources/fileupload/js/ajaxfileupload.js" type="text/javascript"></script>
 <link href="http://ueditor.baidu.com/umeditor/themes/default/css/umeditor.min.css" rel="stylesheet" type="text/css" />    
    <script src="http://ueditor.baidu.com/umeditor/third-party/template.min.js" type="text/javascript"></script>
    <script src="http://ueditor.baidu.com/umeditor/umeditor.config.js" type="text/javascript"></script>
    <script src="http://ueditor.baidu.com/umeditor/umeditor.min.js" type="text/javascript"></script>

</head>
<body>


	<div class="container" style = "width: 80%;margin: 20px auto;padding: 20px;">
		 <h4>填写新闻内容：</h4>
		 <form  id = "form1"   method="post">
		<table border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
		   
		    <tr>
		    	<td width="60px;"><label><span style="color:red;">*</span>标题:</label></td>
		    	<td colspan="3">
		    	<input  class="mini-textbox"  width="100%" id="news_title" name = "news_title"  emptyText="请输入标题" required="true"  />
		    	</td>
		    </tr>
		    <tr>
		    	<td><label><span style="color:red;">*</span>内容:</label></td>
		    	<td colspan="3">
					<div type="text/plain" id="myEditor" style="height:300px;width: 100%" required="true">
	                </div>
				</td>
		    </tr>
		    <tr>
		    	<td colspan="4">
		    		<a class="mini-button"   onclick = "save()" img="${ctx}/resources/res/images/add.png" style = "float: right;">发布新闻</a>
		    	</td>
		    </tr>
		</table>
		</form>
	</div>
	<script type="text/javascript">
mini.parse();
var um = UM.getEditor('myEditor');



function getContent() {
    var arr = um.getContent();
    alert(arr);
};

function setDisabled() {

    um.setDisabled('fullscreen');

};
function setEnabled() {
    um.setEnabled();
}

function save(){
	 var contents = um.getContent();
	 var news_title =$("input[name='news_title']").val();
	 var form = new mini.Form("#form1");
      form.validate();
      if (form.isValid() == false) return;
	 //提交数据
     $.ajax({
     	 type:'post',
          dataType:'text',
          data:{"contents":contents,"news_title":news_title},
          url: "${ctx}/news/addNews",
         contentType:"application/x-www-form-urlencoded;charset=UTF-8", 
         success: function(result) {
             alert("新闻发布成功！");
             window.location.reload();
             
         },
         error:function(result){
         	alert("新闻发布失败！");
         }
     });
}

</script>
	
</body>
</html>