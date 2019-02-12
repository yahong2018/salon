Ext.define("app.view.example.masterDetail.salesOrder.salesItem.SalesItemDetailForm", {
    extend: "app.ux.dbgrid.ItemDetailForm",
    alias: "widget.app_view_example_masterDetail_salesOrder_salesItem_SalesItemDetailForm",
    requires:["app.ux.filterButton.FilterButton",],    

    layout: "anchor",

    defaults: {
        anchor: "100%",
    },
    bodyPadding: 15,
    width: 400,
    items: [
        {
            xtype: "textfield",
            name: "orderId",
            fieldLabel: "订单编号",
            allowBlank: false,
            readOnly: true,
        },
        {
            xtype: "fieldcontainer",
            layout: "hbox",
            fieldLabel: "产品编号",
            defaults: {                
                hideLabel: true
            },
            items: [
                {
                    xtype: "textfield",
                    name: "productId",
                    flex: 1,
                    allowBlank: false,
                },
                {
                    xtype: "filterButton",                    
                    winHeight: 500,
                    winWidth: 600,                  
                    columns: [
                        {
                            text: '用户编号',
                            dataIndex: 'userId',
                            width: 300,
                        },
                        {
                            text: '用户名称',
                            dataIndex: 'userName',
                            flex: 1,
                        }
                    ],
                    store:Ext.create({xtype:"app_store_admin_SystemUserStore"}),
                    callback:function(grid,records){
                        debugger;
                        alert(records.length);
                    }
                }
            ]
        }, {
            xtype: "textfield",
            name: "productName",
            fieldLabel: "产品名称",
            allowBlank: false,
        }, {
            xtype: "textfield",
            name: "qty",
            fieldLabel: "订单数量",
            allowBlank: false,
        }, {
            xtype: "datefield",
            name: "deliveryDate",
            fieldLabel: "交货日期",
            allowBlank: false,
            format: "Y-m-d",
        },{
            xtype:"hidden",
            name:"createBy"
        },{
            xtype:"hidden",
            name:"createDate"
        },{
            xtype:"hidden",
            name:"updateBy"
        },{
            xtype:"hidden",
            name:"updateDate"
        },{
            xtype:"hidden",
            name:"optLock"
        },{
            xtype:"hidden",
            name:"delFlag"
        }
    ],
    getMasterFieldName: function () {
        return {
            detailField: "orderId",
            masterField: "orderId"
        };
    }
});