layui.use(['form', 'jquery', 'jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 更新密码
     */
    form.on("submit(saveBtn)", function (data) {
        data = data.field;
        /**
         * 发送ajax
         */
        $.ajax({
            type: "POST",
            url: ctx + "/user/update_password",
            data: {
                oldPassword: data.old_password,
                newPassword: data.new_password,
                confirmPassword: data.again_password
            },
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    layer.msg("密码修改成功,系统将自动退出系统", function () {
                        //清除cookie
                        $.removeCookie("userIdStr", {domain: "localhost", path: "/crm"})
                        $.removeCookie("username", {domain: "localhost", path: "/crm"})
                        $.removeCookie("trueName", {domain: "localhost", path: "/crm"})
                        setTimeout(function () {
                            parent.location.href = ctx + "/index";
                        }, 3000);
                    });
                } else {
                    layer.msg(data.msg);
                }
            },
            error: function (xhr) {
                alert(xhr.status)
            }
        });
    });
});