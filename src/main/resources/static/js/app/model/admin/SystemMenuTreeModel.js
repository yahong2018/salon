Ext.define('app.model.admin.SystemMenuTreeModel', {
    extend: 'Ext.data.TreeModel',
    fields: [
        { name: 'text', mapping: 'programName' },
        {
            name: 'glyph', convert: function (value) {
                return parseInt(value);
            }
        },
    ]
});