Ext.define('app.model.admin.UserRoleModel',{
    extend:'Ext.data.Model',
    requires: ["app.ux.ZhxhDate","app.model.EmptyGenerator"],
    identifier:'empty',
    fields: [
        { name: "recordId", dbFieldName: 'record_id', type: "int", unique: true },
        { name: "roleId", dbFieldName: 'role_id', type: "string" },
        { name: "userId", dbFieldName: 'user_id', type: "string" },
    ],
    idProperty: 'recordId' 
});