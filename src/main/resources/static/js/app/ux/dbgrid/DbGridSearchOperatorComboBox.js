Ext.define('app.ux.dbgrid.DbGridSearchOperatorComboBox', {
    extend: 'Ext.form.ComboBox',
    xtype: 'dbgrid_DbGridSearchOperatorComboBox',

    constructor: function (config) {
        var configBase = {            
            store: {
                fields: ['abbr', 'name'],
                data: [
                    { "abbr": "=", "name": "等于 (=)" },
                    { "abbr": ">", "name": "大于 (>)" },
                    { "abbr": "<", "name": "小于 (<)" },
                    { "abbr": "<>", "name": "不等于 (<>)" },
                    { "abbr": ">=", "name": "大于等于 (>=)" },
                    { "abbr": "<=", "name": "小于等于 (<=)" },
                    { "abbr": "like", "name": "包含 (like)" }
                ]
            },
            queryMode: 'local',
            valueField: 'abbr',
            displayField: 'name',
        };

        Ext.applyIf(config, configBase);

        this.callParent(arguments);
    }
});