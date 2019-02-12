Ext.define("app.store.example.SalesOrderItemStore",{
    extend:"app.store.BaseStore",
    alias:'widget.app_store_example_SalesOrderItemStore',

    model:"app.model.example.SalesOrderItemModel",
    dao:{
        deleteUrl: '/example/salesOrderItem/delete.handler',
        insertUrl: '/example/salesOrderItem/create.handler',
        updateUrl: '/example/salesOrderItem/update.handler',
        selectUrl: '/example/salesOrderItem/getAll.handler',        
    }
});