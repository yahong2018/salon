Ext.define("app.view.example.tree.department.Department", {
    extend: "app.ux.dbgrid.TreeGrid",
    xtype: "app_view_example_tree_department_Department",
    requires:['app.store.example.DepartmentStore', "app.model.example.DepartmentModel"],
    uses:["app.view.example.tree.department.DepartmentDetailForm"],
    rootVisible: false,        
    columns: [
        {
            xtype: 'treecolumn',
            text: '部门编号',
            dataIndex: 'deptId',
            width:300,
        },
        {
            text: '部门名称',
            dataIndex: 'deptName',
            flex:1,
        }
    ],
    constructor:function(config){
        var configBase = {
           store: Ext.create({xtype:'app_store_example_DepartmentStore'}),
           detailFormClass: 'app_view_example_tree_department_DepartmentDetailForm',
           detailWindowTitle: '部门管理',           
        }
        Ext.applyIf(config,configBase);

        this.callParent(arguments);
    },
});