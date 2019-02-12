Ext.define("app.model.example.SalesOrderModel", {
    extend: "app.model.TrackableModel",
    requires: ["app.ux.ZhxhDate", "app.model.EmptyGenerator"],

    identifier: 'empty',
    fields: [
        { name: "orderId", type: "string", unique: true },
        { name: "customerId", type: "string" },
        { name: "customerName", type: "string" },
        { name: "salesUser", type: "string" },
        { name: "orderDate", type: 'zhxhDate', dateFormat: 'Y-m-d' },
    ],
    idProperty: "orderId"
});