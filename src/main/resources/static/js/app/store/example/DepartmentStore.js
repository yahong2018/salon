Ext.define("app.store.example.DepartmentStore",{
    extend:"app.store.BaseTreeStore",
    model: 'app.model.example.DepartmentModel',
    alias:'widget.app_store_example_DepartmentStore',
     
    dao:{
        deleteUrl: '/example/department/delete.handler',
        insertUrl: '/example/department/create.handler',
        updateUrl: '/example/department/update.handler',
        selectUrl: '/example/department/getAll.handler',
    },     
});