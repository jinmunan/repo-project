layui.use(['table','layer',"form"],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    //客户列表展示
    var  tableIns = table.render({
        elem: '#customerList',
        url : ctx+'/customer/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'name', title: '客户名',align:"center"},
            {field: 'fr', title: '法人',  align:'center'},
            {field: 'khno', title: '客户编号', align:'center'},
            {field: 'area', title: '地区', align:'center'},
            {field: 'cusManager', title: '客户经理',  align:'center'},
            {field: 'myd', title: '满意度', align:'center'},
            {field: 'level', title: '客户级别', align:'center'},
            {field: 'xyd', title: '信用度', align:'center'},
            {field: 'address', title: '详细地址', align:'center'},
            {field: 'postCode', title: '邮编', align:'center'},
            {field: 'phone', title: '电话', align:'center'},
            {field: 'webSite', title: '网站', align:'center'},
            {field: 'fax', title: '传真', align:'center'},
            {field: 'zczj', title: '注册资金', align:'center'},
            {field: 'yyzzzch', title: '营业执照', align:'center'},
            {field: 'khyh', title: '开户行', align:'center'},
            {field: 'khzh', title: '开户账号', align:'center'},
            {field: 'gsdjh', title: '国税', align:'center'},
            {field: 'dsdjh', title: '地税', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'updateDate', title: '更新时间', align:'center'},
            {title: '操作', templet:'#customerListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });


});
