Ext.define('app.view.admin.systemRole.SystemRole', {
    extend: 'Ext.panel.Panel',
    xtype: 'app_view_admin_systemRole_SystemRole',
    requires: ['app.view.admin.systemRole.SystemRoleController', 'app.view.admin.systemRole.SystemRoleViewModel',
        'app.view.admin.systemRole.region.SystemRoleGrid', 'app.view.admin.systemRole.region.RolePrivilegePanel',
        'app.store.admin.SystemRoleStore', 'app.model.admin.SystemRoleModel', 'Ext.form.Panel'
    ],
    controller: {
        type: 'SystemRoleController'
    },
    viewModel: {
        type: 'SystemRoleViewModel'
    },
    layout: 'fit',
    items: [
        {
            xtype: 'panel',
            frame: false,
            layout: 'border',
            items: [
                {
                    region: 'west',
                    xtype: 'SystemRoleGrid',
                    width: 400,
                }, {
                    region: 'center',
                    xtype: 'RolePrivilegePanel'
                }
            ]
        }
    ]
});