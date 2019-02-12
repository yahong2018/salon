Ext.define("app.view.example.masterDetail.salesOrder.SalesOrderGrid", {
    extend: "app.ux.dbgrid.DbGrid",
    xtype: "app_view_example_masterDetail_salesOrder_SalesOrderGrid",
    uses: ["app.store.example.SalesOrderStore", "app.model.example.SalesOrderModel",
        "app.store.example.SalesOrderItemStore", "app.model.example.SalesOrderItemModel",
        "app.view.example.masterDetail.salesOrder.salesItem.SalesItemGrid" , 
        "app.view.example.masterDetail.salesOrder.SalesOrderDetailForm",
    ],
    columns: [
        {
            text: "订单编号",
            dataIndex:"orderId",
            width: 200,
        }, {
            text: "客户编号",
            dataIndex:"customerId",
            width: 200
        }, {
            text: "客户名称",
            dataIndex:"customerName",
            width: 250
        }, {
            text: "下单日期",
            dataIndex:"orderDate",
            width: 200
        }, {
            text: "销售员",
            dataIndex:"salesUser",
            width: 200
        }
    ],
    constructor: function (config) {
        var configBase = {
            store: Ext.create({ xtype: 'app_store_example_SalesOrderStore'}),
            detailFormClass: 'app_view_example_masterDetail_salesOrder_SalesOrderDetailForm',
            detailWindowTitle: '销售订单',
        }
        Ext.applyIf(config, configBase);

        this.callParent(arguments);
    },
    listeners: {       
        beforeselect: function (model, selected, index) {
          //  debugger;
            var detailGrid = this.up('app_view_example_masterDetail_salesOrder_SalesOrder').down('app_view_example_masterDetail_salesOrder_salesItem_SalesItemGrid');
            var detailStore = detailGrid.getStore();

            var orderId = selected.get("orderId");      
            var expr = Ext.util.Base64.encode("ORDER_ID='"+orderId+"'");
            expr = '?filterExpr=' + expr;

            if (detailStore.getProxy().DefaultUrl == null) {
                detailStore.getProxy().DefaultUrl = detailStore.getProxy().url;
            }
            detailStore.getProxy().url =detailStore.getProxy().DefaultUrl + expr;
            detailStore.load();
        },
    }
});