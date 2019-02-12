Ext.define('app.view.admin.systemRole.region.SystemRoleGrid', {
    extend: 'app.ux.dbgrid.DbGrid',
    alias: 'widget.SystemRoleGrid',
    requires: ['app.store.admin.SystemRoleStore', 'app.model.admin.SystemRoleModel'],
    uses:['app.view.admin.systemRole.SystemRoleDetailForm'],
    columns: [
        { dataIndex: 'roleCode', text: '角色编号', width: 100 },
        { dataIndex: 'roleName', text: '角色名称', flex: 1 }
    ],
    hideDefeaultPagebar: true,
    hideSearchBar:true,
    
    detailFormClass:'admin_systemRole_SystemRoleDetailForm',
    detailWindowTitle: '系统角色',
    constructor: function (config) {
        var configBase = {
            store: Ext.create({ xtype: 'app_store_admin_SystemRoleStore' ,grid:this, listeners: {
                load: function () {                
                    if (this.getCount() > 0 && !this.grid.dataProcessed) {
                        this.grid.dataProcessed = true;
                        this.grid.getSelectionModel().select(0);
                    }
                }
            }})
        };

        Ext.applyIf(config, configBase);
        this.callParent(arguments);
    },
    listeners: {
        beforeselect: 'gridSelectionChanged',
    }
});