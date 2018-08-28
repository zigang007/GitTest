<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>捷途软件--智能办公</title> 
<%@ include file="/WEB-INF/taglib.jsp"%>
<link rel="stylesheet" href="${ctx}/css/face.css">
<link rel="stylesheet" href="${ctx}/resources/easyUI/easyui.css">
<script type="text/javascript" src="${ctx }/resources/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/easyUI/jquery.easyui.min.js"></script>
<style type="text/css">
a:link {color: blue; text-decoration:none;} //未访问：蓝色、无下划线   
a:active:{color: red; } //激活：红色   
a:visited {color:purple;text-decoration:none;} //已访问：purple、无下划线  
a:hover {
	color:blue;
	text-decoration:underline;
} 
#tab { overflow:hidden; width:600px; height:500px; position:relative; float:left;}
#tab>img:not(:first-child){ display:none; }
</style>
<script>
    window.onload = function(){
     
        var images = document.getElementsByTagName('img');
        var pos = 0;
        var len = images.length;
         
        setInterval(function(){
         
            images[pos].style.display = 'none';
            pos = ++pos == len ? 0 : pos;
            images[pos].style.display = 'inline';
         
        },3000);
         
    };
</script>
</head> 
<body>
	<div class="container" >
		<div class = "topPart">
			<marquee width=100% scrollamount=3> 
					<a href="" style = "text-decoration:none;">
						<FONT  color=#ff0000 size=3>
						<STRONG>
								本系统用于测试练习使用，请勿用于生产！
						</STRONG>
						</FONT>
					</a>
					<a href="" style = "text-decoration:none;">
						<FONT color=#ff0000 size=3>
						<STRONG>
								top2
						</STRONG>
						</FONT>
					</a>
			</marquee>
		</div>
		
			<div  class="rightPart">
			<div style="width: 100%;height: 25px;background-color: #11A9E2;" ><span style="color: red;"><strong>新闻区</strong></span></div>
			 <c:forEach items="${news }" var = "news">
			 		<a href = "${ctx}/news/viewNews?news_id=${news.news_id}" >${news.news_title }</a><br>
			 	</c:forEach>
			</div>
			<div class = "centerPart" id="tab">
			 <c:forEach items="${news }" var = "news">
				<a href = "${ctx}/news/viewNews?news_id=${news.news_id}" ><img src="https://t1.huanqiu.cn/9522d642a2c86c6b9835114237b90f6d.jpg" style="border: none; max-width: 100%; display: block; height: auto; margin: 10px auto;"/></a>
				<a href = "${ctx}/news/viewNews?news_id=${news.news_id}" ><img src="https://t1.huanqiu.cn/6f976cd9b3eaae7842e7ceb7247ef6ad.jpg"style="border: none; max-width: 100%; display: block; height: auto; margin: 10px auto;"/></a>
			</c:forEach>
			</div>
			<div class = "rightPart">
			<div style="width: 100%;height: 25px;background-color: #11A9E2;" ><span style="color: red;"><strong>公告区</strong></span></div>
			 <c:forEach items="${notice }" var = "notice">
			 		<a href = "${ctx}/notice/findById?notice_id=${notice.notice_id}" >${notice.notice_title }</a><br>
			 	</c:forEach>
			</div>
		
		<div class = "downPart" >
			<p>系统信息区</p>
		</div>		
	</div>
</body> 
</html>
    