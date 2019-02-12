Ext.define('app.model.EntityModel', {
    extend: 'Ext.data.Model',    
    uses:["app.model.EmptyGenerator"],
    identifier:'empty',
    fields: [
        { name: "recordId", dbFieldName: 'record_id', type: "string", unique: true },
    ],
    idProperty: 'recordId'
});