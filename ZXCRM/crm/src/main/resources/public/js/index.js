layui.use(['form', 'jquery', 'jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    //layui登录用户表单提交
    form.on("submit(login)", function (data) {
        //获取表单元素 用户名 密码
        data = data.field;
        console.log(data);

        /**
         * 前端校验
         */
        if (data.username == "undefined" || data.username == "undefined" || data.username.trim() == "") {
            layer.msg("用户名为空");
            return false;
        }

        if (data.password == "undefined" || data.password == "undefined" || data.password.trim() == "") {
            layer.msg("密码为空");
            return false;
        }

        /**
         * 发送ajax
         */
        $.ajax({
            type: "POST",
            url: ctx + "/user/login",
            data: {
                username: data.username,
                password: data.password
            },
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    layer.msg("用户登录成功", function () {
                        $.cookie("userIdStr", data.result.userIdStr);
                        $.cookie("username", data.result.username);
                        $.cookie("trueName", data.result.trueName);

                        //记住我功能
                        if ($("input[type='checkbox']").is(":checked")) {
                            $.cookie("userIdStr", data.result.userIdStr, {expires: 7});
                            $.cookie("username", data.result.username, {expires: 7});
                            $.cookie("trueName", data.result.trueName, {expires: 7});
                        }

                        location.href = ctx + "/main";
                    });
                } else {
                    layer.msg(data.msg);
                }
            },
            error: function (xhr) {
                alert(xhr.status)
            }
        });
        return false;
    });
});