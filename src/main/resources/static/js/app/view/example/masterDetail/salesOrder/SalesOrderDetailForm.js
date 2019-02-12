Ext.define("app.view.example.masterDetail.salesOrder.SalesOrderDetailForm", {
    extend: "app.ux.dbgrid.MasterForm",    
    xtype: "app_view_example_masterDetail_salesOrder_SalesOrderDetailForm",

    requires:["app.ux.TrackableFormPanel"],
    bodyPadding: 15,
    items: [
        {
            xtype: "panel",
            layout: {
                type: 'vbox',
                pack: 'start',
                align: 'stretch'
            },
            defaults: {
                margin: '10 0 0 0',
            },
            items: [
                {
                    xtype: "textfield",
                    fieldLabel: "订单编号",
                    name: "orderId",
                    allowBlank: false,
                    flex: 1
                }, {
                    xtype: "container",
                    layout: "hbox",
                    items: [
                        {
                            xtype: "textfield",
                            fieldLabel: "客户编号",
                            name: "customerId",
                            allowBlank: false,
                        }, {
                            xtype: "textfield",
                            fieldLabel: "客户名称",
                            name: "customerName",
                            margin: '0 0 0 10',
                        }
                    ]
                }, {
                    xtype: "container",
                    layout: "hbox",
                    items: [
                        {
                            xtype: "textfield",
                            fieldLabel: "销售员",
                            name: "salesUser",
                            allowBlank: false,
                        }, {
                            name: "orderDate",
                            xtype: "datefield",
                            fieldLabel: "下单日期",
                            margin: '0 0 0 10',
                            format: 'Y-m-d',
                            allowBlank: false,
                        }
                    ]
                }, 
                {
                    xtype:"app_ux_TrackableFormPanel"
                }

                /*{
                    xtype: "textarea",
                    name: "remark",
                    fieldLabel: "备注",
                    flex: 1
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
                */
            ]
        }, {
            xtype: "app_view_example_masterDetail_salesOrder_salesItem_SalesItemGrid",
            padding: '10 0 0 0',
            height: 300,
        }
    ],
    getDetailPanel: function () {
        return this.down("app_view_example_masterDetail_salesOrder_salesItem_SalesItemGrid");
    },

    getDetailsConfigList: function () {
        var store = this.down('app_view_example_masterDetail_salesOrder_salesItem_SalesItemGrid').store;
        return [
            {
                name: "orderItem",
                store: store,
                filter: "ORDER_ID='{orderId}'"
            }
        ];
    }

});