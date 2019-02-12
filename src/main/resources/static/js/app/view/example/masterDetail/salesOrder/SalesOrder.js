Ext.define("app.view.example.masterDetail.salesOrder.SalesOrder", {
    extend: "Ext.panel.Panel",
    xtype: "app_view_example_masterDetail_salesOrder_SalesOrder",
    uses: ["app.view.example.masterDetail.salesOrder.SalesOrderGrid", "app.view.example.masterDetail.salesOrder.salesItem.SalesItemGrid"],

    layout: 'fit',
    items: [
        {
            xtype: 'panel',
            frame: false,
            layout: 'border',
            items: [
                {
                    region: 'north',
                    xtype: 'app_view_example_masterDetail_salesOrder_SalesOrderGrid',
                    height: 400,
                }, {
                    region: 'center',
                    xtype: 'app_view_example_masterDetail_salesOrder_salesItem_SalesItemGrid',
                    frame:true,
                    border:false,
                    getMasterGrid:function(){
                        return this.up('app_view_example_masterDetail_salesOrder_SalesOrder')
                                   .down('app_view_example_masterDetail_salesOrder_SalesOrderGrid');
                    } 
                }
            ]
        }
    ]
});