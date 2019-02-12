Ext.define('app.model.TrackableModel', {
    extend: 'Ext.data.Model',
    requires: ["app.ux.ZhxhDate"],
    uses:["app.model.EmptyGenerator"],
    fields: [
        { name: "createBy", dbFieldName: 'CREATE_BY', type: "string" },
        { name: "createDate", dbFieldName: 'CREATE_DATE', type: 'zhxhDate', dateFormat: 'Y-m-d H:i:s' },
        { name: "updateBy", dbFieldName: 'UPDATE_BY', type: "string" },
        { name: "updateDate", dbFieldName: 'UPDATE_DATE', type: 'zhxhDate', dateFormat: 'Y-m-d H:i:s' },
        { name: "optLock", dbFieldName: 'OPTLOCK', type: "int" },
        // { name: "delFlag", dbFieldName: 'DEL_FLAG', type: "int" },
    ],
});