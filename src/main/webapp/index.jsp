<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>短连接生成服务器</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container" style="text-align: center">
        <h1 style="margin-top: 50px;">在线生成短链接</h1>
        <form method="post" action="/longLink" style="margin-top: 20px">
            <div class="form-row">
                <input id="link-input" type="text" class="form-control offset-md-3 col-md-6" placeholder="输入长链接 如https://www.baidu.com">
                <button type="button" class="btn btn-primary" style="margin-left: 20px" onclick="submitLink()">生成短链接</button>
            </div>
        </form>
        <h3 style="margin-top: 30px">生成的短链接:</h3>
        <a id="ans" href=""></a>
    </div>
    <footer class="footer" style="position: absolute; margin-bottom: 50px; bottom: 0; height: 50px; width: 100%; text-align: center">
        Powered by <a href="https://github.com/dzzhyk/mine-spring">mine-spring</a>
    </footer>
</body>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function submitLink() {
        var $ans = $('#ans');
        $.ajax({
            url: "/longLink",
            method: "post",
            dataType: "json",
            data: {
                "link": $('#link-input').val()
            },
            success: function (result) {
                console.log(result.data);
                if (result.code === 1){
                    $ans.attr("href", result.data);
                    $ans.text(result.data);
                }else{
                    $ans.attr("href", "");
                    $ans.text("生成失败，请检查长链接是否合法");
                }
            },
            error: function (result) {
                $ans.attr("href", "");
                $ans.text("生成失败，请检查长链接是否合法");
            }
        })
    }
</script>
</html>
