layui.use(['table', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    /**
     * 添加或更新操作
     */
    form.on('submit(addOrUpdateSaleChance)', function (data) {
        var index = top.layer.msg("数据提交中,请稍后...", {icon: 16, time: false, shade: 0.8});
        var url = ctx + "/sale_chance/save";
        if ($("input[name='id']").val()){
            url = ctx + "/sale_chance/update";
        }
        $.post(url, data.field, function (result) {
            if (result.code == 200) {
                top.layer.msg("操作成功");
                top.layer.close(index);
                layer.closeAll("iframe");//关闭弹出层
                parent.location.reload();//刷新父页面
            } else {
                layer.msg(result.msg)
            }
        });
        //禁用表单自动提交
        return false;
    });
});