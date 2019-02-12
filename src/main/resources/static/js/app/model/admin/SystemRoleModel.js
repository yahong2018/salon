Ext.define('app.model.admin.SystemRoleModel',{
    extend: 'Ext.data.Model',
    requires: ["app.ux.ZhxhDate","app.model.EmptyGenerator"],
    identifier:'empty',
    fields: [
        { name: "recordId", dbFieldName: 'record_id', type: "string", unique: true },
        { name: "roleCode", dbFieldName: 'role_code', type: "string", unique: true },
        { name: "roleName", dbFieldName: 'role_name', type: "string" }
    ],
    idProperty: 'recordId'        
});