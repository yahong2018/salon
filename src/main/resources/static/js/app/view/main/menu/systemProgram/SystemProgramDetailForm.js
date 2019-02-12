var statusStoreSex = Ext.create("Ext.data.Store", {
    fields: ["Name", "Value"],
    data: [
    	{ Name: "启用", Value: 0 },
        { Name: "停用", Value: 1 },
    ]
});


Ext.define('app.view.main.menu.systemProgram.SystemProgramDetailForm', {
    extend: 'Ext.form.FormPanel',
    xtype: 'app_view_main_menu_systemProgram_SystemProgramDetailForm',
    bodyPadding: 5,
    layout: "anchor",
    defaults: {
        labelWidth: 90,
        anchor: "100%",
    },
    width: 400,
    items: [
        {
            name: 'parent',
            xtype: "textfield",
            readOnly: true,
            fieldLabel: "上级菜单编号"
        },{
            id:"parentName",
            xtype:"textfield",
            readOnly:true,            
            fieldLabel:"上级菜单名称"
        },
        {
            name: 'programId',
            fieldLabel: '菜单编号',
            allowBlank: false,
            xtype: 'textfield',
            maxLength: 50,
            enforceMaxLength: true,
        }, {
            name: 'programName',
            fieldLabel: '菜单名称	',
            allowBlank: false,
            xtype: 'textfield',
            maxLength: 120,
            enforceMaxLength: true,
        },{
            name: 'url',
            fieldLabel: '菜单路径',
            allowBlank: true,
            xtype: 'textfield',
            maxLength: 255,
            enforceMaxLength: true,
        },{
            name: 'showOrder',
            fieldLabel: '排序',
            allowBlank: false,
            xtype: 'numberfield',
            maxLength:22,
            enforceMaxLength: true,
            minValue:0
        },{
            name: 'parameters',
            fieldLabel: '参数',
            allowBlank: true,
            xtype: 'textfield',
            maxLength: 255,
            enforceMaxLength: true,
        },{
        	name: 'status', 
        	fieldLabel: '状态',
        	allowBlank: false, 
        	xtype: 'combobox', 
        	maxLength:2,  
        	enforceMaxLength:true, 
        	store: statusStoreSex,
        	editable: false,
        	displayField: "Name",
        	valueField: "Value",
        	queryMode: "local", flex:1
        },{
            name: 'glyph',
            fieldLabel: '图标',
            allowBlank: true,
            xtype: 'textfield',
            maxLength: 100,
            enforceMaxLength: true,
        }
    ],
    beforeLoadRecord: function (config) {
        var grid = this.up("detailwindow").listGrid; //获取到Grid
        if(grid.getSelectedRecord() != null){
      
        	var currentRecord = grid.getSelectedRecord();//获取到当前的记录
        	var txtProgramName = this.down('[id="parentName"]');
	
        	 this.getForm().findField("parent").readOnly = true;
        	 this.getForm().findField("parentName").readOnly = true;
	        if (config.isNew === true) { //如果是新建
	            config.record.set("parent", currentRecord.get("programId")); //修改parentDeptId
	            txtProgramName.setValue (currentRecord.get("programName"));
	        }else{
	            var index = grid.store.find("programId",currentRecord.get("parent"));
	            var parent = grid.store.getAt(index);
	            txtProgramName.setValue (parent.get("programName"));
	        }
        }
    },
});