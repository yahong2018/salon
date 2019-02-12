Ext.define("app.model.example.SalesOrderItemModel", {
    extend: "app.model.TrackableModel",
    requires: ["app.ux.ZhxhDate", "app.model.EmptyGenerator"],
    identifier: 'empty',

    fields: [
        { name: "recordId", type: "string", unique: true },
        { name: "orderId", type: "string" },
        { name: "productId", type: "string" },
        { name: "productName", type: "string" },
        { name: "qty", type: "int" },
        { name: "deliveryDate", type: 'zhxhDate', dateFormat: 'Y-m-d' },
    ],
    idProperty: "recordId"
});