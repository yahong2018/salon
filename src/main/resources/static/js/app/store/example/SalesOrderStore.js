Ext.define("app.store.example.SalesOrderStore",{
    extend:"app.store.BaseStore",
    alias:'widget.app_store_example_SalesOrderStore',

    model:"app.model.example.SalesOrderModel",   
     
    dao:{
        deleteUrl: '/example/salesOrder/delete.handler',
        insertUrl: '/example/salesOrder/create.handler',
        updateUrl: '/example/salesOrder/update.handler',
        selectUrl: '/example/salesOrder/getAll.handler',
    },     
});