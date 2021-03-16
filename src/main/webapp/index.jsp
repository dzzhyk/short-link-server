<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>短连接生成服务器</title>
</head>
<body>
    <form method="post" action="/longLink">
        <input type="text" value="输入长链接" name="link">
        <button type="submit">转换短连接</button>
    </form>
    <h3>你的短链接为:</h3>
    <a href="${shortLink}">${shortLink}</a>
</body>
</html>
