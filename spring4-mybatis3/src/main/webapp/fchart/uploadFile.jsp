<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传单个文件示例</title>

</head>
<body>
	<div align="center">

		<h1>上传附件</h1>
		<form method="post" action="${pageContext.request.contextPath}/fchart/doUpload.do"
			enctype="multipart/form-data">
			<input type="file" name="file" />
			<button type="submit">提交</button>
		</form>
	</div>
</body>
</html>