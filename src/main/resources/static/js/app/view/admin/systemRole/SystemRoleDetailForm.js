Ext.define('app.view.admin.systemRole.SystemRoleDetailForm', {
    extend: 'Ext.form.FormPanel',
    xtype: 'admin_systemRole_SystemRoleDetailForm',
    bodyPadding: 5,
    defaults: {
        labelWidth: 70
    },
    width: 400,
    items: [{
        name: 'recordId',
        xtype: 'hidden',
    }, {
        name: 'roleCode',
        fieldLabel: '角色编号',
        allowBlank: false,
        xtype: 'textfield',
        maxLength: 6,
        enforceMaxLength: true,
        width: 150
    }, {
        name: 'roleName',
        fieldLabel: '角色名称',
        allowBlank: false,
        xtype: 'textfield',
        maxLength: 20,
        enforceMaxLength: true,
        width: 350
    }]
});