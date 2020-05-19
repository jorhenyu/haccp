<%@ page language="java" pageEncoding="UTF-8"%>

<%--為了避免在jsp頁面中出現java代碼，這裡引入jstl標籤庫，利用jstl標籤庫提供的標籤來做一些邏輯判斷處理 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	function doLogout() {
		//訪問LogoutServlet註銷當前登錄的用戶
		window.location.href = "${pageContext.request.contextPath}/servlet/LogoutServlet";
	}
</script>
</head>
<body>

	<div id="main">
		<ul id="browser" class="filetree">
			<li><span class="folder">會員管理</span>
				<ul>
					<!--  <li><span class="file"><a
							href="${pageContext.request.contextPath}/user/mgtInfo/index.do"
							target="showframe">會員管理</a></span></li>-->
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/user/myInfo/index.do"
							target="showframe">個人中心</a></span></li>
				</ul></li>
			<li><span class="folder">HACCP模組</span>
				<ul>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/process/index.do"
							target="showframe">加工製程步驟</a></span>
					</li>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/user/index.do"
							target="showframe">危害工作分析表</a></span>
					</li>
					<li><span class="file"><a
							href="${pageContext.request.contextPath}/user/index.do"
							target="showframe">管制監控紀錄(CCP)程序</a></span>
					</li>					
										<li><span class="file"><a
							href="${pageContext.request.contextPath}/user/index.do"
							target="showframe">HACCP計畫表</a></span>
					</li>
				</ul>
			</li>
			<!-- 
			<li><span class="folder">folder</span>
				<ul>
					<li><span class="folder">Subfolder 2.1</span>
						<ul id="folder21">
							<li><span class="file">File 2.1.1</span></li>
							<li><span class="file">File 2.1.2</span></li>
						</ul></li>
					<li><span class="file">File 2.2</span></li>
				</ul></li>
			<li class="closed"><span class="folder">Folder 3 (closed
					at start)</span>
				<ul>
					<li><span class="file">File 3.1</span></li>
				</ul></li>
			<li><span class="file">File 4</span></li>
			 -->
		</ul>

	</div>	

	<c:if test="${user!=null}">
            歡迎您：${user.userName}
            <input type="button" value="退出登入" onclick="doLogout()">
	</c:if>	

</body>
</html>

