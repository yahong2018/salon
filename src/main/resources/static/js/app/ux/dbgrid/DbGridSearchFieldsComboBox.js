Ext.define('app.ux.dbgrid.DbGridSearchFieldsComboBox', {
    extend: 'Ext.form.ComboBox',
    xtype: 'dbgrid_DbGridSearchFieldsComboBox',
    constructor: function (config) {
        var store = Ext.create('Ext.data.Store', {
            data: config.dbGrid.columns
        });

        var configBase = {
            store: store,
            queryMode: 'local',
            valueField: 'dataIndex',
            displayField: 'text'
        }

        Ext.applyIf(config,configBase);

        this.callParent(arguments);
    }
});