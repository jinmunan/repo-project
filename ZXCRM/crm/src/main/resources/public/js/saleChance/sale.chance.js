layui.use(['table', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 加载数据表格
     */
    var tableIns = table.render({
        id: 'saleChanceTable'
        // 容器元素的ID属性值
        , elem: '#saleChanceList'
        // 容器的高度 full-差值
        , height: 'full-125'
        // 单元格最小的宽度
        , cellMinWidth: 95
        // 访问数据的URL（后台的数据接口）
        , url: ctx + '/sale_chance/list'
        // 开启分页
        , page: true
        // 默认每页显示的数量
        , limit: 10
        // 每页页数的可选项
        , limits: [10, 20, 30, 40, 50]
        // 开启头部工具栏
        , toolbar: '#toolbarDemo'
        // 表头
        , cols: [[
            // field：要求field属性值与返回的数据中对应的属性字段名一致
            // title：设置列的标题
            // sort：是否允许排序（默认：false）
            // fixed：固定列
            {type: 'checkbox', fixed: 'center'}
            , {field: 'id', title: '编号', sort: true, fixed: 'left'}
            , {field: 'chanceSource', title: '机会来源', align: 'center'}
            , {field: 'customerName', title: '客户名称', align: 'center'}
            , {field: 'cgjl', title: '成功几率', align: 'center'}
            , {field: 'overview', title: '概要', align: 'center'}
            , {field: 'linkMan', title: '联系人', align: 'center'}
            , {field: 'linkPhone', title: '联系号码', align: 'center'}
            , {field: 'description', title: '描述', align: 'center'}
            , {field: 'createMan', title: '创建人', align: 'center'}
            , {field: 'uname', title: '分配人', align: 'center'}
            , {field: 'assignTime', title: '分配时间', align: 'center'}
            , {field: 'createDate', title: '创建时间', align: 'center'}
            , {
                field: 'state', title: '分配状态', align: 'center', templet: function (d) {
                    // 调用函数，返回格式化的结果
                    return formatState(d.state);
                }
            }
            , {
                field: 'devResult', title: '开发状态', align: 'center', templet: function (d) {
                    // 调用函数，返回格式化的结果
                    return formatDevResult(d.devResult);
                }
            }
            , {title: '操作', templet: '#saleChanceListBar', fixed: 'right', align: 'center', minWidth: 150}
        ]]
    });

    /**
     * 格式化分配状态值
     *  0 = 未分配
     *  1 = 已分配
     *  其他 = 未知
     * @param state
     */
    function formatState(state) {
        if (state == 0) {
            return "<div style='color: yellow'>未分配</div>";
        } else if (state == 1) {
            return "<div style='color: green'>已分配</div>";
        } else {
            return "<div style='color: red'>未知</div>";
        }
    }

    /**
     * 格式化开发状态
     *  0 = 未开发
     *  1 = 开发中
     *  2 = 开发成功
     *  3 = 开发失败
     *  其他 = 未知
     * @param devResult
     */
    function formatDevResult(devResult) {
        if (devResult == 0) {
            return "<div style='color: yellow'>未开发</div>";
        } else if (devResult == 1) {
            return "<div style='color: orange'>开发中</div>";
        } else if (devResult == 2) {
            return "<div style='color: green'>开发成功</div>";
        } else if (devResult == 3) {
            return "<div style='color: red'>开发失败</div>";
        } else {
            return "<div style='color: blue'>未知</div>";
        }
    }

    /**
     * 搜索按钮的点击事件
     */
    $(".search_btn").click(function () {

        /**
         * 表格重载
         *  多条件查询
         */
        tableIns.reload({
            // 设置需要传递给后端的参数
            where: { //设定异步数据接口的额外参数，任意设
                // 通过文本框/下拉框的值，设置传递的参数
                customerName: $("[name='customerName']").val() // 客户名称
                , createMan: $("[name='createMan']").val() // 创建人
                , state: $("#state").val() // 状态
            }
            , page: {
                curr: 1 // 重新从第 1 页开始
            }
        });
    });


    /**
     * 增加和删除监听事件
     */
    table.on('toolbar(saleChances)', function (obj) {
        switch (obj.event) {
            case "add":
                openAddUpdateSaleChanceDialog();
                break;
        }
    });


    /**
     * 更新 行监听
     */
    table.on('tool(saleChances)', function (obj) {
        var layEvent = obj.event;
        if (layEvent === "edit") {
            openAddUpdateSaleChanceDialog(obj.data.id);
        }
    });

    /**
     * 打开添加或更新对话框
     */
    function openAddUpdateSaleChanceDialog(id) {
        var title = "营销机会管理-机会添加";
        var url = ctx + "/sale_chance/addOrUpdateSaleChancePage";
        if (id) {
            title = "营销机会管理-机会更新";
            url = url + "?id=" + id;
        }
        layui.layer.open({
            title: title,
            type: 2,
            area: ["700px", "500px"],
            maxmin: true,
            content: url
        });
    }


});