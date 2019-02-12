Ext.define('app.store.admin.ProgramPrivilegeStore', {
    extend: 'app.store.BaseStore',
    model: 'app.model.admin.ProgramPrivilegesModel',
    alias:'widget.app_store_admin_ProgramPrivilegeStore',
    dao:{
        deleteUrl: '/admin/programPrivilege/delete.handler',
        insertUrl: '/admin/programPrivilege/create.handler',
        updateUrl: '/admin/programPrivilege/update.handler',
        selectUrl: '/admin/programPrivilege/getProgramPrivilegeByProgramId.handler',
    }   
});

