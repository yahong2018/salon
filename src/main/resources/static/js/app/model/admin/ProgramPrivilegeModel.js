Ext.define('app.model.admin.ProgramPrivilegeModel', {
    extend: 'Ext.data.TreeModel',
    fields: [
        { name: 'text', mapping: 'privilegeName' },
        { name: 'leaf', defaultValue: true }
    ]
});

