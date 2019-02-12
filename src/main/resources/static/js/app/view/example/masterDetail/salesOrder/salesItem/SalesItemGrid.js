Ext.define("app.view.example.masterDetail.salesOrder.salesItem.SalesItemGrid", {
    extend: "app.ux.dbgrid.DbGrid",
    xtype: "app_view_example_masterDetail_salesOrder_salesItem_SalesItemGrid",
    uses: ["app.store.example.SalesOrderItemStore", "app.model.example.SalesOrderItemModel", 
    "app.store.admin.SystemUserStore",
    "app.view.example.masterDetail.salesOrder.salesItem.SalesItemDetailForm",],

    title: "订单条目",
    width: 810,
    hideDefeaultPagebar: true,
    dockedItems: [
        {
            xtype: "dbgridtoolbar",
            hideSearch: true,
        }
    ],
    columns: [
        {
            text: "产品编号",
            dataIndex: "productId",
            width: 150
        }, {
            text: "产品名称",
            dataIndex: "productName",
            width: 300
        }, {
            text: "订单数量",
            dataIndex: "qty",
            width: 100
        }, {
            text: "订单交期",
            dataIndex: "deliveryDate",
            width: 150
        }
    ],
    constructor: function (config) {
        var configBase = {
            frame: false,
            border: true,
            store: Ext.create({ xtype: 'app_store_example_SalesOrderItemStore', autoLoad: false }),
            detailFormClass: 'app_view_example_masterDetail_salesOrder_salesItem_SalesItemDetailForm',
            detailWindowTitle: '订单条目',
        }
        Ext.applyIf(config, configBase);

        this.callParent(arguments);
    },
});