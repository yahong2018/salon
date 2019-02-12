Ext.define('app.store.admin.SystemProgramStore',{
    extend:"app.store.BaseTreeStore",
    model: 'app.model.admin.SystemProgramModel',
    alias:'widget.app_store_admin_SystemProgramStore',
    dao:{
        deleteUrl: '/mainPage/delete.handler',
        insertUrl: '/mainPage/create.handler',
        updateUrl: '/mainPage/update.handler',
        selectUrl: '/mainPage/getAllMenu.handler',
        getAllByPageUrl: '/mainPage/getAllByPage.handler',
    }
});