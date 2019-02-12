Ext.define('app.view.main.menu.programPrivilege.ProgramPrivilegeDetailForm', {
    extend: 'Ext.form.FormPanel',
    xtype: 'app_view_main_menu_programPrivilege_ProgramPrivilegeDetailForm',
    bodyPadding: 5,
    hideDefeaultPagebar: true,
    
    fieldDefaults: {
        labelAlign: 'right',
        labelWidth: 90,
        msgTarget: Ext.supports.Touch ? 'side' : 'qtip'
    },
    width: 500,
    height: 500,
    minWidth: 345,
    minHeight: 345,
   // layout: 'fit',
    plain: true,
    
    items: [
    {
        name: 'programPrivilegeId',
        fieldLabel: '编号',
        xtype: 'textfield',
        maxLength: 6,
        readOnly: true,
        enforceMaxLength: true,
        flex: 1
    }, {
        name: 'programId',
        id: 'programId',
        fieldLabel: '菜单编号',
        allowBlank: false,
        xtype: 'textfield',
        maxLength: 20,
        readOnly: true,
        enforceMaxLength: true,
        flex: 1
    }, {
        name: 'privilegeId',
        fieldLabel: '按钮代码',
        allowBlank: false,
        xtype: 'textfield',
        maxLength: 20,
        enforceMaxLength: true,
        flex: 1
    }, {
        name: 'privilegeName',
        fieldLabel: '按钮名称',
        allowBlank: false,
        xtype: 'textfield',
        maxLength: 20,
        enforceMaxLength: true,
        flex: 1
    }],
    beforeLoadRecord:function(config){
	        if (config.isNew) { //如果是新建
	        	 config.record.set("programId", programId);
	        	 //this.getForm().findField("programId").setValue(programId);
	        	 config.record.set("programPrivilegeId", 0);
	        	 this.getForm().findField("programPrivilegeId").readOnly = true;
	        	 this.getForm().findField("programId").readOnly = true;
	        }else{
	        	this.getForm().findField("programPrivilegeId").readOnly = true;
	        	this.getForm().findField("programId").readOnly = true;
	        	this.getForm().findField("privilegeId").readOnly = true;
	        }
    },
    listeners: {
        afterrender: function () {
              this.getForm().findField("programPrivilegeId").readOnly = true;
	          this.getForm().findField("programId").readOnly = true;
	          this.getForm().findField("privilegeId").readOnly = true;
           },
       },
});