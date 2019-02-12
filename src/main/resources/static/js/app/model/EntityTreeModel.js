Ext.define('app.model.EntityTreeModel', {
    extend: 'Ext.data.TreeModel',    
    uses:["app.model.EmptyGenerator"],
    identifier:'empty',
    fields: [
        { name: "recordId", dbFieldName: 'record_id', type: "string", unique: true },
    ],
    idProperty: 'recordId'
});