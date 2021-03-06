<%@ page language="java" pageEncoding="UTF-8"%>

<%--為了避免在jsp頁面中出現java代碼，這裡引入jstl標籤庫，利用jstl標籤庫提供的標籤來做一些邏輯判斷處理 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%   
    String drawPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/draw/index.html";
%>
<!DOCTYPE html>
<html>
<head>
<title>顯示使用者資訊main</title>


<link rel="stylesheet" href="css/jquery.treeview.css" />
<link rel="stylesheet" href="css/screen.css" />

<script src="js/jquery.min.js"></script>
<script src="js/jquery.cookie.js"></script>
<script src="js/jquery.treeview.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#browser").treeview();
	});

</script>
</head>
<body>

	<div id="main">
		<ul id="browser" class="filetree">
			<li><span class="folder">會員管理區</span>
				<ul>
				<c:choose><c:when test="${user.uPosi == 'mg'}">
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/user/mgtInfo/index.do"
							target="showframe">會員管理</a></span></li>
							</c:when></c:choose>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/user/myInfo/index.do"
							target="showframe">個人中心</a></span></li>
				</ul></li>
			<li><span class="folder">HACCP計劃書專案區</span>
				<ul>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/plan/index.do"
							target="showframe">HACCP計劃書專案管理</a></span></li>
				</ul></li>
			<li><span class="folder">五個預備步驟區</span>
				<ul>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/team/index.do"
							target="showframe">食安管制小組</a></span></li>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/ps/index.do"
							target="showframe">產品特性及儲運方式</a></span></li>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/pc/index.do"
							target="showframe">產品用途及消費對象</a></span></li>		
					<li><span class="folder">產品加工流程圖</span>
						<ul id="folder21">
							<li><span class="file"><a href="<%=drawPath %>"
							target="showframe">製作產品加工流程圖</a></span></li>
							<li><span class="file"><a
							href="${pageContext.request.contextPath}/fchart/index.do"
							target="showframe">檔案上傳</a></span></li>
						</ul></li>	
				
				</ul></li>
			<li><span class="folder">七大原則區</span>
				<ul>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/ha/index.do"
							target="showframe">危害工作分析表</a></span></li>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/ccp/index.do"
							target="showframe">重要管制點判定表</a></span></li>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/haccp/index.do"
							target="showframe">HACCP計畫表</a></span></li>
				</ul></li>
			<li><span class="folder">CCP 管制監控區</span>
				<ul>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/monit/index.do"
							target="showframe">管制監控紀錄</a></span></li>					
				</ul></li>
		</ul>

	</div>

	<c:if test="${user!=null}">
            歡迎您：${user.uName}
        <br>          
          <a onclick="if (confirm('確定要退出嗎？')) return true; else return false;" target=_top href="${pageContext.request.contextPath}/toLogout.do" >登出</a>
	</c:if>

</body>
</html>

