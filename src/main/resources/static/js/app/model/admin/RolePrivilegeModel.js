Ext.define('app.model.admin.RolePrivilegeModel',{
    extend:'Ext.data.Model',
    requires: ["app.ux.ZhxhDate","app.model.EmptyGenerator"],
    fields:[
        { name: "recordId", dbFieldName: 'record_id', type: "string"},
        { name: "programPrivilegeId", dbFieldName: 'program_privilege_id', type: "string" },
        { name: "roleId", dbFieldName: 'role_id', type: "string" },
        { name: "programId", dbFieldName: 'program_id', type: "string" },
        { name: "privilegeCode", dbFieldName: 'privilege_Code', type: "string" }
    ],
    idProperty:'recordId'
});