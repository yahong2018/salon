Ext.define('app.model.admin.SystemUserModel', {
    extend: 'app.model.EntityModel',
    requires: ["app.ux.ZhxhDate"],  
    fields: [                
        { name: "userCode", dbFieldName: 'user_code', type: "string", unique: true },
        { name: "userName", dbFieldName: 'user_name', type: "string" },
        { name: "password", dbFieldName: 'pwd', type: "string" },
        { name: "userStatus", dbFieldName: 'user_status', type: "string" },
        { name: "email", dbFieldName: 'email', type: "string" },        
        { name: "online", dbFieldName: 'online', type: "boolean" },        
        { name: "lastLoginTime", dbFieldName: 'last_login_time', type: 'zhxhDate',dateFormat: 'Y-m-d H:i:s'},
    ]
});