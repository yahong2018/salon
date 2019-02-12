Ext.define('app.view.example.tree.department.DepartmentDetailForm', {
    extend: 'Ext.form.FormPanel',
    xtype: 'app_view_example_tree_department_DepartmentDetailForm',
    bodyPadding: 5,
    layout: "anchor",
    defaults: {
        labelWidth: 90,
        anchor: "100%",
    },
    width: 400,
    items: [
        {
            name: 'parentDeptId',
            xtype: "textfield",
            readOnly: false,
            fieldLabel: "上级部门编号"
        },{
            id:"parentDeptName",
            xtype:"textfield",
            readOnly:false,            
            fieldLabel:"上级部门名称"
        },
        {
            name: 'deptId',
            fieldLabel: '部门编号',
            allowBlank: false,
            xtype: 'textfield',
            maxLength: 20,
            enforceMaxLength: true,
        }, {
            name: 'deptName',
            fieldLabel: '部门名称',
            allowBlank: false,
            xtype: 'textfield',
            maxLength: 50,
            enforceMaxLength: true,
        },
        {
            name: 'remark',
            fieldLabel: '备注',
            xtype: "textarea",
            maxLength: 200,
            enforceMaxLength: true,
        }
    ],
    beforeLoadRecord: function (config) {
        debugger;
        var grid = this.up("detailwindow").listGrid; //获取到Grid
        if(grid.getSelectedRecord() != null){
      
        	var currentRecord = grid.getSelectedRecord();//获取到当前的记录
        	var txtDeptName = this.down('[id="parentDeptName"]');
	
	        if (config.isNew === true) { //如果是新建
	            config.record.set("parentDeptId", currentRecord.get("deptId")); //修改parentDeptId
	            txtDeptName.setValue (currentRecord.get("deptName"));
	        }else{
	            var index = grid.store.find("deptId",currentRecord.get("parentDeptId"));
	            var parent = grid.store.getAt(index);
	            txtDeptName.setValue (parent.get("deptName"));
	        }
        }
    }
});